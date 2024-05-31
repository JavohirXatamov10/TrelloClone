package org.example.imtihon_8_modul.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.imtihon_8_modul.entity.Attachment;
import org.example.imtihon_8_modul.entity.Member;
import org.example.imtihon_8_modul.repo.UserRoleRepo;
import org.example.imtihon_8_modul.service.AttachmentService;
import org.example.imtihon_8_modul.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("register")
@RequiredArgsConstructor
public class Register {

    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepo userRole;
    private final MemberService memberService;
    private final AttachmentService attachmentService;



    @GetMapping
    public String sendToRegisterPage(){
        return "register";
    }

    @PostMapping()
    public String register(HttpServletRequest request, @RequestParam("photo") MultipartFile file){
        try {
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        Attachment attachment = Attachment.builder()
                    .name(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .photo(file.getBytes())
                    .build();
        attachmentService.save(attachment);



        Member member= Member.builder()
                .fullName(fullName)
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(List.of(userRole.findById(3).get()))
                .photo(attachment)
                .build();
        memberService.save(member);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/main";
    }

}
