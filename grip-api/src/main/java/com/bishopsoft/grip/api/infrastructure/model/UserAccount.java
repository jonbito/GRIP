package com.bishopsoft.grip.api.infrastructure.model;

import com.bishopsoft.grip.api.infrastructure.model.audit.DateAudit;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_account")
@Getter @Setter @NoArgsConstructor
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class UserAccount extends DateAudit {
    @Id
    @org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;
    private String role;
    private String username;
    private String firstName;
    private String lastName;

    @Type(type = "list-array")
    @Column(name = "starred_projects", columnDefinition = "integer[]")
    private List<Long> starredProjects = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<UserPermissionProject> userPermissionProjects = new HashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<UserPermissionGroup> userPermissionGroups = new HashSet<>();

    @OneToMany(mappedBy = "lead", orphanRemoval = true)
    private Set<Project> leadProjects = new HashSet<>();
}
