package com.vehiculo.vehiculo.Service;

import com.vehiculo.vehiculo.Entity.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface IVehiculoService {
    //Get all courses
    List<Vehiculo> getAllVehiculo();

    //Get course by id
    Optional<Vehiculo> getVehiculoById(Long id);

    //Save a new course
    Vehiculo saveVehiculo(Vehiculo vehiculo);

    //Delete a course
    void deleteVehiculoById(Long id);

    //Update course
    Vehiculo updateVehiculo(Vehiculo vehiculo, Long id);
}
