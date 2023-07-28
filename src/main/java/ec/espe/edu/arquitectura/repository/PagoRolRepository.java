package ec.espe.edu.arquitectura.repository;

import ec.espe.edu.arquitectura.model.PagoRol;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface PagoRolRepository extends MongoRepository<PagoRol, String> {
    Optional<PagoRol> findByMesAndRucEmpresa(String mes, String rucEmpresa);

}