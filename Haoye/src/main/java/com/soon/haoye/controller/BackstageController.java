package com.soon.haoye.controller;

import com.soon.haoye.entity.ProjectMahoushyojyoEntity;
import com.soon.haoye.service.ServiceOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/backstage")
public class BackstageController {

    @Autowired
    ServiceOne serone;

    @RequestMapping(value = "/showAll",produces = { "application/json" },method = RequestMethod.POST)
    public ResponseEntity<Map> showAll(HttpServletRequest request, @RequestBody Map body){
        int pageNum = (int) body.get("pageNum");
        int pageSize = (int) body.get("pageSize");
        String isSelled = body.get("isSelled").toString();
        String str = (String) body.get("number");
        if (str==""){
            Map data=new HashMap();
            data.put("list",serone.findAll(body.get("isSelled"),pageNum,pageSize));
            return new ResponseEntity(data, HttpStatus.OK);
        }
        return new ResponseEntity(serone.findObjByNumber(str), HttpStatus.OK);
    }


    @RequestMapping(value = "/accountNumberManage",produces = { "application/json" },method = RequestMethod.POST)
    public ResponseEntity<Map> accountNumberManage(HttpServletRequest request, @RequestBody Map<String,ProjectMahoushyojyoEntity> body){
        ProjectMahoushyojyoEntity pje = body.get("manage");
        if (pje.getUid()==null || String.valueOf(pje.getUid()).equals("")){
            serone.addAccountNumber(pje);
            return new ResponseEntity("添加成功", HttpStatus.OK);
        }else {
            if (serone.modifyAccountMumer(pje)==null){
                return new ResponseEntity("修改失败", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            }
            return new ResponseEntity("修改完成", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/deleteAccountNumber",produces = { "application/json" },method = RequestMethod.POST)
    public ResponseEntity<Map> deleteAccountNumber(HttpServletRequest request, @RequestBody Map<String,Integer> body){
        int uid = body.get("delete");
        return new ResponseEntity(serone.deleteAccountNumberByUid(uid), HttpStatus.OK);
    }

}
