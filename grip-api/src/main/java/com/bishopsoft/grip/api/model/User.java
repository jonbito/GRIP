package com.bishopsoft.grip.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class User extends AuditModel {
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @Column(unique = true)
    @NotEmpty(message = "email can not be empty")
    @Email(message = "email is invalid")
    private String email;

    @NotEmpty(message = "password cannot be empty")
    private String password;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}
