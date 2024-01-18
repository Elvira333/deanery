package tech.inno.demodeanery.controller.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubjectRequest {
    private String name;

    private List<Long> students;

}
