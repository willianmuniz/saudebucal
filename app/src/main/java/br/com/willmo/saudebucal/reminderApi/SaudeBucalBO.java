package br.com.willmo.saudebucal.reminderApi;

import android.content.Context;
import android.util.Log;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import br.com.willmo.saudebucal.entity.Day;
import br.com.willmo.saudebucal.entity.DayType;
import br.com.willmo.saudebucal.entity.Reminder;
import br.com.willmo.saudebucal.entity.ReminderDateTime;
import br.com.willmo.saudebucal.persistent.dao.ReminderDao;
import br.com.willmo.saudebucal.tools.SqliteUtils;

/**
 * Created by @WillianMuniz on 7/12/2016.
 */
public class SaudeBucalBO {

    public void makeExport() {

        //TODO criar aqui a String que será exportada para arquivo ou enviada por email
    }

    public List<Reminder> getInitialData() {
        //TODO colocar aqui os reminders iniciais que são TEMPLATE
        List<Reminder> listReminders = new ArrayList<Reminder>();

        int param = 0;
        Day d1 = new Day();
        d1.setName("Dia " + (++param));
        d1.setType(DayType.TEMPLATE);
        d1.setDay(param);

        Day d2 = new Day();
        d2.setName("Dia " + (++param));
        d2.setType(DayType.TEMPLATE);
        d2.setDay(param);

        Day d3 = new Day();
        d3.setName("Dia " + (++param));
        d3.setType(DayType.TEMPLATE);
        d3.setDay(param);

        Day d4 = new Day();
        d4.setName("Dia " + (++param));
        d4.setType(DayType.TEMPLATE);
        d4.setDay(param);

        Day d5 = new Day();
        d5.setName("Dia " + (++param));
        d5.setType(DayType.TEMPLATE);
        d5.setDay(param);

        Day d6 = new Day();
        d6.setName("Dia " + (++param));
        d6.setType(DayType.TEMPLATE);
        d6.setDay(param);

        Day d7 = new Day();
        d7.setName("Dia " + (++param));
        d7.setType(DayType.TEMPLATE);
        d7.setDay(param);

        Day d8 = new Day();
        d8.setName("Dia " + (++param));
        d8.setType(DayType.TEMPLATE);
        d8.setDay(param);

        Day d9 = new Day();
        d9.setName("Dia " + (++param));
        d9.setType(DayType.TEMPLATE);
        d9.setDay(param);

        Day d10 = new Day();
        d10.setName("Dia " + (++param));
        d10.setType(DayType.TEMPLATE);
        d10.setDay(param);

        Day d11 = new Day();
        d11.setName("Dia " + (++param));
        d11.setType(DayType.TEMPLATE);
        d11.setDay(param);

        Day d12 = new Day();
        d12.setName("Dia " + (++param));
        d12.setType(DayType.TEMPLATE);
        d12.setDay(param);

        Day d13 = new Day();
        d13.setName("Dia " + (++param));
        d13.setType(DayType.TEMPLATE);
        d13.setDay(param);

        Day d14 = new Day();
        d14.setName("Dia " + (++param));
        d14.setType(DayType.TEMPLATE);
        d14.setDay(param);

        Day d15 = new Day();
        d15.setName("Dia " + (++param));
        d15.setType(DayType.TEMPLATE);
        d15.setDay(param);

        Day d16 = new Day();
        d16.setName("Dia " + (++param));
        d16.setType(DayType.TEMPLATE);
        d16.setDay(param);

        Day d17 = new Day();
        d17.setName("Dia " + (++param));
        d17.setType(DayType.TEMPLATE);
        d17.setDay(param);

        Day d18 = new Day();
        d18.setName("Dia " + (++param));
        d18.setType(DayType.TEMPLATE);
        d18.setDay(param);

        Day d19 = new Day();
        d19.setName("Dia " + (++param));
        d19.setType(DayType.TEMPLATE);
        d19.setDay(param);

        Day d20 = new Day();
        d20.setName("Dia " + (++param));
        d20.setType(DayType.TEMPLATE);
        d20.setDay(param);

        Day d21 = new Day();
        d21.setName("Dia " + (++param));
        d21.setType(DayType.TEMPLATE);
        d21.setDay(param);

        Day d22 = new Day();
        d22.setName("Dia " + (++param));
        d22.setType(DayType.TEMPLATE);
        d22.setDay(param);

        Day d23 = new Day();
        d23.setName("Dia " + (++param));
        d23.setType(DayType.TEMPLATE);
        d23.setDay(param);

        Day d24 = new Day();
        d24.setName("Dia " + (++param));
        d24.setType(DayType.TEMPLATE);
        d24.setDay(param);

        Day d25 = new Day();
        d25.setName("Dia " + (++param));
        d25.setType(DayType.TEMPLATE);
        d25.setDay(param);

        Day d26 = new Day();
        d26.setName("Dia " + (++param));
        d26.setType(DayType.TEMPLATE);
        d26.setDay(param);

        Day d27 = new Day();
        d27.setName("Dia " + (++param));
        d27.setType(DayType.TEMPLATE);
        d27.setDay(param);

        Day d28 = new Day();
        d28.setName("Dia " + (++param));
        d28.setType(DayType.TEMPLATE);
        d28.setDay(param);

        Day d29 = new Day();
        d29.setName("Dia " + (++param));
        d29.setType(DayType.TEMPLATE);
        d29.setDay(param);

        Day d30 = new Day();
        d30.setName("Dia " + (++param));
        d30.setType(DayType.TEMPLATE);
        d30.setDay(param);

        param = 0;
        Reminder r1 = new Reminder();
        r1.setName("tip" + (++param));
        r1.setText1("tip" + param + "_text1");
        r1.setText2("tip" + param + "_text2");
        r1.setDay(d1);
        r1.setDateTime(LocalDateTime.parse(ReminderDateTime.PREFIX.toString()
                        + d1.getDay() + ReminderDateTime.FIRST.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r2 = new Reminder();
        r2.setName("tip" + (++param));
        r2.setText1("tip" + param + "_text1");
        r2.setDay(d1);
        r2.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d1.getDay() + ReminderDateTime.SECOND.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r3 = new Reminder();
        r3.setName("tip" + (++param));
        r3.setText1("tip" + param + "_text1");
        r3.setImagePath1("drawable/image1");
        r3.setText2("tip" + param + "_text2");
        r3.setImagePath2("drawable/image2");
        r3.setDay(d2);
        r3.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d2.getDay() + ReminderDateTime.FIRST.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r4 = new Reminder();
        r4.setName("tip" + (++param));
        r4.setText1("tip" + param + "_text1");
        r4.setDay(d2);
        r4.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d2.getDay() + ReminderDateTime.SECOND.toString(),
                SqliteUtils.getDateTimeFormatter()));
        r4.setVideoPath("video4");

        Reminder r5 = new Reminder();
        r5.setName("tip" + (++param));
        r5.setText1("tip" + param + "_text1");
        r5.setDay(d3);
        r5.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d3.getDay() + ReminderDateTime.FIRST.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r6 = new Reminder();
        r6.setName("tip" + (++param));
        r6.setText1("tip" + param + "_text1");
        r6.setVideoPath("video6and33");
        r6.setDay(d3);
        r6.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d3.getDay() + ReminderDateTime.SECOND.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r7 = new Reminder();
        r7.setName("tip" + (++param));
        r7.setText1("tip" + param + "_text1");
        r7.setDay(d4);
        r7.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d4.getDay() + ReminderDateTime.FIRST.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r8 = new Reminder();
        r8.setName("tip" + (++param));
        r8.setText1("tip" + param + "_text1");
        r8.setImagePath1("drawable/image4");
        r8.setDay(d4);
        r8.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d4.getDay() + ReminderDateTime.SECOND.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r9 = new Reminder();//TODO parei aqui
        r9.setName("tip" + (++param));
        r9.setText1("tip" + param + "_text1");
        r9.setDay(d5);
        r9.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d5.getDay() + ReminderDateTime.FIRST.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r10 = new Reminder();
        r10.setName("tip" + (++param));
        r10.setText3("tip" + param + "_text1");
        r10.setVideoPath("video10and37");
        r10.setDay(d5);
        r10.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d5.getDay() + ReminderDateTime.SECOND.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r11 = new Reminder();
        r11.setName("tip" + (++param));
        r11.setText1("tip" + param + "_text1");
        r11.setImagePath1("drawable/image5");
        r11.setDay(d6);
        r11.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d6.getDay() + ReminderDateTime.FIRST.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r12 = new Reminder();
        r12.setName("tip" + (++param));
        r12.setText1("tip" + param + "_text1");
        r12.setVideoPath("video12and39");
        r12.setDay(d6);
        r12.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d6.getDay() + ReminderDateTime.SECOND.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r13 = new Reminder();
        r13.setName("tip" + (++param));
        r13.setText1("tip" + param + "_text1");
        r13.setDay(d7);
        r13.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d7.getDay() + ReminderDateTime.FIRST.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r14 = new Reminder();
        r14.setName("tip" + (++param));
        r14.setText1("tip" + param + "_text1");
        r14.setVideoPath("video14and41");
        r14.setDay(d7);
        r14.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d7.getDay() + ReminderDateTime.SECOND.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r15 = new Reminder();
        r15.setName("tip" + (++param));
        r15.setText1("tip" + param + "_text1");
        r15.setImagePath1("drawable/image5");
        r15.setDay(d8);
        r15.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d8.getDay() + ReminderDateTime.FIRST.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r16 = new Reminder();
        r16.setName("tip" + (++param));
        r16.setText1("tip" + param + "_text1");
        r16.setDay(d8);
        r16.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d8.getDay() + ReminderDateTime.SECOND.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r17 = new Reminder();
        r17.setName("tip" + (++param));
        r17.setText1("tip" + param + "_text1");
        r17.setDay(d9);
        r17.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d9.getDay() + ReminderDateTime.FIRST.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r18 = new Reminder();
        r18.setName("tip" + (++param));
        r18.setText1("tip" + param + "_text1");
        r18.setVideoPath("video18");
        r18.setDay(d9);
        r18.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d9.getDay() + ReminderDateTime.SECOND.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r19 = new Reminder();
        r19.setName("tip" + (++param));
        r19.setText1("tip" + param + "_text1");
        r19.setVideoPath("video19");
        r19.setDay(d10);
        r19.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d10.getDay() + ReminderDateTime.FIRST.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r20 = new Reminder();
        r20.setName("tip" + (++param));
        r20.setText1("tip" + param + "_text1");
        r20.setVideoPath("video20and47");
        r20.setDay(d10);
        r20.setDateTime(LocalDateTime.parse(
                ReminderDateTime.PREFIX.toString()
                        + d10.getDay() + ReminderDateTime.SECOND.toString(),
                SqliteUtils.getDateTimeFormatter()));

        Reminder r21 = new Reminder();
        r21.setName("tip" + (++param));
        r21.setText1("tip" + param + "_text1");
        r21.setDay(d2);
        r21.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r22 = new Reminder();
        r22.setName("tip" + (++param));
        r22.setText1("tip" + param + "_text1");
        r22.setVideoPath("video22");
        r22.setDay(d2);
        r22.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r23 = new Reminder();
        r23.setName("tip" + (++param));
        r23.setText1("tip" + param + "_text1");
        r23.setDay(d2);
        r23.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r24 = new Reminder();
        r24.setName("tip" + (++param));
        r24.setText1("tip" + param + "_text1");
        r24.setVideoPath("video24");
        r24.setDay(d2);
        r24.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r25 = new Reminder();
        r25.setName("tip" + (++param));
        r25.setText1("tip" + param + "_text1");
        r25.setDay(d2);
        r25.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r26 = new Reminder();
        r26.setName("tip" + (++param));
        r26.setText1("tip" + param + "_text1");
        r26.setVideoPath("video26");
        r26.setDay(d2);
        r26.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r27 = new Reminder();
        r27.setName("tip" + (++param));
        r27.setText1("tip" + param + "_text1");
        r27.setDay(d2);
        r27.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r28 = new Reminder();
        r28.setName("tip" + (++param));
        r28.setText1("tip" + param + "_text1");
        r28.setDay(d2);
        r28.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r29 = new Reminder();
        r29.setName("tip" + (++param));
        r29.setText1("tip" + param + "_text1");
        r29.setDay(d2);
        r29.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r30 = new Reminder();
        r30.setName("tip" + (++param));
        r30.setText1("tip" + param + "_text1");
        r30.setDay(d2);
        r30.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r31 = new Reminder();
        r31.setName("tip" + (++param));
        r31.setText1("tip" + param + "_text1");
        r31.setVideoPath("video31");
        r31.setDay(d2);
        r31.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r32 = new Reminder();
        r32.setName("tip" + (++param));
        r32.setText1("tip" + param + "_text1");
        r32.setDay(d2);
        r32.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r33 = new Reminder();
        r33.setName("tip" + (++param));
        r33.setText1("tip" + param + "_text1");
        r33.setVideoPath("video6and33");
        r33.setDay(d2);
        r33.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r34 = new Reminder();
        r34.setName("tip" + (++param));
        r34.setText1("tip" + param + "_text1");
        r34.setDay(d2);
        r34.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r35 = new Reminder();
        r35.setName("tip" + (++param));
        r35.setText1("tip" + param + "_text1");
        r35.setDay(d2);
        r35.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r36 = new Reminder();
        r36.setName("tip" + (++param));
        r36.setText1("tip" + param + "_text1");
        r36.setDay(d2);
        r36.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r37 = new Reminder();
        r37.setName("tip" + (++param));
        r37.setText1("tip" + param + "_text1");
        r37.setVideoPath("video10and37");
        r37.setDay(d2);
        r37.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r38 = new Reminder();
        r38.setName("tip" + (++param));
        r38.setText1("tip" + param + "_text1");
        r38.setDay(d2);
        r38.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r39 = new Reminder();
        r39.setName("tip" + (++param));
        r39.setText1("tip" + param + "_text1");
        r39.setVideoPath("video12and39");
        r39.setDay(d2);
        r39.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r40 = new Reminder();
        r40.setName("tip" + (++param));
        r40.setText1("tip" + param + "_text1");
        r40.setDay(d2);
        r40.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r41 = new Reminder();
        r41.setName("tip" + (++param));
        r41.setText1("tip" + param + "_text1");
        r41.setVideoPath("video14and41");
        r41.setDay(d2);
        r41.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r42 = new Reminder();
        r42.setName("tip" + (++param));
        r42.setText1("tip" + param + "_text1");
        r42.setDay(d2);
        r42.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r43 = new Reminder();
        r43.setName("tip" + (++param));
        r43.setText1("tip" + param + "_text1");
        r43.setDay(d2);
        r43.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r44 = new Reminder();
        r44.setName("tip" + (++param));
        r44.setText1("tip" + param + "_text1");
        r44.setDay(d2);
        r44.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r45 = new Reminder();
        r45.setName("tip" + (++param));
        r45.setText1("tip" + param + "_text1");
        r45.setVideoPath("video45");
        r45.setDay(d2);
        r45.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r46 = new Reminder();
        r46.setName("tip" + (++param));
        r46.setText1("tip" + param + "_text1");
        r46.setDay(d2);
        r46.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r47 = new Reminder();
        r47.setName("tip" + (++param));
        r47.setText1("tip" + param + "_text1");
        r47.setVideoPath("video20and47");
        r47.setDay(d2);
        r47.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r48 = new Reminder();
        r48.setName("tip" + (++param));
        r48.setText1("tip" + param + "_text1");
        r48.setDay(d2);
        r48.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r49 = new Reminder();
        r49.setName("tip" + (++param));
        r49.setText1("tip" + param + "_text1");
        r49.setDay(d2);
        r49.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r50 = new Reminder();
        r50.setName("tip" + (++param));
        r50.setText1("tip" + param + "_text1");
        r50.setDay(d2);
        r50.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r51 = new Reminder();
        r51.setName("tip" + (++param));
        r51.setText1("tip" + param + "_text1");
        r51.setDay(d2);
        r51.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r52 = new Reminder();
        r52.setName("tip" + (++param));
        r52.setText1("tip" + param + "_text1");
        r52.setDay(d2);
        r52.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r53 = new Reminder();
        r53.setName("tip" + (++param));
        r53.setText1("tip" + param + "_text1");
        r53.setVideoPath("video53");
        r53.setDay(d2);
        r53.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r54 = new Reminder();
        r54.setName("tip" + (++param));
        r54.setText1("tip" + param + "_text1");
        r54.setDay(d2);
        r54.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r55 = new Reminder();
        r55.setName("tip" + (++param));
        r55.setText1("tip" + param + "_text1");
        r55.setDay(d2);
        r55.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r56 = new Reminder();
        r56.setName("tip" + (++param));
        r56.setText1("tip" + param + "_text1");
        r56.setDay(d2);
        r56.setDateTime(LocalDateTime.parse("2016-01-" + d2.getDay() + " 12:30:00",
                SqliteUtils.getDateTimeFormatter()));

        Reminder r57 = new Reminder();
        r57.setName("tip" + (++param));
        r57.setText1("tip" + param + "_text1");
        r57.setDay(d30);
        r57.setDateTime(LocalDateTime.parse("2016-01-" + d30.getDay() + " 20:00:00",
                SqliteUtils.getDateTimeFormatter()));

//        Day d = new Day();
//        d.setType(DayType.TEMPLATE);

        //cadastros teste
//        r1.setText1("Você lembra o que é a placa bacteriana??");
//        r1.setImagePath1("drawable/image1");
//        r1.setText2("Ela causa uma inflamação na gengiva e sangramento gengival.");
//        r1.setImagePath2("drawable/image2");
//        r1.setDay(d);
//        r1.setVideoPath("video1");
//        r2.setText1("Você sabe o que devemos fazer com a placa bacteriana??");
//        r2.setImagePath2("drawable/image1");
//        r2.setText3("Escova e fio dental nela!!");
//        r3.setText1("Assista um vídeo sobre bla bla bla:");
//        r3.setVideoPath("sample");


        listReminders.add(r1);
        listReminders.add(r2);
        listReminders.add(r3);
        listReminders.add(r4);
        listReminders.add(r5);
        listReminders.add(r6);
        listReminders.add(r7);
        listReminders.add(r8);
        listReminders.add(r9);
        listReminders.add(r10);
        listReminders.add(r11);
        listReminders.add(r12);
        listReminders.add(r13);
        listReminders.add(r14);
        listReminders.add(r15);
        listReminders.add(r16);
        listReminders.add(r17);
        listReminders.add(r18);
        listReminders.add(r19);
        listReminders.add(r20);

        return listReminders;

    }

    /**
     * Busca na base de dados os reminders TEMPLATE e agenda os alarmes
     *
     * @param context
     */
    public void setAllAlarms(Context context) {
        try {
            ReminderDao reminderDao = new ReminderDao(context);
            ScheduleReminder scheduler = new ScheduleReminder();
            //Carrega os dados iniciais
            List<Reminder> reminders = reminderDao.getList(DayType.TEMPLATE);
            for (Reminder r : reminders) {
                scheduler.setAlarm(context, r);
            }
        } catch
                (Exception e) {
            Log.e("SaudeBucalBO", "setAllAlarms", e);
        }
    }

    /**
     * Busca na base de dados os reminders TEMPLATE e agenda os alarmes
     *
     * @param context
     */
    public void setAllAlarmsAfterNow(Context context) {
        try {
            ReminderDao reminderDao = new ReminderDao(context);
            ScheduleReminder scheduler = new ScheduleReminder();
            //Carrega os dados iniciais
            List<Reminder> reminders = reminderDao.getList(DayType.TEMPLATE);
            for (Reminder r : reminders) {
                if (r.getDateTime().compareTo(LocalDateTime.now()) >= 0) {

                    scheduler.setAlarm(context, r);
                }
            }
        } catch
                (Exception e) {
            Log.e("SaudeBucalBO", "setAllAlarms", e);
        }
    }
}
