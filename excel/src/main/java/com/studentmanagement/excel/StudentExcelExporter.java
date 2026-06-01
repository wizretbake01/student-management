package com.studentmanagement.excel;

import com.studentmanagement.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class StudentExcelExporter {

    private static final String[] HEADERS = {
            "ID", "Emri", "Mbiemri", "Email", "Data e Lindjes",
            "Departamenti", "ID Studenti", "Telefoni", "Adresa", "Statusi", "Viti i Regjistrimit"
    };

    public byte[] exportStudentsToExcel(List<StudentDTO> students) throws IOException {
        log.info("Fillimi i eksportimit të {} studentëve në Excel", students.size());

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Studentët");

            // Krijo stilin për header
            CellStyle headerStyle = createHeaderStyle(workbook);

            // Krijo stilin për qelizat normale
            CellStyle dataStyle = createDataStyle(workbook);

            // Krijo header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < HEADERS.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(HEADERS[i]);
                cell.setCellStyle(headerStyle);
            }

            // Shto të dhënat e studentëve
            int rowNum = 1;
            for (StudentDTO student : students) {
                Row row = sheet.createRow(rowNum++);
                populateStudentRow(row, student, dataStyle);
            }

            // Auto-size columns
            for (int i = 0; i < HEADERS.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
            log.info("Eksportimi në Excel përfundoi me sukses");
            return outputStream.toByteArray();
        }
    }

    private void populateStudentRow(Row row, StudentDTO student, CellStyle dataStyle) {
        int cellNum = 0;

        createCell(row, cellNum++, student.getId() != null ? student.getId().toString() : "", dataStyle);
        createCell(row, cellNum++, student.getFirstName(), dataStyle);
        createCell(row, cellNum++, student.getLastName(), dataStyle);
        createCell(row, cellNum++, student.getEmail(), dataStyle);
        createCell(row, cellNum++, student.getDateOfBirth() != null ? student.getDateOfBirth().toString() : "", dataStyle);
        createCell(row, cellNum++, student.getDepartment(), dataStyle);
        createCell(row, cellNum++, student.getStudentId(), dataStyle);
        createCell(row, cellNum++, student.getPhoneNumber() != null ? student.getPhoneNumber() : "", dataStyle);
        createCell(row, cellNum++, student.getAddress() != null ? student.getAddress() : "", dataStyle);
        createCell(row, cellNum++, student.getStatus(), dataStyle);
        createCell(row, cellNum++, student.getEnrollmentYear() != null ? student.getEnrollmentYear().toString() : "", dataStyle);
    }

    private void createCell(Row row, int columnIndex, String value, CellStyle style) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);

        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        return style;
    }

    private CellStyle createDataStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }
}
