package ec.espe.edu.arquitectura.controller;

import ec.espe.edu.arquitectura.controller.dto.PagoRolRQ;
import ec.espe.edu.arquitectura.controller.dto.PagoRolRS;
import ec.espe.edu.arquitectura.service.PagoRolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pagorol")
public class PagoRolController{

    private final PagoRolService pagoRolService;


    public PagoRolController(PagoRolService pagoRolService) {
        this.pagoRolService = pagoRolService;
    }

    @PostMapping({"/registrar"})
    public ResponseEntity registrarPagoRolMensual(@RequestBody PagoRolRQ pagoRolRQ) {
        try {
            this.pagoRolService.createPagoRolMensual(pagoRolRQ);
            return ResponseEntity.ok().build();
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().body(rte.getMessage());
        }
    }
    @GetMapping({"validar/{rucEmpresa}/{mes}"})
    public ResponseEntity validarPagoRolMensual(
            @PathVariable("rucEmpresa")  String rucEmpresa,
            @PathVariable("mes")  String mes) {
        try {
            PagoRolRS pagoRolRS = this.pagoRolService.validatePagoRol(mes, rucEmpresa);
            return ResponseEntity.ok().body(pagoRolRS);
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().body(rte.getMessage());
        }
    }
}
