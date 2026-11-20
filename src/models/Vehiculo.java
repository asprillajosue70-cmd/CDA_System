
package models;

public class Vehiculo {

    private int id;
    private String licensePlate;
    private String brand;
    private String model;
    private int year;
    private String vehicleType;
    private String color;
    private Propietario owner;

    // Constructor
    public Vehiculo(int id, String licensePlate, String brand,
                    String model, int year, String vehicleType,
                    String color, Propietario owner) {

        this.id = id;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.vehicleType = vehicleType;
        this.color = color;
        this.owner = owner;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

    public Propietario getOwner() {
        return owner;
    }

    // Setters
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOwner(Propietario owner) {
        this.owner = owner;
    }
}
