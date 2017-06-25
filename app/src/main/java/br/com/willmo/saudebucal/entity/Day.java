package br.com.willmo.saudebucal.entity;

import br.com.willmo.saudebucal.tools.Constants;

/**
 * Created by WillianMuniz on 6/27/2016.
 */
public class Day extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private String name;
    private int day;
    private Reminder reminders;
    private DayType type;

    public String toLine() {
        StringBuilder line = new StringBuilder();
        line.append(name + Constants.PARAM_SEPARATOR);
        line.append(type.name() + Constants.PARAM_SEPARATOR);
        return line.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Reminder getReminders() {
        return reminders;
    }

    public void setReminders(Reminder reminders) {
        this.reminders = reminders;
    }

    public DayType getType() {
        return type;
    }

    public void setType(DayType type) {
        this.type = type;
    }
}
