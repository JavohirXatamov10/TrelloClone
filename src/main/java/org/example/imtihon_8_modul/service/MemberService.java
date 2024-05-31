package org.example.imtihon_8_modul.service;

import lombok.RequiredArgsConstructor;
import org.example.imtihon_8_modul.entity.Member;
import org.example.imtihon_8_modul.projection.Report1;
import org.example.imtihon_8_modul.repo.MemberRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepo memberRepo;

    public Member findByUsername(String username) {
         return memberRepo.findByUsername(username);

    }

    public List<Member> findAll() {
        return memberRepo.findAll();
    }

    public void save(Member member) {
    memberRepo.save(member);
    }


    public Member findById(Integer member) {
         return memberRepo.findById(member).get();
    }

    public List<Member> findAllByTaskId(Integer id) {
        return memberRepo.findAllByTaskId(id);
    }
//
//    public List<Report1> findAllwithTaskData() {
//                       return memberRepo.findAllWithTaskData();
//    }
}
