package com.local.service.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DataController {

    private final ObjectMapper objectMapper;

    @Value("classpath:data/employees.json")
    Resource employeeResource;

    @Value("classpath:data/nba-teams.json")
    Resource nbaTeamResource;

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
}
