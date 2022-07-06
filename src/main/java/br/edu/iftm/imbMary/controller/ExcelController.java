package br.edu.iftm.imbMary.controller;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.edu.iftm.imbMary.domain.Visitor;
import br.edu.iftm.imbMary.repository.VisitorRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class ExcelController {

    @Autowired
    VisitorRepository visitorRepository;

    @GetMapping("/api/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=visitores_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Visitor> list = new ArrayList<>();
        visitorRepository.findAll().forEach(list::add);

        UserExcelExporter excelExporter = new UserExcelExporter(list);

        excelExporter.export(response);
    }

    class UserExcelExporter {
        private XSSFWorkbook workbook;
        private XSSFSheet sheet;
        private List<Visitor> listVisitores;

        public UserExcelExporter(List<Visitor> list) {
            this.listVisitores = list;
            workbook = new XSSFWorkbook();
        }

        private void writeHeaderLine() {
            sheet = workbook.createSheet("Visitores");

            Row row = sheet.createRow(0);

            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16);
            style.setFont(font);

            createCell(row, 0, "Nome", style);
            createCell(row, 1, "E-mail", style);
        }

        private void createCell(Row row, int columnCount, Object value, CellStyle style) {
            sheet.autoSizeColumn(columnCount);
            Cell cell = row.createCell(columnCount);
            if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            } else if (value instanceof Boolean) {
                cell.setCellValue((Boolean) value);
            } else {
                cell.setCellValue((String) value);
            }
            cell.setCellStyle(style);
        }

        private void writeDataLines() {
            int rowCount = 1;

            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setFontHeight(14);
            style.setFont(font);

            for (Visitor visitor : listVisitores) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;

                createCell(row, columnCount++, visitor.getNome(), style);
                createCell(row, columnCount++, visitor.getEmail(), style);
            }
        }

        public void export(HttpServletResponse response) throws IOException {
            writeHeaderLine();
            writeDataLines();

            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();

            outputStream.close();
        }

    }
}