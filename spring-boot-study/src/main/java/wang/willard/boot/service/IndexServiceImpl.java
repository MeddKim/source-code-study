package wang.willard.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.willard.boot.bean.VendorBusinessCashDeposit;
import wang.willard.boot.mapper.VendorBusinessCashDepositMapper;

@Service
public class IndexServiceImpl {

    @Autowired
    private VendorBusinessCashDepositMapper vendorBusinessCashDepositMapper;

    public VendorBusinessCashDeposit findById(String id){
        return vendorBusinessCashDepositMapper.selectByPrimaryKey(id);
    }
}
