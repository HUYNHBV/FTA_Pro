package com.example.administrator.fta8;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class excelReader {

    Context context;
    String filePath;
    int columnsField;
    String [] [] databaseArr;
    String[][] headerarr;

    public excelReader(Context context, String filePath, int columnsField) {
        this.context = context;
        this.filePath = Environment.getExternalStorageDirectory().getPath() +"/" + filePath;
        this.columnsField = columnsField;
    }

    public String[][] getDatabaseArr() {
        return databaseArr;
    }

    public String [][] getHeaderarr(){
        return headerarr;
    }

    public void readExcelData() {
        Log.d("excelReader", "readExcelData: Reading Excel File.");

        //decarle input file
        File inputFile = new File(filePath);

        try {
            InputStream inputStream = new FileInputStream(inputFile);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);                                        // Create Workbook
            XSSFSheet sheet = workbook.getSheetAt(0);                                               // Get Worksheet
            int rowsCount = sheet.getPhysicalNumberOfRows();                                               // Row Count
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            Row row = sheet.getRow(0);
            int cellsCount = row.getPhysicalNumberOfCells();                                                // Columns

            databaseArr = new String[rowsCount - 1][columnsField]; // remove header
            headerarr = new  String[1][columnsField];

            //outter loop, loops through rows
            for (int r = 0; r < rowsCount; r++) {
                row = sheet.getRow(r);
                //Inner loop, loops through columns
                for (int c = 0; c < cellsCount; c++) {
                    //handles if there are to many columns on the excel sheet.
                    if(c>14){                                                                                     // 14 Columns
                        Log.e("excelReader", "readExcelData: ERROR. Excel File Format is incorrect! " );
                        break;
                    }else{
                        String value = getCellAsString(row, c, formulaEvaluator);
                        if (r == 0){
                            headerarr[0][c] = value;
                        }
                        else {
                            databaseArr[r-1][c] = value;
                        }
                    }
                }
            }
            Toast.makeText(context, "Load Data: Done!" , Toast.LENGTH_SHORT).show();

        }catch (FileNotFoundException e) {
            Log.e("excelReader", "readExcelData: FileNotFoundException. " + e.getMessage() );
        } catch (IOException e) {
            Log.e("excelReader", "readExcelData: Error reading inputstream. " + e.getMessage() );
        }
    }

    /**
     * Returns the cell as a string from the excel file
     * @param row
     * @param c
     * @param formulaEvaluator
     * @return
     */
    private String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue = cellValue.getNumberValue();
                    if(HSSFDateUtil.isCellDateFormatted(cell)) {
                        double date = cellValue.getNumberValue();
                        SimpleDateFormat formatter =
                                new SimpleDateFormat("MM/dd/yy");
                        value = formatter.format(HSSFDateUtil.getJavaDate(date));
                    } else {
                        value = ""+numericValue;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = ""+cellValue.getStringValue();
                    break;
                default:
            }
        } catch (NullPointerException e) {

            Log.e("", "getCellAsString: NullPointerException: " + e.getMessage() );
        }
        return value;
    }

}
