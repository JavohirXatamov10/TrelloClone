package org.example.imtihon_8_modul.service;

import lombok.RequiredArgsConstructor;
import org.example.imtihon_8_modul.entity.Comment;
import org.example.imtihon_8_modul.entity.Member;
import org.example.imtihon_8_modul.repo.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;


    public List<Comment> findALlByMember(Member authenticatedMember) {
       return commentRepo.findAllByMember(authenticatedMember);
    }

    public void save(Comment comment1) {
        commentRepo.save(comment1);
    }


    public Optional<Comment> findById(Integer id){
        return commentRepo.findById(id);
    }

    public void deleteById(Integer id) {
        commentRepo.deleteById(id);
    }
//
//    public void deleteByCommentId(Integer id) {
//        commentRepo.deleteByCommentId(id);
//
//    }
}
