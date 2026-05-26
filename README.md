# CI/CD Pipeline Project - Java Spring Boot Application

## 1. Introduction

In modern software development, automation plays a crucial role in improving productivity, reliability, and deployment speed. Traditional software development processes involve manual building, testing, and deployment, which often leads to errors, delays, and inconsistencies.

This project focuses on implementing a **Continuous Integration and Continuous Deployment (CI/CD) pipeline** for a Java-based application using DevOps tools such as **Docker**, **Maven**, and **GitHub Actions**. The pipeline automates the process from code commit to deployment, ensuring faster and more reliable software delivery.

The use of containerization with Docker ensures that the application runs consistently across different environments. GitHub Actions automates workflows, enabling seamless integration and deployment without manual intervention.

## 2. Objectives of the Project

The main objectives of this project are:

- ✅ To automate the build, test, and deployment process of a Java application
- ✅ To implement a CI/CD pipeline using modern DevOps tools
- ✅ To reduce human errors and improve software quality
- ✅ To ensure faster and consistent deployment using containerization
- ✅ To gain practical knowledge of DevOps practices and workflows

## 3. Tools and Technologies Used

The following tools and technologies are used in this project:

| Technology | Purpose |
|------------|---------|
| **Java (Spring Boot)** | Backend application development |
| **Maven** | Build automation and dependency management |
| **Docker** | Containerization of the application |
| **GitHub** | Version control system |
| **GitHub Actions** | CI/CD pipeline automation |
| **Docker Hub** | Container image repository |
| **Cloud VM** | Deployment environment |

## 4. System Architecture

```
┌─────────────┐      ┌──────────────┐      ┌─────────────┐      ┌──────────────┐
│  Developer  │─────▶│    GitHub    │─────▶│   GitHub    │─────▶│  Docker Hub  │
│   (Code)    │      │ (Repository) │      │   Actions   │      │   (Images)   │
└─────────────┘      └──────────────┘      └─────────────┘      └──────────────┘
                                                   │
                                                   ▼
                                            ┌─────────────┐
                                            │  Cloud VM   │
                                            │ (Deployment)│
                                            └─────────────┘
```

## 5. Implementation Details

### Step 1: Java Application Development

Created a simple Spring Boot REST API with the following endpoints:

- `GET /` - Returns "CI/CD Pipeline Working"
- `GET /health` - Returns application health status
- `GET /api/info` - Returns application information

**Key Files:**
- `Application.java` - Main Spring Boot application
- `HealthController.java` - REST API endpoints
- `application.properties` - Configuration

### Step 2: Maven Build

Used `pom.xml` for dependency management with:
- Spring Boot Web Starter
- Spring Boot Actuator
- Spring Boot Test

**Build Command:**
```bash
mvn clean install
```

### Step 3: Dockerization

Created a **multi-stage Dockerfile** to:
- Reduce image size
- Improve build efficiency
- Enhance security with non-root user

**Docker Build Command:**
```bash
docker build -t cicd-pipeline-demo .
```

**Docker Run Command:**
```bash
docker run -p 8080:8080 cicd-pipeline-demo
```

### Step 4: CI/CD Pipeline Setup

Created `.github/workflows/ci-cd.yml` with the following steps:

1. **Checkout code** - Get latest code from repository
2. **Setup Java** - Install JDK 17
3. **Build with Maven** - Compile and package application
4. **Run tests** - Execute unit tests
5. **Docker login** - Authenticate with Docker Hub
6. **Build Docker image** - Create container image
7. **Push to Docker Hub** - Upload image to registry

### Step 5: Secrets Configuration

Configure the following secrets in GitHub repository settings:

1. Go to **Settings** → **Secrets and variables** → **Actions**
2. Add the following secrets:
   - `DOCKER_USERNAME` - Your Docker Hub username
   - `DOCKER_PASSWORD` - Your Docker Hub password or access token

### Step 6: Execution

The pipeline runs automatically on:
- Every push to `main` or `master` branch
- Every pull request to `main` or `master` branch

## 6. How to Run Locally

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Docker (optional)

### Run with Maven
```bash
# Build the application
mvn clean install

# Run the application
mvn spring-boot:run

# Access the application
curl http://localhost:8080/
```

### Run with Docker
```bash
# Build Docker image
docker build -t cicd-pipeline-demo .

# Run container
docker run -p 8080:8080 cicd-pipeline-demo

# Access the application
curl http://localhost:8080/
```

## 7. Testing the Application

### Test Endpoints

```bash
# Home endpoint
curl http://localhost:8080/

# Health check
curl http://localhost:8080/health

# Application info
curl http://localhost:8080/api/info
```

### Expected Responses

**Home Endpoint:**
```
CI/CD Pipeline Working
```

**Health Endpoint:**
```json
{
  "status": "UP",
  "message": "Application is running successfully",
  "timestamp": "2026-05-15T10:30:00",
  "version": "1.0.0"
}
```

## 8. Deployment to Cloud VM

### Option 1: Pull from Docker Hub
```bash
# SSH into your VM
ssh user@your-vm-ip

# Pull the image
docker pull your-dockerhub-username/cicd-pipeline-demo:latest

# Run the container
docker run -d -p 8080:8080 --name cicd-app your-dockerhub-username/cicd-pipeline-demo:latest

# Check logs
docker logs cicd-app
```

### Option 2: Docker Compose (Recommended)
Create `docker-compose.yml`:
```yaml
version: '3.8'
services:
  app:
    image: your-dockerhub-username/cicd-pipeline-demo:latest
    ports:
      - "8080:8080"
    restart: unless-stopped
    healthcheck:
      test: ["CMD", "wget", "--spider", "http://localhost:8080/health"]
      interval: 30s
      timeout: 3s
      retries: 3
```

Run with:
```bash
docker-compose up -d
```

## 9. Results, Observations, and Conclusion

### Results
✅ CI/CD pipeline successfully automated  
✅ Application built and deployed without manual steps  
✅ Docker image successfully pushed to repository  
✅ Consistent deployment across environments  
✅ Automated testing integrated into pipeline  

### Observations
- **Automation reduces human errors** - No manual build or deployment steps
- **Faster development cycle** - Changes are deployed within minutes
- **Easy integration** - Multiple tools work seamlessly together
- **Improved reliability** - Consistent builds and deployments
- **Better collaboration** - Team members can see pipeline status

### Conclusion

This project successfully demonstrates the implementation of a complete CI/CD pipeline for a Java Spring Boot application. The use of modern DevOps tools like Docker, Maven, and GitHub Actions has automated the entire software delivery process, from code commit to deployment.

**Key Achievements:**
1. Fully automated build, test, and deployment process
2. Containerized application for consistent environments
3. Integrated CI/CD pipeline with GitHub Actions
4. Reduced deployment time from hours to minutes
5. Improved code quality through automated testing

**Future Enhancements:**
- Add integration tests and code coverage reports
- Implement blue-green deployment strategy
- Add monitoring and logging with ELK stack
- Implement automated rollback on failure
- Add security scanning for vulnerabilities
- Set up staging and production environments

## 10. Screenshots Guide

To complete your project documentation, capture the following screenshots:

1. **GitHub Repository** - Show the repository structure with all files
2. **GitHub Actions Pipeline** - Show the workflow running
3. **Successful Build Logs** - Show green checkmarks and build output
4. **Docker Hub Repository** - Show the pushed image with tags
5. **Application Running** - Show browser with "CI/CD Pipeline Working"
6. **Health Endpoint** - Show JSON response from /health
7. **Docker Container Running** - Show `docker ps` output
8. **Pipeline Execution Time** - Show how fast the pipeline runs

## 11. Troubleshooting

### Common Issues

**Issue 1: Maven build fails**
```bash
# Clear Maven cache
mvn clean
rm -rf ~/.m2/repository
mvn install
```

**Issue 2: Docker build fails**
```bash
# Check Docker is running
docker --version
docker ps

# Build with verbose output
docker build -t cicd-pipeline-demo . --progress=plain
```

**Issue 3: GitHub Actions fails**
- Check secrets are configured correctly
- Verify Docker Hub credentials
- Check workflow syntax in YAML file

## 12. References

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Docker Documentation](https://docs.docker.com/)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)

---

**Project By:** [Your Name]  
**Date:** May 15, 2026  
**Course:** DevOps and CI/CD Implementation



## Pipeline Status: Active ✅
## Pipeline Status: Active ✅
## Pipeline Status: Active ✅
