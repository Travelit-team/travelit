package com.back.travelit.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ReservationMessage {
    private String message;
    private String redirectUri; //리다이렉트 URI
    private RequestMethod method;
    private Map<String, Object> data;
}
