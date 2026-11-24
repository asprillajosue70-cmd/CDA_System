package services;

import repositories.PropietarioRepository;
import models.Propietario;
import java.util.List;

public class PropietarioService {

    private PropietarioRepository propietarioRepository;

    public PropietarioService(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    public ServiceResponse registerOwner( int id, String name, String document,
        String phone, String email) {
        if (propietarioRepository.findByDocument(document) != null) {
            return ServiceResponse.error(
                "[CONFLICT] Ya existe un propietario con este documento."
            );
        }

        Propietario propietario = new Propietario(id, name, document, phone, email);
        propietarioRepository.add(propietario);
        return ServiceResponse.success("[SUCCESS] Propietario registrado correctamente.");
    }

    public Propietario findOwnerByDocument(String document) {
        return propietarioRepository.findByDocument(document);
    }

    public Propietario findOwnerById(int id) {
        return propietarioRepository.findById(id);
    }

    public List<Propietario> findAllOwners() {
        return propietarioRepository.findAll();
    }
}
