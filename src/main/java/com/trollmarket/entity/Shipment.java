package com.trollmarket.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.IdentityHashMap;

@Entity
@Table(name = "Shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name")
    @NotNull
    private String name;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Service")
    private Boolean service;

    public Shipment(){}

    public Shipment(Long id, String name, BigDecimal price, Boolean service) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getService() {
        return service;
    }

    public void setService(Boolean service) {
        this.service = service;
    }

    public String getStatus(){
        if(this.service == true){
            return "Yes";
        }else{
            return "No";
        }
    }
}
