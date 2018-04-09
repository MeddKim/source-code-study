package wang.willard.mybatis.book.chapter1.mapper;

import org.apache.ibatis.session.RowBounds;
import wang.willard.mybatis.book.chapter1.pojo.VendorBusinessCashDeposit;

import java.util.List;
import java.util.Map;

public interface VendorBusinessCashDepositMapper {

    VendorBusinessCashDeposit selectByPrimaryKey(String id);

    VendorBusinessCashDeposit selectByTaskKey(String id);

    int insert(VendorBusinessCashDeposit vendorBusinessCashDeposit);

    int update(VendorBusinessCashDeposit vendorBusinessCashDeposit);

    List<VendorBusinessCashDeposit> findByParams(Map<String, Object> map);

    List<VendorBusinessCashDeposit> findByParams(Map<String,Object> map, RowBounds rowBounds);
}
