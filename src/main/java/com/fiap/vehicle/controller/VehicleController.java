package com.fiap.vehicle.controller;

import com.fiap.vehicle.model.VehicleModel;
import com.fiap.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping("/forSale")
    public List<VehicleModel> getVehiclesForSale() {
        return service.findVehiclesForSale();
    }

    @GetMapping("/sold")
    public List<VehicleModel> getSoldVehicles() {
        return service.findSoldVehicles();
    }

    @PostMapping(
            path = "{userName}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleModel save(
            @PathVariable("userName") final String userName,
            @RequestParam(value = "file") final MultipartFile file) {
        return service.create(userName, file);
    }

    @PutMapping("/saveStatus/{id}")
    public VehicleModel saveStatusVehicle(@PathVariable Long id, @RequestBody String status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        service.delete(id);
    }
}