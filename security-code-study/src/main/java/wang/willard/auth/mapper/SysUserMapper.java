package wang.willard.auth.mapper;

import org.springframework.stereotype.Repository;
import wang.willard.auth.entity.SysUser;

@Repository
public interface SysUserMapper {
    SysUser selectByPrimaryKey(Long id);
    int insert(SysUser sysUser);
    SysUser findByName(String name);
    SysUser findRoleInfoByName(String name);
}
