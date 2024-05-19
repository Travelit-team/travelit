package com.back.travelit.controller;

import com.back.travelit.domain.test.Test;
import com.back.travelit.dto.test.request.TestRequest;
import com.back.travelit.dto.test.response.TestResponse;
import com.back.travelit.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @PostMapping("/add")
    public String addUser(@RequestBody TestRequest request) {
        testMapper.insertUser(request);
        return "User added successfully!";
    }

    @GetMapping("/{id}")
    public TestResponse getUserById(@PathVariable("id") Long id) {
        Test test = testMapper.getUserById(id);
        return TestResponse.builder().email(test.getEmail()).username(test.getUsername()).build();
    }

}
