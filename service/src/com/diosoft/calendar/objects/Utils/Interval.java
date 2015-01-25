//local code review (vtegza): do not use upper case letters for package names @ 1/25/2015
package com.diosoft.calendar.objects.Utils;

import java.util.Date;

public class Interval {

    //local code review (vtegza): could be final @ 1/25/2015
    private Date starDate;
    private Date endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;

        if (endDate != null ? !endDate.equals(interval.endDate) : interval.endDate != null) return false;
        if (starDate != null ? !starDate.equals(interval.starDate) : interval.starDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = starDate != null ? starDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    public Interval() {

    }

    public Interval(Date starDate, Date endDate) {
        this.starDate = starDate;
        this.endDate  = endDate;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "starDate=" + starDate +
                ", endDate=" + endDate +
                '}';
    }

}
