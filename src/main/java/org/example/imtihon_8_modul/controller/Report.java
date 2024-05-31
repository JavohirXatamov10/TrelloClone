package org.example.imtihon_8_modul.controller;

import lombok.RequiredArgsConstructor;
import org.example.imtihon_8_modul.entity.Card;
import org.example.imtihon_8_modul.entity.Member;
import org.example.imtihon_8_modul.entity.Task;
import org.example.imtihon_8_modul.projection.Report1;
import org.example.imtihon_8_modul.projection.Report2;
import org.example.imtihon_8_modul.service.CardService;
import org.example.imtihon_8_modul.service.MemberService;
import org.example.imtihon_8_modul.service.TaskService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("report")
@RequiredArgsConstructor
public class Report {
    private final TaskService taskService;
    private final CardService cardService;
    private final MemberService memberService;
    @GetMapping("/mytask")
    public String sentMyReport(@AuthenticationPrincipal Member member, Model model){
        List<Task> mytask=taskService.findAllByMember(member.getId());
        model.addAttribute("mytask",mytask);
        return "mytask";
    }
    @GetMapping("/report1")
    public String sentreport1(Model model){
//       List<Report1> report1 =memberService.findAllwithTaskData();
        return "report1";
    }
    @GetMapping("/report2")
    public String sentreport2(Model model){
        List<Report2> cards=cardService.findAllTaskAMount();
        model.addAttribute("cards",cards);
        return "report2";
    }


}
