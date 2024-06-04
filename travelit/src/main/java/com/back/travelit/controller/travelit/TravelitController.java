package com.back.travelit.controller.travelit;

import com.back.travelit.security.LoginUser;
import com.back.travelit.security.dto.UserDTO;
import com.back.travelit.service.planner.PlanService;
import com.back.travelit.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TravelitController {

    private final PlanService planService;
    private final ProductService productService;

    //내 플래너 리스트
    @GetMapping("/travelit")
    public String mainList(@LoginUser UserDTO userDTO, Model model){

        if(userDTO != null) {
            model.addAttribute("myPlanList", planService.getMyPlanList(userDTO.getUserId()));
        }
        //지역정보 리스트(조회수순 6개)
        model.addAttribute("locList",planService.selectLocList());
        //상품정보 리스트(조회수순 8개)
        model.addAttribute("productList",productService.selectProductList());
        return "planner/mainList";
    }
}
