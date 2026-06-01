# Udhëzime për Ekzekutimin e Projektit

## ✅ Hapat për të Testuar Projektin

### Hapi 1: Clone Repository
```bash
git clone <repository-url>
cd AppJira
```

### Hapi 2: Verifiko Parakushtet
```bash
# Kontrollo Java version (duhet të jetë 17+)
java -version

# Kontrollo Maven version
mvn -version
```

### Hapi 3: Build Projektin
```bash
# Build i të gjithë moduleve
mvn clean install

# Ose build pa tests (më shpejt)
mvn clean install -DskipTests
```

**Output i pritshëm:**
```
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  02:15 min
```

### Hapi 4: Run Aplikacionin
```bash
# Navigo në modulin web
cd web

# Start aplikacionin
mvn spring-boot:run
```

**Output i pritshëm:**
```
Started StudentManagementApplication in 8.234 seconds
```

### Hapi 5: Verifiko që Aplikacioni është Running
Hap browser dhe shko në:
```
http://localhost:8080
```

### Hapi 6: Testo API me Postman

#### Import Postman Collection
1. Hap Postman
2. File → Import
3. Zgjidh `postman_collection.json`
4. Click "Import"

#### Test Endpoints

**1. Create një student:**
```
POST http://localhost:8080/api/students
Body (JSON):
{
  "firstName": "Test",
  "lastName": "Student",
  "email": "test@example.com",
  "dateOfBirth": "2000-01-01",
  "department": "Informatikë",
  "studentId": "ST2024999",
  "phoneNumber": "0691111111",
  "address": "Test Address",
  "status": "ACTIVE",
  "enrollmentYear": 2024
}
```

**2. Get all students:**
```
GET http://localhost:8080/api/students
```

**3. Export to Excel:**
```
GET http://localhost:8080/api/students/export/excel
```

### Hapi 7: Shiko H2 Database Console

1. Hap browser: http://localhost:8080/h2-console
2. Konfigurimet:
   - JDBC URL: `jdbc:h2:mem:studentdb`
   - Username: `sa`
   - Password: (lëre bosh)
3. Click "Connect"
4. Run query: `SELECT * FROM students;`

### Hapi 8: Shto Sample Data (Opsionale)

Në H2 Console, ekzekuto scripts nga `sample-data.sql`:
```sql
INSERT INTO students (...) VALUES (...);
```

### Hapi 9: Run Unit Tests

```bash
# Run të gjitha tests
mvn test

# Run tests për një modul specifik
cd service
mvn test
```

**Output i pritshëm:**
```
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
```

### Hapi 10: Build Docker Image (Opsionale)

```bash
# Kthehu në root directory
cd ..

# Build image
docker build -t student-management:1.0.0 .

# Run container
docker run -p 8080:8080 student-management:1.0.0
```

---

## 🐛 Troubleshooting

### Problem: Port 8080 është në përdorim

**Zgjidhja 1:** Mbyll aplikacione të tjera që përdorin port 8080

**Zgjidhja 2:** Ndrysho portin në `web/src/main/resources/application.properties`:
```properties
server.port=8081
```

### Problem: Maven build dështon

**Shkaqe të mundshme:**
1. Java version jo i duhur (duhet Java 17+)
2. Maven dependencies nuk janë downloaded

**Zgjidhja:**
```bash
# Clean Maven repository lokal
mvn clean

# Force update dependencies
mvn clean install -U
```

### Problem: Lombok nuk funksionon në IDE

**Zgjidhja për IntelliJ IDEA:**
1. File → Settings → Plugins
2. Search "Lombok"
3. Install "Lombok Plugin"
4. Restart IDE

**Zgjidhja për Eclipse:**
1. Download lombok.jar
2. Run: `java -jar lombok.jar`
3. Select Eclipse installation
4. Install/Update

### Problem: MapStruct classes nuk gjenerohen

**Zgjidhja:**
```bash
# Clean dhe rebuild
mvn clean compile

# Kontrollo në target/generated-sources/annotations
```

---

## 📚 Resurse të Tjera

### Dokumentacion:
- [README.md](README.md) - Overview i projektit
- [DOKUMENTACION_TEKNIK.md](DOKUMENTACION_TEKNIK.md) - Detaje teknike
- [JIRA.md](JIRA.md) - Jira planning guide
- [openshift/DEPLOY.md](openshift/DEPLOY.md) - OpenShift deployment

### API Testing:
- Postman Collection: `postman_collection.json`
- Sample Data: `sample-data.sql`

---

## ❓ Pyetje të Shpeshta

**Q: Si të ndaloj aplikacionin?**  
A: Press `Ctrl+C` në terminal ku po ekzekutohet `mvn spring-boot:run`

**Q: Si të fshij të dhënat e databazës?**  
A: H2 database është in-memory, kështu që të dhënat fshihen kur mbyll aplikacionin.

**Q: Si të shoh logs?**  
A: Logs shfaqen në terminal. Për më shumë detaje, shto në application.properties:
```properties
logging.level.com.studentmanagement=DEBUG
```

**Q: A mund të përdor MySQL në vend të H2?**  
A: Po! Shto MySQL dependency në `web/pom.xml` dhe ndrysho `application.properties`.

---

**Për çdo problem tjetër, hap një issue në GitHub! 🚀**
