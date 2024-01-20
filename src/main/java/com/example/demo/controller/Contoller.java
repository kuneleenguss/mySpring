package com.example.demo.controller;

import com.example.demo.logic.Pet;
import com.example.demo.logic.PetModel;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Contoller {
    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);

    @PostMapping(value = "/createPet", consumes = "application/json", produces = "text/html")
    public String createPet(@RequestBody Pet pet) {
        petModel.add(pet, newId.getAndIncrement());
        return "Pet has been created successfully";
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }

    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value = "/removePet", consumes = "application/json")
    public void deletePet(@RequestBody Map<String, Integer> id) {
        petModel.remove(id.get("id"));
    }

    @PutMapping(value = "/updatePet", consumes = "application/json")
    public void updatePet(@RequestBody Map<String, String> id) {
        petModel.update(Integer.parseInt(id.get("id")),
                new Pet(id.get("name"), id.get("type"), Integer.parseInt(id.get("age"))));
    }
}
