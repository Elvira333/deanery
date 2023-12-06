package tech.inno.demodeanery.controller.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SubjectResponse {
    private String name;

}
