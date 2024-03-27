package com.eliu.tourist.agency.touristagency.helpers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ValidateParamHelper {

    public ResponseEntity<?> validate(BindingResult bindingResult) {
        Map<String, String> json = new HashMap<>();

        bindingResult.getFieldErrors().forEach(error -> {
            json.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(json);
    }

}
