package br.com.willmo.saudebucal.entity;

import android.content.res.Resources;

import org.joda.time.LocalDateTime;

import java.io.Serializable;

import br.com.willmo.saudebucal.reminderApi.AppSingleton;
import br.com.willmo.saudebucal.tools.Constants;
import br.com.willmo.saudebucal.tools.SqliteUtils;
import br.com.willmo.saudebucal.tools.Utils;

/**
 * Created by WillianMuniz on 6/27/2016.
 */
public class Reminder extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private Day day;
    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private String imagePath1;
    private String imagePath2;
    private String videoPath;
    private LocalDateTime dateTime;
    private String name;
    private LocalDateTime lastView;
    private LocalDateTime beenViewed;
    private String notificationMessage;

    public Reminder() {
    }

    public Reminder(String name) {
        this.name = name;
    }

    public String toLine() {
        StringBuilder line = new StringBuilder();
        line.append(name + Constants.PARAM_SEPARATOR);
        line.append(lastView.toString(SqliteUtils.DATETIME_FORMAT) + Constants.PARAM_SEPARATOR);
        line.append(beenViewed.toString(SqliteUtils.DATETIME_FORMAT) + Constants.PARAM_SEPARATOR);
        line.append(day.toLine() + Constants.PARAM_SEPARATOR);
        line.append("\r\n");
        return line.toString();
    }

    @Override
    public String toString() {

        return Utils.getStringByName(name) + " (" + dateTime.toString("dd/MM/yyyy HH:mm") + ")";
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public LocalDateTime getBeenViewed() {
        return beenViewed;
    }

    public void setBeenViewed(LocalDateTime beenViewed) {
        this.beenViewed = beenViewed;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

    public String getImagePath1() {
        return imagePath1;
    }

    public void setImagePath1(String imagePath1) {
        this.imagePath1 = imagePath1;
    }

    public String getImagePath2() {
        return imagePath2;
    }

    public void setImagePath2(String imagePath2) {
        this.imagePath2 = imagePath2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public LocalDateTime getLastView() {
        return lastView;
    }

    public void setLastView(LocalDateTime lastView) {
        this.lastView = lastView;
    }
}
