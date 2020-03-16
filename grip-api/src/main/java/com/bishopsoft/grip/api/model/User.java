package com.bishopsoft.grip.api.model;

import com.bishopsoft.grip.api.model.audit.DateAudit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter @Setter @NoArgsConstructor
public class User extends DateAudit {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    private Role role;


    public enum Role {
        SOFTWARE_DEVELOPER("Software Developer"),
        DEVELOPMENT_TEAM_LEAD("Development Team Lead"),
        DEVOPS_ENGINEER("Devops Engineer"),
        SYSTEMS_ADMINISTRATOR("Systems Administrator"),
        SECURITY_ANALYST("Security Analyst"),
        DATA_ANALYST("Data Analyst"),
        PRODUCT_MANAGER("Product Manager"),
        PRODUCT_DESIGNER("Product Designer"),
        OTHER("Other");

        private String niceText;

        Role(String niceText) {
            this.niceText = niceText;
        }

        public String getNiceText() {
            return this.niceText;
        }
    }
}
