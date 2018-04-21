package ru.vise.rest;

import altr.BackEnd;
import com.google.gson.Gson;

import javax.ws.rs.*;

@Path("/experiment")
public class ViseExperiment { //TODO Art ViseExperimentWebService
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain" //TODO Art Выпилить
    @Produces("text/plain")
    @Path("/run")
    public String getClichedMessage(@QueryParam("test") Integer test, @QueryParam("q") String q, //TODO Art Удали дублирующиеся методы
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
                                    @QueryParam("multiple") Double multiple) {
        // Return some cliched textual content //TODO Art Выпилить
        String json = new Gson().toJson(BackEnd.runSimpleEgo(capital, distrib, mu, sigma, //TODO Art Не надо создавать переменную если 1 юсадж, сразу return, или логируй переменную
                k, iteration, stepNumber, peopleCount,
                majorityThreshold, start, finish, step, var));
        return json;
    }
    @GET
    @Path("/percentage")
    public String getPercentage() {
        return Double.toString(BackEnd.getPercentage());
    }
}
