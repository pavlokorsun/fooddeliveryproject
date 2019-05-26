package com.go2it.edu.controller;

import com.github.openjson.JSONObject;


import com.go2it.edu.entity.Staff;
import com.go2it.edu.service.IStaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StaffController {

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



    @RequestMapping(value = "/staff", method = RequestMethod.GET)
    public String getStaff(@RequestParam(required = false) Integer fid) {

        List<Staff> lStaff;
        lStaff = staffService.getAll();

        String viewport = "";
        viewport += "<h3>Staff list</h3><table class='table table-striped table-hover'><thead><tr>" +
                "<th scope='col'>#</th>" +
                "<th scope='col'>Name</th>" +
                "<th scope='col'>Login</th>" +
                "<th scope='col'>Password</th>" +
                "<th scope='col'>Phone</th>" +
                "<th scope='col'>Role</th></tr></thead><tbody>";

        int i = 1;
        for (Staff item : lStaff) {
            String staff_name = item.getName();
            String staff_login = item.getLogin();
            String staff_pass = item.getPass();
            String staff_phone = item.getPhone();
            String staff_role = item.getRole();



            viewport += "<tr><th scope='row'>" + i + "</th><td>" + staff_name + "</td>" +
                    "<td>" + staff_login + "</td><td>" + staff_pass + "</td><td>" + staff_phone + "</td><td>" + staff_role + "</td></tr>";
            i++;
        }

        viewport += "</tbody></table>";

        String podval = "</div></html>";
        return headertop + viewport + podval;
    }


    @RequestMapping(value = "/staff/{fid}", method = RequestMethod.GET)
    public String getStaffById(@PathVariable Integer fid) {

        String viewport = "";

        viewport += "<h3>Staff by Id</h3><table class='table table-striped table-hover'><thead><tr>" +
                "<th scope='col'>#</th>" +
                "<th scope='col'>Name</th>" +
                "<th scope='col'>Login</th>" +
                "<th scope='col'>Password</th>" +
                "<th scope='col'>Phone</th>" +
                "<th scope='col'>Role</th></tr></thead><tbody>";

        Staff newStaff;
        newStaff = staffService.findById(fid);

        String staff_name = newStaff.getName();
        String staff_login = newStaff.getLogin();
        String staff_pass = newStaff.getPass();
        String staff_phone = newStaff.getPhone();
        String staff_role = newStaff.getRole();

        viewport += "<tr><th scope='row'>" + fid + "</th><td>" + staff_name + "</td>" +
                "<td>" + staff_login + "</td><td>" + staff_pass + "</td><td>" + staff_phone + "</td><td>" + staff_role + "</td></tr>";

        String podval = "</div></html>";
        return headertop + viewport + podval;
    }


    @RequestMapping(value = "/staff", method = RequestMethod.POST)
    public String addStaffFromJSON(@RequestBody String message) {
        JSONObject json = new JSONObject(message);

        String staffName = "none";
        String staffNameFromRequest = json.getString("name");
        if (StringUtils.isNotBlank(staffNameFromRequest)) {
            staffName = staffNameFromRequest;
        }

        String staffLogin = "none";
        String staffLoginFromRequest = json.getString("login");
        if (StringUtils.isNotBlank(staffLoginFromRequest)) {
            staffLogin = staffLoginFromRequest;
        }

        String staffPass = "none";
        String staffPassFromRequest = json.getString("pass");
        if (StringUtils.isNotBlank(staffPassFromRequest)) {
            staffPass = staffPassFromRequest;
        }

        String staffPhone = "none";
        String staffPhoneFromRequest = json.getString("phone");
        if (StringUtils.isNotBlank(staffPhoneFromRequest)) {
            staffPhone = staffPhoneFromRequest;
        }

        String staffRole = "none";
        String staffRoleFromRequest = json.getString("role");
        if (StringUtils.isNotBlank(staffRoleFromRequest)) {
            staffRole = staffRoleFromRequest;
        }




        Staff newStaff = new Staff();
        newStaff.setName(staffName);
        newStaff.setLogin(staffLogin);
        newStaff.setPass(staffPass);
        newStaff.setPhone(staffPhone);
        newStaff.setRole(staffRole);

        staffService.save(newStaff);

        return "The new staff (" + staffName + ")has been added successfully!";
    }




}