package com.soon.haoye.controller;

import com.soon.haoye.service.ServiceOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/Backstage")
public class BackstageController {

    @Autowired
    ServiceOne serone;

}
