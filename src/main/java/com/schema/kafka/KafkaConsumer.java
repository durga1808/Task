package com.schema.kafka;
import java.util.List;

import com.schema.model.ProductDetails;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


@ApplicationScoped
@Path("/kafka-consumer")
public class KafkaConsumer {

    @Inject
    private KafkaConsumerService kafkaConsumerService; 

    @GET
    public List<ProductDetails> getProductDetails() {
        return kafkaConsumerService.getProductDetails();
    }
}



























// import org.eclipse.microprofile.reactive.messaging.Incoming;

// import com.schema.model.ProductDetails;



// public class KafkaConsumer {
    
//     @Incoming("kafka-producer")
//     public void consumeMessage(ProductDetails productDetails) {
//         System.out.println("simple example for kafka " + productDetails);
//     }
// }


