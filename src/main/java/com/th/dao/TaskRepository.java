package com.th.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.th.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
