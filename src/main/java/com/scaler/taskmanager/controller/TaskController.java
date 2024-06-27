package com.scaler.taskmanager.controller;

import com.scaler.taskmanager.dto.CreateTaskDTO;
import com.scaler.taskmanager.entities.TaskEntity;
import com.scaler.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TaskEntity> getTask(@PathVariable int id){
        var task = taskService.getTaskById(id);
        if(task==null)
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @RequestMapping(value = {"/add"}, consumes = {"application/json"}, produces = {"application/json"}, method = {RequestMethod.POST})
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body){
        var task = taskService.addTask(body.getTitle(),body.getDescription(), body.getDeadline());
        return ResponseEntity.ok(task);

    }
}
