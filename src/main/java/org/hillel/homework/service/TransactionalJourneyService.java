package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.JourneyEntity;
import org.hillel.homework.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(value = "transactionalJourneyService")
public class TransactionalJourneyService {

    @Autowired
    private JourneyRepository journeyRepository;

    @Transactional
    public JourneyEntity createOrUpdate(final JourneyEntity entity) {
        return journeyRepository.createOrUpdate(entity);
    }

    @Transactional(readOnly = true)
    public Optional<JourneyEntity> findById(Integer id, boolean withDependencies) {
        final Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if (withDependencies && byId.isPresent()) {
            byId.get().getVehicle().getName();
            byId.get().getStopPoints().size();
        }
        return byId;
    }
}
