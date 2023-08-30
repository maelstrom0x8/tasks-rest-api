package org.aeros.tasks.manager.domain.model;

import java.time.Instant;

public record Task(
        Long id,
        String name,
        String description,
        Instant schedule,
        boolean completed,
        Long listID) {}
