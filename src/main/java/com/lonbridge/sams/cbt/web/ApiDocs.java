package com.lonbridge.sams.cbt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class ApiDocs
{
    @RequestMapping("/questions-api")
    public String apiDoc()
    {
        return "redirect:swagger-ui.html";
    }
}