package com.crudtask.serviceimplementation;

import java.util.List;

import com.crudtask.entity.EmployeeDetails;
import com.crudtask.repository.EmployeeRepo;
import com.crudtask.service.EmployeeService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService{
    
    @Inject
    private EmployeeRepo employeeRepo;

    @Override
    public Response addEmployees(EmployeeDetails employeeDetails) {
     try {
        employeeRepo.persist(employeeDetails);
        return Response.status(201).entity(employeeDetails).build();
     } catch (Exception e) {
       e.printStackTrace();
        return Response.status(400).entity(e.getMessage()).build();
     }
    }

    @Override
    public Response showEmployeees() {
       try {
        List<EmployeeDetails> employeeDetails=employeeRepo.findAll().list();
        employeeRepo.persist(employeeDetails);
         return Response.status(200).entity(employeeDetails).build();
       } catch(Exception e) {
        e.printStackTrace();
        return Response.status(400).entity(e.getMessage()).build();
       }
    }

    @Override
    public Response readIdAndName(Long empId, String empName) {
        try{
      EmployeeDetails employeeDetails= employeeRepo.getIdAndName(empId,empName);
       return Response.status(200).entity(employeeDetails).build();
        }
        catch(Exception e){
             e.printStackTrace();
        return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response modifyByName(Long empId, String empName, EmployeeDetails updateEmpDetails) {
      try {
        EmployeeDetails employeeDetails=employeeRepo.modifyName(empId, empName);
        // employeeDetails.setEmpName(empName);
        employeeDetails.setCity(updateEmpDetails.getCity());
        employeeDetails.setCompany(updateEmpDetails.getCompany());
        employeeDetails.persist();
        return Response.status(200).entity(employeeDetails).build();
      } catch (Exception e) {
          e.printStackTrace();
        return Response.status(400).entity(e.getMessage()).build();
       
      }
    }

    @Override
    public Response removeByName(String empName, Long empId) {
       try {
        EmployeeDetails employeeDetails=employeeRepo.removeName(empName,empId);
        employeeDetails.delete();
        return Response.status(200).entity(employeeDetails).build();
       } catch (Exception e) {
         e.printStackTrace();
        return Response.status(400).entity(e.getMessage()).build();
       }
    }
    
    
}
