package services;

import repositories.VehiculoRepository;
import repositories.PropietarioRepository;
import repositories.ComparendoRepository;

import models.Propietario;
import models.Comparendo;
import java.util.ArrayList;
import models.Vehiculo;


public class VehiculoService {

    public ServiceResponse registerVehicle(int id, String licensePlate,
        String brand, String model, int year, String vehicleType,
        String color, int ownerId) {

            Propietario propietario = propietarioRepository.findById(ownerId);
            if (propietario == null) {
                return ServiceResponse.error(
                    "[NOT_FOUND] El propietario no existe."
                );
            }

            if(vehiculoRepository.existsByLicensePlate(licensePlate)) {
                return ServiceResponse.error(
                    "[CONFLICT] Ya existe un vehículo con esta placa."
                );
            }

           Vehiculo vehiculo = new Vehiculo( id, licensePlate, brand, model,
            year, vehicleType, color, propietario);

            ArrayList<Comparendo> comparendos = comparendoRepository.findByVehicle(vehiculo);
            for (Comparendo comparendo : comparendos) {
                if (!comparendo.isPaid()) {
                    return ServiceResponse.error(
                        "[DEBT] El vehículo tiene comparendos pendientes."
                    );
                }
            }
            vehiculoRepository.add(vehiculo);
            return ServiceResponse.success( "[SUCCESS] Vehículo registrado correctamente.");

        }

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

}
