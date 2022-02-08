package com.alzal.nadeulseoulbackend.domain.stores.service;

import com.alzal.nadeulseoulbackend.domain.stores.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

}
