package com.scaler.taskmanager.service;

import com.scaler.taskmanager.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    private final List<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;

    public TaskEntity addTask(String title, String description, String deadline){
        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setDescription(description);
        SimpleDateFormat sd = new SimpleDateFormat();
        sd.applyPattern("dd-MM-yyyy");
        Date date;
        try {
             date = sd.parse(deadline);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        task.setDeadLine(date); //TODO: validate date format
        task.setId(taskId);
        taskId++;
        tasks.add(task);
        return task;
    }

    public List<TaskEntity> getTasks(){
        return tasks;
    }

    public TaskEntity getTaskById(int id){
        return tasks.stream().findAny().filter(task -> task.getId() == id).orElse(null);
    }
}
