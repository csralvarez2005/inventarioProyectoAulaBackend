package inventarioEquiposBackend.service.impl;


import inventarioEquiposBackend.dto.EquipoDTO;
import inventarioEquiposBackend.exception.DuplicateResourceException;
import inventarioEquiposBackend.exception.ResourceNotFoundException;
import inventarioEquiposBackend.model.Equipo;
import inventarioEquiposBackend.repository.EquipoRepository;
import inventarioEquiposBackend.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {
    private final EquipoRepository equipoRepository;

    @Autowired
    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    public Equipo crearEquipo(EquipoDTO equipoDTO) {
        if (equipoRepository.existsByNombreEquipo(equipoDTO.getNombreEquipo())) {
            throw new DuplicateResourceException("Ya existe un equipo con el nombre: " + equipoDTO.getNombreEquipo());
        }
        return equipoRepository.save(convertirDTOaEntidad(equipoDTO));
    }

    @Override
    public Equipo obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el equipo con ID: " + id));
    }

    @Override
    public List<Equipo> obtenerTodosLosEquipos() {
        return equipoRepository.findAll();
    }

    @Override
    public Equipo actualizarEquipo(Long id, EquipoDTO equipoDTO) {
        Equipo equipoExistente = obtenerEquipoPorId(id);

        if (!equipoExistente.getNombreEquipo().equals(equipoDTO.getNombreEquipo()) &&
                equipoRepository.existsByNombreEquipo(equipoDTO.getNombreEquipo())) {
            throw new DuplicateResourceException("Ya existe un equipo con el nombre: " + equipoDTO.getNombreEquipo());
        }

        actualizarDatosEquipoDesdeDTO(equipoExistente, equipoDTO);
        return equipoRepository.save(equipoExistente);
    }

    @Override
    public boolean eliminarEquipo(Long id) {
        if (!equipoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el equipo con ID: " + id);
        }
        equipoRepository.deleteById(id);
        return true; // Cambiado a true para indicar que la eliminación fue exitosa.
    }

    private Equipo convertirDTOaEntidad(EquipoDTO dto) {
        Equipo equipo = new Equipo();
        actualizarDatosEquipoDesdeDTO(equipo, dto);
        return equipo;
    }

    private void actualizarDatosEquipoDesdeDTO(Equipo equipo, EquipoDTO dto) {
        equipo.setId(dto.getId());
        equipo.setNombreEquipo(dto.getNombreEquipo());
        equipo.setDescripcion(dto.getDescripcion());
        equipo.setTipo(dto.getTipo());
        equipo.setModelo(dto.getModelo());
        equipo.setMarca(dto.getMarca());
        equipo.setSerie(dto.getSerie());
        equipo.setUbicacionDelEquipo(dto.getUbicacionDelEquipo());
        equipo.setUtilizacion(dto.getUtilizacion());
        equipo.setRecibidoPor(dto.getRecibidoPor());
        equipo.setProveedor(dto.getProveedor());
        equipo.setOrdenDeCompra(dto.getOrdenDeCompra());
        equipo.setFactura(dto.getFactura());
        equipo.setFechaDeCompra(dto.getFechaDeCompra());
        equipo.setFechaFinGarantia(dto.getFechaFinGarantia());
        equipo.setGarantia(dto.getGarantia());
        equipo.setPrecio(dto.getPrecio());
        equipo.setProcesador(dto.getProcesador());
        equipo.setMemoriaRamGB(dto.getMemoriaRamGB());
        equipo.setAlmacenamientoGB(dto.getAlmacenamientoGB());
        equipo.setTipoAlmacenamiento(dto.getTipoAlmacenamiento());
        equipo.setPlacaBase(dto.getPlacaBase());
        equipo.setFuentePoderWatts(dto.getFuentePoderWatts());
        equipo.setTarjetaGrafica(dto.getTarjetaGrafica());
        equipo.setTieneTarjetaRed(dto.isTieneTarjetaRed());
        equipo.setTieneTarjetaSonido(dto.isTieneTarjetaSonido());
        equipo.setGabinete(dto.getGabinete());
        equipo.setPerifericosEntrada(dto.getPerifericosEntrada());
        equipo.setPerifericosSalida(dto.getPerifericosSalida());
        equipo.setComponentes(dto.getComponentes());
        equipo.setAccesorios(dto.getAccesorios());
        equipo.setSistemaOperativo(dto.getSistemaOperativo());
        equipo.setVersionSO(dto.getVersionSO());
        equipo.setDriversInstalados(dto.getDriversInstalados());
        equipo.setProgramasInstalados(dto.getProgramasInstalados());
        equipo.setUtilidadesSistema(dto.getUtilidadesSistema());
        equipo.setDireccionIP(dto.getDireccionIP());
        equipo.setDireccionMAC(dto.getDireccionMAC());
    }
}
