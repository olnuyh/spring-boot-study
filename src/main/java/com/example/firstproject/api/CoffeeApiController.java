package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoffeeApiController {
    @Autowired
    CoffeeRepository coffeeRepository;

    // GET
    @GetMapping("/api/coffees")
    public Iterable<Coffee> index(){
        return coffeeRepository.findAll();
    }

    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);

        return (coffee != null)?
                ResponseEntity.status(HttpStatus.OK).body(coffee):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // POST
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto coffeeDto){
        Coffee coffee = coffeeDto.toEntity();
        if(coffee.getId() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Coffee created = coffeeRepository.save(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto coffeeDto){
        Coffee coffee = coffeeDto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null || id != coffee.getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        else{
            coffeeRepository.delete(target);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
