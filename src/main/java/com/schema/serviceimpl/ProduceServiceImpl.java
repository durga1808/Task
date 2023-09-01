package com.schema.serviceimpl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.schema.model.Address;
import com.schema.model.ProductCategory;
import com.schema.model.ProductDetails;
import com.schema.repository.ProduceDetailsRepo;
import com.schema.service.ProductDetailsService;
import com.mongodb.client.MongoClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ProduceServiceImpl implements ProductDetailsService {
    

    // private static final ProductDetails ProductDetails = null;
    @Inject
    private ProduceDetailsRepo produceDetailsRepo;

    @Inject
    MongoClient mongoClient;

    @Override
    public void createPerson(ProductDetails productDetails) { 
        String unixNanoTimeStr = productDetails.getTime();

        try {
           
            long unixNanoTime = Long.parseLong(unixNanoTimeStr);

        
            Instant instant = Instant.ofEpochSecond(0, unixNanoTime); 
            ZonedDateTime utcDateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"));

            
            ZonedDateTime istDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        
        String formattedDateTime = istDateTime.format(formatter);
            productDetails.setTime(formattedDateTime); 

            produceDetailsRepo.persist(productDetails);
        } catch (NumberFormatException e) {
            System.err.println("Invalid Unix nanotime format: " + unixNanoTimeStr);
        }
                     
    }

   @Override
   public List<ProductDetails> getallProductDetails() {
    return produceDetailsRepo.listAll();
   }

@Override
public Response getFirstname(String firstname) {
    try {
        ProductDetails productDetails=produceDetailsRepo.getProductName(firstname);
        return Response.status(200).entity(productDetails).build();
    } catch (Exception e) {
       e.printStackTrace();
        return Response.status(200).entity(e.getMessage()).build();
    }
   
}

@Override
public List<ProductDetails> ViewByCity(String city) {
   List<ProductDetails> pd =  produceDetailsRepo.getByCity(city);
   System.out.println("--------------------"+pd);
return pd;
   
   

}

@Override
public List<ProductDetails> getByPriceLessThan(double maxPrice) {
    return produceDetailsRepo.findByProductCategoryPriceLessThan(maxPrice);
}

@Override
public List<ProductDetails> getProductsBySearch(String state, double maxPrice, String category) {
 return produceDetailsRepo.findStateOrPriceOrcategories(state, maxPrice, category);
}

@Override
public List<ProductDetails> getByCategories(String category) {
  return produceDetailsRepo.findByCategories(category);
}

@Override
public List<Address> getAddressDetailsByName(String firstname) {
   return produceDetailsRepo.findaddress(firstname);
        
    // Extract and format address details from customers
    // You can modify this logic based on your actual needs
    
    
}

@Override
    public List<Map<String, Object>> getProductDetailsByFirstName(String firstname) {
        List<ProductDetails> products = produceDetailsRepo.findProductDetailsByFirstName(firstname);
        
        return products.stream()
            .filter(product -> product.getAddress() != null && !product.getAddress().isEmpty())
            .filter(product -> product.getProductsCategory() != null && !product.getProductsCategory().isEmpty())
            .map(product -> {
                Map<String, Object> productInfo = new HashMap<>();
                productInfo.put("state", product.getAddress().get(0).getState());
                
                List<String> name = product.getProductsCategory().stream()
                    .map(ProductCategory::getName)
                    .collect(Collectors.toList());
                productInfo.put("name", name);
                
                
                return productInfo;
            })
            .collect(Collectors.toList());
    }
    

    @Override
    public List<Document> customAggregationPipeline(String firstname) {
    
        MongoDatabase database = mongoClient.getDatabase("DurgaTest");
        MongoCollection<Document> collection = database.getCollection("ProductDetails");
    
        // Check if documents matching the query exist
        long count = collection.countDocuments(new Document("firstname", firstname));
        System.out.println("Number of documents matching the query: " + count);
    
        List<Document> pipeline = Arrays.asList(
    new Document("$match", new Document("firstname", firstname
    )),
    new Document("$project", new Document("_id", 0L)
        .append("firstname", 1L)
        .append("lastname", 1L)
        .append("address.city", new Document("$arrayElemAt", Arrays.asList("$address.city", 0L)))
        .append("productsCategory.price", new Document("$arrayElemAt", Arrays.asList("$productsCategory.price", 0L)))
    )
);
    
        // Print the pipeline
        System.out.println("Aggregation pipeline: " + pipeline);
    
        List<Document> result = collection.aggregate(pipeline, Document.class).into(new ArrayList<>());
    
        // Print the result
        System.out.println("Aggregation result: " + result);
    
        return result;
    }

}
