package com.internship.tool.controller;

import com.internship.tool.dto.RegulatoryChangeRequest;
import com.internship.tool.entity.RegulatoryChange;
import com.internship.tool.service.RegulatoryChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/changes")
@RequiredArgsConstructor
public class RegulatoryChangeController {

    private final RegulatoryChangeService service;

    @GetMapping
    public List<RegulatoryChange> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RegulatoryChange getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public RegulatoryChange create(@RequestBody RegulatoryChangeRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public RegulatoryChange update(@PathVariable Long id, @RequestBody RegulatoryChangeRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
