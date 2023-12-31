package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CoffeeService {
    @Autowired
    CoffeeRepository coffeeRepository;

    public Iterable<Coffee> index() {
        return coffeeRepository.findAll();
    }


    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDto coffeeDto) {
        Coffee coffee = coffeeDto.toEntity();
        if(coffee.getId() != null)
            return null;

        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id, CoffeeDto coffeeDto) {
        Coffee coffee = coffeeDto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null || id != coffee.getId())
            return null;

        target.patch(coffee);
        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null)
            return null;
        else{
            coffeeRepository.delete(target);
            return target;
        }
    }
}
