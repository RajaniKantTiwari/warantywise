package com.app.warantywise.network.request;

/**
 * Created by rajnikant on 14/03/18.
 */

public class AddProductRequest {
    private String product_id;
    private String modelno;
    private String serialno;
    private String purchasedate;
    private String extendedwarranty;
    private String warrantyfrom;
    private String productownerid;
    private String companyid;
    private String productimage;
    private String billimage;
    private String barcodeimage;
    private String wcimage;

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

    public String getWarrantyfrom() {
        return warrantyfrom;
    }

    public void setWarrantyfrom(String warrantyfrom) {
        this.warrantyfrom = warrantyfrom;
    }

    public String getProductownerid() {
        return productownerid;
    }

    public void setProductownerid(String productownerid) {
        this.productownerid = productownerid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
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
}
