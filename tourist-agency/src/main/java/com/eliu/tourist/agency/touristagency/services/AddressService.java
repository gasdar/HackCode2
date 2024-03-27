package com.eliu.tourist.agency.touristagency.services;

import java.util.List;
import java.util.Optional;

import com.eliu.tourist.agency.touristagency.models.Address;

public interface AddressService {

    List<Address> findAll();

    Optional<Address> findById(Integer id);

    Address save(Address address);

    Optional<Address> update(Integer id, Address address);

    Optional<Address> delete(Integer id);

}
