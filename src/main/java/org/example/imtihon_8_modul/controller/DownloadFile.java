package org.example.imtihon_8_modul.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.imtihon_8_modul.entity.Attachment;
import org.example.imtihon_8_modul.entity.Task;
import org.example.imtihon_8_modul.service.AttachmentService;
import org.example.imtihon_8_modul.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("download")
@RequiredArgsConstructor
public class DownloadFile {

    private final TaskService taskService;
    private final AttachmentService attachmentService;
    @SneakyThrows
    @GetMapping("downloadFile/{result}")
    public void downloadFile(@PathVariable String[] result, HttpServletResponse response) {
        int taskId = Integer.parseInt(result[0]);
        int attachmentId = Integer.parseInt(result[1]);
        Task task = taskService.findById(taskId);
        Attachment attachment = attachmentService.findAllByAttachmentId(attachmentId);


        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" +task.getName());
        response.getOutputStream().write(attachment.getPhoto());
    }
}
