package com.back.travelit.controller.product;

import com.back.travelit.dto.request.product.ReservationMessage;
import com.back.travelit.dto.request.product.ReservationRequest;
import com.back.travelit.dto.response.product.ReservationResponse;
import com.back.travelit.service.product.ReservationService;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("/product/reservationList")
    public String reservationList(Model model) {
        List<ReservationResponse> response = reservationService.findAllRes();
        model.addAttribute("response", response);
        return "product/reservationList";
    }

    //예약상세 페이지
    @GetMapping("/product/reservationView")
    public String reservationView(@RequestParam final int RES_ID, Model model) {
        ReservationResponse response = reservationService.findByResId(RES_ID);
        model.addAttribute("response", response);
        System.out.println(model);
        System.out.println(response);
        return "product/reservationView";
    }

    //예약 입력 페이지
    @GetMapping("/product/reservationWrite")
    public String reservationWrite(@RequestParam(value = "RES_ID", required = false) final Integer RES_ID, @RequestParam("PRO_ID") int PRO_ID, Model model) {

        if (RES_ID != null) {
            ReservationResponse reservation = reservationService.findByResId(RES_ID);
            model.addAttribute("reservation", reservation);
            System.out.println("Reservation"+reservation);
            System.out.println("model"+model);
        }
        //상품번호를 가져와 model에 추가
        model.addAttribute("PRO_ID", PRO_ID);
        System.out.println(model.toString());
        return "product/reservationWrite";
    }

    //예약 입력
    @PostMapping("/product/reservationSave")
    public String saveReservation(final ReservationRequest params) {
        reservationService.saveRes(params);
        return "redirect:/product/reservationList";
    }

    //게시글 삭제
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
