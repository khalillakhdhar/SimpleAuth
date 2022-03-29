package com.th.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.th.dao.TaskRepository;
import com.th.entities.Task;

@RestController
@RequestMapping("tasks")
public class TaskContoller {
@Autowired
TaskRepository taskRepository;
	
@GetMapping	
public List<Task> getTasks()
{
	 return taskRepository.findAll();
}
@GetMapping("{id}")
public Optional<Task> findTasks(@PathVariable long id)
{
	 return taskRepository.findById(id);
}
@PostMapping
public Task addTask(@Valid @RequestBody Task task) // valid => controller ou Rest 
// RB => uniquement RestController
{
return taskRepository.save(task);	

}


}
