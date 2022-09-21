package bat.ke.qq.com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gsd
 */
@RestController
public class IndexController {

    @Value("${common.age}")
    private String age;
    @Value("${common.name}")
    private String name;

    @GetMapping("/index")
    public String hello() {
        return name+","+age;
    }
    
    
    
}
