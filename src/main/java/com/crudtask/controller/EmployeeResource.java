package com.crudtask.controller;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.crudtask.entity.EmployeeDetails;
import com.crudtask.service.EmployeeService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/show")
public class EmployeeResource {
    
@Inject
private EmployeeService employeeService;



@Transactional
@POST
@Path("/create")
@Consumes(MediaType.APPLICATION_JSON)
public Response createEmployees(EmployeeDetails employeeDetails){
    return employeeService.addEmployees(employeeDetails);
}
@Transactional
@GET
@Path("/view")
@Consumes(MediaType.APPLICATION_JSON)
public Response viewEmployees(){
    return employeeService.showEmployeees();
}
@Transactional
@GET
@Path("/showEmpIdAndName")
public Response getEmployees(@QueryParam("empId") Long empId, @QueryParam("empName") String empName){
    return employeeService.readIdAndName(empId,empName);
}
@Transactional
@PUT
@Path("/updateIdAndName")
public Response updateName(@QueryParam("empId") Long empId, @QueryParam("empName") String empName,@RequestBody EmployeeDetails updateEmpDetails){
    return employeeService.modifyByName(empId, empName, updateEmpDetails);
}

@Transactional
@DELETE
@Path("/remove")
public Response deleteName(@QueryParam("empName") String  empName, @QueryParam("empId") Long empId){
    return employeeService.removeByName(empName,empId);
}
}
