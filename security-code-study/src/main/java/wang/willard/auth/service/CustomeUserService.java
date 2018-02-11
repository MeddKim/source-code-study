package wang.willard.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import wang.willard.auth.entity.SysUser;
import wang.willard.auth.mapper.SysUserMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomeUserService implements UserDetailsService{

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = userMapper.findRoleInfoByName(username);
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        user.getRoles().forEach(sysRole -> {
            authorityList.add(new SimpleGrantedAuthority(sysRole.getRole()));
        });

        return new User(user.getUsername(),user.getPassword(),authorityList);
    }
}
