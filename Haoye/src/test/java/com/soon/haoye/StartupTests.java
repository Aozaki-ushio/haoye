package com.soon.haoye;

import com.soon.haoye.service.ServiceOne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StartupTests {

    @Autowired
    ServiceOne serviceOne;

    @Test
    void contextLoads() {
        List<String> str = new ArrayList<>();
        str.add("301");
        str.add("401");
        List list1 = serviceOne.FindNumerByCode(str);
        int int1 = 0;

    }

}
