package com.bishopsoft.grip.api.infrastructure.model;

import com.bishopsoft.grip.api.infrastructure.model.audit.UserDateAudit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "milestone")
@Getter @Setter @NoArgsConstructor
public class Milestone extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private Date plannedStart;
    private Date plannedEnd;
    private Date actualStart;
    private Date actualEnd;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;
}
