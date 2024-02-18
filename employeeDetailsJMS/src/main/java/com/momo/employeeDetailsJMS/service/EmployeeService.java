package com.momo.employeeDetailsJMS.service;

import com.momo.employeeDetailsJMS.model.Employee;
import com.momo.employeeDetailsJMS.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    public void saveEmployee(Employee emp) {
        employeeRepository.save(emp);
    }
}
