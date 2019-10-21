package com.gohool.theblog.blog.Model;

import java.io.Serializable;

/**
 * Created by paulodichone on 4/17/17.
 */

public class Blog implements Serializable {
    public String qty;
    public String desc;
    public String image;
    public String timestamp;
    public String title;
    public String shop;
    public String price;
    public String username;

    public Blog() {
    }

    public Blog(String qty,String desc, String image,String title, String timestamp,String shop,String price,String username) {

        this.qty=qty;
        this.desc = desc;
        this.image = image;
        this.timestamp = timestamp;
        this.title = title;
        this.shop=shop;
        this.price=price;
        this.username = username;
    }


    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShopType() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }
//
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
//
//
    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}
