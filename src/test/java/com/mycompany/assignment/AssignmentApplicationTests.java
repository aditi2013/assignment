package com.mycompany.assignment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EntityScan(basePackages = "com.mycompany.assignment.entity")
class AssignmentApplicationTests {

    @Test
    void contextLoads() {
    }

}
