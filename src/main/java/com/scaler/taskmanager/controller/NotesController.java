package com.scaler.taskmanager.controller;

import com.scaler.taskmanager.dto.CreateNoteDto;
import com.scaler.taskmanager.dto.NoteResponseDto;
import com.scaler.taskmanager.entities.NoteEntity;
import com.scaler.taskmanager.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/get-notes-for-task/{taskId}")
    public ResponseEntity<List<NoteEntity>> getNoteById(@PathVariable("taskId") int taskId){
        var taskNotes = noteService.getNotesForTask(taskId);
        if(taskNotes == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(taskNotes);
    }

    @RequestMapping(value = {"/add-note-for-task/{taskId}"}, consumes = {"application/json"}, produces = {"application/json"}, method = {RequestMethod.POST})
    public ResponseEntity<NoteResponseDto> addNoteToTask(@PathVariable("taskId") int taskId, @RequestBody CreateNoteDto createNoteDto){
        var note = noteService.addNoteForTask(taskId, createNoteDto);
        return ResponseEntity.ok(new NoteResponseDto(note, taskId));
    }
}
