package com.analise.gtaimport.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date extends java.util.Date {

    private static final long serialVersionUID = 8314598216339402201L;

    public final static int MILLI_TO_HOUR = 1000 * 60 * 60;
    public final static int MILLI_TO_MIMUTES = 1000 * 60;

    private Calendar c;

    public static void main(String[] args) {

    }

    public Date() {
        c = Calendar.getInstance();
    }

    public Date(String data) throws ParseException {
        c = Calendar.getInstance();
        String pattern = data.charAt(4) == '-' && data.charAt(7) == '-' ? "yyyy-MM-dd" : "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        java.util.Date date = format.parse(data);
        c.setTime(date);
        super.setTime(date.getTime());

    }

    public Date(long date) {
        super(date);
        c = Calendar.getInstance();
        c.setTime(this);
    }

    public Date(GregorianCalendar date) {
        super(date.getTime().getTime());
        c = Calendar.getInstance();
        c.setTime(this);
        c.setTimeZone(date.getTimeZone());
    }

    public Date(int day, int month, int year) {
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        super.setTime(c.getTime().getTime());
    }

    public Boolean isBiggerThan(Date date) {
        c.setTime(this);
        Calendar anotherCalendar = Calendar.getInstance();
        anotherCalendar.setTime(date);
        if (c.compareTo(anotherCalendar) == 1)
            return true;
        return false;
    }

    public Boolean isSmallerThan(Date date) {
        c.setTime(this);
        Calendar anotherCalendar = Calendar.getInstance();
        anotherCalendar.setTime(date);
        if (c.compareTo(anotherCalendar) == -1)
            return true;
        return false;
    }

    public Boolean isEqualsThan(Date date) {
        c.setTime(this);
        Calendar anotherCalendar = Calendar.getInstance();
        anotherCalendar.setTime(date);
        if (c.compareTo(anotherCalendar) == 0)
            return true;
        return false;
    }

    public void addHours(int hour) {
        c.setTime(this);
        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + hour);
        super.setTime(c.getTime().getTime());
    }

    public void addDays(int days) {
        c.setTime(this);
        c.add(Calendar.DATE, days);  // number of days to add
        super.setTime(c.getTime().getTime());
    }

    public void addMinutes(int min) {
        c.setTime(this);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + min);
        super.setTime(c.getTime().getTime());
    }

    public void subtractMinutes(int min) {
        c.setTime(this);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - min);
        super.setTime(c.getTime().getTime());
    }

    public void addSeconds(int sec) {
        c.setTime(this);
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) + sec);
        super.setTime(c.getTime().getTime());
    }

    /**
     * @return Retorna uma data no formato DD/MM/YYYY
     * @author Janio
     */
    public String toStringDate() {
        c.setTime(this);
        return retornaDuasCasas(c.get(Calendar.DAY_OF_MONTH)) + "/" + retornaDuasCasas(c.get(Calendar.MONTH) + 1) + "/"
                + retornaDuasCasas(c.get(Calendar.YEAR));
    }

    /**
     * @return Retorna a hora no formato HH/MM/SS
     * @author Janio
     */

    public String toStringHour() {
        c.setTime(this);
        return retornaDuasCasas(c.get(Calendar.HOUR_OF_DAY)) + ":" + retornaDuasCasas(c.get(Calendar.MINUTE)) + ":"
                + retornaDuasCasas(c.get(Calendar.SECOND));
    }

    /**
     * @return Retorna uma data no formato DD/MM/YYYY - HH:MM:SS
     * @author Janio
     */

    public String toStringDateAndHour() {
        c.setTime(this);
        return retornaDuasCasas(c.get(Calendar.DAY_OF_MONTH)) + "/" + retornaDuasCasas(c.get(Calendar.MONTH) + 1) + "/"
                + retornaDuasCasas(c.get(Calendar.YEAR)) + " - " + retornaDuasCasas(c.get(Calendar.HOUR_OF_DAY)) + ":"
                + retornaDuasCasas(c.get(Calendar.MINUTE)) + ":" + retornaDuasCasas(c.get(Calendar.SECOND));
    }

    /**
     * @return Retorna o ano no formato YYYY
     * @author Janio
     */

    public String toStringYear() {
        c.setTime(this);
        return String.valueOf(c.get(Calendar.YEAR));
    }

    /**
     * @return Retorna o ano no formato YYYY
     * @author Janio
     */

    public int toYear() {
        c.setTime(this);
        return (c.get(Calendar.YEAR));
    }

    /**
     * @return Retorna o mes no formato MM
     * @author Janio
     */

    public String toStringMonth() {
        c.setTime(this);
        return String.valueOf(c.get(Calendar.MONTH) + 1);
    }

    public int toMonth() {
        c.setTime(this);
        return (c.get(Calendar.MONTH) + 1);
    }

    public double getMinutesDiff(Date dateDiff) {
        return (this.getTime() - dateDiff.getTime()) / MILLI_TO_MIMUTES;
    }

    public static String getSpelledMonth(int mes) {
        switch (mes) {
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Desembro";
            default:
                return "Data Inválida";
        }
    }

    public java.sql.Date toSqlDate() {
        return new java.sql.Date(this.getTime());
    }

    private String retornaDuasCasas(int valor) {
        if (valor < 10)
            return "0" + valor;
        return "" + valor;
    }

}
