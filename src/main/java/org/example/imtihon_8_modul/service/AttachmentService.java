package org.example.imtihon_8_modul.service;

import lombok.RequiredArgsConstructor;
import org.example.imtihon_8_modul.entity.Attachment;
import org.example.imtihon_8_modul.repo.AttachmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepo attachmentRepo;
    public void save(Attachment attachment) {
        attachmentRepo.save(attachment);
    }
    public List<Attachment> findAllByTaskId(Integer id) {
         return attachmentRepo.findAllByTaskId(id);
    }
    public Attachment findAllByAttachmentId(Integer id) {
        return attachmentRepo.findAttachmentById(id);
    }
}
