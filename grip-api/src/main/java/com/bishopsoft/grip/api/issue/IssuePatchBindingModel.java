package com.bishopsoft.grip.api.issue;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Optional;

@Data
public class IssuePatchBindingModel {

    private Optional<@Length(max = 5) String> summary;
    private Optional<String> description;
}
