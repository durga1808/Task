package com.schema.repository;

import java.util.List;
import java.util.Map;

import com.schema.model.Address;
import com.schema.model.ProductDetails;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ProduceDetailsRepo implements PanacheMongoRepository<ProductDetails>{

    public ProductDetails getProductName(String firstname){
return find("firstname=?1",firstname).singleResult();
    }

    public List<ProductDetails> getByCity(String city){
        System.out.println(city);
        List<ProductDetails> pd = find("address.city",city).list();
        System.out.println("pd-------------------"+pd);
        return pd;
    }

     public List<ProductDetails> findByProductCategoryPriceLessThan(double maxPrice) {
        return find("productsCategory.price <= ?1", maxPrice).list();
    }

    public List<ProductDetails> findStateOrPriceOrcategories(String state, double maxPrice, String category){
        return find("address.state=?1 or productsCategory.price=?2 or productsCategory.category=?3",state,maxPrice,category).list();
    }

    public List<ProductDetails> findByCategories(String category){
        return find("productsCategory.category=?1",category).list();
    }

    public List<Address> findaddress(String firstname){
        return find("firstname=?1",firstname).firstResult().getAddress();
    }

   public List<ProductDetails> findProductDetailsByFirstName(String firstname){
    return find("firstname=?1",firstname).list();
   }
}








