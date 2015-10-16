package org.kluge.curs.cbr;

import org.junit.Test;
import org.kluge.curs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by giko on 16.10.15.
 */
public class CbrClientTest extends CursApplicationTests {
    @Autowired
    Broker client;

    @Test
    public void testGetCurrencies() throws Exception {
        Set<Currency> currencies = client.getCurrencies();
        assert !currencies.isEmpty();
        assert !StringUtils.isEmpty(currencies.iterator().next().getName());
    }

    @Test
    public void testGetRate() throws Exception {
        List<Rate> rates = client.getRate(new DateParam("20.01.2015").getDate(), new DateParam("25.01.2015").getDate(), "R01235");
        assert rates.size() == 5;
    }
}