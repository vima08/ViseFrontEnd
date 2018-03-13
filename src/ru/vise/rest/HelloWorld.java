package ru.vise.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import altr.BackEnd;
import com.google.gson.Gson;

import static sun.plugin2.util.PojoUtil.toJson;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage(@QueryParam("test") Integer test, @QueryParam("q") String q) {
        // Return some cliched textual content
        return new Gson().toJson(BackEnd.runSimpleEgo(10, "ParetoDistribution", 0.1, 1,
                20, 100, 10, 101,
                0.5, -250, 100, 10));
//        return "Hi";//TestGetter.get().toString() + test.toString() + " " + q;
    }


}