package com.scaler.taskmanager.entities;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskEntity {
    private int taskId;
    private String title;
    private String description;
    private Date deadLine;
    private boolean completed;
}
