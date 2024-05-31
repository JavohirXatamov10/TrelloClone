package org.example.imtihon_8_modul.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.imtihon_8_modul.entity.Card;
import org.example.imtihon_8_modul.entity.Member;
import org.example.imtihon_8_modul.entity.enums.StatusCard;
import org.example.imtihon_8_modul.service.CardService;
import org.example.imtihon_8_modul.service.MemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("card")
@RequiredArgsConstructor
public class CardController {
    private final MemberService memberService;
    private final CardService cardService;
    @GetMapping("editName/{id}")
    public String editName(@PathVariable Integer id, Model model){
        Card card = cardService.findById(id);
        model.addAttribute("editCard", card);
        model.addAttribute("statuss", StatusCard.values());
        return "editCardName";
    }

    @PostMapping("editName/{id}")
    public String save(@PathVariable Integer id, HttpServletRequest request, @AuthenticationPrincipal Member member1){
        Member member=memberService.findById(member1.getId());
        Card card = cardService.findById(id);
        String name = request.getParameter("name");
        card.setName(name);
        cardService.save(card);
        memberService.save(member);
        return "redirect:/main";
    }


    @GetMapping("delete/{id}")
    public String deleteCard(@PathVariable Integer id,@AuthenticationPrincipal Member member){

        Card card=cardService.findById(id);
        member.getCards().remove(card);
        memberService.save(member);
        cardService.deleteById(id);
        return "redirect:/main";
    }
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("change/{results}")
    public String changeNext(@PathVariable String [] results ) {


        Integer cardId = Integer.parseInt(results[0]);
        Integer orderNumberOfCard = Integer.parseInt(results[1]);
        cardService.change(cardId, orderNumberOfCard);



        return "redirect:/main";

    }

}
