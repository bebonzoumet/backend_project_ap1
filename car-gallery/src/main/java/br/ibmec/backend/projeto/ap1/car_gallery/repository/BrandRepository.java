package br.ibmec.backend.projeto.ap1.car_gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ibmec.backend.projeto.ap1.car_gallery.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{
}
