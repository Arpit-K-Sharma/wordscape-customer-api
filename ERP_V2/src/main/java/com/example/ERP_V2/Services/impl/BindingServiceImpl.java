package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.Binding;
import com.example.ERP_V2.Repository.BindingRepo;
import com.example.ERP_V2.Services.BindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BindingServiceImpl implements BindingService {

    @Autowired
    private BindingRepo bindingRepository; // assuming BindingRepository is a Spring Data JPA repository

    @Override
    public List<Binding> getAllBindings() {
        return bindingRepository.findAll();
    }

    @Override
    public void createBinding(Binding binding) {
        bindingRepository.save(binding);
    }

    @Override
    public void updateBinding(int id, Binding updatedBinding) {
        Binding existingBinding = bindingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Binding with id " + id + " not found"));

        if (updatedBinding.getBindingType() != null) {
            existingBinding.setBindingType(updatedBinding.getBindingType());
        }
        if (updatedBinding.getRate() != null) {
            existingBinding.setRate(updatedBinding.getRate());
        }

        bindingRepository.save(existingBinding);
    }
}
