package web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ServiceTest {

    @RequestMapping(value = "/webTest",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String get(@RequestParam(value = "keyword",required = true) String[] keyword)
    {
        System.err.println(keyword.length);
        System.err.println(new String(keyword.toString()));
        System.err.println("This is a test");
        return "Hello ,this is a test!";
    }
}