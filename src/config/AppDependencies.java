package config;

import repositories.UsuarioRepository;
import repositories.PropietarioRepository;
import repositories.VehiculoRepository;
import repositories.ComparendoRepository;
import repositories.CitaRepository;
import models.Usuario;

import services.UsuarioService;
import services.PropietarioService;
import services.VehiculoService;
import services.ComparendoService;
import services.CitaService;

public class AppDependencies {

    // Repositories
    public final UsuarioRepository usuarioRepository;
    public final PropietarioRepository propietarioRepository;
    public final VehiculoRepository vehiculoRepository;
    public final ComparendoRepository comparendoRepository;
    public final CitaRepository citaRepository;

    // Services
    public final UsuarioService usuarioService;
    public final PropietarioService propietarioService;
    public final VehiculoService vehiculoService;
    public final ComparendoService comparendoService;
    public final CitaService citaService;

    public AppDependencies() {

        // Inicialización de repositorios
        usuarioRepository = new UsuarioRepository();
        propietarioRepository = new PropietarioRepository();
        vehiculoRepository = new VehiculoRepository();
        comparendoRepository = new ComparendoRepository();
        citaRepository = new CitaRepository();

        // Inicialización de servicios
         usuarioService = new UsuarioService(usuarioRepository);

        propietarioService = new PropietarioService(
            propietarioRepository
        );

        comparendoService = new ComparendoService(
            comparendoRepository
        );
        
        vehiculoService = new VehiculoService(
            vehiculoRepository,
            propietarioRepository,
            comparendoRepository
        );

        citaService = new CitaService(
            citaRepository,
            vehiculoRepository,
            comparendoRepository
        );

        // Registro de usuario inicial
        usuarioRepository.add(
            new Usuario(1, "Administrador", "admin", "12345")
        );
    }
}

