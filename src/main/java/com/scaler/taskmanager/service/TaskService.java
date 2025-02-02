package com.scaler.taskmanager.service;

import com.scaler.taskmanager.dto.UpdateTaskDto;
import com.scaler.taskmanager.entities.TaskEntity;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Getter
    private final List<TaskEntity> tasks = new ArrayList<>();
    private int id = 1;

    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setDescription(description);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        task.setDeadLine(sd.parse(deadline));
        task.setTaskId(id);
        id++;
        tasks.add(task);
        return task;
    }

    public TaskEntity getTaskById(int id){
        return tasks.stream().filter(task -> task.getTaskId() == id).findAny().orElse(null);
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
