package com.schema.kafka;

import java.util.concurrent.CompletionStage;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
  @Path("/kafka-producer")
public class ProducerResource {


    @Inject
    ProducerService producerService;

    @POST
    @Path("/send")
    public Response sendMessageToKafka() {
        producerService.sendMessage();
        return Response.ok("durga").build();
    }

   
}
