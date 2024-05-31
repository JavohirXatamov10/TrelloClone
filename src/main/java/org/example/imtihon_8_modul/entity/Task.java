package org.example.imtihon_8_modul.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer order_id;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private LocalDateTime deadline;
    private Integer ownerId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Member> members;
    @OneToMany(cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    private List<Attachment> attachments;
}
