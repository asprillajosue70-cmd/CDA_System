package services;

import repositories.VehiculoRepository;
import repositories.PropietarioRepository;
import repositories.ComparendoRepository;

import models.Propietario;
import models.Comparendo;
import models.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;

public class VehiculoService {

    private VehiculoRepository vehiculoRepository;
    private PropietarioRepository propietarioRepository;
    private ComparendoRepository comparendoRepository;

    public VehiculoService(
            VehiculoRepository vehiculoRepository,
            PropietarioRepository propietarioRepository,
            ComparendoRepository comparendoRepository) {

        this.vehiculoRepository = vehiculoRepository;
        this.propietarioRepository = propietarioRepository;
        this.comparendoRepository = comparendoRepository;
    }

    public ServiceResponse registerVehicle( int id, String licensePlate, String brand,
        String model, int year, String vehicleType, String color, int ownerId) {

        if (id <= 0) {
            return ServiceResponse.error(
                "[INVALID_INPUT] El ID debe ser mayor que cero."
            );
        }

        if (vehiculoRepository.findById(id) != null) {
            return ServiceResponse.error(
                "[CONFLICT] Ya existe un vehículo con este ID."
            );
        }

        if (ownerId <= 0) {
            return ServiceResponse.error(
                "[INVALID_INPUT] El ID del propietario no es válido."
            );
        }

        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            return ServiceResponse.error(
                "[INVALID_INPUT] La placa no puede estar vacía."
            );
        }

        licensePlate = licensePlate.trim().toUpperCase();

        if (brand == null || brand.trim().isEmpty()) {
            return ServiceResponse.error(
                "[INVALID_INPUT] La marca no puede estar vacía."
            );
        }

        brand = brand.trim().toUpperCase();

        if (model == null || model.trim().isEmpty()) {
            return ServiceResponse.error(
                "[INVALID_INPUT] El modelo no puede estar vacío."
            );
        }

        model = model.trim().toUpperCase();

        if (vehicleType == null || vehicleType.trim().isEmpty()) {
            return ServiceResponse.error(
                "[INVALID_INPUT] El tipo de vehículo no puede estar vacío."
            );
        }

        vehicleType = vehicleType.trim().toUpperCase();

        if (year <= 0 || year > LocalDate.now().getYear()) {
            return ServiceResponse.error(
                "[INVALID_INPUT] El año del vehículo no es válido."
            );
        }

        if (color == null || color.trim().isEmpty()) {
            return ServiceResponse.error(
                "[INVALID_INPUT] El color no puede estar vacío."
            );
        }

        color = color.trim().toUpperCase();

        Propietario propietario = propietarioRepository.findById(ownerId);

        if (propietario == null) {
            return ServiceResponse.error(
                "[NOT_FOUND] El propietario no existe."
            );
        }

        if (vehiculoRepository.existsByLicensePlate(licensePlate)) {
            return ServiceResponse.error(
                "[CONFLICT] Ya existe un vehículo con esta placa."
            );
        }

        ArrayList<Comparendo> comparendos = comparendoRepository.findByVehicleLicensePlate(licensePlate);

        for (Comparendo comparendo : comparendos) {
            if (!comparendo.isPaid()) {
                return ServiceResponse.error(
                    "[DEBT] El vehículo tiene comparendos pendientes."
                );
            }
        }

        Vehiculo vehiculo = new Vehiculo( id, licensePlate, brand, model,
            year, vehicleType, color, propietario
        );

        vehiculoRepository.add(vehiculo);

        return ServiceResponse.success(
            "[SUCCESS] Vehículo registrado correctamente."
        );
    }
}

