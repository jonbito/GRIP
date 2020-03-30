package com.bishopsoft.grip.api.infrastructure.model;

import com.bishopsoft.grip.api.infrastructure.model.audit.DateAudit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_account")
@Getter @Setter @NoArgsConstructor
public class UserAccount extends DateAudit {
    @Id
    @org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;
    private String role;
    private String username;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<UserPermissionProject> userPermissionProjects = new HashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<UserPermissionGroup> userPermissionGroups = new HashSet<>();

    @OneToMany(mappedBy = "lead", orphanRemoval = true)
    private Set<Project> leadProjects = new HashSet<>();
}
