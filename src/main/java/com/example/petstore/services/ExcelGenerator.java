package com.example.petstore.services;

import com.example.petstore.models.directorModels.DirectorViewModel;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ExcelGenerator {
    private List<DirectorViewModel> directors;

    public ExcelGenerator(List <DirectorViewModel> directors) {
        this.directors = directors;
    }

    public void generate(HttpServletResponse response) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        setTitles(sheet);

        int rowCount = 0;

        for (DirectorViewModel director : directors) {
            Row row = sheet.createRow(++rowCount);
            writeDirector(director, row);
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
    }
    private void writeDirector(DirectorViewModel director, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(director.getId().toString());

        cell = row.createCell(1);
        cell.setCellValue(director.getName());

        cell = row.createCell(2);
        cell.setCellValue(director.getSalary());

        cell = row.createCell(3);
        cell.setCellValue(director.getGender().toString());

        cell = row.createCell(4);
        cell.setCellValue(director.getPetStoreName());
    }

    private void setTitles(Sheet sheet){
        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellValue("Id");

        cell = row.createCell(1);
        cell.setCellValue("Name");

        cell = row.createCell(2);
        cell.setCellValue("Salary");

        cell = row.createCell(3);
        cell.setCellValue("Gender");

        cell = row.createCell(4);
        cell.setCellValue("Pet store name");
    }
}
