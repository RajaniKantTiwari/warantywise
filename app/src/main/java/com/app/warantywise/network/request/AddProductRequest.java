package com.app.warantywise.network.request;

/**
 * Created by rajnikant on 14/03/18.
 */

public class AddProductRequest {
    private String product_id="";
    private String product_name;
    private String modelno;
    private String serialno;
    private String purchasedate;
    private String extendedwarranty;
    private String warrantyperiod;
    private String productownerid;
    private String manufacturer_id="";
    private String manufacturer_name;
    private String productimage="";
    private String billimage="";
    private String barcodeimage="";
    private String wcimage="";
    private String extended_warranty_days="0";

    public String getExtended_warranty_days() {
        return extended_warranty_days;
    }

    public void setExtended_warranty_days(String extended_warranty_days) {
        this.extended_warranty_days = extended_warranty_days;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(String manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }

    public String getModelno() {
        return modelno;
    }

    public void setModelno(String modelno) {
        this.modelno = modelno;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(String purchasedate) {
        this.purchasedate = purchasedate;
    }

    public String getExtendedwarranty() {
        return extendedwarranty;
    }

    public void setExtendedwarranty(String extendedwarranty) {
        this.extendedwarranty = extendedwarranty;
    }



    public String getProductownerid() {
        return productownerid;
    }

    public void setProductownerid(String productownerid) {
        this.productownerid = productownerid;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getBarcodeimage() {
        return barcodeimage;
    }

    public void setBarcodeimage(String barcodeimage) {
        this.barcodeimage = barcodeimage;
    }

    public void setBillimage(String billimage) {
        this.billimage = billimage;
    }

    public String getBillimage() {
        return billimage;
    }

    public void setWcimage(String wcimage) {
        this.wcimage = wcimage;
    }

    public String getWcimage() {
        return wcimage;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getWarrantyperiod() {
        return warrantyperiod;
    }

    public void setWarrantyperiod(String warrantyperiod) {
        this.warrantyperiod = warrantyperiod;
    }
}
