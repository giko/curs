package org.kluge.curs;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by giko on 16.10.15.
 */
@Component
class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RatesEndpoint.class);
    }

}