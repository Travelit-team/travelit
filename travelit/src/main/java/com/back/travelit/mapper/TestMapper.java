package com.back.travelit.mapper;

import com.back.travelit.domain.test.Test;
import com.back.travelit.dto.test.request.TestRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    void insertUser(TestRequest user);
    Test getUserById(Long id);
}
