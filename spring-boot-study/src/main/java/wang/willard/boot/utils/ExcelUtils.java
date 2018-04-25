package wang.willard.boot.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import wang.willard.boot.bean.VendorBusinessCashDeposit;

import javax.xml.crypto.Data;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
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

    public static List<Region> readObjExcel(File file){
        try {
            InputStream is = new FileInputStream(file.getAbsolutePath());
            Workbook wb = getWorkbook(file);
            //页签数量
            int sheet_size = wb.getNumberOfSheets();
            Sheet sheet = wb.getSheetAt(0);
            //获取最大行数
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            List<Region> sheeValues = new ArrayList<Region>();
            DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
            for (int rowNum = firstRowNum + 1 ; rowNum <= lastRowNum ; rowNum ++){
                Row row = sheet.getRow(rowNum);
                //最大列数目
                int startColNum  = row.getFirstCellNum();
                int lastColNum = row.getLastCellNum();
                Region region = new Region();
                for(int colNum  = startColNum; colNum < lastColNum; colNum ++){
                    Cell cell = row.getCell(colNum);
                    if(colNum == 3){
                        region.setCode(decimalFormat.format(cell.getNumericCellValue()));
                    }
                    if(colNum == 4){
                        region.setName(cell.getStringCellValue());
                    }
//                    if(colNum == 6){
//                        region.setCode(cell.getStringCellValue());
//                    }
                    region.setId(UUID.randomUUID().toString());
                }
                sheeValues.add(region);
//                System.out.println("第-----"+rowNum+"---行处理成功--");
            }
            return sheeValues;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void createSql(){
        File file = new File("D:\\kaifawenjian\\sprint31\\region.xlsx");
        List<Region> regions = readObjExcel(file);

//        map.forEach((key,items) -> {
//            BigDecimal count = BigDecimal.ZERO;
//            for(Region item :regions) {
//                count = count.add(item.getAmount());
//            }
//            String sql = "UPDATE vendor_business_cash_count SET recommend_cash_count = recommend_cash_count + " + count.toString() +
//                    " where vendor_company_id = '"+key+"'; \t\n";
//
//            bf.append(sql);
//        });
        StringBuffer createRegionBf = new StringBuffer();
        //将机构信息按照名称分类
        Map<String,List<Region>> regionNameMap = regions.stream().collect(Collectors.groupingBy(Region::getName));
        regionNameMap.forEach((key,value)->{
            String sql = "INSERT INTO insurance_direct_supply_institution_region(id,name) values ('"+value.get(0).getId()+"'" +
                    ",'"+key+"');\t\n";
            createRegionBf.append(sql);
        });
        try {
            FileWriter writer = new FileWriter("D:\\kaifawenjian\\sprint31\\test1.sql",true);
            writer.write(createRegionBf.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer updateRegionBf = new StringBuffer();
        regions.forEach(region -> {
            List<Region> regionGroup = regionNameMap.get(region.getName());
            String sql = "UPDATE insurance_direct_supply_institution SET region_id = '"+regionGroup.get(0).getId()+"' where institution_code='"+region.getCode()+"';\t\n";
            updateRegionBf.append(sql);
        });
        try {
            FileWriter writer = new FileWriter("D:\\kaifawenjian\\sprint31\\test2.sql",true);
            writer.write(updateRegionBf.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class Region{
        private String id;
        private String code;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
