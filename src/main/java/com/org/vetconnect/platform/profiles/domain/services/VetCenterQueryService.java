package com.org.vetconnect.platform.profiles.domain.services;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetVetCenterByIdQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetVetCenterByNameQuery;

import java.util.Optional;

public interface VetCenterQueryService {

    Optional<VetCenter> handle(GetVetCenterByIdQuery query);
    Optional<VetCenter> handle(GetVetCenterByNameQuery query);
}