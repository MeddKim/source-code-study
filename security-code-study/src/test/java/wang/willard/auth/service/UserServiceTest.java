package wang.willard.auth.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.willard.auth.entity.SysUser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = wang.willard.auth.AuthApplication.class)
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Test
    public void findUserById(){
        SysUser user = userService.find(1L);
        System.out.println(user.getUsername());
    }
}
