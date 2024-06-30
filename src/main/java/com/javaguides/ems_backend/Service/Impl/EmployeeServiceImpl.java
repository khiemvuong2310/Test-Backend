package com.javaguides.ems_backend.Service.Impl;

import com.javaguides.ems_backend.Service.EmployeeService;
import com.javaguides.ems_backend.dto.EmployeeDto;
import com.javaguides.ems_backend.entity.Employee;
import com.javaguides.ems_backend.exception.ResourceNotFoundException;
import com.javaguides.ems_backend.mapper.EmployeeMapper;
import com.javaguides.ems_backend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.maToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee= employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exist with given Id:"+ employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees=employeeRepository.findAll();
        return employees.stream().map((employee)
                -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long Id, EmployeeDto updateEmployeeDto) {

        Employee employee=employeeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given Id:"+ Id) );

        employee.setFirstName(updateEmployeeDto.getFirstName());
        employee.setLastName(updateEmployeeDto.getLastName());
        employee.setEmail(updateEmployeeDto.getEmail());

        Employee updateEmployeeObj=employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long Id) {
        Employee employee=employeeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given Id:"+ Id) );
        employeeRepository.delete(employee);
    }
}
