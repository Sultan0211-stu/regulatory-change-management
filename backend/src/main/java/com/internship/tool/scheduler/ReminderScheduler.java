package com.internship.tool.scheduler;

import com.internship.tool.entity.RegulatoryChange;
import com.internship.tool.repository.RegulatoryChangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReminderScheduler {

    private final RegulatoryChangeRepository repository;

    @Scheduled(cron = "0 0 8 * * *")
    public void checkUpcomingChanges() {
        LocalDate upcoming = LocalDate.now().plusDays(7);
        List<RegulatoryChange> changes = repository.findByStatus("PENDING");
        changes.stream()
                .filter(c -> c.getEffectiveDate() != null && c.getEffectiveDate().isEqual(upcoming))
                .forEach(c -> log.info("Upcoming regulatory change in 7 days: {}", c.getTitle()));
    }
}
