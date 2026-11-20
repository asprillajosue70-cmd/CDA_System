package repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import models.Cita;
import models.Vehiculo;

public class CitaRepository {

    private ArrayList<Cita> citas = new ArrayList<>();

    public void add(Cita cita) {
        citas.add(cita);
    }

    public Cita findById(int id) {
        for (Cita cita : citas) {
            if (cita.getId() == id) {
                return cita;
            }
        }
        return null;
    }

    public ArrayList<Cita> findByDate(LocalDate date) {
        ArrayList<Cita> citasPorFecha = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getDate().equals(date)) {
                citasPorFecha.add(cita);
            }
        }
        return citasPorFecha;
    }

    public ArrayList<Cita> findByVehicle(Vehiculo vehiculo) {
        ArrayList<Cita> citasPorVehiculo = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getVehicle().getId() == vehiculo.getId()) {
                citasPorVehiculo.add(cita);
            }
        }
        return citasPorVehiculo;
    }

    public ArrayList<Cita> findAll() {
        return citas;
    }
}