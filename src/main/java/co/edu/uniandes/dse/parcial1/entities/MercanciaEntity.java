package co.edu.uniandes.dse.parcial1.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class MercanciaEntity extends BaseEntity{
   
    private String nombre;
    private int codigo_barras;
    private LocalDate fecha_recepcion;
    private int cantidad_disponible;
    

}
