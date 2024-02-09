package tech.inno.demodeanery.controller.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CreateStudentRequest {
    private Long id;
    private String login;
    private String name;
    private String surname;
    private Integer age;
    private CreateSubjectRequest subject;
}
