package com.example.OrderManagementSystem.model;

public class OrderLine {

    private String id;
    private SellableItem item;
    private UnitOfMeasure unit;
    private double quantity;

    public OrderLine() {}

    public OrderLine(String id, SellableItem item, UnitOfMeasure unit, double quantity) {
        this.id = id;
        this.item = item;
        this.unit = unit;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SellableItem getItem() {
        return item;
    }

    public void setItem(SellableItem item) {
        this.item = item;
    }

    public UnitOfMeasure getUnit() {
        return unit;
    }

    public void setUnit(UnitOfMeasure unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalValue() {
        if (item instanceof Product) {
            Product product = (Product) item;
            return product.getValue() * quantity;
        }
        return 0.0; // Services don't have a fixed value
    }
}
