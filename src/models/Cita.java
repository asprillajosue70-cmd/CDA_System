
package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {

    private int id;
    private Vehiculo vehicle;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String status;
    private boolean assigned;
    private String unassignedReason;
    private String observations;

    // Constructor
    public Cita(int id, Vehiculo vehicle, LocalDate date,
                LocalTime time, String location, String status,
                boolean assigned, String unassignedReason,
                String observations) {

        this.id = id;
        this.vehicle = vehicle;
        this.date = date;
        this.time = time;
        this.location = location;
        this.status = status;
        this.assigned = assigned;
        this.unassignedReason = unassignedReason;
        this.observations = observations;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Vehiculo getVehicle() {
        return vehicle;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public String getUnassignedReason() {
        return unassignedReason;
    }

    public String getObservations() {
        return observations;
    }

    // Setters
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public void setUnassignedReason(String unassignedReason) {
        this.unassignedReason = unassignedReason;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}

