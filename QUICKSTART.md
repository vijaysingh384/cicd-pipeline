# Quick Start Guide - How to Use This Project

This guide will walk you through using the CI/CD pipeline project from start to finish.

## 📋 Prerequisites Checklist

Before starting, install these tools:

- [ ] **Java 17+** - Download from [Adoptium](https://adoptium.net/)
- [ ] **Maven 3.6+** - Download from [Maven](https://maven.apache.org/download.cgi)
- [ ] **Docker** - Download from [Docker](https://www.docker.com/products/docker-desktop)
- [ ] **Git** - Download from [Git](https://git-scm.com/downloads)
- [ ] **GitHub Account** - Sign up at [GitHub](https://github.com/signup)
- [ ] **Docker Hub Account** - Sign up at [Docker Hub](https://hub.docker.com/signup)

### Verify Installation

Open terminal and run:

```bash
java -version    # Should show version 17 or higher
mvn -version     # Should show version 3.6 or higher
docker --version # Should show version 20 or higher
git --version    # Should show version 2 or higher
```

If any command fails, install the missing tool first.

---

## 🚀 Part 1: Run Locally (5 minutes)

### Option A: Run with Maven (Easiest)

```bash
# 1. Navigate to project directory
cd cicd-pipeline-demo

# 2. Build the project
mvn clean install

# 3. Run the application
mvn spring-boot:run
```

**Expected output:**
```
Started Application in 3.456 seconds
```

### Test the Application

Open a new terminal and run:

```bash
# Test home endpoint
curl http://localhost:8080/
# Output: CI/CD Pipeline Working

# Test health endpoint
curl http://localhost:8080/health
# Output: JSON with status "UP"

# Test info endpoint
curl http://localhost:8080/api/info
# Output: JSON with application info
```

Or open in browser:
- http://localhost:8080/
- http://localhost:8080/health
- http://localhost:8080/api/info

**Stop the application:** Press `Ctrl+C` in the terminal

---

### Option B: Run with Docker

```bash
# 1. Build Docker image
docker build -t cicd-pipeline-demo .

# 2. Run container
docker run -p 8080:8080 cicd-pipeline-demo

# 3. Test (in new terminal)
curl http://localhost:8080/

# 4. Stop container
docker ps                    # Get container ID
docker stop <container-id>   # Stop it
```

---

## 🔧 Part 2: Set Up GitHub Repository (10 minutes)

### Step 1: Create GitHub Repository

1. Go to https://github.com
2. Click the **+** icon (top right) → **New repository**
3. Fill in:
   - **Repository name:** `cicd-pipeline-demo`
   - **Description:** `Java Spring Boot CI/CD Pipeline Project`
   - **Visibility:** Public (or Private)
   - **DO NOT** check "Initialize with README"
4. Click **Create repository**

### Step 2: Push Your Code to GitHub

```bash
# 1. Initialize Git (if not already done)
git init

# 2. Add all files
git add .

# 3. Commit
git commit -m "Initial commit: CI/CD pipeline project"

# 4. Add remote (replace YOUR_USERNAME with your GitHub username)
git remote add origin https://github.com/YOUR_USERNAME/cicd-pipeline-demo.git

# 5. Push to GitHub
git branch -M main
git push -u origin main
```

**Verify:** Go to your GitHub repository URL - you should see all files uploaded.

---

## 🐳 Part 3: Set Up Docker Hub (5 minutes)

### Step 1: Create Docker Hub Repository

1. Go to https://hub.docker.com and log in
2. Click **Create Repository**
3. Fill in:
   - **Name:** `cicd-pipeline-demo`
   - **Visibility:** Public
4. Click **Create**

### Step 2: Create Access Token (Recommended)

1. Click your profile icon → **Account Settings**
2. Go to **Security** tab
3. Click **New Access Token**
4. Fill in:
   - **Description:** `GitHub Actions CI/CD`
   - **Access permissions:** Read, Write, Delete
5. Click **Generate**
6. **IMPORTANT:** Copy the token immediately (you won't see it again!)

---

## 🔐 Part 4: Configure GitHub Secrets (5 minutes)

### Add Docker Credentials to GitHub

1. Go to your GitHub repository
2. Click **Settings** (top menu)
3. In left sidebar: **Secrets and variables** → **Actions**
4. Click **New repository secret**

**Add Secret 1:**
- Name: `DOCKER_USERNAME`
- Secret: Your Docker Hub username (e.g., `johndoe`)
- Click **Add secret**

**Add Secret 2:**
- Name: `DOCKER_PASSWORD`
- Secret: Your Docker Hub access token (from Part 3, Step 2)
- Click **Add secret**

**Verify:** You should see both secrets listed (values will be hidden).

---

## 🎯 Part 5: Trigger the CI/CD Pipeline (5 minutes)

### Method 1: Make a Small Change

```bash
# 1. Make a small change to README
echo "\n## Pipeline Status: Active ✅" >> README.md

# 2. Commit and push
git add README.md
git commit -m "Trigger CI/CD pipeline"
git push origin main
```

### Method 2: Re-push Existing Code

```bash
# Create an empty commit
git commit --allow-empty -m "Trigger CI/CD pipeline"
git push origin main
```

---

## 📊 Part 6: Monitor Pipeline Execution (5 minutes)

### Watch the Pipeline Run

1. Go to your GitHub repository
2. Click **Actions** tab (top menu)
3. You should see a workflow run with your commit message
4. Click on the workflow run to see details

### Pipeline Steps (should all show green ✅)

1. ✅ **Checkout code** - Downloads your code
2. ✅ **Set up JDK 17** - Installs Java
3. ✅ **Build with Maven** - Compiles your application
4. ✅ **Run tests** - Executes unit tests
5. ✅ **Log in to Docker Hub** - Authenticates with Docker Hub
6. ✅ **Extract metadata** - Prepares Docker tags
7. ✅ **Build and push Docker image** - Creates and uploads image
8. ✅ **Image digest** - Shows success message

**Total time:** Usually 3-5 minutes

### If Pipeline Fails ❌

**Common issues:**

1. **Docker login failed**
   - Check `DOCKER_USERNAME` and `DOCKER_PASSWORD` secrets
   - Verify Docker Hub access token is correct

2. **Maven build failed**
   - Check the build logs for errors
   - Ensure `pom.xml` is correct

3. **Tests failed**
   - Review test logs
   - Run tests locally: `mvn test`

---

## 🎉 Part 7: Verify Docker Hub (2 minutes)

### Check Your Docker Image

1. Go to https://hub.docker.com
2. Click **Repositories**
3. Click on `cicd-pipeline-demo`
4. You should see:
   - **Tag:** `latest`
   - **Tag:** `main-<commit-sha>`
   - **Last pushed:** Just now

**Screenshot this page for your project report!**

---

## 🌐 Part 8: Deploy to Cloud (Optional - 15 minutes)

### Option A: Deploy to Any Cloud VM

If you have access to AWS EC2, Google Cloud, Azure, or DigitalOcean:

```bash
# 1. SSH into your VM
ssh user@your-vm-ip

# 2. Install Docker (if needed)
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# 3. Pull your image (replace YOUR_USERNAME)
docker pull YOUR_USERNAME/cicd-pipeline-demo:latest

# 4. Run the container
docker run -d -p 8080:8080 --name cicd-app \
  --restart unless-stopped \
  YOUR_USERNAME/cicd-pipeline-demo:latest

# 5. Check if running
docker ps

# 6. Test it
curl http://localhost:8080/

# 7. Configure firewall (if needed)
sudo ufw allow 8080/tcp
```

**Access from browser:** `http://your-vm-ip:8080/`

### Option B: Deploy with Docker Compose

```bash
# 1. SSH into your VM
ssh user@your-vm-ip

# 2. Create docker-compose.yml
cat > docker-compose.yml << 'EOF'
version: '3.8'
services:
  app:
    image: YOUR_USERNAME/cicd-pipeline-demo:latest
    ports:
      - "8080:8080"
    restart: unless-stopped
EOF

# 3. Start the application
docker-compose up -d

# 4. View logs
docker-compose logs -f

# 5. Stop the application
docker-compose down
```

---

## 🧪 Part 9: Test Everything (5 minutes)

### Local Testing

```bash
# Run all tests
mvn test

# Run with coverage
mvn test jacoco:report

# Build without tests
mvn clean install -DskipTests
```

### Docker Testing

```bash
# Build and run
docker build -t test-app .
docker run -p 8080:8080 test-app

# Check health
curl http://localhost:8080/health

# View logs
docker logs <container-id>

# Stop and remove
docker stop <container-id>
docker rm <container-id>
```

---

## 📸 Part 10: Capture Screenshots for Report

Take these screenshots for your project documentation:

1. **GitHub Repository**
   - Show all files in the repository
   - URL: `https://github.com/YOUR_USERNAME/cicd-pipeline-demo`

2. **GitHub Actions Pipeline**
   - Show the Actions tab with successful run (green checkmarks)
   - Click on a workflow run to show all steps

3. **Build Logs**
   - Show the detailed logs of each step
   - Highlight the "Build and push Docker image" step

4. **Docker Hub Repository**
   - Show your repository with the pushed images
   - Show the tags (latest, main-xxx)

5. **Application Running**
   - Browser showing `http://localhost:8080/`
   - Output: "CI/CD Pipeline Working"

6. **Health Endpoint**
   - Browser showing `http://localhost:8080/health`
   - JSON response with status "UP"

7. **Terminal Output**
   - Show `mvn spring-boot:run` output
   - Show `docker ps` output

8. **Pipeline Execution Time**
   - Show how long the pipeline took to run

---

## 🔄 Part 11: Make Changes and See CI/CD in Action

### Try This Exercise

1. **Modify the application:**

```bash
# Edit the controller
nano src/main/java/com/cicd/controller/HealthController.java

# Change the home endpoint to return:
return "CI/CD Pipeline Working - Updated Version!";
```

2. **Commit and push:**

```bash
git add .
git commit -m "Update home endpoint message"
git push origin main
```

3. **Watch the magic:**
   - Go to GitHub Actions
   - Watch the pipeline run automatically
   - See the new image pushed to Docker Hub
   - Pull and run the new version:

```bash
docker pull YOUR_USERNAME/cicd-pipeline-demo:latest
docker run -p 8080:8080 YOUR_USERNAME/cicd-pipeline-demo:latest
curl http://localhost:8080/
# Output: CI/CD Pipeline Working - Updated Version!
```

**This demonstrates the full CI/CD cycle!**

---

## 🆘 Troubleshooting

### Problem: "mvn: command not found"

**Solution:**
```bash
# macOS
brew install maven

# Ubuntu/Debian
sudo apt-get install maven

# Windows
# Download from https://maven.apache.org/download.cgi
```

### Problem: "docker: command not found"

**Solution:**
- Install Docker Desktop from https://www.docker.com/products/docker-desktop

### Problem: "Port 8080 already in use"

**Solution:**
```bash
# Find what's using port 8080
lsof -i :8080  # macOS/Linux
netstat -ano | findstr :8080  # Windows

# Kill the process or use different port
docker run -p 8081:8080 cicd-pipeline-demo
```

### Problem: GitHub Actions fails with "Docker login failed"

**Solution:**
1. Verify secrets are set correctly in GitHub
2. Use Docker Hub access token instead of password
3. Check Docker Hub account is active

### Problem: "Tests failed" during Maven build

**Solution:**
```bash
# Run tests with verbose output
mvn test -X

# Check specific test
mvn test -Dtest=HealthControllerTests

# Skip tests temporarily (not recommended)
mvn clean install -DskipTests
```

---

## 📚 What You've Learned

By completing this guide, you've:

✅ Built a Java Spring Boot REST API  
✅ Containerized an application with Docker  
✅ Set up a CI/CD pipeline with GitHub Actions  
✅ Automated build, test, and deployment  
✅ Published Docker images to Docker Hub  
✅ Deployed to cloud infrastructure  
✅ Implemented DevOps best practices  

---

## 🎓 Next Steps

1. **Add more features:**
   - Database integration (PostgreSQL/MySQL)
   - User authentication (Spring Security)
   - More REST endpoints

2. **Improve pipeline:**
   - Add code coverage reports (JaCoCo)
   - Add security scanning (Snyk, Trivy)
   - Add performance testing

3. **Advanced deployment:**
   - Set up Kubernetes deployment
   - Implement blue-green deployment
   - Add monitoring (Prometheus, Grafana)

4. **Learn more:**
   - Spring Boot documentation
   - Docker best practices
   - Kubernetes fundamentals

---

## 📞 Need Help?

- Check `README.md` for detailed documentation
- Check `SETUP.md` for installation help
- Check `DEPLOYMENT.md` for deployment options
- Review GitHub Actions logs for errors
- Check Docker container logs: `docker logs <container-id>`

---

**Congratulations! You've successfully set up and used a complete CI/CD pipeline! 🎉**
