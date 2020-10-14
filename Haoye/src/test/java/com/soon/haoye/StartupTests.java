package com.soon.haoye;

import com.soon.haoye.service.ServiceOne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class StartupTests {

    @Autowired
    ServiceOne serviceOne;

    @Test
    void contextLoads() {
//        List<String> str = new ArrayList<>();
//        str.add("301");
//        str.add("401");
//        List list1 = serviceOne.FindNumerByCode(str);
//        int int1 = 0;
        for(int i=0 ;i<=5 ;i++){
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(uuid);
        }

    }

}
