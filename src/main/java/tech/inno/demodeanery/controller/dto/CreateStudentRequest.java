package tech.inno.demodeanery.controller.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateStudentRequest {
    private Long id;
    private String login;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer age;
    private CreateSubjectRequest subject;
}
