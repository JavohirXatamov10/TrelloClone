package org.example.imtihon_8_modul.repo;

import org.example.imtihon_8_modul.entity.Comment;
import org.example.imtihon_8_modul.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByMember(Member authenticatedMember);}

//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM task_comments WHERE comments_id = :id", nativeQuery = true)
//    void deleteByCommentId(@Param("id") Integer id);}
