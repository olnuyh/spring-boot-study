package com.example.firstproject.api;

import com.example.firstproject.entity.Pizza;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PizzaApiController {
    @GetMapping("/api/pizzas")
    public ResponseEntity<Pizza> pizzas(){
        return null;
    }

    @GetMapping("/api/pizzas/{id}")
    public ResponseEntity<Pizza> pizza(@PathVariable Long id){
        return null;
    }

    @PostMapping("/api/pizzas")
    public ResponseEntity<Pizza> create(@RequestBody Pizza pizza){
        return null;
    }

    @PatchMapping("/api/pizzas/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Long id, @RequestBody Pizza pizza){
        return null;
    }

    @DeleteMapping("/api/pizzas/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Long id){
        return null;
    }
}
