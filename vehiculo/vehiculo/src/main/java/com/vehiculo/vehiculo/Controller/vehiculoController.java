package com.vehiculo.vehiculo.Controller;

import com.vehiculo.vehiculo.Entity.Vehiculo;
import com.vehiculo.vehiculo.Service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/vehiculo")
public class vehiculoController {
    @Autowired
    private VehiculoService vehiculoServiceS;

    @GetMapping
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoServiceS.getAllVehiculo();
    }

    @GetMapping("/{id}")
    public Optional<Vehiculo> getVehiculosById(@PathVariable("id") Long id) {
        return vehiculoServiceS.getVehiculoById(id);
    }
    @PostMapping
    public ResponseEntity<?> createVehiculos(@Valid @RequestBody Vehiculo vehiculo, BindingResult result) {
        if(result.hasErrors()){

            return error(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoServiceS.saveVehiculo(vehiculo));
    }

    @DeleteMapping("/{id}")
    public void deleteVehiculo(@PathVariable Long id) {
        vehiculoServiceS.deleteVehiculoById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  updateCourse(@Valid @RequestBody Vehiculo vehiculo, @PathVariable("id") Long id, BindingResult result){
        if(result.hasErrors()){

            return error(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehiculoServiceS.updateVehiculo(vehiculo, id));

    }


    public ResponseEntity<?> error(BindingResult result){
        Map<String,String> errores = new HashMap<>();
        result.getFieldErrors().forEach(
                err-> errores.put(
                        err.getField(),err.getDefaultMessage()
                )
        );
        return ResponseEntity.badRequest().body(errores);
    }
}
