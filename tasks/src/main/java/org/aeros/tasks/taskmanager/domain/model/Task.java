package org.aeros.tasks.taskmanager.domain.model;

import java.time.Instant;

public record Task(Long id, Long listID, String name, String description, Instant schedule, boolean completed) {}
