package repositories;

import java.util.ArrayList;
import models.Propietario;

public class PropietarioRepository {

    private ArrayList<Propietario> propietarios = new ArrayList<>();

    public void add(Propietario propietario) {
        propietarios.add(propietario);
    }

    public Propietario findById(int id) {

        for (Propietario propietario : propietarios) {

            if (propietario.getId() == id) {
                return propietario;
            }
        }

        return null;
    }

    public Propietario findByDocument(String document) {

        for (Propietario propietario : propietarios) {

            if (propietario.getDocument().equals(document)) {
                return propietario;
            }
        }

        return null;
    }

    public ArrayList<Propietario> findAll() {
        return propietarios;
    }

}
