package org.example.imtihon_8_modul.service;

import lombok.RequiredArgsConstructor;
import org.example.imtihon_8_modul.entity.Member;
import org.example.imtihon_8_modul.entity.Task;
import org.example.imtihon_8_modul.repo.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepo taskRepo;
    public Task save(Task task) {
       return taskRepo.save(task);
    }
    public Task findById(Integer id) {
        return taskRepo.findById(id).get();
    }

    public void findALlMemberByTaskId() {
    }

    public List<Task> findAllByMember(Integer id) {
       return taskRepo.findAllByMember(id);
    }
}

