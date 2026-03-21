package com.onik.eduspring;

import com.onik.eduspring.controller.admin.AdminController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EduSpringApplicationTests {

    @Autowired
    private AdminController adminController;

    @Test
    void contextLoads() {
    }


}
