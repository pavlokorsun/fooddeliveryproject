package com.go2it.edu.entity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name = "parcels")
public class Parcels {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int delivery_id;
    private LocalDateTime parcel_date;
    private LocalDateTime parcel_start;
    private LocalDateTime parcel_finish;
    @ManyToOne
    private Staff staff;
    public Parcels() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
    }

    public LocalDateTime getParcel_date() {
        return parcel_date;
    }

    public void setParcel_date(LocalDateTime parcel_date) {
        this.parcel_date = parcel_date;
    }

    public LocalDateTime getParcel_start() {
        return parcel_start;
    }

    public void setParcel_start(LocalDateTime parcel_start) {
        this.parcel_start = parcel_start;
    }

    public LocalDateTime getParcel_finish() {
        return parcel_finish;
    }

    public void setParcel_finish(LocalDateTime parcel_finish) {
        this.parcel_finish = parcel_finish;
    }
}

//    LocalDate dtl = LocalDate.of(2018,4,30);
//    java.sql.Date dt=java.sql.Date.valueOf(dtl);