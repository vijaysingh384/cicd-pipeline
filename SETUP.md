# Setup Guide for CI/CD Pipeline Project

## Prerequisites

Before you begin, ensure you have the following installed:

- ✅ Java 17 or higher ([Download](https://adoptium.net/))
- ✅ Maven 3.6+ ([Download](https://maven.apache.org/download.cgi))
- ✅ Docker ([Download](https://www.docker.com/products/docker-desktop))
- ✅ Git ([Download](https://git-scm.com/downloads))
- ✅ Docker Hub account ([Sign up](https://hub.docker.com/signup))
- ✅ GitHub account ([Sign up](https://github.com/signup))

## Step-by-Step Setup

### 1. Verify Prerequisites

```bash
# Check Java version
java -version
# Should show: openjdk version "17.x.x" or higher

# Check Maven version
mvn -version
# Should show: Apache Maven 3.6.x or higher

# Check Docker version
docker --version
# Should show: Docker version 20.x.x or higher

# Check Git version
git --version
# Should show: git version 2.x.x or higher
```

### 2. Create GitHub Repository

1. Go to [GitHub](https://github.com) and log in
2. Click the **+** icon → **New repository**
3. Repository name: `cicd-pipeline-demo`
4. Description: `Java Spring Boot CI/CD Pipeline with Docker and GitHub Actions`
5. Choose **Public** or **Private**
6. **Do NOT** initialize with README (we already have one)
7. Click **Create repository**

### 3. Initialize Local Repository

```bash
# Navigate to your project directory
cd /path/to/cicd-pipeline-demo

# Initialize Git repository
git init

# Add all files
git add .

# Create initial commit
git commit -m "Initial commit: CI/CD pipeline setup"

# Add remote repository (replace YOUR_USERNAME with your GitHub username)
git remote add origin https://github.com/YOUR_USERNAME/cicd-pipeline-demo.git

# Push to GitHub
git branch -M main
git push -u origin main
```

### 4. Configure Docker Hub

1. Log in to [Docker Hub](https://hub.docker.com)
2. Click **Create Repository**
3. Repository name: `cicd-pipeline-demo`
4. Visibility: **Public**
5. Click **Create**

### 5. Configure GitHub Secrets

1. Go to your GitHub repository
2. Click **Settings** → **Secrets and variables** → **Actions**
3. Click **New repository secret**
4. Add the following secrets:

**Secret 1: DOCKER_USERNAME**
- Name: `DOCKER_USERNAME`
- Value: Your Docker Hub username
- Click **Add secret**

**Secret 2: DOCKER_PASSWORD**
- Name: `DOCKER_PASSWORD`
- Value: Your Docker Hub password or access token
- Click **Add secret**

**To create a Docker Hub access token (recommended):**
1. Go to Docker Hub → Account Settings → Security
2. Click **New Access Token**
3. Description: `GitHub Actions CI/CD`
4. Access permissions: **Read, Write, Delete**
5. Click **Generate**
6. Copy the token and use it as `DOCKER_PASSWORD`

### 6. Test Locally

#### Test with Maven

```bash
# Build the application
mvn clean install

# Run the application
mvn spring-boot:run

# In another terminal, test the endpoints
curl http://localhost:8080/
# Expected: CI/CD Pipeline Working

curl http://localhost:8080/health
# Expected: JSON with status "UP"

# Stop the application (Ctrl+C)
```

#### Test with Docker

```bash
# Build Docker image
docker build -t cicd-pipeline-demo .

# Run container
docker run -p 8080:8080 cicd-pipeline-demo

# In another terminal, test the endpoints
curl http://localhost:8080/
# Expected: CI/CD Pipeline Working

# Stop the container
docker ps  # Get container ID
docker stop <container-id>
```

### 7. Trigger CI/CD Pipeline

```bash
# Make a small change (e.g., update README.md)
echo "\n## Pipeline Status" >> README.md

# Commit and push
git add .
git commit -m "Trigger CI/CD pipeline"
git push origin main
```

### 8. Monitor Pipeline Execution

1. Go to your GitHub repository
2. Click **Actions** tab
3. You should see the workflow running
4. Click on the workflow run to see details
5. Watch each step execute:
   - ✅ Checkout code
   - ✅ Set up JDK 17
   - ✅ Build with Maven
   - ✅ Run tests
   - ✅ Log in to Docker Hub
   - ✅ Build and push Docker image

### 9. Verify Docker Hub

1. Go to [Docker Hub](https://hub.docker.com)
2. Navigate to your repository: `your-username/cicd-pipeline-demo`
3. You should see the image with tags:
   - `latest`
   - `main-<commit-sha>`

### 10. Deploy to Cloud VM (Optional)

#### Using AWS EC2, Google Cloud, or Azure VM:

```bash
# SSH into your VM
ssh user@your-vm-ip

# Install Docker (if not already installed)
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# Pull your image from Docker Hub
docker pull your-dockerhub-username/cicd-pipeline-demo:latest

# Run the container
docker run -d -p 8080:8080 --name cicd-app \
  your-dockerhub-username/cicd-pipeline-demo:latest

# Check if it's running
docker ps

# Test the application
curl http://localhost:8080/

# View logs
docker logs cicd-app
```

## Troubleshooting

### Issue 1: Maven build fails with "JAVA_HOME not set"

**Solution:**
```bash
# On macOS/Linux
export JAVA_HOME=$(/usr/libexec/java_home -v 17)

# On Windows
set JAVA_HOME=C:\Program Files\Java\jdk-17

# Verify
echo $JAVA_HOME
```

### Issue 2: Docker build fails with "permission denied"

**Solution:**
```bash
# On macOS/Linux
sudo usermod -aG docker $USER
newgrp docker

# Restart Docker Desktop on Windows/macOS
```

### Issue 3: GitHub Actions fails with "Docker login failed"

**Solution:**
- Verify `DOCKER_USERNAME` and `DOCKER_PASSWORD` secrets are correct
- Use Docker Hub access token instead of password
- Check Docker Hub account is active

### Issue 4: Port 8080 already in use

**Solution:**
```bash
# Find process using port 8080
lsof -i :8080  # macOS/Linux
netstat -ano | findstr :8080  # Windows

# Kill the process or use a different port
docker run -p 8081:8080 cicd-pipeline-demo
```

### Issue 5: Tests fail during Maven build

**Solution:**
```bash
# Run tests with verbose output
mvn test -X

# Skip tests temporarily (not recommended for production)
mvn clean install -DskipTests
```

## Next Steps

After successful setup:

1. ✅ Add more endpoints to your Spring Boot application
2. ✅ Write additional unit and integration tests
3. ✅ Add code coverage reports (JaCoCo)
4. ✅ Implement database integration (PostgreSQL/MySQL)
5. ✅ Add monitoring with Prometheus and Grafana
6. ✅ Set up staging and production environments
7. ✅ Implement blue-green deployment
8. ✅ Add security scanning (Snyk, Trivy)

## Useful Commands

```bash
# Maven commands
mvn clean                    # Clean build artifacts
mvn compile                  # Compile source code
mvn test                     # Run tests
mvn package                  # Create JAR file
mvn spring-boot:run          # Run application

# Docker commands
docker build -t app .        # Build image
docker run -p 8080:8080 app  # Run container
docker ps                    # List running containers
docker logs <container-id>   # View logs
docker stop <container-id>   # Stop container
docker rm <container-id>     # Remove container
docker images                # List images
docker rmi <image-id>        # Remove image

# Git commands
git status                   # Check status
git add .                    # Stage all changes
git commit -m "message"      # Commit changes
git push origin main         # Push to remote
git pull origin main         # Pull from remote
git log --oneline            # View commit history
```

## Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Docker Documentation](https://docs.docker.com/)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Docker Hub Documentation](https://docs.docker.com/docker-hub/)

## Support

If you encounter any issues:

1. Check the troubleshooting section above
2. Review GitHub Actions logs for error messages
3. Check Docker container logs: `docker logs <container-id>`
4. Verify all prerequisites are installed correctly
5. Ensure all secrets are configured properly

---

**Happy Coding! 🚀**
