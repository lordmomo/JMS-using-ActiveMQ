package com.momo.employeeDetailsJMS.controller;

import com.momo.employeeDetailsJMS.model.Employee;

import jakarta.jms.Queue;
import jakarta.jms.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/publish")
public class EmployeeController {
    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${emp.jms.topic}")
    String topicName;
    @Value("${emp.jms.queue}")
    String queueName;

    @PostMapping("/topic/employee")
    public ResponseEntity<Employee> newEmployeeInTopic(@RequestBody Employee employee) {
        try {
            Topic empTopic = jmsTemplate.getConnectionFactory()
                    .createConnection()
                    .createSession()
                    .createTopic(topicName);
            Employee emp = Employee.builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .role(employee.getRole())
                    .build();
            log.info("Sending Employee Object: " + emp);
            jmsTemplate.convertAndSend(empTopic, emp);
            return new ResponseEntity<>(emp, HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/queue/employee")
    public ResponseEntity<Employee> newEmployeeInQueue(@RequestBody Employee employee) {
        try {
            Queue empQueue = jmsTemplate.getConnectionFactory()
                    .createConnection()
                    .createSession()
                    .createQueue(queueName);
            Employee emp = Employee.builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .role(employee.getRole())
                    .build();
            log.info("Sending Employee Object: " + emp);
            jmsTemplate.convertAndSend(empQueue, emp);
            return new ResponseEntity<>(emp, HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
