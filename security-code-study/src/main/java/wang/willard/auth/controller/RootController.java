package wang.willard.auth.controller;


import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wang.willard.auth.entity.SysUser;
import wang.willard.auth.service.IPasswordService;
import wang.willard.auth.service.IUserService;

@RestController
public class RootController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IPasswordService passwordService;

    @GetMapping("/user/add")
    public Object addUser(@RequestParam String username,
                          @RequestParam String password){
        if(Strings.isBlank(username) || Strings.isBlank(password)){
            return "用户名或密码不能为空";
        }
        userService.registerUser(SysUser.builder()
                .username(username)
                .password(passwordService.generateBCryptPassword(password))
                .build());

        return "创建成功";
    }
}
