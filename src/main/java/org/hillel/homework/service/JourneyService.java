package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.JourneyEntity;
import org.hillel.homework.persistence.entity.VehicleEntity;
import org.hillel.homework.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service(value = "transactionalJourneyService")
public class JourneyService {

    private JourneyRepository journeyRepository;

    @Autowired
    public JourneyService(JourneyRepository repository) {

        this.journeyRepository = repository;
    }

    @Transactional
    public JourneyEntity createOrUpdate(final JourneyEntity entity) {
        return journeyRepository.save(entity);
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

    @javax.transaction.Transactional
    public Collection<JourneyEntity> findAll() {
        return journeyRepository.findAll();
    }

    @javax.transaction.Transactional
    public Collection<JourneyEntity> getPageSortBy(int page, int pageSize, String orderFieldName) {
        Page<JourneyEntity> pageList = journeyRepository.findAll(PageRequest.of(page, pageSize, Sort.by(orderFieldName)));
        return pageList.getContent();
    }

    @Transactional
    public void removeById(Integer id) {
        journeyRepository.deleteById(id);
    }

    @Transactional
    public void remove(JourneyEntity journey) {
        journeyRepository.delete(journey);
    }
}
