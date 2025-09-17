package co.edu.uniandes.dse.parcial1.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaService {
    
    @Autowired
     private MercanciaRepository mercanciaRepository;

    @Transactional
    public MercanciaEntity crearMercancia(MercanciaEntity mercancia) throws IllegalOperationException{
    if (!MercanciaRepository.findBycodigoBarras(mercancia.getCodigoBarras()).isEmpty()){
			throw new IllegalOperationException("el codigo de barras ya existe");
    }
    if (mercancia.getNombre() == null){
			throw new IllegalOperationException("El nombre es obligatorio y no puede estar vacio");
    }
    if(mercancia.getFechaRecepcion().isAfter(LocalDateTime.now())){
        throw new IllegalOperationException("La fecha de recepción no puede ser posterior a la fecha actual");
    }
}
}
