package com.eliu.tourist.agency.touristagency.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eliu.tourist.agency.touristagency.helpers.ValidateParamHelper;
import com.eliu.tourist.agency.touristagency.models.Address;
import com.eliu.tourist.agency.touristagency.services.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    @Qualifier("addressServiceImpl")
    private AddressService addressService;

    @Autowired
    private ValidateParamHelper validator;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Address> addresses = addressService.findAll();
        if(!addresses.isEmpty()) {
            return ResponseEntity.ok().body(addresses);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(Collections.singletonMap("no-registers", "No se han encontrado registros de direcciones"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<Address> optional = addressService.findById(id);
        if(optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Address address, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return validator.validate(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(addressService.save(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Address address, BindingResult bindingResult, @PathVariable Integer id) {
        if(bindingResult.hasErrors()) {
            return validator.validate(bindingResult);
        }
        Optional<Address> optional = addressService.update(id, address);
        if(optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED.value()).body(optional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(Collections.singletonMap("no-register", "El registro no fue encontrado, por lo tanto, no se pudo actualizar"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Optional<Address> optional = addressService.delete(id);
        if(!optional.isEmpty()) {
            return ResponseEntity.ok().body(optional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(Collections.singletonMap("no-register", "El registro no fue encontrado, por lo tanto, no puso ser eliminado"));
    }

}
