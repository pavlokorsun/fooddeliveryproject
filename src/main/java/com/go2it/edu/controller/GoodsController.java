package com.go2it.edu.controller;

import com.github.openjson.JSONObject;
import com.go2it.edu.entity.Goods;
import com.go2it.edu.service.IGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    String headertop = "<!DOCTYPE html><html><head><title>FOOD DELIVERY | Toronto | Canada</title><meta charset='utf-8'>" +
            "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
            "<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>" +
            "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>" +
            "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>" +
            "<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js'></script>" +
            "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js'></script></head>" +
            "<br><br><div class='container'><img src='http://korsun.me/food_delivery_logo.png' height='50'><br><br>";


    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public String getGoods(@RequestParam(required = false) Integer fid) {

        List<Goods> lGoods;
        lGoods = goodsService.getAll();

        String viewport = "";
        viewport += "<h3>Goods stock</h3><table class='table table-striped table-hover'><thead><tr>" +
                "<th scope='col'>#</th>" +
                "<th scope='col'>Goods</th>" +
                "<th scope='col'>Price</th>" +
                "<th scope='col'>Stock</th>" +
                "<th scope='col'>Units</th>" +
                "<th scope='col'>Limit</th></tr></thead><tbody>";

        int i = 1;
        for (Goods item : lGoods) {
            String goods_name = item.getName();
            Double goods_price = item.getPrice();
            Integer goods_stock = item.getStock();
            String goods_units = item.getUnits();
            Integer goods_limit = item.getStock_limit();


            viewport += "<tr><th scope='row'>" + i + "</th><td>" + goods_name + "</td>" +
                    "<td>" + goods_price + "</td><td>" + goods_stock + "</td><td>" + goods_units + "</td><td>" + goods_limit + "</td></tr>";
            i++;
        }

        viewport += "</tbody></table>";

        String podval = "</div></html>";
        return headertop + viewport + podval;
    }


    @RequestMapping(value = "/goods/{fid}", method = RequestMethod.GET)
    public String getGoodsById(@PathVariable Integer fid) {

        String viewport = "";

        viewport += "<h3>Goods by Id</h3><table class='table table-striped table-hover'><thead><tr>" +
                "<th scope='col'>#</th>" +
                "<th scope='col'>Goods</th>" +
                "<th scope='col'>Price</th>" +
                "<th scope='col'>Stock</th>" +
                "<th scope='col'>Units</th>" +
                "<th scope='col'>Limit</th></tr></thead><tbody>";

        Goods newGoods;
        newGoods = goodsService.findById(fid);

        String goods_name = newGoods.getName();
        Double goods_price = newGoods.getPrice();
        Integer goods_stock = newGoods.getStock();
        String goods_units = newGoods.getUnits();
        Integer goods_limit = newGoods.getStock_limit();

        viewport += "<tr><th scope='row'>" + fid + "</th><td>" + goods_name + "</td>" +
                "<td>" + goods_price + "</td><td>" + goods_stock + "</td><td>" + goods_units + "</td><td>" + goods_limit + "</td></tr>";

        String podval = "</div></html>";
        return headertop + viewport + podval;
    }


    @RequestMapping(value = "/goods", method = RequestMethod.POST)
    public String addGoodsFromJSON(@RequestBody String message) {
        JSONObject json = new JSONObject(message);

        String goodsName = "none";
        String goodsNameFromRequest = json.getString("name");
        if (StringUtils.isNotBlank(goodsNameFromRequest)) {
            goodsName = goodsNameFromRequest;
        }

        Double goodsPrice = 0.0;
        Double goodsPriceFromRequest = json.getDouble("price");
        if (goodsPriceFromRequest != 0.0) {
            goodsPrice = goodsPriceFromRequest;
        }

        Integer goodsStock = 0;
        Integer goodsStockFromRequest = json.getInt("stock");
        if (goodsStockFromRequest != 0) {
            goodsStock = goodsStockFromRequest;
        }

        String goodsUnits = "none";
        String goodsUnitsFromRequest = json.getString("units");
        if (StringUtils.isNotBlank(goodsUnitsFromRequest)) {
            goodsUnits = goodsUnitsFromRequest;
        }

        Integer goodsLimit = 0;
        Integer goodsLimitFromRequest = json.getInt("stock_limit");
        if (goodsLimitFromRequest != 0) {
            goodsLimit = goodsLimitFromRequest;
        }

        Goods newGoods = new Goods();
        newGoods.setName(goodsName);
        newGoods.setPrice(goodsPrice);
        newGoods.setStock(goodsStock);
        newGoods.setUnits(goodsUnits);
        newGoods.setStock_limit(goodsLimit);



        goodsService.save(newGoods);

        return "The new goods item (" + goodsName + ")has been added successfully!";
    }


}