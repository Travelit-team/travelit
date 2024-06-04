package com.back.travelit.controller.product;

import com.back.travelit.dto.request.product.ReservationMessage;
import com.back.travelit.dto.request.product.ReservationRequest;
import com.back.travelit.dto.response.product.ReservationResponse;
import com.back.travelit.security.LoginUser;
import com.back.travelit.security.dto.UserDTO;
import com.back.travelit.service.product.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    //예약 목록
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/product/reservationList")
    public String reservationList(@LoginUser UserDTO user, Model model) {
        int USER_ID = user.getUserId();
        List<ReservationResponse> response = reservationService.findAllRes(USER_ID);
        List<ReservationResponse> fiveProduct = reservationService.findByRand();
        model.addAttribute("response", response);
        model.addAttribute("fiveProduct", fiveProduct);
        return "product/reservationList";
    }

    //예약상세 페이지
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/product/reservationView")
    public String reservationView(@RequestParam final int RES_ID, Model model) {
        ReservationResponse response = reservationService.findByResId(RES_ID);
        model.addAttribute("response", response);
        return "product/reservationView";
    }

    //예약 입력 페이지
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/product/reservationWrite")
    public String reservationWrite(@RequestParam(value = "RES_ID", required = false) final Integer RES_ID, @RequestParam("PRO_ID") int PRO_ID, Model model) {
        if (RES_ID != null) {
            ReservationResponse reservation = reservationService.findByResId(RES_ID);
            model.addAttribute("reservation", reservation);
        }
        //상품번호를 가져와 model에 추가
        model.addAttribute("PRO_ID", PRO_ID);
        return "product/reservationWrite";
    }

    //예약 입력
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/product/reservationSave")
    public String saveReservation(@LoginUser UserDTO user, final ReservationRequest params, Model model) {
        int USER_ID = user.getUserId();
        params.setUSER_ID(USER_ID);
        reservationService.saveRes(params);
        model.addAttribute("USER_ID", USER_ID);
        model.addAttribute("params", params);
        return "redirect:/product/reservationList";
    }

    //예약 삭제
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/product/reservationDelete")
    public String deleteReservation(@RequestParam final Integer RES_ID, Model model) {
        reservationService.deleteRes(RES_ID);
        ReservationMessage message = new ReservationMessage("예약 취소가 완료되었습니다.", "/product/reservationList", RequestMethod.GET, null);
        return AlertMessage(message, model);
    }

    //알림창
    private String AlertMessage(final ReservationMessage params, Model model) {
        model.addAttribute("params", params);
        return "product/alertMessage";
    }

}
