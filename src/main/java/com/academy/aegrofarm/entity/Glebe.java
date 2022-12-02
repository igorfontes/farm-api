package com.academy.aegrofarm.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "glebes")
public class Glebe {

    @Id
    private String id;

    private String name;

    private BigDecimal area;

    @DBRef(lazy = true)
    private List<Production> productions;

    private BigDecimal productivity;

    public Glebe(String id, String name, BigDecimal area, List<Production> productions, BigDecimal productivity) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.productions = productions;
        this.productivity = productivity;
    }

    public Glebe() {
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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }

    public BigDecimal getProductivity() {
        return productivity;
    }

    public void setProductivity(BigDecimal productivity) {
        this.productivity = productivity;
    }

    @Override
    public String toString() {
        return "Glebe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", productions=" + productions +
                ", productivity=" + productivity +
                '}';
    }
}
