package org.aeros.tasks.manager.domain.dto;

import java.time.LocalDateTime;

public record TaskRequest(
        String name, String description, LocalDateTime schedule, boolean completed) {}
