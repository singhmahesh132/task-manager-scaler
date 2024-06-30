package com.scaler.taskmanager.controller;

import com.scaler.taskmanager.dto.CreateTaskDto;
import com.scaler.taskmanager.dto.ErrorResponseDto;
import com.scaler.taskmanager.dto.TaskResponseDto;
import com.scaler.taskmanager.dto.UpdateTaskDto;
import com.scaler.taskmanager.entities.TaskEntity;
import com.scaler.taskmanager.service.NoteService;
import com.scaler.taskmanager.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    NoteService noteService;

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/get-all")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable int id){
        //Usage of model mapper
        //When the map method is called, the source and destination types are analyzed to determine which properties
        //implicitly match according to a matching strategy and other configuration.
        //Data is then mapped according to these matches.
        var task = taskService.getTaskById(id);
        var notes =  noteService.getNotesForTask(id);
        if(task==null)
            return  ResponseEntity.notFound().build();
        var taskResponseNotesDto = modelMapper.map(task, TaskResponseDto.class);
        taskResponseNotesDto.setNotesList(notes);
        return ResponseEntity.ok(taskResponseNotesDto);
    }

    @RequestMapping(value = {"/add"}, consumes = {"application/json"}, produces = {"application/json"}, method = {RequestMethod.POST})
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDto body) throws ParseException {
        var task = taskService.addTask(body.getTitle(),body.getDescription(), body.getDeadline());
        return ResponseEntity.ok(task);

    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable int id, @RequestBody UpdateTaskDto taskDto){
        var task = taskService.updateTask(id, taskDto);
        if(task==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleErrors(Exception e){
        e.printStackTrace();
        if(e instanceof ParseException)
            return ResponseEntity.badRequest().body(new ErrorResponseDto("Invalid Date Format"));

        return ResponseEntity.internalServerError().body(new ErrorResponseDto("Internal Server Error"));
    }

}
