package com.junjunguo.localfilehandling.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This file is part of LocalFileHandling
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 08/03/15.
 * <p/>
 * Responsible for this file: GuoJunjun
 */
public class LocationReport {
    private String userid;
    private boolean isreported;
    private Calendar datetime;
    private double latitude, longitude;

    private SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");

    /**
     * Sets new longitude.
     *
     * @param longitude New value of longitude.
     */
    public void setLongitude(double longitude) { this.longitude = longitude; }

    /**
     * Gets latitude.
     *
     * @return Value of latitude.
     */
    public double getLatitude() { return latitude; }

    /**
     * Gets isreported.
     *
     * @return Value of isreported.
     */
    public boolean isIsreported() { return isreported; }

    /**
     * Gets longitude.
     *
     * @return Value of longitude.
     */
    public double getLongitude() { return longitude; }

    /**
     * Sets new datetime.
     *
     * @param datetime New value of datetime.
     */
    public void setDatetime(Calendar datetime) { this.datetime = datetime; }

    /**
     * Sets new datetime.
     *
     * @param datetime New value of datetime.
     */
    public void setDatetime(long datetime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(datetime);
        this.datetime = cal;
    }

    /**
     * Sets new latitude.
     *
     * @param latitude New value of latitude.
     */
    public void setLatitude(double latitude) { this.latitude = latitude; }

    /**
     * Sets new userid.
     *
     * @param userid New value of userid.
     */
    public void setUserid(String userid) { this.userid = userid; }

    /**
     * Gets datetime.
     *
     * @return Value of datetime.
     */
    public Calendar getDatetime() { return datetime; }

    /**
     * Gets userid.
     *
     * @return Value of userid.
     */
    public String getUserid() { return userid; }

    /**
     * Sets new isreported.
     *
     * @param isreported New value of isreported.
     */
    public void setIsreported(boolean isreported) { this.isreported = isreported; }

    @Override public String toString() {
        return "userid='" + userid +
                ", isreported=" + isreported +
                "\ndatetime=" + df.format(datetime.getTime()) +
                "\nlatitude=" + latitude +
                ", longitude=" + longitude;
    }
}
