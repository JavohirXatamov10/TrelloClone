package org.example.imtihon_8_modul.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.imtihon_8_modul.entity.Attachment;
import org.example.imtihon_8_modul.entity.Comment;
import org.example.imtihon_8_modul.entity.Member;
import org.example.imtihon_8_modul.entity.Task;
import org.example.imtihon_8_modul.service.AttachmentService;
import org.example.imtihon_8_modul.service.CommentService;
import org.example.imtihon_8_modul.service.MemberService;
import org.example.imtihon_8_modul.service.TaskService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final MemberService memberService;
    private final CommentService commentService;
    private final AttachmentService attachmentService;


    @GetMapping("/fillTasks/{id}")
    public String sentToTaskPage(@PathVariable Integer id, Model model, @AuthenticationPrincipal Member authenticatedMember) {

        List<Member> taskMembers=memberService.findAllByTaskId(id);
        List<Attachment> taskAttachments=attachmentService.findAllByTaskId(id);
        List<Member> members = memberService.findAll();
        Task task = taskService.findById(id);
        List<Comment> comments1 = task.getComments();
        model.addAttribute("taskAttachment", taskAttachments);
        model.addAttribute("taskMembers", taskMembers);
        model.addAttribute("members", members);
        model.addAttribute("currentTaskOwner", authenticatedMember);
        model.addAttribute("chosenTask", task);
        model.addAttribute("comments", comments1);
        return "task";
    }

    @PostMapping("/editName/{id}")
    public String editTaskName(@PathVariable Integer id, @RequestParam("editName") String editName) {
        Task editedTask = taskService.findById(id);
        editedTask.setName(editName);
        taskService.save(editedTask);
        return "redirect:/task/fillTasks/" + id;
    }

    @PostMapping("/addDeadLine")
    public String addDeadLock(Integer id, LocalDateTime deadline) {
        Task editedTask = taskService.findById(id);
        editedTask.setDeadline(deadline);
        taskService.save(editedTask);
        return "redirect:/task/fillTasks/" + id;
    }

    @PostMapping("/addFinishTime")
    public String addFinishTime(Integer id, LocalDateTime finishTime) {
        Task editedTask = taskService.findById(id);
        editedTask.setFinishTime(finishTime);
        taskService.save(editedTask);
        return "redirect:/task/fillTasks/" + id;
    }

    @PostMapping("/deleteDeadline")
    public String deleteDeadLock(Integer id) {
        Task editedTask = taskService.findById(id);
        editedTask.setDeadline(null);
        taskService.save(editedTask);
        return "redirect:/task/fillTasks/" + id;
    }@PostMapping("/deleteFinishTime")
    public String deleteFinishTime(Integer id) {
        Task editedTask = taskService.findById(id);
        editedTask.setFinishTime(null);
        taskService.save(editedTask);
        return "redirect:/task/fillTasks/" + id;
    }


    @PostMapping("/addMember/{id}")
    public String addMember(@PathVariable Integer id, Integer member) {
        Task editedTask = taskService.findById(id);
        Member member1 = memberService.findById(member);
        List<Member> members = editedTask.getMembers();

        if (members == null) {
            members = new ArrayList<>();
        }
        if (!members.contains(member1)) {
            members.add(member1);
            editedTask.setMembers(members);
            taskService.save(editedTask);
        }
        return "redirect:/task/fillTasks/" + id;
    }


    @PostMapping("/addComment")
    public String addComment(Integer currentTaskId,String comment,Integer userId){
        System.out.println("kirdi");
        Member member = memberService.findById(userId);
        Task editedTask=taskService.findById(currentTaskId);
        Comment comment1= Comment.builder()
                .member(member)
                .text(comment)
                .build();
        commentService.save(comment1);
        editedTask.getComments().add(comment1);
        taskService.save(editedTask);
        return "redirect:/task/fillTasks/" +currentTaskId;
    }
    @PostMapping("/deleteComment")
    public String deleteComment(Integer id){
//        commentService.deleteByCommentId(id);
        commentService.deleteById(id);
        return "redirect:/task/fillTasks/" +id;
    }
@PostMapping("/addFile")
    public String addFile(Integer taskId, @RequestParam("file") MultipartFile file){
    try {
    Task editedTask = taskService.findById(taskId);
    List<Attachment> files=attachmentService.findAllByTaskId(taskId);

    Attachment attachment = null;
        attachment = Attachment.builder()
                .name(file.getOriginalFilename())
                .contentType(file.getContentType())
                .photo(file.getBytes())
                .build();
        files.add(attachment);
        attachmentService.save(attachment);
        editedTask.setAttachments(files);

        taskService.save(editedTask);


    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    return "redirect:/task/fillTasks/" +taskId;
    }







}