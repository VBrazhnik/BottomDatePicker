package com.vbrazhnikdev.bottomdatepicker.datepicker;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class CalendarDate {

    private int day;
    private int month;
    private int year;

    public CalendarDate() {
    }

    public CalendarDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String toString() {

        StringBuilder date = new StringBuilder();
        date.append(year).append(" ");
        if (month < 10) date.append("0");
        date.append(month).append(" ");
        if (day < 10) date.append("0");
        date.append(day);

        return date.toString();
    }

    public String toFormattedString() {
        StringBuilder date = new StringBuilder();
        date.append(new DateFormatSymbols().getMonths()[getMonth()]).append(" ").append(getDay()).append(", ").append(getYear());

        return date.toString();
    }

    public boolean equals(CalendarDate date) {
        return (this.day == date.getDay()) && (this.month == date.getMonth()) && (this.year == date.getYear());
    }

    public static CalendarDate getCurrentDate() {

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        return new CalendarDate(day, month, year);
    }

    public static String getFormattedMonthAndYear(int month, int year) {
        StringBuilder date = new StringBuilder();
        date.append(new DateFormatSymbols().getMonths()[month]).append(" ").append(year);

        return date.toString();
    }

    public static int compare(CalendarDate first, CalendarDate second) {
        return first.toString().compareTo(second.toString());
    }

}
