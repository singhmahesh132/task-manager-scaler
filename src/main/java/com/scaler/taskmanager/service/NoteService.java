package com.scaler.taskmanager.service;

import com.scaler.taskmanager.dto.CreateNoteDto;
import com.scaler.taskmanager.dto.NoteResponseDto;
import com.scaler.taskmanager.entities.NoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private TaskService taskService;

    private final HashMap<Integer, TaskNotesHolder> taskNotesHolderMap = new HashMap<>();

    static class TaskNotesHolder{
        protected int id = 1;
        protected List<NoteEntity> notes = new ArrayList<>();
    }

    public List<NoteEntity> getNotesForTask(int taskId){
        var task = taskService.getTaskById(taskId);
        if(task == null)
            return null;

        if(taskNotesHolderMap.get(taskId) == null)
            taskNotesHolderMap.put(taskId, new TaskNotesHolder());

        return taskNotesHolderMap.get(taskId).notes;
    }

    public NoteEntity addNoteForTask(int taskId, CreateNoteDto createNoteDto){
        var task = taskService.getTaskById(taskId);
        if(task == null)
                return null;

        if(taskNotesHolderMap.get(taskId) == null)
            taskNotesHolderMap.put(taskId, new TaskNotesHolder());

        var taskNotesHolder = taskNotesHolderMap.get(taskId);
        NoteEntity note = new NoteEntity();
        note.setTitle(createNoteDto.getTitle());
        note.setBody(createNoteDto.getBody());
        note.setNoteId(taskNotesHolder.id);
        taskNotesHolder.id++;
        taskNotesHolder.notes.add(note);
        return note;
    }
}
