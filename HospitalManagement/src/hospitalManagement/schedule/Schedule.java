package hospitalManagement.schedule;

import java.time.LocalTime;

public class Schedule {

    Integer id;
    String day, location;
    LocalTime start_time, end_time;

    public Schedule() {
    }

    public Schedule(Integer id, String day, LocalTime start_time, LocalTime end_time, String location) {
        setId(id);
        setDay(day);
        setStart_time(start_time);
        setEnd_time(end_time);
        setLocation(location);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", day=" + day + ", location=" + location + ", start_time=" + start_time
                + ", end_time=" + end_time + "]";
    }

}
