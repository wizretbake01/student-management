# Student Management System - Jira Planning Guide

## 📊 Organizimi i Projektit në Jira

Ky dokument shpjegon se si të organizoni projektin në Jira duke përdorur metodologjinë **Agile/Scrum**.

## 🎯 Struktura e Projektit

### 1. Project Setup

**Emri i Projektit:** Student Management System  
**Project Key:** SMS  
**Project Type:** Scrum

### 2. Epics (Funksionalitete Kryesore)

#### Epic 1: Project Setup dhe Konfigurimi
**Key:** SMS-EPIC-1  
**Përshkrimi:** Setup i strukturës bazë të projektit

#### Epic 2: Student CRUD Operations
**Key:** SMS-EPIC-2  
**Përshkrimi:** Implementimi i operacioneve CRUD për studentët

#### Epic 3: Excel Export Functionality
**Key:** SMS-EPIC-3  
**Përshkrimi:** Gjenerimi i raporteve Excel

#### Epic 4: CI/CD Pipeline
**Key:** SMS-EPIC-4  
**Përshkrimi:** Automatizimi i build dhe deploy

#### Epic 5: OpenShift Deployment
**Key:** SMS-EPIC-5  
**Përshkrimi:** Deploy i aplikacionit në cloud

## 📝 User Stories dhe Tasks

### EPIC 1: Project Setup

#### User Story 1.1: Maven Multi-Module Setup
**As a** developer  
**I want** të krijoj një strukturë Maven multi-module  
**So that** kodi të jetë i organizuar dhe modular

**Tasks:**
- [SMS-1] Krijo root pom.xml
- [SMS-2] Krijo modulin model
- [SMS-3] Krijo modulin dto
- [SMS-4] Krijo modulin mapper
- [SMS-5] Krijo modulin repository
- [SMS-6] Krijo modulin service
- [SMS-7] Krijo modulin web
- [SMS-8] Krijo modulin excel

**Story Points:** 5  
**Priority:** Highest

---

### EPIC 2: Student CRUD Operations

#### User Story 2.1: Create Student
**As a** administrator  
**I want** të shtoj studentë të rinj në sistem  
**So that** të mund të menaxhoj informacionin e studentëve

**Tasks:**
- [SMS-10] Krijo Student entity me Lombok
- [SMS-11] Krijo StudentDTO
- [SMS-12] Implemento StudentMapper me MapStruct
- [SMS-13] Krijo StudentRepository
- [SMS-14] Implemento createStudent në service
- [SMS-15] Krijo POST endpoint në controller
- [SMS-16] Shto validim për email dhe studentId unique

**Story Points:** 8  
**Priority:** High

**Acceptance Criteria:**
- Një student i ri krijohet me sukses
- Email duhet të jetë unik
- StudentId duhet të jetë unik
- Validimi i të dhënave funksionon

#### User Story 2.2: View All Students
**As a** administrator  
**I want** të shikoj listën e të gjithë studentëve  
**So that** të kem një overview të plotë

**Tasks:**
- [SMS-20] Implemento getAllStudents në service
- [SMS-21] Krijo GET /api/students endpoint
- [SMS-22] Testo me disa studentë

**Story Points:** 3  
**Priority:** High

#### User Story 2.3: View Student Details
**As a** administrator  
**I want** të shikoj detajet e një studenti specifik  
**So that** të kem informacion të plotë për një student

**Tasks:**
- [SMS-25] Implemento getStudentById në service
- [SMS-26] Krijo GET /api/students/{id} endpoint
- [SMS-27] Handle ResourceNotFoundException

**Story Points:** 3  
**Priority:** Medium

#### User Story 2.4: Update Student
**As a** administrator  
**I want** të përditësoj informacionin e një studenti  
**So that** të mbaj të dhënat aktuale

**Tasks:**
- [SMS-30] Krijo StudentUpdateRequest DTO
- [SMS-31] Implemento updateStudent në service
- [SMS-32] Krijo PUT /api/students/{id} endpoint
- [SMS-33] Handle email uniqueness në update

**Story Points:** 5  
**Priority:** Medium

#### User Story 2.5: Delete Student
**As a** administrator  
**I want** të fshij një student nga sistemi  
**So that** të heq regjistrimet e pavlefshme

**Tasks:**
- [SMS-35] Implemento deleteStudent në service
- [SMS-36] Krijo DELETE /api/students/{id} endpoint
- [SMS-37] Shto validim për student që nuk ekziston

**Story Points:** 3  
**Priority:** Medium

#### User Story 2.6: Search and Filter
**As a** administrator  
**I want** të kërkoj studentë sipas kriteresh të ndryshme  
**So that** të gjej shpejt informacionin që më duhet

**Tasks:**
- [SMS-40] Implemento search by email
- [SMS-41] Implemento filter by department
- [SMS-42] Implemento filter by status
- [SMS-43] Implemento filter by enrollment year
- [SMS-44] Krijo endpoints për këto filtra

**Story Points:** 5  
**Priority:** Low

---

### EPIC 3: Excel Export

#### User Story 3.1: Export Students to Excel
**As a** administrator  
**I want** të eksportoj listën e studentëve në Excel  
**So that** të mund ta përdor jashtë sistemit

**Tasks:**
- [SMS-50] Shto Apache POI dependency
- [SMS-51] Krijo StudentExcelExporter service
- [SMS-52] Implemento logjikën e gjenerimit të Excel
- [SMS-53] Stilizo header dhe qelizat
- [SMS-54] Krijo GET /api/students/export/excel endpoint
- [SMS-55] Testo me të dhëna reale

**Story Points:** 8  
**Priority:** High

**Acceptance Criteria:**
- File Excel gjenerohet me sukses
- Të gjitha kolonat janë të pranishme
- Formatting është i lexueshëm
- Download funksionon në browser

---

### EPIC 4: Testing

#### User Story 4.1: Unit Tests
**As a** developer  
**I want** të shkruaj unit tests  
**So that** të siguroj cilësinë e kodit

**Tasks:**
- [SMS-60] Setup JUnit dhe Mockito
- [SMS-61] Shkruaj teste për StudentService
- [SMS-62] Shkruaj teste për StudentMapper
- [SMS-63] Shkruaj teste për StudentController
- [SMS-64] Arrij 80%+ test coverage

**Story Points:** 13  
**Priority:** High

---

### EPIC 5: CI/CD Pipeline

#### User Story 5.1: Jenkins Pipeline
**As a** DevOps engineer  
**I want** të automatizoj build dhe deploy  
**So that** procesi të jetë i shpejtë dhe reliable

**Tasks:**
- [SMS-70] Krijo Jenkinsfile
- [SMS-71] Konfiguro stage: Checkout
- [SMS-72] Konfiguro stage: Build
- [SMS-73] Konfiguro stage: Unit Tests
- [SMS-74] Konfiguro stage: Package
- [SMS-75] Konfiguro stage: Deploy to OpenShift
- [SMS-76] Setup notifications (email/Slack)
- [SMS-77] Testo pipeline end-to-end

**Story Points:** 13  
**Priority:** High

---

### EPIC 6: OpenShift Deployment

#### User Story 6.1: Containerize Application
**As a** DevOps engineer  
**I want** të containerizoj aplikacionin  
**So that** të jetë i deployueshëm në cloud

**Tasks:**
- [SMS-80] Krijo Dockerfile multi-stage
- [SMS-81] Build Docker image lokalisht
- [SMS-82] Test container lokalisht
- [SMS-83] Push në container registry

**Story Points:** 5  
**Priority:** High

#### User Story 6.2: Deploy to OpenShift
**As a** DevOps engineer  
**I want** të deployoj aplikacionin në OpenShift  
**So that** të jetë i aksesueshëm online

**Tasks:**
- [SMS-85] Krijo OpenShift deployment.yaml
- [SMS-86] Krijo service.yaml
- [SMS-87] Krijo route.yaml
- [SMS-88] Krijo configmap.yaml
- [SMS-89] Krijo secret.yaml
- [SMS-90] Deploy në OpenShift cluster
- [SMS-91] Verifiko që aplikacioni është i aksesueshëm
- [SMS-92] Setup database në OpenShift
- [SMS-93] Konfig health checks dhe readiness probes
- [SMS-94] Setup auto-scaling (optional)

**Story Points:** 13  
**Priority:** High

---

## 📅 Sprint Planning

### Sprint 1 (Javë 1)
**Objektiva:** Setup i projektit dhe implementimi i CRUD bazë

**User Stories:**
- [SMS-EPIC-1] Project Setup (Story 1.1)
- [SMS-EPIC-2] Create Student (Story 2.1)
- [SMS-EPIC-2] View All Students (Story 2.2)
- [SMS-EPIC-2] View Student Details (Story 2.3)

**Total Story Points:** 19

### Sprint 2 (Javë 2)
**Objektiva:** Përfundimi i CRUD, Excel export, dhe testing

**User Stories:**
- [SMS-EPIC-2] Update Student (Story 2.4)
- [SMS-EPIC-2] Delete Student (Story 2.5)
- [SMS-EPIC-2] Search and Filter (Story 2.6)
- [SMS-EPIC-3] Excel Export (Story 3.1)
- [SMS-EPIC-4] Unit Tests (Story 4.1)

**Total Story Points:** 34

### Sprint 3 (Javë 3)
**Objektiva:** CI/CD dhe deployment

**User Stories:**
- [SMS-EPIC-5] Jenkins Pipeline (Story 5.1)
- [SMS-EPIC-6] Containerize Application (Story 6.1)
- [SMS-EPIC-6] Deploy to OpenShift (Story 6.2)

**Total Story Points:** 31

---

## 🎨 Workflow në Jira

### Kolona në Board:

1. **To Do** - Detyra që duhet të fillojnë
2. **In Progress** - Detyra në proces
3. **Code Review** - Kodit i duhet review
4. **Testing** - Në proces të testimit
5. **Done** - Përfunduar

### Definition of Done:

✅ Kodi është shkruar  
✅ Unit tests janë shkruar dhe kalojnë  
✅ Code review është bërë  
✅ Dokumentacioni është update  
✅ Merged në main branch  
✅ Deployed në test environment

---

## 📈 Reporting

### Metrics për të monitoruar:

- **Velocity** - Story points të përfunduara për sprint
- **Burndown Chart** - Progresi brenda sprint
- **Cumulative Flow** - Flow i detyrave përmes workflow
- **Sprint Report** - Përmbledhje e sprint

---

## 💡 Best Practices

1. **Daily Standup** - Takime të përditshme (15 min)
   - Çfarë bëra dje?
   - Çfarë do të bëj sot?
   - A ka ndonjë bllokues?

2. **Sprint Planning** - Në fillim të çdo sprint
   - Review i backlog
   - Prioritizimi i detyrave
   - Commitment për sprint

3. **Sprint Review** - Në fund të sprint
   - Demo i funksionaliteteve të reja
   - Feedback nga stakeholders

4. **Sprint Retrospective** - Pas review
   - Çfarë shkoi mirë?
   - Çfarë mund të përmirësohet?
   - Action items për sprint tjetër

---

**Sukses me projektin! 🚀**
