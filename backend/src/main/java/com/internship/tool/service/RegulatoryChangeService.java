package com.internship.tool.service;

import com.internship.tool.dto.RegulatoryChangeRequest;
import com.internship.tool.entity.RegulatoryChange;
import com.internship.tool.repository.RegulatoryChangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegulatoryChangeService {

    private final RegulatoryChangeRepository repository;

    public List<RegulatoryChange> getAll() {
        return repository.findAll();
    }

    public RegulatoryChange getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Change not found"));
    }

    public RegulatoryChange create(RegulatoryChangeRequest req) {
        RegulatoryChange change = new RegulatoryChange();
        change.setTitle(req.getTitle());
        change.setDescription(req.getDescription());
        change.setSource(req.getSource());
        change.setStatus(req.getStatus() != null ? req.getStatus() : "PENDING");
        change.setEffectiveDate(req.getEffectiveDate());
        change.setCreatedAt(LocalDateTime.now());
        change.setUpdatedAt(LocalDateTime.now());
        return repository.save(change);
    }

    public RegulatoryChange update(Long id, RegulatoryChangeRequest req) {
        RegulatoryChange change = getById(id);
        change.setTitle(req.getTitle());
        change.setDescription(req.getDescription());
        change.setSource(req.getSource());
        change.setStatus(req.getStatus());
        change.setEffectiveDate(req.getEffectiveDate());
        change.setUpdatedAt(LocalDateTime.now());
        return repository.save(change);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
