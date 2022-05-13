package cmcglobal.exambook.service.impl;

import cmcglobal.exambook.common.AbstracClassCommon;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.List;


public class FileService {

    private static FileService instance;

    private FileService() {
    }

    public static FileService getInstance(){
        if(instance==null){
            return new FileService();
        }
        else return instance;
    }


    public static void writeFile(List<? extends AbstracClassCommon>  objects) {
       //create workBook
        Workbook newBook= new XSSFWorkbook();
        //create sheetbook
        Sheet sheet = newBook.createSheet();

        //writeHeader
        String[] headerContext = getHeaderContent(objects.get(0));
        writeHeader(sheet, 1,headerContext);

        //writeData
        String[][] stringDatas = changToArray(objects);
        writeData(sheet,2,stringDatas);


        //writeFooter
        writeFooter(sheet,1000);

        //Output File
        createOutputFile(newBook);

    }


    private static void writeHeader(Sheet sheet , int rowIndex, String[] headerContext) {
        //B2.1 create cellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        //B2.2 write row
        Row row = sheet.createRow(rowIndex);

        //B2.3 write header cells  with doi tuong array
        writeHeaderContent(row,cellStyle,headerContext );


    }


    //B2.1 write Style For Header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 16); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    //B2.3 write header cells  with doi tuong array
    private static void writeHeaderContent(Row row, CellStyle cellStyle , String[] headerStrings) {
        for (int i=0; i<headerStrings.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(headerStrings[i]);
        }
    }


    //B3. writeData
    private static void writeData(Sheet sheet, int index, String[][] dataStrings ) {

        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(false);
        font.setFontHeightInPoints((short) 12); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);


        for(int i=0; i< dataStrings.length; i++) {
            Row idataRow = sheet.createRow(index+i+1);
            for(int j= 0;j<dataStrings[0].length; j++) {
                Cell ijcell	= idataRow.createCell(j);
                ijcell.setCellStyle(cellStyle);
                ijcell.setCellValue(dataStrings[i][j]);
            }
        }


    }


    // Write footer
    private static void writeFooter(Sheet sheet, int rowIndex) {
        // Create row
//        Row row = sheet.createRow(rowIndex);
//        Cell cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
//        cell.setCellFormula("SUM(E2:E6)");
    }

    // Create output file
    private  static void createOutputFile(Workbook workbook) {
        String nameFile = LocalDate.now().toString();
        String pathFile= ("D:/IDE/project/exambook/fileExcel/"+nameFile+".xlsx");

        File file = new File(pathFile);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            workbook.write(os);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                os.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }



    }








    private static String[][] changToArray(List<? extends AbstracClassCommon> list){

        int row = list.size();
        AbstracClassCommon element = list.get(0);

        String[] headerStrings =   element.getProperty();
        int column = headerStrings.length;


        String [][] resultStrings = new String[row][column];

        for(int i=0; i<row;i++) {
            AbstracClassCommon elment = list.get(i);
            for(int j=0; j<column; j++) {
                AbstracClassCommon e = list.get(i);
                resultStrings[i][j] = e.getValueOfObject()[j];
            }
        }

        return resultStrings;
    }

    private  static String[] getHeaderContent( AbstracClassCommon element) {
        return element.getProperty();

    }
}
