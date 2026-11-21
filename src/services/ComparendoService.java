package services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import models.Comparendo;
import repositories.ComparendoRepository;

public class ComparendoService {
    private ComparendoRepository comparendoRepository;

    public ComparendoService(ComparendoRepository comparendoRepository) {
        this.comparendoRepository = comparendoRepository;
    }

    public ServiceResponse registerComparendo(int id, String vehicleLicensePlate,
        String description, BigDecimal value, LocalDate date, boolean isPaid) {

            if (id <= 0) {
                return ServiceResponse.error(
                    "[INVALID_INPUT] El ID debe ser mayor que cero."
                );
            }

            if (comparendoRepository.findById(id) != null) {
                return ServiceResponse.error(
                    "[CONFLICT] Ya existe un comparendo con este ID."
                );
            }

            if (vehicleLicensePlate == null || vehicleLicensePlate.trim().isEmpty()) {
                return ServiceResponse.error(
                    "[INVALID_INPUT] La placa del vehículo no puede estar vacía."
                );
            }
            
            if (description == null || description.trim().isEmpty()) {
                return ServiceResponse.error(
                    "[INVALID_INPUT] La descripción no puede estar vacía."
                );
            }

            if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
                return ServiceResponse.error(
                    "[INVALID_INPUT] El valor del comparendo debe ser mayor que cero."
                );
            }

            if (date == null || date.isAfter(LocalDate.now())) {
                return ServiceResponse.error(
                    "[INVALID_INPUT] La fecha del comparendo no puede ser futura."
                );
            }

            vehicleLicensePlate = vehicleLicensePlate.trim().toUpperCase();
            description = description.trim();
            Comparendo comparendo = new Comparendo(id, vehicleLicensePlate, description, value, date, isPaid);
            comparendoRepository.add(comparendo);
            return ServiceResponse.success("Comparendo registrado exitosamente.");
    }

    public ArrayList<Comparendo> findByVehicleLicensePlate(String licensePlate) {
        
        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return comparendoRepository.findByVehicleLicensePlate(licensePlate.trim().toUpperCase());
    }

    public ArrayList<Comparendo> findByDate(LocalDate date) {
        
        if (date == null) {
            return new ArrayList<>();
        }

        return comparendoRepository.findByDate(date);
    }

    public Comparendo findById(int id) {
        return comparendoRepository.findById(id);
    }

    public ArrayList<Comparendo> findPendingComparendos() {
        return comparendoRepository.findByPaid(false);
    }

    public ServiceResponse updatePaymentStatus(int id, boolean isPaid) {
        
        Comparendo comparendo = comparendoRepository.findById(id);
        
        if (comparendo == null) {
            return ServiceResponse.error("[NOT_FOUND] Comparendo no encontrado.");
        }

        if (comparendo.isPaid() == isPaid) {
            return ServiceResponse.error(
                "[CONFLICT] El comparendo ya tiene ese estado de pago."
            );
        }

        comparendo.setPaid(isPaid);
        return ServiceResponse.success("[SUCCESS] Estado de pago actualizado correctamente.");
    }
}
