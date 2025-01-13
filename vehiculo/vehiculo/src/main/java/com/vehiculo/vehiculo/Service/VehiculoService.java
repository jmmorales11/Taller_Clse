package com.vehiculo.vehiculo.Service;

import com.vehiculo.vehiculo.Entity.Vehiculo;
import com.vehiculo.vehiculo.Repository.IVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService implements IVehiculoService {
    @Autowired
    private IVehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> getAllVehiculo() {
        try{
            List<Vehiculo> vehiculos= vehiculoRepository.findAll();
            if(vehiculos.isEmpty()){
                return new ArrayList<>();
            }
            return vehiculos;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Vehiculo> getVehiculoById(Long id) {
        try{
            Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
            if(vehiculo.isPresent()){
                return vehiculo;
            }else{
                throw new RuntimeException("Vehiculo "+id+" no encontradp");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error " + id, e);
        }
    }

    @Override
    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        try {
            vehiculo.setFechaRegistro(LocalDate.now());
            return vehiculoRepository.save(vehiculo);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al guardar el curso", e);
        }
    }

    @Override
    public void deleteVehiculoById(Long id) {
        try{
            Optional<Vehiculo> course = vehiculoRepository.findById(id);
            if(course.isPresent()){
                vehiculoRepository.deleteById(id);
            }else{
                throw new RuntimeException("Vehiculo "+id);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Vehiculo updateVehiculo(Vehiculo vehiculo, Long id) {
        try {
            Optional<Vehiculo> existingCourse = vehiculoRepository.findById(id);
            if (existingCourse.isPresent()) {
                Vehiculo updateVehiculo = existingCourse.get();
                updateVehiculo.setMarca(vehiculo.getMarca());
                updateVehiculo.setModelo(vehiculo.getModelo());
                updateVehiculo.setAnio(vehiculo.getAnio());

                return vehiculoRepository.save(updateVehiculo);
            } else {
                throw new RuntimeException("Vehiculo con  " + id + " no existe");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al actualizar el  " + id, e);
        }
    }
}
