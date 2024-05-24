package com.back.travelit.common.annotation;

import com.back.travelit.common.validator.ImageFileValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageFileValidator.class)
public @interface ValidImageFile {

    String message() default "선택하지 않은 파일 데이터가 존재합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
