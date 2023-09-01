package com.schema.controller;

import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.schema.model.Address;
import com.schema.model.ProductDetails;
import com.schema.service.ProductDetailsService;

import jakarta.inject.Inject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;







@Path("/view")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class ProductResource {

    @Inject
    private ProductDetailsService productDetailsService;
    
@POST
@Path("/create")    
// public Response addProducts(){}
public Response addPerson(ProductDetails productDetails) {
    try {
            productDetailsService.createPerson(productDetails);
return Response.status(200).entity(productDetails).build();
    } catch (Exception e) {
        return Response.status(500).entity(e.getMessage()).build();
    }
}

@GET
@Path("/getallproducts")
public Response getAllProducts(){
    productDetailsService.getallProductDetails();
    return Response.status(200).entity(productDetailsService).build();
}

@GET
@Path("/getFirstname")
public Response getByfirstname(@QueryParam("firstname") String firstname){
    return productDetailsService.getFirstname(firstname);
}
@GET
@Path("/getcity")
public Response getCity(@QueryParam("city") String city){
    List<ProductDetails> products = productDetailsService.ViewByCity(city);
    return Response.status(200).entity(products).build();
}
@GET
@Path("/getByPrice")
public Response getByPrice(@QueryParam("maxPrice") double maxPrice) {
    List<ProductDetails> products = productDetailsService.getByPriceLessThan(maxPrice);
    return Response.status(200).entity(products).build();
}


@GET
@Path("/searchDetails")
public Response getBySearch(@QueryParam("state") String state, @QueryParam("maxPrice") double maxPrice, @QueryParam("category") String category){
    List<ProductDetails> products = productDetailsService.getProductsBySearch(state, maxPrice, category);
    return Response.status(200).entity(products).build();
}

@GET
@Path("/searchCategory")
public Response getCategories(@QueryParam("category") String category){
    List<ProductDetails> products = productDetailsService.getByCategories(category);
    return Response.status(200).entity(products).build();
}

@GET
@Path("/getNameToAddress")
public Response getFirstnameAddress(@QueryParam("firstname") String firstname){
    List<Address> products = productDetailsService.getAddressDetailsByName(firstname);
     return Response.status(200).entity(products).build();
}
@GET
@Path("/getFirstnametoDetails/{firstname}")
public Response getFistnameTodetails(@PathParam("firstname") String firstname){
    List<Map<String, Object>> productDetails = productDetailsService.getProductDetailsByFirstName(firstname);
     return Response.status(200).entity(productDetails).build();

}

@GET
@Path("/aggregate/{firstname}")
public List<Document> customAggregationPipeline(@PathParam("firstname") String firstname) {
    List<Document> project = productDetailsService.customAggregationPipeline(firstname);
    return project;
}
}
