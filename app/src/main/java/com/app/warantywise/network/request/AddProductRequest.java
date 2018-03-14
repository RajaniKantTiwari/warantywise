package com.app.warantywise.network.request;

/**
 * Created by rajnikant on 14/03/18.
 */

public class AddProductRequest {
    private String product_name;
    private String model_no;
    private String serial_no;
    private String purchase_date;
    private String extended_warranty;
    private String warranty_from;
    private String product_owner_id;
    private String company_id;
    private String product_image;
    private String barcode_image;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getModel_no() {
        return model_no;
    }

    public void setModel_no(String model_no) {
        this.model_no = model_no;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getExtended_warranty() {
        return extended_warranty;
    }

    public void setExtended_warranty(String extended_warranty) {
        this.extended_warranty = extended_warranty;
    }

    public String getWarranty_from() {
        return warranty_from;
    }

    public void setWarranty_from(String warranty_from) {
        this.warranty_from = warranty_from;
    }

    public String getProduct_owner_id() {
        return product_owner_id;
    }

    public void setProduct_owner_id(String product_owner_id) {
        this.product_owner_id = product_owner_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getBarcode_image() {
        return barcode_image;
    }

    public void setBarcode_image(String barcode_image) {
        this.barcode_image = barcode_image;
    }
}
