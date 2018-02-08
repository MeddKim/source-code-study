package wang.willard.auth.mapper;

import org.springframework.stereotype.Repository;
import wang.willard.auth.entity.SysUser;

@Repository
public interface SysUserMapper {
    SysUser selectByPrimaryKey(Long id);
}
