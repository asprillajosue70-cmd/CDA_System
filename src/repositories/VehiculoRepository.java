package repositories;

import java.util.ArrayList;
import models.Vehiculo;

public class VehiculoRepository {

    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();

    public void add(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public Vehiculo findById(int id) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getId() == id) {
                return vehiculo;
            }
        }
        return null;
    }

    public Vehiculo findByLicensePlate(String licensePlate) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getLicensePlate().equals(licensePlate)) {
                return vehiculo;
            }
        }
        return null;
    }

    public ArrayList<Vehiculo> findAll() {
        return vehiculos;
    }
}

