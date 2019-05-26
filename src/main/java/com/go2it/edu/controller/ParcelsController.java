package com.go2it.edu.controller;

import com.github.openjson.JSONObject;
import com.go2it.edu.entity.Parcels;
import com.go2it.edu.entity.Staff;
import com.go2it.edu.service.IParcelsService;
import com.go2it.edu.service.IStaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RestController
public class ParcelsController {

    @Autowired
    private IParcelsService parcelsService;

    @Autowired
    private IStaffService staffService;

    String headertop = "<!DOCTYPE html><html><head><title>FOOD DELIVERY | Toronto | Canada</title><meta charset='utf-8'>" +
            "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
            "<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>" +
            "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>" +
            "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>" +
            "<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js'></script>" +
            "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js'></script></head>" +
            "<br><br><div class='container'><img src='http://korsun.me/food_delivery_logo.png' height='50'><br><br>";


    @RequestMapping(value = "/parcels", method = RequestMethod.GET)
    public String getParcels(@RequestParam(required = false) Integer fid) {

        List<Parcels> lParcels;
        lParcels = parcelsService.getAll();

        String viewport = "";
        viewport += "<h3>Parcels list</h3><table class='table table-striped table-hover'><thead><tr>" +
                "<th scope='col'>#</th>" +
                "<th scope='col'>Name</th>" +
                "<th scope='col'>Date</th>" +
                "<th scope='col'>Delivery</th>" +
                "<th scope='col'>Start</th>" +
                "<th scope='col'>Finish</th></tr></thead><tbody>";

        int i = 1;
        for (Parcels item : lParcels) {
            String parcel_name = item.getName();
            LocalDateTime parcel_date = item.getParcel_date();
            int delivery_id = item.getDelivery_id();
            LocalDateTime parcel_start = item.getParcel_start();
            LocalDateTime parcel_finish = item.getParcel_finish();

            //Delivery name
            Staff newStaff;
            newStaff = staffService.findById(delivery_id);
            String staff_name = newStaff.getName();


            //date extract
            int parcel_date_day = parcel_date.getDayOfMonth();
            Month parcel_date_month = parcel_date.getMonth();
            int parcel_date_year = parcel_date.getYear();

            //time extract
            int parcel_start_hour = parcel_start.getHour();
            int parcel_start_minutes = parcel_start.getMinute();

            int parcel_finish_hour = parcel_finish.getHour();
            int parcel_finish_minutes = parcel_finish.getMinute();


            viewport += "<tr><th scope='row'>" + i + "</th><td>" + parcel_name + "</td>" +
                    "<td>" + parcel_date_day + " " + parcel_date_month + " " + parcel_date_year +
                    "</td><td>" + staff_name + "</td><td>" + parcel_start_hour + ":" + parcel_start_minutes +
                    "</td><td>" + parcel_finish_hour + ":" + parcel_finish_minutes + "</td></tr>";
            i++;
        }

        viewport += "</tbody></table>";

        String podval = "</div></html>";
        return headertop + viewport + podval;
    }


    @RequestMapping(value = "/parcels/{fid}", method = RequestMethod.GET)
    public String getParcelsById(@PathVariable Integer fid) {

        String viewport = "";

        viewport += "<h3>Parcels list</h3><table class='table table-striped table-hover'><thead><tr>" +
                "<th scope='col'>#</th>" +
                "<th scope='col'>Name</th>" +
                "<th scope='col'>Date</th>" +
                "<th scope='col'>Delivery</th>" +
                "<th scope='col'>Start</th>" +
                "<th scope='col'>Finish</th></tr></thead><tbody>";

        Parcels newParcels;
        newParcels = parcelsService.findById(fid);

        String parcel_name = newParcels.getName();
        LocalDateTime parcel_date = newParcels.getParcel_date();
        int delivery_id = newParcels.getDelivery_id();
        LocalDateTime parcel_start = newParcels.getParcel_start();
        LocalDateTime parcel_finish = newParcels.getParcel_finish();

        //Delivery name
        Staff newStaff;
        newStaff = staffService.findById(delivery_id);
        String staff_name = newStaff.getName();


        //date extract
        int parcel_date_day = parcel_date.getDayOfMonth();
        Month parcel_date_month = parcel_date.getMonth();
        int parcel_date_year = parcel_date.getYear();

        //time extract
        int parcel_start_hour = parcel_start.getHour();
        int parcel_start_minutes = parcel_start.getMinute();

        int parcel_finish_hour = parcel_finish.getHour();
        int parcel_finish_minutes = parcel_finish.getMinute();




        viewport += "<tr><th scope='row'>" + fid + "</th><td>" + parcel_name + "</td>" +
                "<td>" + parcel_date_day + " " + parcel_date_month + " " + parcel_date_year +
                "</td><td>" + staff_name + "</td><td>" + parcel_start_hour + ":" + parcel_start_minutes +
                "</td><td>" + parcel_finish_hour + ":" + parcel_finish_minutes + "</td></tr>";

        String podval = "</div></html>";
        return headertop + viewport + podval;
    }


    @RequestMapping(value = "/parcels", method = RequestMethod.POST)
    public String addParcelFromJSON(@RequestBody String message) {
        JSONObject json = new JSONObject(message);

        String parcelName = "none";
        String parcelNameFromRequest = json.getString("name");
        if (StringUtils.isNotBlank(parcelNameFromRequest)) {
            parcelName = parcelNameFromRequest;
        }



        Parcels newParcel = new Parcels();
        newParcel.setName(parcelName);
        LocalDateTime localDateTime_now = LocalDateTime.now();
        newParcel.setParcel_date(localDateTime_now);
        newParcel.setParcel_start(localDateTime_now);
        newParcel.setParcel_finish(localDateTime_now);
        newParcel.setDelivery_id(2);

        parcelsService.save(newParcel);

        return "The new staff (" + parcelName + ")has been added successfully!";
    }

}
