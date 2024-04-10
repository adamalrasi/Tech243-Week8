package org.example.aa.mocking;

import java.time.LocalDate;

public class Spartan {
    private String name;
    private String course;
    private LocalDate startDate;
    private int id;

    public Spartan(int id, String name, String course,LocalDate startDate) {
        this.course = course;
        this.startDate = startDate;
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", startDate=" + startDate +
                ", id=" + id +
                '}';
    }
}
