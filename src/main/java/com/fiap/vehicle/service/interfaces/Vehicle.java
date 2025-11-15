package com.fiap.vehicle.service.interfaces;

import com.fiap.vehicle.model.VehicleModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Vehicle {
    List<VehicleModel> findVehiclesForSale();
    List<VehicleModel> findSoldVehicles();
    VehicleModel create(String userName, MultipartFile file);
    VehicleModel updateStatus(Long id, String status);
    void delete(Long id);
}
