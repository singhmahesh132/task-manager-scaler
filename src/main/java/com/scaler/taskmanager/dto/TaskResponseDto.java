package com.scaler.taskmanager.dto;

import com.scaler.taskmanager.entities.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {
    private int taskId;
    private String title;
    private String description;
    private Date deadLine;
    private boolean completed;
    private List<NoteEntity> notesList;
}
