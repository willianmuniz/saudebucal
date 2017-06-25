package br.com.willmo.saudebucal.entity;

import org.joda.time.LocalDate;

/**
 * Created by @WillianMuniz on 7/7/2016.
 */
public class Contact extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private String name;
    private String phone;
    private LocalDate initialDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }
}
