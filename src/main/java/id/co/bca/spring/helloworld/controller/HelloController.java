package id.co.bca.spring.helloworld.controller;

import id.co.bca.spring.helloworld.config.Api;
import id.co.bca.spring.helloworld.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    Employee employee;

    @Autowired
    Api api;

    @Value("${spring.message}")
    private String message;

    @GetMapping("/hello")
    public @ResponseBody String hello() {
        return "Hello World!";
    }

    @GetMapping("/salary")
    public @ResponseBody String salary() {
        return employee.salary();
    }

    @GetMapping("/message")
    public @ResponseBody String message() {
        return message;
    }

    @GetMapping("/api")
    public @ResponseBody String api() {
        logger.info("I am in api method");
        return "Base URL" + " : " + api.getUrl() + "<br>" + "" + "Base URL2" + " : " + api.getUrl2() + "<br>" + "" + "Data Type" + " : " + api.getDataType();
    }
}
