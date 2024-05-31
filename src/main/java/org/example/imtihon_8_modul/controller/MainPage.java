package org.example.imtihon_8_modul.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.imtihon_8_modul.entity.Card;
import org.example.imtihon_8_modul.entity.Member;
import org.example.imtihon_8_modul.entity.Task;
import org.example.imtihon_8_modul.entity.enums.StatusCard;
import org.example.imtihon_8_modul.repo.MemberRepo;
import org.example.imtihon_8_modul.service.CardService;
import org.example.imtihon_8_modul.service.MemberService;
import org.example.imtihon_8_modul.service.TaskService;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainPage {

    private final CardService cardService;
    private final MemberService memberService;
    private final TaskService taskService;

    @GetMapping
    public String sentToMainPage(Model model, @AuthenticationPrincipal Member member1) {
        List<Card> cards=cardService.findAll();

//        List<Card> cards =member.getCards();
        model.addAttribute("loggedUser",member1);
        model.addAttribute("cards", cards);
        return "index";
    }

    @PostMapping
    public String addToCardDB(@ModelAttribute Card card, @AuthenticationPrincipal Member member1) {
        Member member= memberService.findById(member1.getId());
        member1=member;
        List<Card> cards = cardService.findByMemberId(member.getId());
        cards.sort(Comparator.comparing(Card::getOrderId));
        int orderId;
        if (cards.isEmpty()) {
            orderId = 1;
        } else {
            orderId = cards.get(cards.size()-1).getOrderId() + 1;
        }
        card.setOwner(member1.getId());
        card.setStatus(StatusCard.OPEN);
        card.setOrderId(orderId);
        cardService.save(card);
        member.getCards().add(card);
        memberService.save(member1);
        return "redirect:/main";
    }

    @PostMapping("/addTask")
    public String drawTasks(@RequestParam("name") String name, @RequestParam("cardId") Integer id,@ModelAttribute Task task,@AuthenticationPrincipal Member member) {
        Card card = cardService.findById(id);
        List<Task> tasks=card.getTasks();
        tasks.sort(Comparator.comparing(Task::getOrder_id));
        int order_id;
        if (tasks.isEmpty()) {
            order_id = 1;
        } else {
            order_id = tasks.get(tasks.size()-1).getOrder_id() + 1;;
        }

        Task task1 = taskService.save(Task.builder()
                .name(name)
                .ownerId(member.getId())
                .order_id(order_id)
                .startTime(LocalDateTime.now())

                .build());
        taskService.save(task1);
        if (card.getTasks()==null){
            card.setTasks(new ArrayList<>());
        }
        card.getTasks().add(task1);
        cardService.save(card);


        memberService.save(member);
        return "redirect:/main";
    }


}
