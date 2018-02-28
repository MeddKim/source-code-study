package wang.willard.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GoController {

    @RequestMapping(value = {"/"},method = RequestMethod.HEAD)
    public String head(){
        return "go.jsp";
    }

    @RequestMapping(value = {"/index","/"},method = {RequestMethod.GET})
    public String index(Model model){
        model.addAttribute("msg","Go Go Go!");
        return "go.jsp";
    }
}
