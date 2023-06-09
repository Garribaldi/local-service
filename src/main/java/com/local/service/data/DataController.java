package com.local.service.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.local.service.data.entities.CityEntity;
import com.local.service.data.entities.CountryEntity;
import com.local.service.data.entities.EmployeeEntity;
import com.local.service.data.entities.InventoryEntity;
import com.local.service.data.entities.NbaTeamEntity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1")
public class DataController {

    private final ObjectMapper objectMapper;

    @Value("classpath:data/employees.json")
    Resource employeeResource;

    @Value("classpath:data/nba-teams.json")
    Resource nbaTeamResource;

    @Value("classpath:data/cities.json")
    Resource citiesResource;

    @Value("classpath:data/countries.json")
    Resource countriesResource;

    @Value("classpath:data/inventory.json")
    Resource inventoryResource;

    public DataController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EmployeeEntity>> getEmployees() throws IOException {

        File file = employeeResource.getFile();
        List<EmployeeEntity> employees = objectMapper.readValue(new File(file.toURI()), new TypeReference<>() {
        });

        if (employees.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/nba-teams")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NbaTeamEntity>> getNbaTeams() throws IOException {

        File file = nbaTeamResource.getFile();
        List<NbaTeamEntity> nbaTeams = objectMapper.readValue(new File(file.toURI()), new TypeReference<>() {
        });

        if (nbaTeams.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(nbaTeams);
    }

    @GetMapping("/cities")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CityEntity>> getCities() throws IOException {

        File file = citiesResource.getFile();
        List<CityEntity> cities = objectMapper.readValue(new File(file.toURI()), new TypeReference<>() {
        });

        if (cities.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(cities);
    }

    @GetMapping("/countries")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CountryEntity>> getCountries() throws IOException {

        File file = countriesResource.getFile();
        List<CountryEntity> countries = objectMapper.readValue(new File(file.toURI()), new TypeReference<>() {
        });

        if (countries.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(countries);
    }

    @GetMapping("/inventory")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<InventoryEntity>> getInventory() throws IOException {

        File file = inventoryResource.getFile();
        List<InventoryEntity> inventory = objectMapper.readValue(new File(file.toURI()), new TypeReference<>() {
        });

        if (inventory.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(inventory);
    }
}
