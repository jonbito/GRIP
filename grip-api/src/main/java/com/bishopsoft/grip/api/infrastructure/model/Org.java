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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "org")
@Getter @Setter @NoArgsConstructor
public class Org extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String url;

    @OneToMany(mappedBy = "org", orphanRemoval = true)
    private Set<Project> projects = new HashSet<>();

    @OneToOne(mappedBy = "org")
    private Upload avatar;

    @OneToMany(mappedBy = "org", orphanRemoval = true)
    private Set<OrgUserPermission> userPermissions = new HashSet<>();

    @PrePersist
    @PreUpdate
    private void beforeAnyUpdate() {
        url = convertNameToUrl(name);
    }

    public static String convertNameToUrl(String name) {
        return name.toLowerCase().replace(" ", "-").replaceAll("^[-\\s]+|[-\\s]+$", "");
    }
}
