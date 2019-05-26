package com.go2it.edu.controller;

import com.github.openjson.JSONObject;
import com.go2it.edu.entity.Meal;
import com.go2it.edu.service.IMealService;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MealController {

    @Autowired
    private IMealService mealService;

    String headertop = "<!DOCTYPE html><html><head><title>FOOD DELIVERY | Toronto | Canada</title><meta charset='utf-8'>" +
            "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
            "<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>" +
            "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>" +
            "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>" +
            "<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js'></script>" +
            "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js'></script></head>" +
            "<br><br><div class='container'><img src='http://korsun.me/food_delivery_logo.png' height='50'><br><br>";



    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String getMeals(@RequestParam(required = false) Integer fid) {

        List<Meal> lMeal;
        lMeal = mealService.getAll();

        String viewport = "";
        viewport += "<h3>Meals list</h3><table class='table table-striped table-hover'><thead><tr>" +
                "<th scope='col'>#</th>" +
                "<th scope='col'>Meal</th>" +
                "<th scope='col'>Price</th>" +
                "<th scope='col'>Type</th></tr></thead><tbody>";

        int i = 1;
        for (Meal item : lMeal) {
            String meal_name = item.getName();
            Double meal_price = item.getPrice();
            String meal_type = item.getMeal_type();

            viewport += "<tr><th scope='row'>" + i + "</th><td>" + meal_name + "</td><td>" + meal_price + "</td><td>" + meal_type + "</td></tr>";
            i++;
        }

        viewport += "</tbody></table>";

        String podval = "</div></html>";
        return headertop + viewport + podval;
    }


    @RequestMapping(value = "/meals/{fid}", method = RequestMethod.GET)
    public String getMealById(@PathVariable Integer fid) {

        String viewport = "";

        viewport += "<h3>Meal by Id</h3><table class='table table-striped table-hover'><thead><tr>" +
                "<th scope='col'>#</th>" +
                "<th scope='col'>Meal</th>" +
                "<th scope='col'>Price</th>" +
                "<th scope='col'>Type</th></tr></thead><tbody>";

        Meal newMeal;
        newMeal = mealService.findById(fid);
        String meal_name = newMeal.getName();
        Double meal_price = newMeal.getPrice();
        String meal_type = newMeal.getMeal_type();

        viewport += "<tr><th scope='row'>" + fid + "</th><td>" + meal_name + "</td><td>" + meal_price + "</td><td>" + meal_type + "</td></tr>";
        viewport += "</tbody></table>";

        String podval = "</div></html>";
        return headertop + viewport + podval;
    }



    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String addMealFromJSON(@RequestBody String message) {
        JSONObject json = new JSONObject(message);

        String mealName = "none";
        String mealNameFromRequest = json.getString("name");
        if (StringUtils.isNotBlank(mealNameFromRequest)) {
            mealName = mealNameFromRequest;
        }

        String mealType = "none";
        String mealTypeFromRequest = json.getString("meal_type");
        if (StringUtils.isNotBlank(mealTypeFromRequest)) {
            mealType = mealTypeFromRequest;
        }

        Double mealPrice = 0.0;
        Double mealPriceFromRequest = json.getDouble("price");
        if (mealPriceFromRequest != 0.0) {
            mealPrice = mealPriceFromRequest;
        }

        Meal newMeal = new Meal();
        newMeal.setName(mealName);
        newMeal.setPrice(mealPrice);
        newMeal.setMeal_type(mealType);

        mealService.save(newMeal);

        return "The new meal (" + mealName + ", " + mealPrice + ", " + mealType +")has been added successfully!";
    }





}