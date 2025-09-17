package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(UbicacionBodegaService.class)
public class UbicacionBodegaServiceTest {

    @Autowired
    private UbicacionBodegaService ubicacionBodegaService;

    @PersistenceContext
    private EntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    private List<UbicacionBodegaEntity> ubicacionList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.createQuery("delete from UbicacionBodegaEntity").executeUpdate();
    }
    private void insertData() {
    for (int i = 0; i < 3; i++) {
        UbicacionBodegaEntity ubicacionEntity = new UbicacionBodegaEntity();
        ubicacionEntity.setNumeroEstante(10+i);
        ubicacionEntity.setCanastaDeposita("x");
        ubicacionEntity.setPesoMaximo(10);
        entityManager.persist(ubicacionEntity);
        ubicacionList.add(ubicacionEntity);
    }
}
@Test
void testCrearUbicacion(){
    UbicacionBodegaEntity nuevaUbicacion = factory.manufacturePojo(UbicacionBodegaEntity.class);
    nuevaUbicacion.setNumeroEstante(10);
    nuevaUbicacion.setCanastaDeposita("xx");
    nuevaUbicacion.setPesoMaximo(11);
    UbicacionBodegaEntity ubicacionCreada = ubicacionBodegaService.crearUbicacionBodega(nuevaUbicacion);
    assertNotNull(ubicacionCreada);
    UbicacionBodegaEntity ubicacion = entityManager.find(UbicacionBodegaEntity.class,ubicacionCreada.getId());
    assertEquals(ubicacionCreada.getId(), ubicacion.getId());
    assertEquals(ubicacionCreada.getNumeroEstante(), ubicacion.getNumeroEstante());
    assertEquals(ubicacionCreada.getCanastaDeposita(), ubicacion.getCanastaDeposita());
    assertEquals(ubicacionCreada.getPesoMaximo(), ubicacion.getPesoMaximo());

}
    
}
