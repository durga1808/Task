package com.crudtask.repository;

import com.crudtask.entity.EmployeeDetails;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class EmployeeRepo implements PanacheRepository<EmployeeDetails> {
    public EmployeeDetails getIdAndName(Long empId,String empName){
        return find("empId=?1 and empName=?2", empId, empName).singleResult();
    }
     public EmployeeDetails modifyName(Long empId,String empName){
        return find("empId=?1", empId).singleResult();
    }
     public EmployeeDetails removeName(String empName,Long empId){
        return find("empName=?1 and empId=?2", empName,empId).singleResult();
    }
}
