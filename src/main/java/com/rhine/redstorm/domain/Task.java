package com.rhine.redstorm.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Task {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;

    @NotNull
    private String name;
    
    private String description;
    private LocalDateTime schedule;
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private TaskList list;
}
