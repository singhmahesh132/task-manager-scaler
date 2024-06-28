package com.scaler.taskmanager.entities;

import lombok.Data;
import lombok.Generated;

import java.util.Date;

@Data
public class TaskEntity {
    private int id;
    private String title;
    private String description;
    private Date deadLine;
    private boolean completed;
}
