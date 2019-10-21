package com.gohool.theblog.blog.Model;

import java.io.Serializable;

public class BlogOrders implements Serializable {
    public String item;
    public String qty;
    public String price;

    public BlogOrders(){

    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
