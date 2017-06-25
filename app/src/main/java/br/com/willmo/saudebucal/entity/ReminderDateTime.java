package br.com.willmo.saudebucal.entity;

/**
 * Created by WillianMuniz on 6/27/2016.
 */
public enum ReminderDateTime {
    PREFIX("2016-01-"), FIRST(" 12:00:10"), SECOND(" 12:30:10");

    private String value;

    private ReminderDateTime(String value) {
        this.setValue(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
