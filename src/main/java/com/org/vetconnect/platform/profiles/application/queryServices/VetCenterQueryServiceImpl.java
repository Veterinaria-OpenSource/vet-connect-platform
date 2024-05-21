package com.org.vetconnect.platform.profiles.application.queryServices;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllVetCentersQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetVetCenterByIdQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetVetCenterByNameQuery;
import com.org.vetconnect.platform.profiles.domain.services.VetCenterQueryService;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.VetCenterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetCenterQueryServiceImpl implements VetCenterQueryService {

    private final VetCenterRepository vetCenterRepository;

    public VetCenterQueryServiceImpl(VetCenterRepository vetCenterRepository) {
        this.vetCenterRepository = vetCenterRepository;
    }

    @Override
    public Optional<VetCenter> handle(GetVetCenterByIdQuery query) {
        return vetCenterRepository.findById(query.id());
    }

    @Override
    public Optional<VetCenter> handle(GetVetCenterByNameQuery query) {
        return vetCenterRepository.findVetCenterByVetCenterName(query.vetCenterName());
    }

    @Override
    public List<VetCenter> handle(GetAllVetCentersQuery query) {
        return vetCenterRepository.findAll();
    }
}
