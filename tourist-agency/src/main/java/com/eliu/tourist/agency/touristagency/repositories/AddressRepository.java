package com.eliu.tourist.agency.touristagency.repositories;

import org.springframework.data.repository.CrudRepository;

import com.eliu.tourist.agency.touristagency.models.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    
}
