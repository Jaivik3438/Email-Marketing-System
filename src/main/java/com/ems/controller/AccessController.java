package com.ems.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController()
public class AccessController {


        @GetMapping("/access")
        public String index() {
            return "all Access";
        }



}
