package com.eliu.tourist.agency.touristagency.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliu.tourist.agency.touristagency.models.Address;
import com.eliu.tourist.agency.touristagency.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional(readOnly=true)
    @Override
    public List<Address> findAll() {
        return ((List<Address>) addressRepository.findAll());
    }

    @Transactional(readOnly=true)
    @SuppressWarnings("null")
    @Override
    public Optional<Address> findById(Integer id) {
        return addressRepository.findById(id);
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public Optional<Address> update(Integer id, Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isPresent()) {
            Address addressDB = optionalAddress.get();
            addressDB.setName(address.getName());
            addressDB.setNumber(address.getNumber());
            addressDB.setPeople(address.getPeople());
            return Optional.of(addressRepository.save(addressDB));
        }
        return optionalAddress;
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public Optional<Address> delete(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        optionalAddress.ifPresent(address -> addressRepository.delete(address));
        return optionalAddress;
    }

}
