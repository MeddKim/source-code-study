package wang.willard.boot.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static List readExcel(File file){
        try {
            InputStream is = new FileInputStream(file.getAbsolutePath());
            Workbook wb = getWorkbook(file);
            //页签数量
            int sheet_size = wb.getNumberOfSheets();
            Sheet sheet = wb.getSheetAt(0);
            //获取最大行数
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            List<List<String>> sheeValues = new ArrayList<List<String>>();
            for (int rowNum = firstRowNum + 1 ; rowNum <= lastRowNum ; rowNum ++){
                Row row = sheet.getRow(rowNum);
                //最大列数目
                int startColNum  = row.getFirstCellNum();
                int lastColNum = row.getLastCellNum();
                List<String> colValues = new ArrayList<String>();
                for(int colNum  = startColNum; colNum < lastColNum; colNum ++){
                    Cell cell = row.getCell(colNum);
                    String cellValue = null;
                    if (cell != null) {
                        // 验证每一个单元格的类型
                        switch (cell.getCellTypeEnum()) {
                            case NUMERIC:
                                // 表格中返回的数字类型是科学计数法因此不能直接转换成字符串格式
                                cellValue = new BigDecimal(cell.getNumericCellValue()).toPlainString();
                                break;
                            case STRING:
                                cellValue = cell.getStringCellValue();
                                break;
                            case FORMULA:
                                cellValue = cell.getCellFormula();
                                break;
                            case BLANK:
                                cellValue = "";
                                break;
                            case BOOLEAN:
                                cellValue = Boolean.toString(cell.getBooleanCellValue());
                                break;
                            case ERROR:
                                cellValue = "ERROR";
                                break;
                            default:
                                cellValue = "UNDEFINE";
                        }
                    } else {
                        cellValue = "";
                    }
                    colValues.add(cellValue);
                }
                sheeValues.add(colValues);
            }
            return sheeValues;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Workbook getWorkbook(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

}
