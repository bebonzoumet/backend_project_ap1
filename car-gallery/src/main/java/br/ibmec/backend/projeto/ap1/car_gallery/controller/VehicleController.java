package br.ibmec.backend.projeto.ap1.car_gallery.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ibmec.backend.projeto.ap1.car_gallery.model.Brand;
import br.ibmec.backend.projeto.ap1.car_gallery.model.Vehicle;
import br.ibmec.backend.projeto.ap1.car_gallery.repository.BrandRepository;
import br.ibmec.backend.projeto.ap1.car_gallery.repository.VehicleRepository;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/listVehicles/{brandId}")
    public String listVehicles(@PathVariable("brandId") Integer brandId, Model modelView) {
        Optional<Brand> optBrand = brandRepository.findById(brandId);
        if (optBrand.isPresent()) {
            modelView.addAttribute("listVehicles", optBrand.get().getVehicles());
            modelView.addAttribute("brand", optBrand.get());
            return "list-vehicles";
        }
        return "redirect:/brand";
    }

    @GetMapping("/createVehicle/{brandId}")
    public String create(@PathVariable("brandId") Integer brandId, Model modelView, Vehicle vehicle) {
        Optional<Brand> optBrand = brandRepository.findById(brandId);
        if (optBrand.isPresent()) {
            modelView.addAttribute("brand", optBrand.get());
            return "create-vehicle";
        }
        return "redirect:/brand";
    }

    @PostMapping("/saveVehicle/{brandId}")
    public String saveVehicle(@PathVariable("brandId") Integer brandId, Vehicle vehicle) {
        Optional<Brand> optBrand = brandRepository.findById(brandId);
        if (optBrand.isPresent()) {
            vehicleRepository.save(vehicle);
            optBrand.get().getVehicles().add(vehicle);
            brandRepository.save(optBrand.get());
            return "redirect:/vehicle/listVehicles/" + brandId;
        }
        return "redirect:/brand";
    }

    @GetMapping("/editVehicle/{brandId}/{id}")
    public String editVehicle(Model modelView, @PathVariable("id") Integer id, @PathVariable("brandId") Integer brandId) {
        Optional<Brand> optBrand = brandRepository.findById(brandId);
        Optional<Vehicle> optVehicle = vehicleRepository.findById(id);
        if (optVehicle.isPresent() && optBrand.isPresent()) {
            modelView.addAttribute("vehicle", optVehicle.get());
            modelView.addAttribute("brand", optBrand.get());
            return "edit-vehicle";
        }
        return "redirect:/brand";
    }

    @PostMapping("/updateVehicle/{brandId}/{id}")
    public String updateVehicle(@PathVariable("id") Integer id, @PathVariable("brandId") Integer brandId, Vehicle vehicle) {
        Optional<Vehicle> optVehicle = vehicleRepository.findById(id);
        if (optVehicle.isPresent()) {
            vehicleRepository.save(vehicle);
            return "redirect:/vehicle/listVehicles/" + brandId;
        }
        return "redirect:/brand";
    }

    @GetMapping("/deleteVehicle/{brandId}/{id}")
    public String deleteVehicle(@PathVariable("id") Integer id, @PathVariable("brandId") Integer brandId) {
        Optional<Vehicle> optVehicle = vehicleRepository.findById(id);
        Optional<Brand> optBrand = brandRepository.findById(brandId);
        if (optVehicle.isPresent() && optBrand.isPresent()) {
            optBrand.get().getVehicles().remove(optVehicle.get());
            brandRepository.save(optBrand.get());
            vehicleRepository.delete(optVehicle.get());
            return "redirect:/vehicle/listVehicles/" + brandId;
        }
        return "redirect:/brand";
    }
}
