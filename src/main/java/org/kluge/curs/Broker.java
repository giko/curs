package org.kluge.curs;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by giko on 16.10.15.
 */
public interface Broker {
    List<Rate> getRate(Date from, Date to, String id);

    Set<Currency> getCurrencies();
}
