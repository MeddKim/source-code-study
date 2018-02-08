package wang.willard.auth.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.willard.auth.entity.SysUser;
import wang.willard.auth.mapper.SysUserMapper;
import wang.willard.auth.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser find(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
}
