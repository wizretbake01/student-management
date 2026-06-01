# Student Management System 🎓

Një aplikacion modern për menaxhimin e studentëve, zhvilluar me Java, Spring Boot, dhe teknologji të tjera moderne.

## 📋 Përmbajtja

- [Teknologjitë e Përdorura](#teknologjitë-e-përdorura)
- [Arkitektura e Projektit](#arkitektura-e-projektit)
- [Instalimi dhe Ekzekutimi](#instalimi-dhe-ekzekutimi)
- [API Endpoints](#api-endpoints)
- [CI/CD Pipeline](#cicd-pipeline)
- [Deploy në OpenShift](#deploy-në-openshift)
- [Testimi](#testimi)

## 🛠 Teknologjitë e Përdorura

- **Java 17** - Gjuha e programimit
- **Spring Boot 3.2.0** - Framework për aplikacionin
- **Maven** - Build tool (multi-module project)
- **Lombok** - Reduktimi i boilerplate code
- **MapStruct** - Mapimi i objekteve (DTO ↔ Entity)
- **Apache POI** - Gjenerimi i file Excel
- **H2 Database** - In-memory database për development
- **JUnit 5 & Mockito** - Unit testing
- **Jenkins** - CI/CD pipeline
- **OpenShift** - Container orchestration dhe deployment

## 🏗 Arkitektura e Projektit

Projekti përdor një arkitekturë **multi-module Maven**:

```
student-management/
│
├── model/          # Entity classes me Lombok
├── dto/            # Data Transfer Objects
├── mapper/         # MapStruct mappers (DTO ↔ Entity)
├── repository/     # Spring Data JPA repositories
├── service/        # Business logic layer
├── excel/          # Excel export me Apache POI
└── web/            # REST API controllers dhe main app
```

## 🚀 Instalimi dhe Ekzekutimi

### Parakushtet

- Java 17 ose më i ri
- Maven 3.8+
- Git

### Ekzekutimi lokal

```bash
# Klono repository
git clone <repository-url>
cd AppJira

# Build projektin
mvn clean install

# Run aplikacionin
cd web
mvn spring-boot:run
```

Aplikacioni do të jetë i aksesueshëm në: `http://localhost:8080`

### H2 Console

Për të parë databazën në development mode:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:studentdb`
- Username: `sa`
- Password: (bosh)

## 📡 API Endpoints

### Student Management

#### Krijo një student të ri
```http
POST /api/students
Content-Type: application/json

{
  "firstName": "Arben",
  "lastName": "Shala",
  "email": "arben.shala@example.com",
  "dateOfBirth": "2000-05-15",
  "department": "Informatikë",
  "studentId": "ST2024001",
  "phoneNumber": "0691234567",
  "address": "Prishtinë",
  "status": "ACTIVE",
  "enrollmentYear": 2024
}
```

#### Lista e të gjithë studentëve
```http
GET /api/students
```

#### Merr një student sipas ID
```http
GET /api/students/{id}
```

#### Përditëso një student
```http
PUT /api/students/{id}
Content-Type: application/json

{
  "firstName": "Arben",
  "lastName": "Shala Updated",
  "email": "arben.new@example.com"
}
```

#### Fshi një student
```http
DELETE /api/students/{id}
```

#### Eksporto në Excel
```http
GET /api/students/export/excel
```

Shkarkon një file `.xlsx` me të gjithë studentët.

#### Endpoints të tjera

- `GET /api/students/email/{email}` - Merr student sipas email
- `GET /api/students/department/{department}` - Lista sipas departamentit
- `GET /api/students/status/{status}` - Lista sipas statusit
- `GET /api/students/year/{year}` - Lista sipas vitit të regjistrimit
- `GET /api/students/count` - Numri total i studentëve

## 🔄 CI/CD Pipeline

Projekti përdor **Jenkins** për CI/CD automation.

### Faza të Pipeline:

1. **Checkout** - Merr kodin nga Git
2. **Build** - Kompajlon projektin me Maven
3. **Unit Tests** - Ekzekuton testet
4. **Code Analysis** - Verifikon cilësinë e kodit
5. **Package** - Krijon JAR artifact
6. **Archive Artifacts** - Ruaj artifacts
7. **Deploy to OpenShift** - Deploy në cloud

### Setup i Jenkins

1. Instalo Jenkins
2. Konfiguro Maven dhe JDK
3. Krijo një pipeline job të ri
4. Point në `Jenkinsfile` në repository
5. Run pipeline

## ☁️ Deploy në OpenShift

### Build Docker Image

```bash
docker build -t student-management:1.0.0 .
```

### Deploy në OpenShift

```bash
# Login në OpenShift
oc login --token=<token> --server=<server-url>

# Krijo project
oc new-project student-management

# Apply configurations
oc apply -f openshift/secret.yaml
oc apply -f openshift/configmap.yaml
oc apply -f openshift/deployment.yaml

# Shiko statusin
oc get pods
oc get routes
```

Shih dokumentacion të detajuar në [`openshift/DEPLOY.md`](openshift/DEPLOY.md)

## 🧪 Testimi

### Run unit tests

```bash
mvn test
```

### Run tests për një modul specifik

```bash
cd service
mvn test
```

### Test coverage

```bash
mvn test jacoco:report
```

## 📚 Struktura e Kodit

### Entity (Model Layer)

```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    // ... fields të tjera
}
```

### DTO Layer

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    // ... fields të tjera
}
```

### MapStruct Mapper

```java
@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);
    Student toEntity(StudentDTO dto);
    List<StudentDTO> toDTOList(List<Student> students);
}
```

### Service Layer

```java
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper mapper;
    
    public StudentDTO createStudent(StudentCreateRequest request) {
        // Business logic
    }
}
```

### REST Controller

```java
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;
    
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }
}
```

## 📊 Jira Integration

Projekti përdor **Jira** për menaxhimin e detyrave sipas metodologjisë Agile.

### Struktura e Jira Board:

- **Epics**: Funksionalitete kryesore (CRUD, Excel Export, etc.)
- **User Stories**: Kërkesa të përdoruesve
- **Tasks**: Detyrë teknike specifike
- **Sprints**: 1-2 javë

Shih [`JIRA.md`](JIRA.md) për më shumë detaje.

## 📄 Licensa

MIT License

## 👥 Kontribuesit

- Projekti i Detyrës së Kursit

## 📞 Kontakti

Për çdo pyetje ose problem, hapni një issue në GitHub.

---

**Zhvilluar me ❤️ për Detyrën e Kursit**
