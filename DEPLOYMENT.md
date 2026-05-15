# Deployment Guide

## Deployment Options

This guide covers multiple deployment strategies for your CI/CD pipeline application.

## Option 1: Docker Hub + Cloud VM

### Prerequisites
- Cloud VM (AWS EC2, Google Cloud, Azure, DigitalOcean)
- SSH access to the VM
- Docker installed on VM

### Steps

1. **SSH into your VM:**
```bash
ssh user@your-vm-ip
```

2. **Install Docker (if not installed):**
```bash
# Update package manager
sudo apt-get update

# Install Docker
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# Add user to docker group
sudo usermod -aG docker $USER
newgrp docker

# Verify installation
docker --version
```

3. **Pull and run your application:**
```bash
# Pull the latest image
docker pull your-dockerhub-username/cicd-pipeline-demo:latest

# Run the container
docker run -d \
  --name cicd-app \
  -p 8080:8080 \
  --restart unless-stopped \
  your-dockerhub-username/cicd-pipeline-demo:latest

# Verify it's running
docker ps

# Check logs
docker logs cicd-app

# Test the application
curl http://localhost:8080/
```

4. **Configure firewall:**
```bash
# Allow port 8080
sudo ufw allow 8080/tcp
sudo ufw enable
```

5. **Access from browser:**
```
http://your-vm-ip:8080/
```

## Option 2: Docker Compose Deployment

### Create docker-compose.yml on VM

```bash
# Create directory
mkdir -p ~/cicd-app
cd ~/cicd-app

# Create docker-compose.yml
cat > docker-compose.yml << 'EOF'
version: '3.8'

services:
  app:
    image: your-dockerhub-username/cicd-pipeline-demo:latest
    container_name: cicd-app
    ports:
      - "8080:8080"
    restart: unless-stopped
    healthcheck:
      test: ["CMD", "wget", "--no-verbose", "--tries=1", "--spider", "http://localhost:8080/health"]
      interval: 30s
      timeout: 3s
      retries: 3
      start_period: 40s
    environment:
      - SPRING_PROFILES_ACTIVE=prod
EOF
```

### Deploy with Docker Compose

```bash
# Start the application
docker-compose up -d

# View logs
docker-compose logs -f

# Stop the application
docker-compose down

# Update to latest version
docker-compose pull
docker-compose up -d
```

## Option 3: AWS EC2 with Auto-Deployment

### Setup EC2 Instance

1. **Launch EC2 instance:**
   - AMI: Ubuntu 22.04 LTS
   - Instance type: t2.micro (free tier)
   - Security group: Allow ports 22 (SSH) and 8080 (HTTP)

2. **Connect to EC2:**
```bash
ssh -i your-key.pem ubuntu@ec2-instance-ip
```

3. **Install Docker:**
```bash
sudo apt-get update
sudo apt-get install -y docker.io docker-compose
sudo systemctl start docker
sudo systemctl enable docker
sudo usermod -aG docker ubuntu
```

4. **Create deployment script:**
```bash
cat > ~/deploy.sh << 'EOF'
#!/bin/bash

# Pull latest image
docker pull your-dockerhub-username/cicd-pipeline-demo:latest

# Stop and remove old container
docker stop cicd-app || true
docker rm cicd-app || true

# Run new container
docker run -d \
  --name cicd-app \
  -p 8080:8080 \
  --restart unless-stopped \
  your-dockerhub-username/cicd-pipeline-demo:latest

echo "Deployment completed successfully!"
EOF

chmod +x ~/deploy.sh
```

5. **Run deployment:**
```bash
./deploy.sh
```

## Option 4: Kubernetes Deployment

### Prerequisites
- Kubernetes cluster (minikube, EKS, GKE, AKS)
- kubectl installed

### Create Kubernetes manifests

**deployment.yaml:**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: cicd-app
  labels:
    app: cicd-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: cicd-app
  template:
    metadata:
      labels:
        app: cicd-app
    spec:
      containers:
      - name: cicd-app
        image: your-dockerhub-username/cicd-pipeline-demo:latest
        ports:
        - containerPort: 8080
        livenessProbe:
          httpGet:
            path: /health
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 10
        readinessProbe:
          httpGet:
            path: /health
            port: 8080
          initialDelaySeconds: 10
          periodSeconds: 5
---
apiVersion: v1
kind: Service
metadata:
  name: cicd-app-service
spec:
  type: LoadBalancer
  selector:
    app: cicd-app
  ports:
  - protocol: TCP
    port: 80
    targetPort: 8080
```

### Deploy to Kubernetes

```bash
# Apply deployment
kubectl apply -f deployment.yaml

# Check deployment status
kubectl get deployments
kubectl get pods
kubectl get services

# View logs
kubectl logs -l app=cicd-app

# Scale deployment
kubectl scale deployment cicd-app --replicas=5

# Update to new version
kubectl set image deployment/cicd-app cicd-app=your-dockerhub-username/cicd-pipeline-demo:latest
```

## Option 5: Heroku Deployment

### Prerequisites
- Heroku account
- Heroku CLI installed

### Steps

1. **Login to Heroku:**
```bash
heroku login
heroku container:login
```

2. **Create Heroku app:**
```bash
heroku create cicd-pipeline-demo
```

3. **Push Docker image:**
```bash
# Tag image for Heroku
docker tag cicd-pipeline-demo registry.heroku.com/cicd-pipeline-demo/web

# Push to Heroku
docker push registry.heroku.com/cicd-pipeline-demo/web

# Release the app
heroku container:release web -a cicd-pipeline-demo
```

4. **Open application:**
```bash
heroku open -a cicd-pipeline-demo
```

## Monitoring and Maintenance

### Health Checks

```bash
# Check application health
curl http://your-server:8080/health

# Expected response:
# {
#   "status": "UP",
#   "message": "Application is running successfully",
#   "timestamp": "2026-05-15T10:30:00",
#   "version": "1.0.0"
# }
```

### View Logs

```bash
# Docker logs
docker logs -f cicd-app

# Docker Compose logs
docker-compose logs -f

# Kubernetes logs
kubectl logs -f -l app=cicd-app
```

### Update Application

```bash
# Pull latest image
docker pull your-dockerhub-username/cicd-pipeline-demo:latest

# Restart container
docker restart cicd-app

# Or use deployment script
./deploy.sh
```

### Rollback

```bash
# Docker - use specific tag
docker pull your-dockerhub-username/cicd-pipeline-demo:main-abc123
docker stop cicd-app
docker rm cicd-app
docker run -d --name cicd-app -p 8080:8080 \
  your-dockerhub-username/cicd-pipeline-demo:main-abc123

# Kubernetes
kubectl rollout undo deployment/cicd-app
```

## Production Best Practices

### 1. Use Environment Variables

```bash
docker run -d \
  --name cicd-app \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e SERVER_PORT=8080 \
  your-dockerhub-username/cicd-pipeline-demo:latest
```

### 2. Set Resource Limits

```bash
docker run -d \
  --name cicd-app \
  -p 8080:8080 \
  --memory="512m" \
  --cpus="1.0" \
  your-dockerhub-username/cicd-pipeline-demo:latest
```

### 3. Use Reverse Proxy (Nginx)

```nginx
server {
    listen 80;
    server_name your-domain.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
```

### 4. Enable HTTPS with Let's Encrypt

```bash
# Install certbot
sudo apt-get install certbot python3-certbot-nginx

# Get certificate
sudo certbot --nginx -d your-domain.com

# Auto-renewal
sudo certbot renew --dry-run
```

### 5. Set Up Monitoring

```bash
# Install monitoring tools
docker run -d \
  --name prometheus \
  -p 9090:9090 \
  prom/prometheus

docker run -d \
  --name grafana \
  -p 3000:3000 \
  grafana/grafana
```

## Troubleshooting

### Container won't start

```bash
# Check logs
docker logs cicd-app

# Check container status
docker ps -a

# Inspect container
docker inspect cicd-app
```

### Port already in use

```bash
# Find process using port
sudo lsof -i :8080

# Kill process
sudo kill -9 <PID>

# Or use different port
docker run -d -p 8081:8080 cicd-app
```

### Out of memory

```bash
# Check container stats
docker stats cicd-app

# Increase memory limit
docker update --memory="1g" cicd-app
```

---

**Deployment completed! Your application is now live! 🚀**
