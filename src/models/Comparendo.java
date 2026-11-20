
package models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Comparendo {

    private int id;
    private Vehiculo vehicle;
    private String description;
    private BigDecimal value;
    private LocalDate date;
    private boolean isPaid;

    // Constructor
    public Comparendo(int id, Vehiculo vehicle, String description,
                      BigDecimal value, LocalDate date, boolean isPaid) {

        this.id = id;
        this.vehicle = vehicle;
        this.description = description;
        this.value = value;
        this.date = date;
        this.isPaid = isPaid;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Vehiculo getVehicle() {
        return vehicle;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPaid() {
        return isPaid;
    }

    // Setters
    public void setVehicle(Vehiculo vehicle) {
        this.vehicle = vehicle;
    }

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
