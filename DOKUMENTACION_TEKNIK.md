# Dokumentacioni Teknik - Student Management System

## Përshkrimi i Projektit

Student Management System është një aplikacion web për menaxhimin e informacionit të studentëve, i zhvilluar si pjesë e detyrës së kursit. Aplikacioni ofron funksionalitete CRUD të plota, eksportim në Excel, dhe është i integruar me një pipeline CI/CD për deployment automatik.

## Arkitektura e Aplikacionit

### 1. Arsyetimi i Arkitekturës Multi-Module

Projekti përdor një arkitekturë **Maven multi-module** për arsyet e mëposhtme:

**Avantazhet:**
- ✅ **Separimi i përgjegjësive** - Çdo modul ka një qëllim specifik
- ✅ **Reusability** - Modulet mund të ripërdoren në projekte të tjera
- ✅ **Testimi i izoluar** - Çdo modul mund të testohet veçmas
- ✅ **Paralelizimi i build** - Maven mund të build-ojë module në paralel
- ✅ **Team collaboration** - Ekipe të ndryshme mund të punojnë në module të ndryshme

### 2. Pershkrimi i Moduleve

#### a) **model** - Entity Layer
Përmban class-at JPA entity që përfaqësojnë tabelat e databazës.

**Teknologjitë:**
- JPA/Hibernate për ORM
- Lombok për reduktimin e boilerplate code

**Klasa kryesore:**
```java
@Entity
@Table(name = "students")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    // ... fields të tjera
}
```

**Përdorimi i Lombok:**
- `@Getter/@Setter` - Gjeneron getter/setter automatically
- `@NoArgsConstructor/@AllArgsConstructor` - Gjeneron konstruktorë
- `@Builder` - Implementon Builder pattern për krijimin e objekteve

#### b) **dto** - Data Transfer Objects
Përmban objektet që transferohen midis layers dhe API.

**Arsyet për përdorimin e DTO:**
- Ndan modelin e databazës nga API contract
- Kontrollon çfarë të dhënash ekspozen në API
- Lejon validim më të mirë të input

**Klasa kryesore:**
- `StudentDTO` - Për response
- `StudentCreateRequest` - Për krijimin e studentëve
- `StudentUpdateRequest` - Për update

#### c) **mapper** - MapStruct Mappers
Përmban mappers për konvertimin e objekteve Entity ↔ DTO.

**Përse MapStruct?**
- 🚀 Performance i lartë (gjeneron kod në compile-time)
- 🛡️ Type-safe (gabime zbulohen në compile-time)
- 📝 Kod minimal (annotim-based)

**Mapper example:**
```java
@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);
    Student toEntity(StudentDTO dto);
    List<StudentDTO> toDTOList(List<Student> students);
}
```

#### d) **repository** - Data Access Layer
Përmban Spring Data JPA repositories për operacione të databazës.

**Avantazhet e Spring Data JPA:**
- Eliminon boilerplate SQL code
- Query methods nga emri i metodës
- Support për custom queries me `@Query`

**Repository example:**
```java
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    List<Student> findByDepartment(String department);
    boolean existsByEmail(String email);
}
```

#### e) **service** - Business Logic Layer
Përmban logjikën e biznesit dhe orkestrimin e operacioneve.

**Përgjegjësitë:**
- Validimi i business rules
- Koordinimi i repository dhe mapper
- Exception handling
- Transaction management

**Service example:**
```java
@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper mapper;
    
    public StudentDTO createStudent(StudentCreateRequest request) {
        // Business logic
        validateUniqueEmail(request.getEmail());
        Student student = mapper.toEntity(request);
        Student saved = repository.save(student);
        return mapper.toDTO(saved);
    }
}
```

#### f) **excel** - Excel Export Module
Përmban logjikën për gjenerimin e file Excel me Apache POI.

**Apache POI Features:**
- Krijim dhe lexim i file Excel (.xlsx)
- Styling të qelizave (fonts, colors, borders)
- Auto-sizing i kolonave
- Formula support

**Implementation:**
```java
@Service
public class StudentExcelExporter {
    public byte[] exportStudentsToExcel(List<StudentDTO> students) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Studentët");
        // Krijo header dhe populo të dhënat
        return toByteArray(workbook);
    }
}
```

#### g) **web** - Presentation Layer
Përmban REST API controllers dhe main Spring Boot application.

**Komponenetet:**
- `StudentManagementApplication` - Main class
- `StudentController` - REST endpoints
- `GlobalExceptionHandler` - Centralized error handling
- `application.properties` - Konfigurimi

**Controller example:**
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

## Teknologjitë dhe Libraritë

### 1. Lombok
**Versioni:** 1.18.30

**Annotacionet e përdorura:**
- `@Data` - Gjeneron getters, setters, toString, equals, hashCode
- `@Getter/@Setter` - Gjeneron getter/setter për fields
- `@NoArgsConstructor/@AllArgsConstructor` - Gjeneron konstruktorë
- `@Builder` - Implementon Builder pattern
- `@RequiredArgsConstructor` - Gjeneron konstruktor për final fields
- `@Slf4j` - Injekton Logger instance

**Konfigurimi në Maven:**
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
    <scope>provided</scope>
</dependency>
```

### 2. MapStruct
**Versioni:** 1.5.5.Final

**Konfigurimi:**
```xml
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.5.5.Final</version>
</dependency>
```

**Annotation Processor:**
```xml
<annotationProcessorPaths>
    <path>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct-processor</artifactId>
        <version>1.5.5.Final</version>
    </path>
</annotationProcessorPaths>
```

### 3. Apache POI
**Versioni:** 5.2.5

**Dependencies:**
```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.5</version>
</dependency>
```

**Features të përdorura:**
- `XSSFWorkbook` - Për .xlsx files
- `Sheet`, `Row`, `Cell` - Për strukturën e Excel
- `CellStyle`, `Font` - Për styling

### 4. Spring Boot
**Versioni:** 3.2.0

**Modules:**
- `spring-boot-starter-web` - REST API
- `spring-boot-starter-data-jpa` - Database access
- `spring-boot-starter-validation` - Input validation
- `spring-boot-starter-test` - Testing framework

### 5. H2 Database
**In-memory database** për development dhe testing.

**Konfigurimi:**
```properties
spring.datasource.url=jdbc:h2:mem:studentdb
spring.h2.console.enabled=true
```

## CI/CD Pipeline me Jenkins

### Struktura e Pipeline

#### Stage 1: Checkout
```groovy
stage('Checkout') {
    steps {
        checkout scm
    }
}
```
Merr kodin nga Git repository.

#### Stage 2: Build
```groovy
stage('Build') {
    steps {
        sh 'mvn clean install -DskipTests'
    }
}
```
Kompajlon të gjithë projektin dhe install-on në local Maven repository.

#### Stage 3: Unit Tests
```groovy
stage('Unit Tests') {
    steps {
        sh 'mvn test'
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
```
Ekzekuton unit tests dhe publikon rezultatet.

#### Stage 4: Package
```groovy
stage('Package') {
    steps {
        sh 'mvn package -DskipTests'
    }
}
```
Krijon JAR executable.

#### Stage 5: Deploy to OpenShift
Deployron aplikacionin në OpenShift cluster.

### Konfigurimi i Jenkins Job

1. **New Item** → Pipeline
2. **Pipeline script from SCM** → Git
3. **Repository URL** → URL e projektit
4. **Script Path** → Jenkinsfile
5. **Save** dhe **Build Now**

## Deployment në OpenShift

### 1. Dockerfile (Multi-stage Build)

**Stage 1: Build**
```dockerfile
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
```

**Stage 2: Runtime**
```dockerfile
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /app/web/target/web-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Avantazhet e Multi-stage:**
- Image më i vogël (JRE në vend të JDK)
- Security më i mirë (nuk përfshin build tools)
- Layer caching më i mirë

### 2. OpenShift Resources

#### Deployment
- **Replicas:** 2 (për high availability)
- **Resources:** 256Mi-512Mi memory, 250m-500m CPU
- **Health checks:** Liveness dhe Readiness probes

#### Service
- **Type:** ClusterIP
- **Port:** 8080

#### Route
- **TLS:** Edge termination
- **Insecure traffic:** Redirect to HTTPS

### 3. Komanda për Deploy

```bash
# Login
oc login --token=<token> --server=<server>

# Create project
oc new-project student-management

# Deploy
oc apply -f openshift/secret.yaml
oc apply -f openshift/configmap.yaml
oc apply -f openshift/deployment.yaml

# Check status
oc get pods
oc get routes
```

## Testimi

### Unit Tests

**Framework:** JUnit 5 + Mockito

**Test coverage:**
- StudentService - 10 test cases
- Edge cases (exceptions, validations)
- Mocking dependencies

**Ekzekutimi:**
```bash
mvn test
```

### Integration Tests (Optional)

Test i plot i flow nga controller → service → repository.

**Tool:** MockMvc + TestContainers

## API Documentation

### Base URL
```
Local: http://localhost:8080
OpenShift: https://<route-url>
```

### Endpoints Summary

| Method | Endpoint | Përshkrimi |
|--------|----------|-----------|
| POST | `/api/students` | Krijo student |
| GET | `/api/students` | Lista e të gjithëve |
| GET | `/api/students/{id}` | Merr sipas ID |
| PUT | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Fshi student |
| GET | `/api/students/export/excel` | Eksporto në Excel |

### Postman Collection

Importo `postman_collection.json` për të testuar të gjitha endpoints.

## Monitorimi dhe Logging

### Logging Strategy

**Framework:** SLF4J + Logback

**Levels:**
- `INFO` - Business operations
- `ERROR` - Exceptions dhe gabime
- `DEBUG` - Detailed information

**Example:**
```java
@Slf4j
public class StudentService {
    public StudentDTO createStudent(...) {
        log.info("Krijimi i studentit: {}", email);
        // ...
        log.info("Studenti u krijua me sukses: {}", id);
    }
}
```

### Health Checks (për OpenShift)

```properties
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always
```

## Best Practices të Përdorura

### 1. Clean Code
- Emra të qartë dhe descriptive
- Single Responsibility Principle
- Comentime vetëm kur është e nevojshme

### 2. Security
- Input validation me Bean Validation
- Exception handling i centralizuar
- Nuk ekspozon stack traces në production

### 3. Performance
- Lazy loading në JPA
- Connection pooling
- Pagination (për implementim të ardhshëm)

### 4. Maintainability
- Modular architecture
- Dependency Injection
- Configuration externalization

## Përmirësime të Ardhshme

1. **Pagination** - Për lista të mëdha studentësh
2. **Filtering** - Më shumë opsione filtrimi
3. **Authentication/Authorization** - Spring Security
4. **Real Database** - PostgreSQL/MySQL
5. **Caching** - Redis për performance
6. **API Documentation** - Swagger/OpenAPI
7. **Monitoring** - Prometheus + Grafana

## Konkluzion

Projekti Student Management System demonstron:
- ✅ Arkitekturë të pastër dhe modulare
- ✅ Përdorim të teknologjive moderne
- ✅ Best practices në software development
- ✅ CI/CD automation
- ✅ Cloud-ready deployment

---

**Zhvilluar për Detyrën e Kursit**  
**Data:** Maj 2026
