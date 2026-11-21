package services;

import repositories.CitaRepository;
import repositories.VehiculoRepository;
import repositories.ComparendoRepository;

import models.Cita;
import models.Vehiculo;
import models.Comparendo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CitaService {

    private CitaRepository citaRepository;
    private VehiculoRepository vehiculoRepository;
    private ComparendoRepository comparendoRepository;

    public CitaService(
            CitaRepository citaRepository,
            VehiculoRepository vehiculoRepository,
            ComparendoRepository comparendoRepository) {

        this.citaRepository = citaRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.comparendoRepository = comparendoRepository;
    }

    public ServiceResponse registerAppointment( int id, String licensePlate, LocalDate date,
        LocalTime time, String location, String status, String observations) {

        if (id <= 0) {
            return ServiceResponse.error(
                "[INVALID_INPUT] El ID debe ser mayor que cero."
            );
        }

        if (citaRepository.findById(id) != null) {
            return ServiceResponse.error(
                "[CONFLICT] Ya existe una cita con este ID."
            );
        }

        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            return ServiceResponse.error(
                "[INVALID_INPUT] La placa no puede estar vacía."
            );
        }

        licensePlate = licensePlate.trim().toUpperCase();

        if (date == null || time == null) {
            return ServiceResponse.error(
                "[INVALID_INPUT] La fecha y hora son obligatorias."
            );
        }

        if (location == null || location.trim().isEmpty()) {
            return ServiceResponse.error(
                "[INVALID_INPUT] La ubicación no puede estar vacía."
            );
        }

        location = location.trim();

        if (status == null || status.trim().isEmpty()) {
            return ServiceResponse.error(
                "[INVALID_INPUT] El estado no puede estar vacío."
            );
        }

        status = status.trim();

        Vehiculo vehiculo = vehiculoRepository.findByLicensePlate(licensePlate);

        if (vehiculo == null) {
            return ServiceResponse.error(
                "[NOT_FOUND] No existe un vehículo con esta placa."
            );
        }

        ArrayList<Cita> citasVehiculo = citaRepository.findByVehicle(vehiculo);

        for (Cita cita : citasVehiculo) {
            if (cita.getDate().equals(date)
                    && cita.getTime().equals(time)) {

                return ServiceResponse.error(
                    "[CONFLICT] El vehículo ya tiene una cita en esa fecha y hora."
                );
            }
        }

        ArrayList<Comparendo> comparendos = comparendoRepository.findByVehicleLicensePlate(licensePlate);

        boolean hasPendingDebt = false;

        for (Comparendo comparendo : comparendos) {
            if (!comparendo.isPaid()) {
                hasPendingDebt = true;
                break;
            }
        }

        boolean assigned = !hasPendingDebt;
        String unassignedReason = hasPendingDebt ? "DEBT" : null;

        Cita cita = new Cita( id, vehiculo, date, time, location,
            status, assigned, unassignedReason, observations
        );

        citaRepository.add(cita);

        if (hasPendingDebt) {
            return ServiceResponse.success(
                "[SUCCESS] Solicitud registrada, pero no asignada por comparendos pendientes."
            );
        }

        return ServiceResponse.success(
            "[SUCCESS] Cita registrada y asignada correctamente."
        );
    }

    public ArrayList<Cita> findAppointmentsByVehicle(String licensePlate) {

        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            return new ArrayList<>();
        }

        Vehiculo vehiculo =
            vehiculoRepository.findByLicensePlate(
                licensePlate.trim().toUpperCase()
            );

        if (vehiculo == null) {
            return new ArrayList<>();
        }

        return citaRepository.findByVehicle(vehiculo);
    }

    public int countUnassignedByDebt() {

        int count = 0;

        for (Cita cita : citaRepository.findAll()) {
            if (!cita.isAssigned()
                    && "DEBT".equals(cita.getUnassignedReason())) {
                count++;
            }
        }

        return count;
    }
}


