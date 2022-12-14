package com.trollmarket.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
@Table(name="Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "BuyerID")
    private Buyer buyer;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ProductID")
    private Product product;

    @Column(name = "Quantity", columnDefinition = "default 1")
    private Integer quantity;

    @ManyToOne
    @NotNull
    @JoinColumn(name="ShipmentID")
    private Shipment shipment;

    public Cart(){}

    public Cart(Buyer buyer, Product product, Integer quantity, Shipment shipment) {
        this.buyer = buyer;
        this.product = product;
        this.quantity = quantity;
        this.shipment = shipment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public String totalPrice(){
        Locale indonesia = new Locale("id", "ID");
        String indoFormat = NumberFormat.getCurrencyInstance(indonesia).format((this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity))).add(this.shipment.getPrice()));
        return indoFormat;
    }

    public BigDecimal totalPriceInBigDecimal(){
        return this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity)).add(this.shipment.getPrice());
    }

    public BigDecimal totalPriceWithoutShipment(){
        return this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

}
