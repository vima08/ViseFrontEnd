package ru.vise.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage(@QueryParam("test") Integer test, @QueryParam("q") String q) {
        // Return some cliched textual content
        return "Hi";//TestGetter.get().toString() + test.toString() + " " + q;
    }
}