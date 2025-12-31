package com.example.taskproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "taskName")
    private String taskName;


    @Column(name = "completed")
    private boolean completed = false;

    @ManyToOne(fetch = FetchType.LAZY)  // if dont want user data Lazy else Eager
    @JoinColumn(name = "user_id")
    private User user;

}
