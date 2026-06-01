-- Sample data për testim të aplikacionit
-- Mund të ekzekutohet në H2 Console: http://localhost:8080/h2-console

-- Insert sample students
INSERT INTO students (first_name, last_name, email, date_of_birth, department, student_id, phone_number, address, status, enrollment_year) 
VALUES 
('Arben', 'Shala', 'arben.shala@student.ubt.edu.al', '2000-05-15', 'Informatikë', 'ST2024001', '0691234567', 'Rr. Nënë Tereza, Prishtinë', 'ACTIVE', 2024),
('Blerta', 'Gashi', 'blerta.gashi@student.ubt.edu.al', '2001-08-22', 'Informatikë', 'ST2024002', '0697654321', 'Rr. UCK, Prishtinë', 'ACTIVE', 2024),
('Drilon', 'Hoxha', 'drilon.hoxha@student.ubt.edu.al', '1999-12-10', 'Inxhinieri Elektrike', 'ST2023015', '0693456789', 'Rr. Pejton, Prishtinë', 'ACTIVE', 2023),
('Erjona', 'Krasniqi', 'erjona.krasniqi@student.ubt.edu.al', '2002-03-18', 'Menaxhment Biznesi', 'ST2024030', '0694567890', 'Rr. Zahir Pajaziti, Prizren', 'ACTIVE', 2024),
('Fitim', 'Berisha', 'fitim.berisha@student.ubt.edu.al', '2000-07-05', 'Arkitekturë', 'ST2023042', '0695678901', 'Rr. 28 Nëntori, Gjilan', 'INACTIVE', 2023),
('Gresa', 'Mustafa', 'gresa.mustafa@student.ubt.edu.al', '1998-11-30', 'Mjekësi', 'ST2021008', '0696789012', 'Rr. Muharrem Fejza, Pejë', 'GRADUATED', 2021),
('Hana', 'Bytyqi', 'hana.bytyqi@student.ubt.edu.al', '2001-04-25', 'Informatikë', 'ST2023055', '0697890123', 'Rr. Mbreti Agron, Ferizaj', 'ACTIVE', 2023),
('Ilir', 'Mahmuti', 'ilir.mahmuti@student.ubt.edu.al', '2000-09-14', 'Inxhinieri Mekanike', 'ST2024012', '0698901234', 'Rr. Skënderbeu, Mitrovicë', 'ACTIVE', 2024),
('Jetmira', 'Halili', 'jetmira.halili@student.ubt.edu.al', '2002-01-20', 'Psikologji', 'ST2024025', '0699012345', 'Rr. Adem Jashari, Prishtinë', 'ACTIVE', 2024),
('Kushtrim', 'Sejdiu', 'kushtrim.sejdiu@student.ubt.edu.al', '1999-06-08', 'Informatikë', 'ST2022018', '0691123456', 'Rr. Bill Clinton, Prishtinë', 'SUSPENDED', 2022);

-- Verifiko data
SELECT * FROM students;

-- Count by department
SELECT department, COUNT(*) as total 
FROM students 
GROUP BY department 
ORDER BY total DESC;

-- Count by status
SELECT status, COUNT(*) as total 
FROM students 
GROUP BY status;

-- Active students per year
SELECT enrollment_year, COUNT(*) as total 
FROM students 
WHERE status = 'ACTIVE' 
GROUP BY enrollment_year 
ORDER BY enrollment_year DESC;
