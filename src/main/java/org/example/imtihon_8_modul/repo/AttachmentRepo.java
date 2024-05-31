package org.example.imtihon_8_modul.repo;

import org.example.imtihon_8_modul.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepo extends JpaRepository<Attachment,Integer> {


    @Query(value = "select  a.* from attachment a join public.task_attachments ta on a.id = ta.attachments_id where  task_id=?1", nativeQuery = true)
    List<Attachment> findAllByTaskId(Integer id);
    Attachment findAttachmentById(Integer id);
}
