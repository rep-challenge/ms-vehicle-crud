package com.fiap.vehicle.repository;

import com.fiap.vehicle.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Long> {
    List<VehicleModel> findVehiclesForSale();
    List<VehicleModel> findSoldVehicles();
}