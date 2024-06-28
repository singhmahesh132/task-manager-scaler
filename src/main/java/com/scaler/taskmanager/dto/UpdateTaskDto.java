package com.scaler.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateTaskDto {
    private Date deadline;
    private Boolean completed;
}
