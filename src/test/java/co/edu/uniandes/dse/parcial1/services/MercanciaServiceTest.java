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
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(MercanciaService.class)
public class MercanciaServiceTest {
    
    @Autowired
    private MercanciaService mercanciaService;

    @PersistenceContext
    private EntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    private List<MercanciaEntity> mercanciaList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.createQuery("delete from MercanciaEntity").executeUpdate();
    }
    private void insertData() {
    for (int i = 0; i < 3; i++) {
        MercanciaEntity mercanciaEntity = new MercanciaEntity();
        mercanciaEntity.setNombre("Mercancia " + i);
        mercanciaEntity.setCodigoBarras(10+i);
        mercanciaEntity.setFechaRecepcion(java.time.LocalDateTime.now());
        mercanciaEntity.setCantidadDisponible(i);
        entityManager.persist(mercanciaEntity);
        mercanciaList.add(mercanciaEntity);
    }
}
@Test
void testCrearMercancia(){
    MercanciaEntity nuevaMercancia = factory.manufacturePojo(MercanciaEntity.class);
    nuevaMercancia.setNombre("papas");
    nuevaMercancia.setCodigoBarras(10);
    nuevaMercancia.setFechaRecepcion(java.time.LocalDateTime.now());
    nuevaMercancia.setCantidadDisponible(10);
    UbicacionBodegaEntity ubicacion= new UbicacionBodegaEntity();
    ubicacion.setNumeroEstante(1);
    ubicacion.setCanastaDeposita("x");
    ubicacion.setPesoMaximo(10.5);
    nuevaMercancia.setUbicacionBodega(ubicacion);
    MercanciaEntity mercanciaCreada= mercanciaService.crearMercancia(nuevaMercancia);
    assertNotNull(mercanciaCreada);
    MercanciaEntity mercancia= entityManager.find(MercanciaEntity.class,mercanciaCreada.getId());
    assertEquals(mercanciaCreada.getId(), mercancia.getId());
    assertEquals(mercanciaCreada.getNombre(), mercancia.getNombre());
    assertEquals(mercanciaCreada.getCodigoBarras(), mercancia.getCodigoBarras());
    assertEquals(mercanciaCreada.getFechaRecepcion(), mercancia.getFechaRecepcion());
    assertEquals(mercanciaCreada.getCantidadDisponible(), mercancia.getCantidadDisponible());
}
@Test
void testCrearMercanciaSinNombre(){
    MercanciaEntity nuevaMercancia = factory.manufacturePojo(MercanciaEntity.class);
    nuevaMercancia.setNombre("");
    nuevaMercancia.setCodigoBarras(10);
    nuevaMercancia.setFechaRecepcion(java.time.LocalDateTime.now());
    nuevaMercancia.setCantidadDisponible(10);

    try {
        mercanciaService.crearMercancia(nuevaMercancia);
    }
    catch (IllegalOperationException e){
        assertEquals("el nombre no puede ser vacio", e.getMessage());
    }
}
    
}
