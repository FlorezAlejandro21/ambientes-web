package edi.unilibre.AppEstudiantes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import edi.unilibre.AppEstudiantes.models.EstudianteModel;

 

public interface EstudianteRepository extends JpaRepository<EstudianteModel, Integer> {}