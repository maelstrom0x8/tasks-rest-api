package com.rhine.redstorm.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "list")
public class TaskList {

    @Id
    @GeneratedValue
    @Column(name = "list_id")
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "list", cascade = CascadeType.ALL)
    public List<Task> tasks = new ArrayList<>();


}
