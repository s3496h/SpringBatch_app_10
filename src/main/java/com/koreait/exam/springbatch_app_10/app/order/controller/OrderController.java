package com.koreait.exam.springbatch_app_10.app.order.controller;

import com.koreait.exam.springbatch_app_10.app.member.entity.Member;
import com.koreait.exam.springbatch_app_10.app.order.entity.Order;
import com.koreait.exam.springbatch_app_10.app.order.exception.ActorCanNotSeeOrderException;
import com.koreait.exam.springbatch_app_10.app.order.service.OrderService;
import com.koreait.exam.springbatch_app_10.app.security.dto.MemberContext;
import com.koreait.exam.springbatch_app_10.app.song.exception.ActorCanNotSeeException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public String showDetail(@AuthenticationPrincipal MemberContext memberContext, @PathVariable Long id, Model model) {
        Order order = orderService.findForPrintById(id).get();
        Member actor = memberContext.getMember();
        if (orderService.actorCanSee(actor, order) == false) {
            throw new ActorCanNotSeeOrderException();
        }
        model.addAttribute("order", order);
        return "order/detail";
    }
}