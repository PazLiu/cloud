package com.springcloud.client.clientboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
public class SpringBootController {

    @Value("${server.port}")
    String port;

    @GetMapping("hi")
    public String home(@RequestParam String name) {
        return "hi:==== "+name+",i am from port:" + port;
    }

    @RequestMapping(value = "/gethi",method = RequestMethod.GET)
    public String getHi(@RequestParam String name) {
        return "gethi:==== "+name+",i am from port:" + port;
    }
}