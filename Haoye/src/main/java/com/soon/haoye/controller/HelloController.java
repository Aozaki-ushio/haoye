package com.soon.haoye.controller;

import com.soon.haoye.service.ServiceOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class HelloController {

    @Autowired
    ServiceOne serone;

    @RequestMapping(value = "/RoleQuery", produces = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<Map> selectData(HttpServletRequest request, @RequestBody Map<String, List<String>> body) {
        List<String> list = body.get("X");
        Map data=new HashMap();
        data.put("list",serone.FindNumerByCode(list));
        return new ResponseEntity(data, HttpStatus.OK);
    }


}