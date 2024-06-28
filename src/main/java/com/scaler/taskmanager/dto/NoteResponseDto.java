package com.scaler.taskmanager.dto;

import com.scaler.taskmanager.entities.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoteResponseDto {
    private NoteEntity note;
    private int taskId;
}
