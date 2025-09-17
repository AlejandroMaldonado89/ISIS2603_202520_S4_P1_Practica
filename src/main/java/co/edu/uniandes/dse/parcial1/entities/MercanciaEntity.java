package co.edu.uniandes.dse.parcial1.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class MercanciaEntity extends BaseEntity{
   
    private String Nombre;
    private int CodigoBarras;
    private LocalDateTime FechaRecepcion;
    private int CantidadDisponible;

    @PodamExclude
    @ManyToOne
    private UbicacionBodegaEntity ubicacionBodega;

}
