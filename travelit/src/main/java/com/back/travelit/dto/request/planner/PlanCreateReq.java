package com.back.travelit.dto.request.planner;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class PlanCreateReq {

    @Setter
    private int planId;

    @NotBlank(message = "제목은 필수입니다.")
    @Size(min = 2, max = 40, message = "제목의 길이는 2~40 글자 사이어야 합니다.")
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate  endDate;

    private String LocCode;

    public PlanCreateReq(String title, LocalDate startDate, LocalDate endDate, String LocCode) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.LocCode = LocCode;
    }


}
