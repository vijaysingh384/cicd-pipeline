# 🎯 HOW TO USE THIS PROJECT - Simple Visual Guide

## 📁 What You Have

```
cicd-pipeline-demo/
├── 📘 README.md              ← Complete project documentation
├── 🚀 QUICKSTART.md          ← Step-by-step guide (START HERE!)
├── ⚙️  SETUP.md               ← Installation & setup instructions
├── 🌐 DEPLOYMENT.md          ← How to deploy to cloud
├── 💻 COMMANDS.md            ← All commands in one place
├── 🐳 Dockerfile             ← Docker configuration
├── 📦 pom.xml                ← Maven configuration
├── 🔄 docker-compose.yml     ← Docker Compose setup
├── .github/workflows/
│   └── ci-cd.yml            ← GitHub Actions pipeline
└── src/
    ├── main/java/com/cicd/
    │   ├── Application.java           ← Main application
    │   └── controller/
    │       └── HealthController.java  ← REST API endpoints
    └── test/java/com/cicd/
        ├── ApplicationTests.java      ← Application tests
        └── controller/
            └── HealthControllerTests.java  ← Controller tests
```

---

## 🎬 Three Ways to Use This Project

### 🟢 Option 1: Quick Demo (5 minutes)

**Just want to see it work?**

```bash
# 1. Run the application
mvn spring-boot:run

# 2. Open browser and visit:
http://localhost:8080/

# You should see: "CI/CD Pipeline Working"
```

**That's it! ✅**

---

### 🟡 Option 2: Full Local Setup (15 minutes)

**Want to test everything locally?**

```bash
# 1. Build with Maven
mvn clean install

# 2. Run tests
mvn test

# 3. Build Docker image
docker build -t cicd-pipeline-demo .

# 4. Run Docker container
docker run -p 8080:8080 cicd-pipeline-demo

# 5. Test all endpoints
curl http://localhost:8080/
curl http://localhost:8080/health
curl http://localhost:8080/api/info
```

**Done! You've tested everything locally! ✅**

---

### 🔴 Option 3: Complete CI/CD Pipeline (30 minutes)

**Want the full automated pipeline?**

Follow these steps in order:

#### Step 1: Create GitHub Repository (5 min)
1. Go to https://github.com → New repository
2. Name: `cicd-pipeline-demo`
3. Click "Create repository"

#### Step 2: Push Your Code (2 min)
```bash
git init
git add .
git commit -m "Initial commit"
git remote add origin https://github.com/YOUR_USERNAME/cicd-pipeline-demo.git
git push -u origin main
```

#### Step 3: Create Docker Hub Repository (3 min)
1. Go to https://hub.docker.com → Create Repository
2. Name: `cicd-pipeline-demo`
3. Click "Create"

#### Step 4: Add GitHub Secrets (5 min)
1. Go to GitHub repo → Settings → Secrets → Actions
2. Add `DOCKER_USERNAME` (your Docker Hub username)
3. Add `DOCKER_PASSWORD` (your Docker Hub password/token)

#### Step 5: Trigger Pipeline (2 min)
```bash
echo "\n## Pipeline Active" >> README.md
git add README.md
git commit -m "Trigger pipeline"
git push origin main
```

#### Step 6: Watch the Magic! (5 min)
1. Go to GitHub → Actions tab
2. Watch your pipeline run automatically
3. See all steps complete with ✅

#### Step 7: Verify Docker Hub (2 min)
1. Go to Docker Hub
2. Check your repository
3. See the new image with `latest` tag

#### Step 8: Deploy (Optional - 10 min)
```bash
# Pull and run from Docker Hub
docker pull YOUR_USERNAME/cicd-pipeline-demo:latest
docker run -p 8080:8080 YOUR_USERNAME/cicd-pipeline-demo:latest
```

**Congratulations! Full CI/CD pipeline working! 🎉**

---

## 📚 Which Guide Should I Read?

| Guide | When to Use | Time |
|-------|-------------|------|
| **QUICKSTART.md** | First time using the project | 30 min |
| **COMMANDS.md** | Need specific commands | 2 min |
| **SETUP.md** | Installing prerequisites | 15 min |
| **DEPLOYMENT.md** | Deploying to cloud | 20 min |
| **README.md** | Understanding the project | 10 min |

---

## 🎯 Common Use Cases

### "I just want to run it locally"

```bash
mvn spring-boot:run
# Visit: http://localhost:8080/
```

### "I want to test with Docker"

```bash
docker build -t cicd-pipeline-demo .
docker run -p 8080:8080 cicd-pipeline-demo
# Visit: http://localhost:8080/
```

### "I want to set up the full CI/CD pipeline"

**Read:** `QUICKSTART.md` (follow all steps)

### "I want to deploy to a cloud server"

**Read:** `DEPLOYMENT.md` (choose your cloud provider)

### "I need a specific command"

**Read:** `COMMANDS.md` (copy and paste)

### "Something is not working"

**Check:**
1. `QUICKSTART.md` → Troubleshooting section
2. `SETUP.md` → Common issues
3. GitHub Actions logs (if pipeline fails)
4. Docker logs: `docker logs <container-id>`

---

## 🔥 Quick Start Commands

### Run Application

```bash
# With Maven
mvn spring-boot:run

# With Docker
docker build -t cicd-pipeline-demo . && docker run -p 8080:8080 cicd-pipeline-demo

# With JAR
mvn package && java -jar target/cicd-app.jar
```

### Test Application

```bash
# Run tests
mvn test

# Test endpoints
curl http://localhost:8080/
curl http://localhost:8080/health
curl http://localhost:8080/api/info
```

### Deploy to GitHub

```bash
git add .
git commit -m "Your message"
git push origin main
```

---

## 🎓 Learning Path

### Beginner (Day 1)
1. ✅ Read `README.md` to understand the project
2. ✅ Run locally with `mvn spring-boot:run`
3. ✅ Test the endpoints in browser

### Intermediate (Day 2)
1. ✅ Follow `QUICKSTART.md` completely
2. ✅ Set up GitHub repository
3. ✅ Configure Docker Hub
4. ✅ Run the CI/CD pipeline

### Advanced (Day 3)
1. ✅ Deploy to cloud VM (follow `DEPLOYMENT.md`)
2. ✅ Modify the application
3. ✅ Watch automatic deployment
4. ✅ Add new features

---

## 🎬 Video Tutorial Script

If you're creating a video demonstration:

### Part 1: Introduction (2 min)
- Show project structure
- Explain what CI/CD is
- Show the tools used

### Part 2: Local Demo (3 min)
```bash
mvn spring-boot:run
# Show in browser
curl http://localhost:8080/
```

### Part 3: Docker Demo (3 min)
```bash
docker build -t cicd-pipeline-demo .
docker run -p 8080:8080 cicd-pipeline-demo
# Show in browser
```

### Part 4: GitHub Setup (5 min)
- Create repository
- Push code
- Show files in GitHub

### Part 5: Docker Hub Setup (3 min)
- Create repository
- Show empty repository

### Part 6: Configure Secrets (3 min)
- Add DOCKER_USERNAME
- Add DOCKER_PASSWORD

### Part 7: Trigger Pipeline (5 min)
- Make a change
- Push to GitHub
- Show Actions tab
- Watch pipeline run

### Part 8: Verify Results (3 min)
- Show Docker Hub with new image
- Pull and run the image
- Test in browser

### Part 9: Conclusion (2 min)
- Recap what was built
- Show the automation
- Mention next steps

**Total: ~30 minutes**

---

## 📊 Project Checklist

Use this checklist for your project report:

### Setup Phase
- [ ] Java 17+ installed
- [ ] Maven 3.6+ installed
- [ ] Docker installed
- [ ] Git installed
- [ ] GitHub account created
- [ ] Docker Hub account created

### Development Phase
- [ ] Application runs locally with Maven
- [ ] All tests pass
- [ ] Application runs in Docker
- [ ] Endpoints tested and working

### CI/CD Phase
- [ ] GitHub repository created
- [ ] Code pushed to GitHub
- [ ] Docker Hub repository created
- [ ] GitHub secrets configured
- [ ] Pipeline triggered successfully
- [ ] All pipeline steps passed
- [ ] Docker image pushed to Docker Hub

### Deployment Phase
- [ ] Image pulled from Docker Hub
- [ ] Container running successfully
- [ ] Application accessible from browser
- [ ] Health check working

### Documentation Phase
- [ ] Screenshots captured
- [ ] README.md reviewed
- [ ] Project report written
- [ ] Demo prepared

---

## 🎯 Success Criteria

You'll know everything is working when:

✅ **Local Test:**
```bash
mvn spring-boot:run
# Browser shows: "CI/CD Pipeline Working"
```

✅ **Docker Test:**
```bash
docker run -p 8080:8080 cicd-pipeline-demo
# Browser shows: "CI/CD Pipeline Working"
```

✅ **Pipeline Test:**
- GitHub Actions shows all green checkmarks ✅
- Docker Hub shows your image with `latest` tag
- You can pull and run from Docker Hub

✅ **Deployment Test:**
- Application runs on cloud VM
- Accessible from public IP
- Health endpoint returns JSON

---

## 🆘 Getting Help

### If something doesn't work:

1. **Check the guides:**
   - `QUICKSTART.md` has troubleshooting section
   - `SETUP.md` has common issues
   - `COMMANDS.md` has all commands

2. **Check the logs:**
   ```bash
   # Maven logs
   mvn spring-boot:run
   
   # Docker logs
   docker logs <container-id>
   
   # GitHub Actions logs
   # Go to Actions tab in GitHub
   ```

3. **Verify prerequisites:**
   ```bash
   java -version
   mvn -version
   docker --version
   git --version
   ```

4. **Common fixes:**
   ```bash
   # Clean Maven
   mvn clean
   
   # Clean Docker
   docker system prune -f
   
   # Reset Git
   git reset --hard HEAD
   ```

---

## 🎉 You're Ready!

**Start with:** `QUICKSTART.md`

**Need commands?** `COMMANDS.md`

**Need help?** Check troubleshooting sections

**Good luck with your project! 🚀**

---

## 📞 Quick Reference

| Task | Command |
|------|---------|
| Run app | `mvn spring-boot:run` |
| Run tests | `mvn test` |
| Build Docker | `docker build -t cicd-pipeline-demo .` |
| Run Docker | `docker run -p 8080:8080 cicd-pipeline-demo` |
| Test endpoint | `curl http://localhost:8080/` |
| Push to GitHub | `git add . && git commit -m "msg" && git push` |
| View logs | `docker logs <container-id>` |
| Stop Docker | `docker stop <container-id>` |

---

**Remember:** Start simple, then add complexity! 💡
