package wang.willard.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.willard.auth.service.UserService;

@RestController
public class RootController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Object main(){
        return userService.find(1L);
    }
}
