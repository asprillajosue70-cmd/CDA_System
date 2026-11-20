package models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Comparendo {
    private int id;
    private String vehicleLicensePlate;
    private String description;
    private BigDecimal value;
    private LocalDate date;
    private boolean isPaid;

    public Comparendo(int id, String vehicleLicensePlate, String description, BigDecimal value,
        LocalDate date, boolean isPaid) {
        this.id = id;
        this.vehicleLicensePlate = vehicleLicensePlate.trim().toUpperCase();
        this.description = description;
        this.value = value;
        this.date = date;
        this.isPaid = isPaid;
    }

    public int getId() { return id; }
    public String getVehicleLicensePlate() { return vehicleLicensePlate; }
    public String getDescription() { return description; }
    public BigDecimal getValue() { return value; }
    public LocalDate getDate() { return date; }
    public boolean isPaid() { return isPaid; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
}

