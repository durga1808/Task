package com.crudtask.service;
import com.crudtask.entity.EmployeeDetails;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;



@ApplicationScoped
public interface EmployeeService {
    public Response addEmployees(EmployeeDetails employeeDetails);
    public Response showEmployeees();
    public Response readIdAndName(Long empId,String empName);
    public Response modifyByName(Long empId,String empName,EmployeeDetails updateEmpDetails);
    public Response removeByName(String empName,Long empId);
}
