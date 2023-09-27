package edi.unilibre.AppEstudiantes.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edi.unilibre.AppEstudiantes.models.EstudianteModel;
import edi.unilibre.AppEstudiantes.repository.EstudianteRepository;

@RestController
@RequestMapping("/api/")

public class EstudianteController {
    @Autowired
    private EstudianteRepository repositorioEstudiante;

    @PostMapping("/crea")
    public ResponseEntity<EstudianteModel> creaEstudiante(@RequestBody EstudianteModel estudiante) {
        EstudianteModel nuevoEstudiante = repositorioEstudiante.save(estudiante);
        return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
    }

    @GetMapping("/estudiantes")
    public List<EstudianteModel> traeEstudiantes() {
        return repositorioEstudiante.findAll();
    }

    @GetMapping("/estudiante/{id}")
    public Optional<EstudianteModel> traeUnEstudiante(@PathVariable Integer id) {
        return repositorioEstudiante.findById(id);
    }

    @PutMapping("/actualiza/{id}")
    public ResponseEntity<EstudianteModel> actualizaEstudiante(@PathVariable Integer id,
            @RequestBody EstudianteModel estudiante) {
        Optional<EstudianteModel> estudianteActual = repositorioEstudiante.findById(id);
        if (estudianteActual == null) {
            return new ResponseEntity<EstudianteModel>(HttpStatus.NOT_FOUND);
        }
        estudianteActual.get().setNombre(estudiante.getNombre());
        estudianteActual.get().setApellido(estudiante.getApellido());
        estudianteActual.get().setCorreo(estudiante.getCorreo());
        repositorioEstudiante.save(estudianteActual.get());
        return new ResponseEntity<EstudianteModel>(HttpStatus.OK);
    }

    // DELETE Delete

    @DeleteMapping("/borra/{id}")
    public ResponseEntity<HttpStatus> borraEstudiante(@PathVariable Integer id) {
        Optional<EstudianteModel> estudiante = repositorioEstudiante.findById(id);
        if (estudiante == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repositorioEstudiante.delete(estudiante.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}