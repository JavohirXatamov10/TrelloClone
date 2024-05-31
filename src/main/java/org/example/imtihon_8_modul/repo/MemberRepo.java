package org.example.imtihon_8_modul.repo;

import org.example.imtihon_8_modul.entity.Member;
import org.example.imtihon_8_modul.projection.Report1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepo extends JpaRepository<Member, Integer> {

    Member findByUsername(String username);

    @Query(value = "select m.* from task_members tm join public.members m on m.id = tm.members_id where task_id=?1", nativeQuery = true)
    List<Member> findAllByTaskId( Integer id);

//    @Query(value = "",nativeQuery = true)
//    List<Report1> findAllWithTaskData();

}
