package br.ibmec.backend.projeto.ap1.car_gallery.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ibmec.backend.projeto.ap1.car_gallery.model.Brand;
import br.ibmec.backend.projeto.ap1.car_gallery.repository.BrandRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    public BrandRepository repository;

    @GetMapping()
    public String index(Model modelView){
        modelView.addAttribute("listBrands", repository.findAll());
        return "list-brand";
    }

    @GetMapping("/createBrand")
    public String create(Brand brand){
        return "create-brand";
    }

    @PostMapping("/saveBrand")
    public String saveBrand(@Valid @ModelAttribute Brand brand, BindingResult result){
        if(result.hasErrors()){
            return "create-brand";
        }
        repository.save(brand);
        return "redirect:/brand";
    }

    @GetMapping("/editBrand/{id}")
    public String editBrand(Model modelView, @PathVariable("id") Integer id){
        Optional<Brand> optBrand = repository.findById(id);
        modelView.addAttribute("brand", optBrand.get());
        return "edit-brand";
    }

    @PostMapping("/updateBrand/{id}")
    public String updateBrand(@PathVariable("id") Integer id, @Valid @ModelAttribute Brand brand, BindingResult result){
        if(result.hasErrors()){
            return "edit-brand";
        }
        repository.save(brand);
        return "redirect:/brand";
    }

    @GetMapping("/deleteBrand/{id}")
    public String deleteBrand(@PathVariable("id") Integer id){
        Optional<Brand> optBrand = repository.findById(id);
        if(optBrand.isPresent()){
            repository.delete(optBrand.get());
        }
        return "redirect:/brand";
    }
}
