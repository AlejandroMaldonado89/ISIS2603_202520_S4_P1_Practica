package co.edu.uniandes.dse.parcial1.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class UbicacionBodegaEntity extends BaseEntity{
   
    private int numero_estante;
    private String canasta_deposita;
    private float peso_maximo;
}
