 package com.schema.kafka;


import com.schema.model.ProductDetails;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



@ApplicationScoped
@Path("/product-details")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KafkaProducerResource {

    @Inject
    private KafkaProducerService kafkaProducerService;

    @POST
    public Response sendProductDetails(ProductDetails productDetails) {
        kafkaProducerService.sendProductDetails(productDetails);
        return Response.ok().build();
    }
}

























































// import java.util.concurrent.CompletionStage;

// import org.eclipse.microprofile.openapi.annotations.media.Content;
// import org.eclipse.microprofile.openapi.annotations.media.Schema;
// import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

// import com.schema.model.ProductDetails;

// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;
// import jakarta.ws.rs.Consumes;

// import jakarta.ws.rs.POST;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;
//   @Path("/my-kafka-producer")
//   @ApplicationScoped
// public class ProducerResource {

//      @Inject
//     KafkaConsumer kafkaConsumer; 
    

//     @Inject
//     ProducerService producerService;

//     @POST
//     @Path("/send")
//     @Consumes(MediaType.APPLICATION_JSON)
//     public Response sendMessageToKafka() {
//         producerService.sendMessage();
//         return Response.ok("message send to kafka").build();
//     }
//     //  public Response produceKafkaMessage(
//     //     @RequestBody(
//     //         description = "ProductDetails object to send to Kafka",
//     //         content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProductDetails.class))
//     //     ) ProductDetails productDetails
//     // ) {
//     //     ProducerService.produceMessage(productDetails);
//     //     return Response.ok("Kafka message sent").build();
//     // }

//     // public static void send(ProductDetails productDetails) {

//     // }

    
   
// }
