package ec.espe.edu.arquitectura.repository;
import ec.espe.edu.arquitectura.model.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {
    Optional<Empresa> findByRuc(String ruc);

}