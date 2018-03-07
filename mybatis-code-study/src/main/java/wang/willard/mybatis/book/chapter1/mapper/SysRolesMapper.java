package wang.willard.mybatis.book.chapter1.mapper;

import wang.willard.mybatis.book.chapter1.pojo.SysRoles;

import java.util.List;
import java.util.Map;

public interface SysRolesMapper {
    SysRoles getRole(Long id);
    List<SysRoles> findByParams(Map<String,Object> parmas);
}
