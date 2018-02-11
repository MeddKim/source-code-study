package wang.willard.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wang.willard.auth.entity.SysUser;
import wang.willard.auth.mapper.SysUserMapper;
import wang.willard.auth.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {



    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser find(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int registerUser(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

}
