package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.Binding;

import java.util.List;

public interface BindingService {
    List<Binding> getAllBindings();
    void createBinding(Binding binding);
    void updateBinding(String  id, Binding updatedBinding);

    void deleteBinding(String bindingId);
}