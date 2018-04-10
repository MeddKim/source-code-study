package wang.willard.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.willard.boot.service.IndexServiceImpl;

@RestController
public class indexController {

    @Autowired
    private IndexServiceImpl indexService;

    @RequestMapping("/index")
    public Object index(){
        indexService.findById("1");
        return "hello";
    }
}
