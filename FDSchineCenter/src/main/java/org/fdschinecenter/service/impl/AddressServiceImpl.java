package org.fdschinecenter.service.impl;

import org.fdschinecenter.entity.Address;
import org.fdschinecenter.repository.AddressRepository;
import org.fdschinecenter.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
/**
 * Class to manage AddressServiceImpl.
 */
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public void saveAddress(Address address) {
        this.addressRepository.save(address);
    }
}
