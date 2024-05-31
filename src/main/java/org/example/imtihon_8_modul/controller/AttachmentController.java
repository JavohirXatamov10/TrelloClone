package org.example.imtihon_8_modul.controller;

import lombok.RequiredArgsConstructor;
import org.example.imtihon_8_modul.entity.Attachment;
import org.example.imtihon_8_modul.entity.Member;
import org.example.imtihon_8_modul.service.AttachmentService;
import org.example.imtihon_8_modul.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final MemberService memberService;
    private final AttachmentService attachmentService;
    @GetMapping("/photo/{id}")
    public synchronized ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        Member foundMember = memberService.findById(id);
        Attachment photo = foundMember.getPhoto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.valueOf(photo.getContentType()));
        return new ResponseEntity<>(photo.getPhoto(), headers, HttpStatus.OK);
    }

    @GetMapping("/file/{id}")
    public synchronized ResponseEntity<byte[]> getAttachment(@PathVariable Integer id) {

        Attachment attachment= attachmentService.findAllByAttachmentId(id);
        Attachment photo = attachment;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.valueOf(photo.getContentType()));
        return new ResponseEntity<>(photo.getPhoto(), headers, HttpStatus.OK);
    }



}
