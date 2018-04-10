package wang.willard.boot.mapper;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import wang.willard.boot.bean.VendorBusinessCashDeposit;

import java.util.List;
import java.util.Map;

@Repository
public interface VendorBusinessCashDepositMapper {

    VendorBusinessCashDeposit selectByPrimaryKey(String id);

    void insertBatch(List<VendorBusinessCashDeposit> vendorBusinessCashDeposits);
}
