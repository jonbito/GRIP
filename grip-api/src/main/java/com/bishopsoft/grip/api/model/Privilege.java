package com.bishopsoft.grip.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Privilege {
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles;
}
