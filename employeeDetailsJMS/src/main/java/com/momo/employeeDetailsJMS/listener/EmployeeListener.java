package com.momo.employeeDetailsJMS.listener;

import com.momo.employeeDetailsJMS.model.Employee;
import com.momo.employeeDetailsJMS.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmployeeListener {

    @Autowired
    EmployeeService employeeService;

    @JmsListener(destination = "${emp.jms.topic}",containerFactory = "jmsListenerContainerFactory")
    public void getEmployeeListener1(Employee emp){
        log.info("Employee listener1: "+emp);
    }

    @JmsListener(destination = "${emp.jms.topic}",containerFactory = "jmsListenerContainerFactory")
    public void getEmployeeListener2(Employee emp){
        log.info("Employee listener2: "+emp);
    }

    @JmsListener(destination = "${emp.jms.topic}",containerFactory = "jmsListenerContainerFactory")
    public void getEmployeeListener5(Employee emp){
        log.info("Employee listener5: "+emp);
    }

    @JmsListener(destination = "${emp.jms.queue}",containerFactory = "queueListenerContainerFactory")
    public void getEmployeeListener3(Employee emp){
        log.info("Employee listener3: "+emp);
        employeeService.saveEmployee(emp);
    }

    @JmsListener(destination = "${emp.jms.queue}",containerFactory = "queueListenerContainerFactory")
    public void getEmployeeListener4(Employee emp){
        log.info("Employee listener4: "+emp);
    }


}
