package com.junjunguo.sqlitedb.model;

import java.util.Calendar;

/**
 * This file is part of LocalFileHandling
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 06/03/15.
 * <p/>
 * Responsible for this file: GuoJunjun
 */
public class TextReport {
    private String userid, report;
    private boolean isreported;
    private Calendar datetime;
    private double latitude, longitude;

    /**
     * Sets new report.
     *
     * @param report New value of report.
     */
    public void setReport(String report) { this.report = report; }

    /**
     * Sets new datetime.
     *
     * @param datetime New value of datetime.
     */
    public void setDatetime(Calendar datetime) { this.datetime = datetime; }

    /**
     * Gets userid.
     *
     * @return Value of userid.
     */
    public String getUserid() { return userid; }

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
     * Sets new userid.
     *
     * @param userid New value of userid.
     */
    public void setUserid(String userid) { this.userid = userid; }

    /**
     * Gets report.
     *
     * @return Value of report.
     */
    public String getReport() { return report; }

    /**
     * Gets datetime.
     *
     * @return Value of datetime.
     */
    public Calendar getDatetime() { return datetime; }

    /**
     * Sets new longitude.
     *
     * @param longitude New value of longitude.
     */
    public void setLongitude(double longitude) { this.longitude = longitude; }

    /**
     * Gets longitude.
     *
     * @return Value of longitude.
     */
    public double getLongitude() { return longitude; }

    /**
     * Sets new latitude.
     *
     * @param latitude New value of latitude.
     */
    public void setLatitude(double latitude) { this.latitude = latitude; }

    /**
     * Sets new isreported.
     *
     * @param isreported New value of isreported.
     */
    public void setIsreported(boolean isreported) { this.isreported = isreported; }
}
