# Guide për Screenshot - Dorëzimi i Projektit

Studentët duhet të marrin screenshots për të dokumentuar punën e tyre. Këtu janë screenshots që duhet të përfshihen në dorëzim.

## 📸 Screenshots të Kërkuara

### 1. Jira Board Screenshots

#### a) Jira Project Overview
**Çfarë të përfshijë:**
- Emri i projektit dhe project key
- Lista e Epics
- Overview i backlog

**Ku ta gjeni:** Jira Dashboard → Your Project

#### b) Sprint Board
**Çfarë të përfshijë:**
- Kolona: To Do, In Progress, Code Review, Testing, Done
- User stories dhe tasks në board
- Sprint name dhe dates

**Ku ta gjeni:** Jira → Board → Active Sprint

#### c) Backlog View
**Çfarë të përfshijë:**
- Lista e User Stories
- Story points
- Priority levels
- Sprint planning

**Ku ta gjeni:** Jira → Backlog

#### d) Epic Breakdown
**Çfarë të përfshijë:**
- Një Epic i plotë me të gjitha stories
- Tasks për çdo story
- Progress tracking

**Ku ta gjeni:** Jira → Backlog → Click on an Epic

#### e) Sprint Report
**Çfarë të përfshijë:**
- Completed stories
- Velocity chart
- Burndown chart

**Ku ta gjeni:** Jira → Reports → Sprint Report

---

### 2. Jenkins Pipeline Screenshots

#### a) Jenkins Dashboard
**Çfarë të përfshijë:**
- Lista e jobs
- Build history
- Status indicators (green/red)

**Ku ta gjeni:** Jenkins Home Page

#### b) Pipeline Build Success
**Çfarë të përfshijë:**
- Pipeline stages visualization
- Të gjitha stages me ✅ (green)
- Build duration
- Build number

**Ku ta gjeni:** Jenkins → Job Name → Latest Build

#### c) Console Output
**Çfarë të përfshijë:**
- Maven build logs
- Test execution results
- "BUILD SUCCESS" message

**Ku ta gjeni:** Jenkins → Job → Build → Console Output

#### d) Test Results
**Çfarë të përfshijë:**
- Number of tests run
- Tests passed/failed
- Test duration

**Ku ta gjeni:** Jenkins → Job → Build → Test Results

#### e) Artifacts
**Çfarë të përfshijë:**
- JAR file generated
- File size
- Download link

**Ku ta gjeni:** Jenkins → Job → Build → Artifacts

---

### 3. OpenShift Deployment Screenshots

#### a) OpenShift Console Overview
**Çfarë të përfshijë:**
- Project name
- Overview dashboard
- Resources summary

**Ku ta gjeni:** OpenShift Web Console → Project

#### b) Pods Status
**Çfarë të përfshijë:**
- Running pods (green)
- Pod names
- Ready status (2/2)
- Restarts count

**Ku ta gjeni:** OpenShift → Workloads → Pods

#### c) Deployment Details
**Çfarë të përfshijë:**
- Deployment name
- Replicas: 2/2
- Image information
- Resource limits

**Ku ta gjeni:** OpenShift → Workloads → Deployments

#### d) Route (URL)
**Çfarë të përfshijë:**
- Route hostname (URL publik)
- Target Service
- TLS settings

**Ku ta gjeni:** OpenShift → Networking → Routes

#### e) Logs
**Çfarë të përfshijë:**
- Application startup logs
- "Started StudentManagementApplication"
- No errors

**Ku ta gjeni:** OpenShift → Workloads → Pods → Select Pod → Logs

#### f) Application Running
**Çfarë të përfshijë:**
- Browser screenshot me URL të OpenShift
- API response (GET /api/students)
- HTTP 200 status

**Ku ta gjeni:** Browser → Route URL

---

### 4. Application Screenshots

#### a) API Testing - Postman/Insomnia
**Çfarë të përfshijë:**

**1. Create Student (POST)**
- Request body (JSON)
- Response 201 Created
- Created student data

**2. Get All Students (GET)**
- Response 200 OK
- Array of students
- All fields visible

**3. Get Student by ID (GET)**
- URL with ID parameter
- Response with single student

**4. Update Student (PUT)**
- Request body with changes
- Response 200 OK
- Updated data

**5. Delete Student (DELETE)**
- Response 204 No Content

**6. Export to Excel (GET)**
- Response with file download
- File size visible

#### b) Excel Export
**Çfarë të përfshijë:**
- Opened Excel file
- Headers (Emri, Mbiemri, Email, etc.)
- Data rows populated
- Proper formatting

---

### 5. Code Screenshots (Opsionale por të rekomanduara)

#### a) Project Structure
**Çfarë të përfshijë:**
- Multi-module structure
- All modules visible (model, dto, mapper, etc.)
- Maven structure

**Tool:** IDE (IntelliJ/Eclipse) → Project Explorer

#### b) Entity Class
**Çfarë të përfshijë:**
- Student.java
- Lombok annotations visible
- JPA annotations

#### c) MapStruct Mapper
**Çfarë të përfshijë:**
- StudentMapper interface
- Mapping methods

#### d) Service Class
**Çfarë të përfshijë:**
- StudentService.java
- Business logic methods
- @Transactional annotation

#### e) REST Controller
**Çfarë të përfshijë:**
- StudentController.java
- API endpoints with mappings
- HTTP methods

#### f) Unit Test
**Çfarë të përfshijë:**
- StudentServiceTest.java
- Test methods with @Test annotation
- Assertions

---

### 6. Git Repository Screenshots

#### a) GitHub Repository
**Çfarë të përfshijë:**
- Repository name
- File structure visible
- README preview
- Commits count

#### b) Commit History
**Çfarë të përfshijë:**
- List of commits
- Commit messages
- Contributors

#### c) Code Files
**Çfarë të përfshijë:**
- pom.xml files
- Source code files
- Jenkinsfile
- Dockerfile

---

## 📋 Checklist për Dorëzim

Sigurohuni që të keni të gjitha këto screenshots:

### Jira (5 screenshots)
- [ ] Project Overview
- [ ] Sprint Board
- [ ] Backlog
- [ ] Epic Breakdown
- [ ] Sprint Report

### Jenkins (5 screenshots)
- [ ] Dashboard
- [ ] Pipeline Success
- [ ] Console Output
- [ ] Test Results
- [ ] Artifacts

### OpenShift (6 screenshots)
- [ ] Console Overview
- [ ] Pods Running
- [ ] Deployment Details
- [ ] Route URL
- [ ] Application Logs
- [ ] Application Running in Browser

### Application Testing (6 screenshots)
- [ ] POST - Create Student
- [ ] GET - All Students
- [ ] GET - Student by ID
- [ ] PUT - Update Student
- [ ] DELETE - Delete Student
- [ ] GET - Export Excel

### Excel Export (1 screenshot)
- [ ] Opened Excel file with data

### Git Repository (3 screenshots)
- [ ] Repository overview
- [ ] Commit history
- [ ] Code files

---

## 💡 Tips për Screenshots të Mira

1. **Përdor tools profesionale:**
   - Windows: Snipping Tool, Snip & Sketch
   - Screenshot extensions në browser

2. **Rezolucioni:**
   - Minimum 1280x720
   - Format: PNG (preferuar) ose JPG

3. **Clarity:**
   - Fokuso në pjesën relevante
   - Avoid clutter në background
   - Use full-screen mode kur është e mundur

4. **Organizimi:**
   - Emërto files në mënyrë descriptive
   - Example: `01_jira_project_overview.png`
   - Vendosi në folder: `screenshots/`

5. **Privacy:**
   - Blur/redact passwords
   - Blur personal information
   - Check që nuk ka sensitive data

---

## 📦 Struktura e Folder për Dorëzim

```
Detyra_Kursi_YourName/
│
├── screenshots/
│   ├── jira/
│   │   ├── 01_project_overview.png
│   │   ├── 02_sprint_board.png
│   │   ├── 03_backlog.png
│   │   ├── 04_epic_breakdown.png
│   │   └── 05_sprint_report.png
│   │
│   ├── jenkins/
│   │   ├── 01_dashboard.png
│   │   ├── 02_pipeline_success.png
│   │   ├── 03_console_output.png
│   │   ├── 04_test_results.png
│   │   └── 05_artifacts.png
│   │
│   ├── openshift/
│   │   ├── 01_console_overview.png
│   │   ├── 02_pods_status.png
│   │   ├── 03_deployment.png
│   │   ├── 04_route.png
│   │   ├── 05_logs.png
│   │   └── 06_app_running.png
│   │
│   ├── api_testing/
│   │   ├── 01_create_student.png
│   │   ├── 02_get_all.png
│   │   ├── 03_get_by_id.png
│   │   ├── 04_update.png
│   │   ├── 05_delete.png
│   │   └── 06_export_excel.png
│   │
│   └── excel/
│       └── 01_excel_file.png
│
├── code/
│   └── [projekti juaj]
│
└── dokumentacion/
    ├── DOKUMENTACION_TEKNIK.pdf
    └── README.pdf
```

---

**Sukses me dorëzimin! 🎓**
