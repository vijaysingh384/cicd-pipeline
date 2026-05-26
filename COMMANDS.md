# Command Reference - Copy & Paste Guide

This is a quick reference of all commands you'll need. Just copy and paste!

## 🔍 Step 1: Verify Prerequisites

```bash
# Check all prerequisites at once
java -version && mvn -version && docker --version && git --version
```

Expected output should show versions for all tools.

---

## 🏃 Step 2: Run the Application Locally

### Option A: Maven (Recommended for development)

```bash
# Build and run in one command
mvn clean spring-boot:run
```

### Option B: Build JAR and run

```bash
# Build
mvn clean package

# Run the JAR
java -jar target/cicd-app.jar
```

### Option C: Docker

```bash
# Build image
docker build -t cicd-pipeline-demo .

# Run container
docker run -p 8080:8080 --name cicd-app cicd-pipeline-demo
```

### Test the application

```bash
# Test all endpoints
curl http://localhost:8080/
curl http://localhost:8080/health
curl http://localhost:8080/api/info
```

---

## 📦 Step 3: Initialize Git Repository

```bash
# Initialize Git
git init

# Add all files
git add .

# Create first commit
git commit -m "Initial commit: CI/CD pipeline project"

# Check status
git status
```

---

## 🌐 Step 4: Push to GitHub

**Replace `YOUR_USERNAME` with your actual GitHub username!**

```bash
# Add remote repository
git remote add origin https://github.com/YOUR_USERNAME/cicd-pipeline-demo.git

# Rename branch to main
git branch -M main

# Push to GitHub
git push -u origin main
```

### If you get authentication error:

```bash
# Use personal access token
# Go to GitHub → Settings → Developer settings → Personal access tokens
# Generate new token with 'repo' scope
# Use token as password when prompted
```

---

## 🔐 Step 5: Configure GitHub Secrets

**Do this in GitHub web interface:**

1. Go to: `https://github.com/YOUR_USERNAME/cicd-pipeline-demo/settings/secrets/actions`
2. Click "New repository secret"
3. Add these two secrets:
   - Name: `DOCKER_USERNAME`, Value: your Docker Hub username
   - Name: `DOCKER_PASSWORD`, Value: your Docker Hub password/token

---

## 🚀 Step 6: Trigger CI/CD Pipeline

### Method 1: Make a change

```bash
# Add a line to README
echo "\n## CI/CD Status: Active ✅" >> README.md

# Commit and push
git add README.md
git commit -m "Trigger CI/CD pipeline"
git push origin main
```

### Method 2: Empty commit

```bash
# Create empty commit to trigger pipeline
git commit --allow-empty -m "Trigger CI/CD pipeline"
git push origin main
```

---

## 📊 Step 7: Monitor Pipeline

**Do this in GitHub web interface:**

1. Go to: `https://github.com/YOUR_USERNAME/cicd-pipeline-demo/actions`
2. Click on the latest workflow run
3. Watch the steps execute

---

## 🐳 Step 8: Pull and Run from Docker Hub

**Replace `YOUR_USERNAME` with your Docker Hub username!**

```bash
# Pull the image
docker pull YOUR_USERNAME/cicd-pipeline-demo:latest

# Run the container
docker run -d -p 8080:8080 --name cicd-app \
  --restart unless-stopped \
  YOUR_USERNAME/cicd-pipeline-demo:latest

# Check if running
docker ps

# View logs
docker logs cicd-app

# Test the application
curl http://localhost:8080/
```

---

## ☁️ Step 9: Deploy to Cloud VM (Optional)

### Connect to your VM

```bash
# SSH into VM (replace with your details)
ssh user@your-vm-ip
```

### Install Docker on VM

```bash
# Install Docker
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# Add user to docker group
sudo usermod -aG docker $USER
newgrp docker

# Verify installation
docker --version
```

### Deploy application on VM

```bash
# Pull your image (replace YOUR_USERNAME)
docker pull YOUR_USERNAME/cicd-pipeline-demo:latest

# Run container
docker run -d -p 8080:8080 --name cicd-app \
  --restart unless-stopped \
  YOUR_USERNAME/cicd-pipeline-demo:latest

# Configure firewall
sudo ufw allow 8080/tcp
sudo ufw enable

# Check status
docker ps
curl http://localhost:8080/
```

### Access from browser

```
http://your-vm-ip:8080/
```

---

## 🧪 Testing Commands

### Run tests

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=HealthControllerTests

# Run tests with coverage
mvn clean test jacoco:report

# Skip tests
mvn clean install -DskipTests
```

### Test Docker image

```bash
# Build
docker build -t test-app .

# Run
docker run -p 8080:8080 test-app

# Test in another terminal
curl http://localhost:8080/health

# Stop
docker stop $(docker ps -q --filter ancestor=test-app)
```

---

## 🔄 Docker Management Commands

### Container management

```bash
# List running containers
docker ps

# List all containers
docker ps -a

# Stop container
docker stop cicd-app

# Start container
docker start cicd-app

# Restart container
docker restart cicd-app

# Remove container
docker rm cicd-app

# Remove container (force)
docker rm -f cicd-app

# View logs
docker logs cicd-app

# Follow logs
docker logs -f cicd-app

# View last 100 lines
docker logs --tail 100 cicd-app
```

### Image management

```bash
# List images
docker images

# Remove image
docker rmi cicd-pipeline-demo

# Remove unused images
docker image prune

# Remove all unused images
docker image prune -a

# Pull latest image
docker pull YOUR_USERNAME/cicd-pipeline-demo:latest

# Tag image
docker tag cicd-pipeline-demo YOUR_USERNAME/cicd-pipeline-demo:v1.0
```

### System cleanup

```bash
# Remove all stopped containers
docker container prune

# Remove all unused images
docker image prune -a

# Remove all unused volumes
docker volume prune

# Remove everything unused
docker system prune -a
```

---

## 📝 Git Commands

### Basic workflow

```bash
# Check status
git status

# Add files
git add .
git add filename.txt

# Commit
git commit -m "Your message"

# Push
git push origin main

# Pull latest changes
git pull origin main
```

### View history

```bash
# View commit history
git log

# View compact history
git log --oneline

# View last 5 commits
git log -5

# View changes
git diff
```

### Branch management

```bash
# Create new branch
git checkout -b feature-branch

# Switch branch
git checkout main

# List branches
git branch

# Delete branch
git branch -d feature-branch
```

### Undo changes

```bash
# Discard changes in file
git checkout -- filename.txt

# Unstage file
git reset HEAD filename.txt

# Undo last commit (keep changes)
git reset --soft HEAD~1

# Undo last commit (discard changes)
git reset --hard HEAD~1
```

---

## 🔧 Maven Commands

### Build commands

```bash
# Clean build artifacts
mvn clean

# Compile
mvn compile

# Package (create JAR)
mvn package

# Install to local repository
mvn install

# Clean and install
mvn clean install

# Skip tests
mvn clean install -DskipTests
```

### Run commands

```bash
# Run application
mvn spring-boot:run

# Run with profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Run with arguments
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

### Dependency commands

```bash
# Download dependencies
mvn dependency:resolve

# Show dependency tree
mvn dependency:tree

# Update dependencies
mvn versions:use-latest-versions
```

---

## 🐳 Docker Compose Commands

### Basic commands

```bash
# Start services
docker-compose up

# Start in background
docker-compose up -d

# Stop services
docker-compose down

# View logs
docker-compose logs

# Follow logs
docker-compose logs -f

# Restart services
docker-compose restart

# Pull latest images
docker-compose pull

# Rebuild images
docker-compose build

# Rebuild and start
docker-compose up --build
```

---

## 🔍 Debugging Commands

### Check application

```bash
# Check if port is in use
lsof -i :8080  # macOS/Linux
netstat -ano | findstr :8080  # Windows

# Kill process on port
kill -9 $(lsof -t -i:8080)  # macOS/Linux

# Check Java processes
jps

# Check Docker processes
docker ps
docker stats
```

### View logs

```bash
# Application logs (Maven)
mvn spring-boot:run

# Docker logs
docker logs cicd-app
docker logs -f cicd-app --tail 100

# System logs (Linux)
journalctl -u docker
```

### Test connectivity

```bash
# Test endpoint
curl http://localhost:8080/

# Test with headers
curl -i http://localhost:8080/health

# Test with verbose output
curl -v http://localhost:8080/

# Test from inside container
docker exec cicd-app curl http://localhost:8080/
```

---

## 📊 Monitoring Commands

### Docker stats

```bash
# View resource usage
docker stats

# View specific container
docker stats cicd-app

# View once (no stream)
docker stats --no-stream
```

### Application health

```bash
# Health check
curl http://localhost:8080/health

# Pretty print JSON
curl http://localhost:8080/health | json_pp

# Check response time
time curl http://localhost:8080/
```

---

## 🚨 Emergency Commands

### Stop everything

```bash
# Stop all containers
docker stop $(docker ps -q)

# Remove all containers
docker rm $(docker ps -aq)

# Stop Maven process
# Press Ctrl+C in terminal

# Kill Java process
pkill -f "spring-boot"
```

### Reset Docker

```bash
# Remove everything
docker system prune -a --volumes

# Restart Docker
# macOS: Restart Docker Desktop
# Linux:
sudo systemctl restart docker
```

### Reset Git

```bash
# Discard all changes
git reset --hard HEAD

# Clean untracked files
git clean -fd

# Reset to remote
git fetch origin
git reset --hard origin/main
```

---

## 📋 Complete Workflow (Copy All)

```bash
# 1. Build and test locally
mvn clean install
mvn spring-boot:run

# 2. Test endpoints (in new terminal)
curl http://localhost:8080/
curl http://localhost:8080/health

# 3. Stop application (Ctrl+C)

# 4. Build Docker image
docker build -t cicd-pipeline-demo .

# 5. Run Docker container
docker run -p 8080:8080 cicd-pipeline-demo

# 6. Test Docker (in new terminal)
curl http://localhost:8080/

# 7. Stop Docker (Ctrl+C)

# 8. Push to GitHub
git add .
git commit -m "Update application"
git push origin main

# 9. Pull from Docker Hub (after pipeline completes)
docker pull YOUR_USERNAME/cicd-pipeline-demo:latest
docker run -d -p 8080:8080 YOUR_USERNAME/cicd-pipeline-demo:latest

# 10. Verify
curl http://localhost:8080/
```

---

## 🎯 One-Line Commands

```bash
# Build, test, and run
mvn clean install && mvn spring-boot:run

# Build Docker and run
docker build -t cicd-pipeline-demo . && docker run -p 8080:8080 cicd-pipeline-demo

# Git add, commit, push
git add . && git commit -m "Update" && git push origin main

# Stop and remove all containers
docker stop $(docker ps -q) && docker rm $(docker ps -aq)

# Clean Maven and Docker
mvn clean && docker system prune -f
```

---

**Pro Tip:** Bookmark this page for quick reference! 🔖
