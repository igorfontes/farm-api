package com.academy.aegrofarm.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "productions")
public class Production {

    @Id
    private String id;

    private BigDecimal production;

    public Production(String id, BigDecimal production) {
        this.id = id;
        this.production = production;
    }

    public Production() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getProduction() {
        return production;
    }

    public void setProduction(BigDecimal production) {
        this.production = production;
    }

    @Override
    public String toString() {
        return "Production{" +
                "id='" + id + '\'' +
                ", production=" + production +
                '}';
    }
}
