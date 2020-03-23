package com.bishopsoft.grip.api.infrastructure.model;

import com.bishopsoft.grip.api.infrastructure.model.audit.DateAudit;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "upload")
@Getter @Setter @NoArgsConstructor
@TypeDef(
        name = "upload_type",
        typeClass = PostgreSQLEnumType.class
)
public class Upload extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fileName;
    private String fileType;

    @Enumerated(EnumType.STRING)
    @Type(type = "upload_type")
    private UploadTypeEnum uploadType;

    private String referencedId;
}
