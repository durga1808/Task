package com.crudtask;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.crudtask.entity.EmployeeDetails;

import io.quarkus.builder.Json;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
public class EmployeeTest {
    @Test
    void createEmployees(){
        EmployeeDetails employeeDetails=new EmployeeDetails();
        employeeDetails.setEmpId(105L);
        employeeDetails.setEmpName("vishu");
        employeeDetails.setCity("madurai");
        employeeDetails.setCompany("vsd");
        EmployeeDetails employees= RestAssured.given()
        .body(employeeDetails)
        .contentType(ContentType.JSON)
        .when().post("/show/create")
        .then()
        .statusCode(201)
        .extract()
        .body().as(EmployeeDetails.class);
    }
    @Test
    void getEmployees(){
        List<EmployeeDetails> employeeDetails= RestAssured.given()
        // .body(employeeDetails)
        .contentType(ContentType.JSON)
        .when().get("/show/view")
        .then()
        .statusCode(200)
        .extract()
        .jsonPath().getList(".", EmployeeDetails.class);
    }
    @Test
    void getIdAndName(){
        EmployeeDetails employeeDetails=RestAssured.given()
        .queryParam("empId",102L)
        .queryParam("empName","Durgalakshmi M")
        .when().get("/show/showEmpIdAndName")
        .then()
        .statusCode(200)
        .extract()
        .body().as(EmployeeDetails.class);
    }
    @Test
    void updateIdAndName(){
        EmployeeDetails employeeDetails=new EmployeeDetails();
        // employeeDetails.setEmpId(105L);
        // employeeDetails.setEmpName("vishu");
        employeeDetails.setCity("madurai");
        employeeDetails.setCompany("vsd");
        RestAssured.given()
        .queryParam("empId",103L)
        .queryParam("empName","abi")
        .body(employeeDetails)
        .contentType(ContentType.JSON)
        .when().put("/show/updateIdAndName")
        .then()
        .statusCode(200)
        .extract()
        .body().as(EmployeeDetails.class);

    }
    @Test
    void deleteName(){
        RestAssured.given()
        .queryParam("empName","deepi")
        .queryParam("empId",109L)
        .contentType(ContentType.JSON)
        .when().delete("/show/remove")
        .then().statusCode(200);
    }
}
