package work.hdjava.sample.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class TestController {
    @RequestMapping("/a")
    public String a(){
        return "{'a':'1'}";
    }

}
