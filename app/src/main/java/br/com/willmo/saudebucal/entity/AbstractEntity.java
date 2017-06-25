package br.com.willmo.saudebucal.entity;

import java.io.Serializable;

/**
 * Created by @WillianMuniz on 7/7/2016.
 */
public abstract class AbstractEntity implements Serializable {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
