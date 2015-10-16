package org.kluge.curs;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by giko on 16.10.15.
 */
public class Rate {
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date date;
    private Float value;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
