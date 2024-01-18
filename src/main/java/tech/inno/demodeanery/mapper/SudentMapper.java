package tech.inno.demodeanery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tech.inno.demodeanery.controller.dto.CreateStudentRequest;
import tech.inno.demodeanery.controller.dto.StudentResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SudentMapper {


}
