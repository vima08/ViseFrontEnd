package ru.vise.rest;

import altr.BackEnd;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.lang.reflect.Array;
import java.util.List;

@Path("/experiment")
public class ViseExperiment {
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    @Path("/run")
    public String getClichedMessage(@QueryParam("test") Integer test, @QueryParam("q") String q,
                                    @QueryParam("capital") Double capital,
                                    @QueryParam("mu") Double mu,
                                    @QueryParam("sigma") Double sigma,
                                    @QueryParam("k") Double k,
                                    @QueryParam("stepNumber") Integer stepNumber,
                                    @QueryParam("iteration") Integer iteration,
                                    @QueryParam("peopleCount") Integer peopleCount,
                                    @QueryParam("majorityThreshold") Double majorityThreshold,
                                    @QueryParam("var") String var,
                                    @QueryParam("distrib") String distrib,
                                    @QueryParam("start") Integer start,
                                    @QueryParam("finish") Integer finish,
                                    @QueryParam("step")Integer step,
                                    @QueryParam("multiple") Double multiple,
                                    @QueryParam("experimentId") String experimentId,
                                    @QueryParam("groupPeopleCount") List<Integer> groupPeopleCount,
                                    @QueryParam("a") List<Double> a,
                                    @QueryParam("e") List<Double> e,
                                    @QueryParam("g") List<Double> g,
                                    @QueryParam("groupCapital") List<Double> groupCapital,
                                    @QueryParam("formSelect") String formName) {
        // Return some cliched textual content
        String json;
        if (formName.equals("SimpleEgo")) {
            json = new Gson().toJson(BackEnd.runSimpleEgo(capital, distrib, mu, sigma,
                    k, iteration, stepNumber, peopleCount,
                    majorityThreshold, start, finish, step, var, experimentId));
        } else { // formName == "Combined"
            json = new Gson().toJson(BackEnd.runCombined(groupCapital, distrib, mu, sigma, k, iteration, stepNumber, groupPeopleCount,
                    majorityThreshold, start, finish, step, a, e, g, groupPeopleCount.size(), var, experimentId));
        }
//        String json = new Gson().toJson(BackEnd.runSimpleEgo(capital, distrib, mu, sigma,
//                k, iteration, stepNumber, peopleCount,
//                majorityThreshold, start, finish, step, var));
        return json;
    }
    @GET
    @Path("/percentage")
    public String getPercentage(@QueryParam("experimentId") String experimentId) {
        return Double.toString(BackEnd.getPercentage(experimentId));
    }

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    @Path("/test")
    public String testMethod(@QueryParam("test") Integer test, @QueryParam("q") String q,
                                    @QueryParam("capital") Double capital,
                                    @QueryParam("mu") Double mu,
                                    @QueryParam("sigma") Double sigma,
                                    @QueryParam("k") Double k,
                                    @QueryParam("stepNumber") Integer stepNumber,
                                    @QueryParam("iteration") Integer iteration,
                                    @QueryParam("peopleCount") Integer peopleCount,
                                    @QueryParam("majorityThreshold") Double majorityThreshold,
                                    @QueryParam("var") String var,
                                    @QueryParam("distrib") String distrib,
                                    @QueryParam("start") Integer start,
                                    @QueryParam("finish") Integer finish,
                                    @QueryParam("step")Integer step,
                                    @QueryParam("multiple") Double multiple,
                                    @QueryParam("experimentId") String experimentId) {
        // Return some cliched textual content


        return peopleCount.toString();
    }
}
