package com.example.demoopentracing.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@ApplicationScoped
@Path("/hello")
public class HelloWorldEndpoint {

    @GET
    @Produces("application/json")
    public TestDTO doGet() {
        TestDTO testDto = new TestDTO();
        testDto.setText("sometext");
        return testDto;
    }
}
