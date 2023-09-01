package com.schema.service;

import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.schema.model.Address;
import com.schema.model.ProductDetails;


import jakarta.enterprise.context.ApplicationScoped;

import jakarta.ws.rs.core.Response;


@ApplicationScoped
public interface ProductDetailsService {

    

    void createPerson(ProductDetails productDetails);
    List<ProductDetails> getallProductDetails();
    Response getFirstname(String firstname);
   List<ProductDetails> ViewByCity(String city);
    List<ProductDetails> getByPriceLessThan(double maxPrice);
    List<ProductDetails> getProductsBySearch(String state, double maxPrice, String category);
     List<ProductDetails> getByCategories(String category);
     List<Address> getAddressDetailsByName(String firstname);
    List<Map<String, Object>> getProductDetailsByFirstName(String firstname);
    List<Document> customAggregationPipeline(String firstname);
  
}