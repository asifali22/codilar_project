package com.hybrid.codilarproject;

/**
 * Created by monster on 18/11/16.
 */

public class Product {
    String entity_id;
    String name;
    String sku;
    String image;
    String small_image;
    String thumbnail;
    String color;
    String type_id;
    String mpassignproduct_id;
    String seller_id;
    String qty;
    String price;
    String special_price;
    String special_price_form;
    String special_price_to;
    String offer;
    String flag;
    String product_condition;
    String used_type;
    String config_options;
    String warranty_offered;


    public Product(String color, String config_options, String entity_id, String flag, String image, String mpassignproduct_id, String name, String offer, String product_condition, String qty, String price, String seller_id, String sku, String small_image, String special_price, String special_price_form, String special_price_to, String thumbnail, String type_id, String used_type, String warranty_offered) {
        this.color = color;
        this.config_options = config_options;
        this.entity_id = entity_id;
        this.flag = flag;
        this.image = image;
        this.mpassignproduct_id = mpassignproduct_id;
        this.name = name;
        this.offer = offer;
        this.product_condition = product_condition;
        this.qty = qty;
        this.price = price;
        this.seller_id = seller_id;
        this.sku = sku;
        this.small_image = small_image;
        this.special_price = special_price;
        this.special_price_form = special_price_form;
        this.special_price_to = special_price_to;
        this.thumbnail = thumbnail;
        this.type_id = type_id;
        this.used_type = used_type;
        this.warranty_offered = warranty_offered;
    }


    public Product(){

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getConfig_options() {
        return config_options;
    }

    public void setConfig_options(String config_options) {
        this.config_options = config_options;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMpassignproduct_id() {
        return mpassignproduct_id;
    }

    public void setMpassignproduct_id(String mpassignproduct_id) {
        this.mpassignproduct_id = mpassignproduct_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduct_condition() {
        return product_condition;
    }

    public void setProduct_condition(String product_condition) {
        this.product_condition = product_condition;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }

    public String getSpecial_price() {
        return special_price;
    }

    public void setSpecial_price(String special_price) {
        this.special_price = special_price;
    }

    public String getSpecial_price_form() {
        return special_price_form;
    }

    public void setSpecial_price_form(String special_price_form) {
        this.special_price_form = special_price_form;
    }

    public String getSpecial_price_to() {
        return special_price_to;
    }

    public void setSpecial_price_to(String special_price_to) {
        this.special_price_to = special_price_to;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getUsed_type() {
        return used_type;
    }

    public void setUsed_type(String used_type) {
        this.used_type = used_type;
    }

    public String getWarranty_offered() {
        return warranty_offered;
    }

    public void setWarranty_offered(String warranty_offered) {
        this.warranty_offered = warranty_offered;
    }
}
