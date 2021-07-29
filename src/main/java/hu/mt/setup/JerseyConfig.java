package hu.mt.setup;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("hu.mt.webservices","hu.mt.security");
        register(RolesAllowedDynamicFeature.class);
    }
}
