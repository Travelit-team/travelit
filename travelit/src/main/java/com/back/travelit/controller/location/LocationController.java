package com.back.travelit.controller.location;

import com.back.travelit.dto.response.location.LocationCode;
import com.back.travelit.dto.request.location.LocationWriteRequest;
import com.back.travelit.service.location.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("writeRequest", new LocationWriteRequest());
        return "location/write";
    }

    @GetMapping("/locationCodes")
    @ResponseBody
    public List<LocationCode> locationCodes() {
        return locationService.getLocationCodes();
    }

    @PostMapping("/write")
    public String write(@Valid @ModelAttribute("writeRequest") LocationWriteRequest writeRequest, BindingResult bindingResult) {
        log.info("request {}", writeRequest);

        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return "/location/write";
        }

        locationService.saveLocationInfo(writeRequest);

        return "redirect:/location/detail/1";
    }

    @GetMapping("/detail/{location_id}")
    public String detail(@PathVariable Long location_id, Model model) {
        return "location/detail";
    }

}
