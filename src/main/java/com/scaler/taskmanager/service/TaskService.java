package com.scaler.taskmanager.service;

import com.scaler.taskmanager.dto.UpdateTaskDto;
import com.scaler.taskmanager.entities.TaskEntity;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Getter
    private final List<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;

    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setDescription(description);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        task.setDeadLine(sd.parse(deadline)); //TODO: validate date format
        task.setId(taskId);
        taskId++;
        tasks.add(task);
        return task;
    }

    public TaskEntity getTaskById(int id){
        return tasks.stream().findAny().filter(task -> task.getId() == id).orElse(null);
    }

    public TaskEntity updateTask(int id, UpdateTaskDto taskDto){
        TaskEntity task =  getTaskById(id);
        if(task != null){
            task.setCompleted((taskDto.getCompleted()));
            task.setDeadLine(taskDto.getDeadline());
            return task;
        }else
            return null;
    }
}
