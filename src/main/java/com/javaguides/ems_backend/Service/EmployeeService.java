package com.javaguides.ems_backend.Service;

import com.javaguides.ems_backend.dto.EmployeeDto;

import java.util.List;


public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long Id,EmployeeDto updateEmployeeDto);

    void deleteEmployee(Long Id);
}
