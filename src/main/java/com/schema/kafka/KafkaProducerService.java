package com.schema.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.schema.model.ProductDetails;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class KafkaProducerService {
    
        @Inject
        @Channel("product") 
        Emitter<ProductDetails> kafkaProducer;
    
        public void sendProductDetails(ProductDetails productDetails) {
            kafkaProducer.send(productDetails);
        }
    }










































// import java.nio.charset.StandardCharsets;

// import org.apache.kafka.common.header.internals.RecordHeader;
// import org.apache.kafka.common.header.internals.RecordHeaders;
// import org.eclipse.microprofile.reactive.messaging.Channel;
// import org.eclipse.microprofile.reactive.messaging.Emitter;
// import org.eclipse.microprofile.reactive.messaging.Outgoing;

// import com.schema.model.ProductDetails;


// import io.smallrye.reactive.messaging.kafka.KafkaRecord;
// import io.smallrye.reactive.messaging.kafka.OutgoingKafkaRecord;
// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;



// @ApplicationScoped
// public class KafkaProducerService {
     
//   @Inject
//   private ProductDetails productDetails;
  
  

//   @Outgoing("kafka-producer")
//     public OutgoingKafkaRecord<Object, Object> sendMessage() {
//         // String message = "Hello, Kafka!";
//         RecordHeaders headers = new RecordHeaders();
//         headers.add(new RecordHeader("message-type", "product-details".getBytes(StandardCharsets.UTF_8)));

//         return KafkaRecord.of("kafka", null, productDetails, null, 0);

//     }

    
//     // @Channel("kafka-producer")
    
// //         
// // Emitter<ProductDetails> kafkaProducer;
// //     public static void produceMessage(ProductDetails productDetails) {
// //         ProducerResource.send(productDetails);
        

// //     }
   
  
// }
