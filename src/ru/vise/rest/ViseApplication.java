package ru.vise.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs. //TODO Art Выпилить
@ApplicationPath("/resource") //TODO Art resource -> resources
//The java class declares root resource and provider classes //TODO Art Выпилить
public class ViseApplication extends Application {
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application //TODO Art Выпилить
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add( HelloWorld.class );
        h.add( ViseExperiment.class );
        h.add( DataBase.class );
        return h;
    }
}