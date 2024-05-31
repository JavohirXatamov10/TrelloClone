package org.example.imtihon_8_modul.repo;

import org.example.imtihon_8_modul.entity.Card;
import org.example.imtihon_8_modul.entity.Member;
import org.example.imtihon_8_modul.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Integer> {

    @Query(value = "select t.* from task  t join public.task_members tm on t.id = tm.task_id where members_id=?1; ", nativeQuery = true)
    List<Task> findAllByMember(Integer id);
}
