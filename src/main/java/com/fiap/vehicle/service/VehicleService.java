package com.fiap.vehicle.service;

import com.fiap.vehicle.VehicleNotFoundException;
import com.fiap.vehicle.model.VehicleModel;
import com.fiap.vehicle.producer.interfaces.Sender;
import com.fiap.vehicle.repository.VehicleRepository;
import com.fiap.vehicle.service.interfaces.Vehicle;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleService implements Vehicle {

    private final VehicleRepository vehicleRepository;
    private final Sender messageSender;

    public VehicleService(
            final VehicleRepository vehicleRepository,
            final Sender messageSender) {
        this.vehicleRepository = vehicleRepository;
        this.messageSender = messageSender;
    }

    public List<VehicleModel> findVehiclesForSale() {
        return vehicleRepository.findVehiclesForSale();
    }

    public List<VehicleModel> findSoldVehicles() {
        return vehicleRepository.findSoldVehicles();
    }

    @SneakyThrows
    public VehicleModel create(final String userName, final MultipartFile file) {
        final var key = UUID.randomUUID().toString();
        final var vehicle = VehicleModel
                .builder()
                .userName(userName)
                .vehicleKey(key)
                .title(file.getOriginalFilename())
                .status("IN_PROCESS")
                .build();

        final var newEntity = vehicleRepository.save(vehicle);
        this.messageSender.send(newEntity);
        return newEntity;
    }

    public VehicleModel updateStatus(final Long id, final String status) {
        VehicleModel vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));

        vehicle.setStatus(status.replace("\"", ""));
        return vehicleRepository.save(vehicle);
    }

    public void delete(final Long id) {
        vehicleRepository.deleteById(id);
    }
}
