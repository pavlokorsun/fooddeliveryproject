package com.go2it.edu.controller;

import com.github.openjson.JSONObject;
import com.go2it.edu.entity.Customer;
import com.go2it.edu.service.ICustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    String headertop = "<!DOCTYPE html><html><head><title>FOOD DELIVERY | Toronto | Canada</title><meta charset='utf-8'>" +
            "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
            "<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>" +
            "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>" +
            "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>" +
            "<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js'></script>" +
            "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js'></script></head>" +
            "<br><br><div class='container'><img src='http://korsun.me/food_delivery_logo.png' height='50'><br><br>";


    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getCustomers(@RequestParam(required = false) Integer fid) {

        List<Customer> lCustomer;
        lCustomer = customerService.getAll();

        String viewport = "";
        viewport += "<h3>Customers list</h3><table class='table table-striped table-hover'><thead><tr>" +
                "<th scope='col'>#</th>" +
                "<th scope='col'>Customer Name</th>" +
                "<th scope='col'>Region</th>" +
                "<th scope='col'>Address</th>" +
                "<th scope='col'>E-mail</th>" +
                "<th scope='col'>Phone</th></tr></thead><tbody>";

        int i = 1;
        for (Customer item : lCustomer) {
            String customer_name = item.getName();
            String customer_login = item.getLogin();
            String customer_region = item.getRegion();
            String customer_address = item.getAddress();
            String customer_email = item.getEmail();
            String customer_phone = item.getPhone();

            viewport += "<tr><th scope='row'>" + i + "</th><td>" + customer_name + " (" + customer_login + ")</td>" +
                    "<td>" + customer_region + "</td><td>" + customer_address + "</td><td>" + customer_email + "</td><td>" + customer_phone + "</td></tr>";
            i++;
        }

        viewport += "</tbody></table>";

        String podval = "</div></html>";
        return headertop + viewport + podval;
    }


    @RequestMapping(value = "/customers/{fid}", method = RequestMethod.GET)
    public String getCustomerById(@PathVariable Integer fid) {

        String viewport = "";

        viewport += "<h3>Customer by Id</h3><table class='table table-striped table-hover'><thead><tr>" +
                "<th scope='col'>#</th>" +
                "<th scope='col'>Customer Name</th>" +
                "<th scope='col'>Region</th>" +
                "<th scope='col'>Address</th>" +
                "<th scope='col'>E-mail</th>" +
                "<th scope='col'>Phone</th></tr></thead><tbody>";

        Customer newCustomer;
        newCustomer = customerService.findById(fid);

        String customer_name = newCustomer.getName();
        String customer_login = newCustomer.getLogin();
        String customer_region = newCustomer.getRegion();
        String customer_address = newCustomer.getAddress();
        String customer_email = newCustomer.getEmail();
        String customer_phone = newCustomer.getPhone();

        viewport += "<tr><th scope='row'>" + fid + "</th><td>" + customer_name + " (" + customer_login + ")</td>" +
                "<td>" + customer_region + "</td><td>" + customer_address + "</td><td>" + customer_email + "</td><td>" + customer_phone + "</td></tr></tbody></table>";

        String podval = "</div></html>";
        return headertop + viewport + podval;
    }


    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public String addCustomerFromJSON(@RequestBody String message) {
        JSONObject json = new JSONObject(message);

        String customerName = "none";
        String customerNameFromRequest = json.getString("name");
        if (StringUtils.isNotBlank(customerNameFromRequest)) {
            customerName = customerNameFromRequest;
        }

        String customerRegion = "none";
        String customerRegionFromRequest = json.getString("region");
        if (StringUtils.isNotBlank(customerRegionFromRequest)) {
            customerRegion = customerRegionFromRequest;
        }

        String customerAddress = "none";
        String customerAddressFromRequest = json.getString("address");
        if (StringUtils.isNotBlank(customerAddressFromRequest)) {
            customerAddress = customerAddressFromRequest;
        }

        String customerLogin = "none";
        String customerLoginFromRequest = json.getString("login");
        if (StringUtils.isNotBlank(customerLoginFromRequest)) {
            customerLogin = customerLoginFromRequest;
        }

        String customerPass = "none";
        String customerPassFromRequest = json.getString("pass");
        if (StringUtils.isNotBlank(customerPassFromRequest)) {
            customerPass = customerPassFromRequest;
        }

        String customerEmail = "none";
        String customerEmailFromRequest = json.getString("email");
        if (StringUtils.isNotBlank(customerEmailFromRequest)) {
            customerEmail = customerEmailFromRequest;
        }

        String customerPhone = "none";
        String customerPhoneFromRequest = json.getString("phone");
        if (StringUtils.isNotBlank(customerPhoneFromRequest)) {
            customerPhone = customerPhoneFromRequest;
        }


        Customer newCustomer = new Customer();
        newCustomer.setName(customerName);
        newCustomer.setAddress(customerAddress);
        newCustomer.setEmail(customerEmail);
        newCustomer.setLogin(customerLogin);
        newCustomer.setRegion(customerRegion);
        newCustomer.setPass(customerPass);
        newCustomer.setPhone(customerPhone);


        customerService.save(newCustomer);

        return "The new customer (" + customerName + ")has been added successfully!";
    }


}