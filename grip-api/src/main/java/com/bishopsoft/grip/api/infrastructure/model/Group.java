package com.bishopsoft.grip.api.infrastructure.model;

import com.bishopsoft.grip.api.infrastructure.model.audit.DateAudit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project_group")
@Getter @Setter @NoArgsConstructor
public class Group extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String key;
    private String description;

    @OneToMany(mappedBy = "ownerGroup", orphanRemoval = true)
    private Set<Project> projects = new HashSet<>();

    @OneToOne(mappedBy = "group")
    private Upload avatar;

    @OneToMany(mappedBy = "group", orphanRemoval = true)
    private Set<UserPermissionGroup> userPermissions = new HashSet<>();
}
