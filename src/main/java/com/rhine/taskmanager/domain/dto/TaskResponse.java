package com.rhine.taskmanager.domain.dto;

import java.time.LocalDateTime;

public record TaskResponse(Long id, String name,
        String description, LocalDateTime schedule,
        boolean completed) {

}
