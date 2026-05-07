package com.internship.tool.repository;

import com.internship.tool.entity.RegulatoryChange;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegulatoryChangeRepository extends JpaRepository<RegulatoryChange, Long> {
    List<RegulatoryChange> findByStatus(String status);
}
