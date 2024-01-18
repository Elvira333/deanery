package tech.inno.demodeanery.controller.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateStudentRequest {
    private String login;
    private String name;
    private String surname;
    private Integer age;
    private CreateSubjectRequest subject;
}
