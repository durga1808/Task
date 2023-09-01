package com.schema.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("id")
@MongoEntity(collection = "ProductDetails",database = "DurgaTest")
public class ProductDetails extends PanacheMongoEntity {
    public String firstname;
    public String lastname;
    public List<Address> address;
    public List<ProductCategory> productsCategory;
    public String time;
}
