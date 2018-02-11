package wang.willard.auth.service;

import wang.willard.auth.entity.SysUser;

public interface IUserService {
    SysUser find(Long id);
    int registerUser(SysUser sysUser);

}
