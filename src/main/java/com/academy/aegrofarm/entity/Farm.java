package com.academy.aegrofarm.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "farms")
public class Farm {

    @Id
    private String id;

    private String name;

    @DBRef(lazy = true)
    private List<Glebe> glebes;

    private BigDecimal productivity;

    public Farm(String id, String name, List<Glebe> glebes, BigDecimal productivity) {
        this.id = id;
        this.name = name;
        this.glebes = glebes;
        this.productivity = productivity;
    }

    public Farm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Glebe> getGlebes() {
        return glebes;
    }

    public void setGlebes(List<Glebe> glebes) {
        this.glebes = glebes;
    }

    public BigDecimal getProductivity() {
        return productivity;
    }

    public void setProductivity(BigDecimal productivity) {
        this.productivity = productivity;
    }

    @Override
    public String toString() {
        return "Farm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", glebes=" + glebes +
                ", productivity=" + productivity +
                '}';
    }

}
