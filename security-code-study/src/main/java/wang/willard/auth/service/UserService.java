package wang.willard.auth.service;

import wang.willard.auth.entity.SysUser;

public interface UserService {
    SysUser find(Long id);
}
