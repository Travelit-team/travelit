package com.back.travelit.common.validator;

import com.back.travelit.common.annotation.ValidImageFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ImageFileValidator implements ConstraintValidator<ValidImageFile, List<MultipartFile>> {
    @Override
    public boolean isValid(List<MultipartFile> files, ConstraintValidatorContext constraintValidatorContext) {
        if (files == null || files.isEmpty()) {
            return false;
        }

        //파일명이 존재하지 않는 파일 데이터가 넘어오는 경우 false
        for (MultipartFile file : files) {
            if (!StringUtils.hasText(file.getOriginalFilename())) {
                return false;
            }
        }

        return true;
    }
}
