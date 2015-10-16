package org.kluge.curs.cbr;

import org.kluge.curs.Broker;
import org.kluge.curs.Currency;
import org.kluge.curs.DateParam;
import org.kluge.curs.Rate;
import org.kluge.curs.cbr.generated.ValCurs;
import org.kluge.curs.cbr.generated.Valuta;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by giko on 16.10.15.
 */
@Service
public class CbrClient implements Broker {

    @Override
    @Cacheable("rates")
    public List<Rate> getRate(Date from, Date to, String id) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        RestTemplate restTemplate = new RestTemplate();
        ValCurs valCurs = restTemplate.getForObject("http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=" + df.format(from) + "&date_req2=" + df.format(to) + "&VAL_NM_RQ=" + id, ValCurs.class);

        List<Rate> result = new LinkedList<>();
        for (ValCurs.Record record : valCurs.getRecord()) {
            Rate rate = new Rate();
            rate.setDate(new DateParam(record.getDate()).getDate());
            rate.setValue(Float.valueOf(record.getValue().replace(',', '.').replaceAll(String.valueOf((char) 160), "")));
            result.add(rate);
        }

        return result;
    }

    @Override
    @Cacheable("currencies")
    public Set<Currency> getCurrencies() {
        RestTemplate restTemplate = new RestTemplate();
        Valuta valuta = restTemplate.getForObject("http://www.cbr.ru/scripts/XML_val.asp?d=0", Valuta.class);
        Valuta valuta1 = restTemplate.getForObject("http://www.cbr.ru/scripts/XML_val.asp?d=1", Valuta.class);
        valuta.getItem().addAll(valuta1.getItem());

        Set<Currency> currencies = new HashSet<>();
        for (Valuta.Item item : valuta.getItem()) {
            Currency currency = new Currency();
            currency.setId(item.getID());
            currency.setName(item.getName());
            currencies.add(currency);
        }

        return currencies;
    }
}
