package wang.willard.source;

import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.willard.boot.bean.VendorBusinessCashDeposit;
import wang.willard.boot.mapper.VendorBusinessCashDepositMapper;
import wang.willard.boot.utils.ExcelUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = wang.willard.boot.BootApplication.class)
public class BootApplicationTest {

    @Autowired
    private VendorBusinessCashDepositMapper vendorBusinessCashDepositMapper;

    @Test
    public void test(){
        Map<String,Object> paramMap = new HashMap<>();
//        List<VendorBusinessCashDeposit>  cashDeposits = vendorBusinessCashDepositMapper.findByParams(paramMap);
//        VendorBusinessCashDeposit cashDeposit = vendorBusinessCashDepositMapper.selectByPrimaryKey("1");
//        File file = new File("D:\\cash.xlsx");
//        List<VendorBusinessCashDeposit> list = ExcelUtils.readObjExcel(file);
//        vendorBusinessCashDepositMapper.insertBatch(list);
//        ExcelUtils.createSql(list);
    }
}

