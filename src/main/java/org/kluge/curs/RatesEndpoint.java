package org.kluge.curs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by giko on 16.10.15.
 */
@Component
@Path("/api/rate")
public class RatesEndpoint {
    @Autowired
    private Broker service;

    @GET
    @Path("/")
    @Produces("application/json")
    public Set<Currency> getCurrencies() {
        return service.getCurrencies();
    }


    @GET
    @Path("/{id}")
    @Produces("application/json")
    public List<Rate> getRate(@PathParam("id") @NotNull String id) {
        Date dateParam = new Date();

        return service.getRate(dateParam, dateParam, id);
    }

    @GET
    @Path("/{id}/{date}")
    @Produces("application/json")
    public List<Rate> getRate(@PathParam("id") @NotNull String id, @PathParam("date") @NotNull String date) {
        Date dateParam = new DateParam(date).getDate();

        return service.getRate(dateParam, dateParam, id);
    }

    @GET
    @Path("/{id}/{dateFrom}/{dateTo}")
    @Produces("application/json")
    public List<Rate> getRate(@PathParam("id") @NotNull String id, @PathParam("dateFrom") @NotNull String dateFromString, @PathParam("dateTo") @NotNull String dateToString) {
        Date dateFrom = new DateParam(dateFromString).getDate();
        Date dateTo = new DateParam(dateToString).getDate();

        return service.getRate(dateFrom, dateTo, id);
    }
}
