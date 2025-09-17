package co.edu.uniandes.dse.parcial1.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class UbicacionBodegaEntity extends BaseEntity{
   
    private int numero_estante;
    private String canasta_deposita;
    private float peso_maximo;

    @PodamExclude
    @OneToMany
    private List<MercanciaEntity> mercancia = new ArrayList();
}
