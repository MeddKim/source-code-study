package wang.willard.source;

import org.junit.Test;
import wang.willard.boot.bean.VendorBusinessCashDeposit;
import wang.willard.boot.utils.ExcelUtils;

import java.io.File;
import java.util.List;

public class ExcelTest {
    @Test
    public void test(){
        File file = new File("D:\\cash.xlsx");
//        List<List<String>> lists = ExcelUtils.readExcel(file);
//        System.out.println(lists.size());
//        List<VendorBusinessCashDeposit> list = ExcelUtils.readObjExcel(file);

        ExcelUtils.createSql();

    }
}
