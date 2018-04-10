package wang.willard.boot.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import wang.willard.boot.bean.VendorBusinessCashDeposit;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public static List<VendorBusinessCashDeposit> readObjExcel(File file){
        try {
            InputStream is = new FileInputStream(file.getAbsolutePath());
            Workbook wb = getWorkbook(file);
            //页签数量
            int sheet_size = wb.getNumberOfSheets();
            Sheet sheet = wb.getSheetAt(0);
            //获取最大行数
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            List<VendorBusinessCashDeposit> sheeValues = new ArrayList<VendorBusinessCashDeposit>();
            for (int rowNum = firstRowNum + 1 ; rowNum <= lastRowNum ; rowNum ++){
                Row row = sheet.getRow(rowNum);
                //最大列数目
                int startColNum  = row.getFirstCellNum();
                int lastColNum = row.getLastCellNum();
                VendorBusinessCashDeposit cashDeposit = new VendorBusinessCashDeposit();
                for(int colNum  = startColNum; colNum < lastColNum; colNum ++){
                    Cell cell = row.getCell(colNum);
                    if(colNum == 0){
                        cashDeposit.setVendorCompanyId(cell.getStringCellValue());
                    }
                    if(colNum == 1){

                    }
                    if(colNum == 2){
                        cashDeposit.setProposer(cell.getStringCellValue());
                        cashDeposit.setAuditor(cell.getStringCellValue());
                    }
                    if(colNum == 3){
                        cashDeposit.setCreateAt(new Timestamp(cell.getDateCellValue().getTime()));
                        cashDeposit.setAuditTime(new Timestamp(cell.getDateCellValue().getTime()));
                    }
                    if(colNum == 4){
                        cashDeposit.setReason(cell.getStringCellValue());
                    }
                    if(colNum == 5){
                        cashDeposit.setAmount(new BigDecimal(cell.getNumericCellValue()));
                        cashDeposit.setPreAmount(new BigDecimal(cell.getNumericCellValue()));
                    }
                    if(colNum == 6){
                        cashDeposit.setType(cell.getNumericCellValue() > 0 ? 1 : 0);
                    }
                    cashDeposit.setStatus(1);
                    cashDeposit.setBusinessType(1);
                    cashDeposit.setIsDeleted(false);
                    cashDeposit.setId(UUID.randomUUID().toString());
                }
                sheeValues.add(cashDeposit);
//                System.out.println("第-----"+rowNum+"---行处理成功--");
            }
            return sheeValues;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void createSql(List<VendorBusinessCashDeposit> deposits){
        Map<String,List<VendorBusinessCashDeposit>> map = deposits.stream().collect(Collectors.groupingBy(VendorBusinessCashDeposit
        ::getVendorCompanyId));

        StringBuffer bf = new StringBuffer();
        map.forEach((key,items) -> {
            BigDecimal count = BigDecimal.ZERO;
            for(VendorBusinessCashDeposit item :items) {
                count = count.add(item.getAmount());
            }
            String sql = "UPDATE vendor_business_cash_count SET recommend_cash_count = recommend_cash_count + " + count.toString() +
                    " where vendor_company_id = '"+key+"'; \t\n";

            bf.append(sql);
        });

        try {
            FileWriter writer = new FileWriter("D:\\count.sql",true);
            writer.write(bf.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bf.toString());
    }
}
