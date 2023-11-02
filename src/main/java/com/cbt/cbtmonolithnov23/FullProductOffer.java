package com.cbt.cbtmonolithnov23;

public class FullProductOffer {

    Productoffer productoffer;
    Integer amnt;
    Product product;

    public void setProductoffer(Productoffer productoffer) {
        this.productoffer = productoffer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmnt(Integer amnt) {
        this.amnt = amnt;
    }

    public Productoffer getProductoffer() {
        return productoffer;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getAmnt() {
        return amnt;
    }
}
