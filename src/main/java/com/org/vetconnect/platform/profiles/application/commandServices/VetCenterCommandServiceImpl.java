package com.org.vetconnect.platform.profiles.application.commandServices;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.profiles.domain.model.commands.CreateVetCenterCommand;
import com.org.vetconnect.platform.profiles.domain.model.commands.UpdateVetCenterCommand;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterRUC;
import com.org.vetconnect.platform.profiles.domain.services.VetCenterCommandService;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.VetCenterRepository;
import org.springframework.stereotype.Service;

@Service
public class VetCenterCommandServiceImpl implements VetCenterCommandService {

    private final VetCenterRepository vetCenterRepository;

    public VetCenterCommandServiceImpl(VetCenterRepository vetCenterRepository) {
        this.vetCenterRepository = vetCenterRepository;
    }


    @Override
    public Long handle(CreateVetCenterCommand command) {

        // Validate that the vet center has a unique RUC
        var ruc =  new VetCenterRUC(command.ruc());

        vetCenterRepository.findVetCenterByVetCenterRUC(ruc)
                .map(vetCenter -> {
                    throw new IllegalArgumentException("VetCenter already exists with RUC "
                            + ruc.vetCenterRUC() + "");
                });

        // Create a new vet center and save it
        var vetCenter = new VetCenter(
                command.name(),
                command.email(),
                command.ruc(),
                command.phone()
        );

        vetCenterRepository.save(vetCenter);
        return vetCenter.getId();
    }

    @Override
    public Long handle(UpdateVetCenterCommand command) {
        var vetCenter = vetCenterRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("VetCenter with id " + command.id() + " does not exist"));
        vetCenter.setName(command.name());
        vetCenter.setEmail(command.email());
        vetCenter.setPhone(command.phone());
        vetCenter.setRUC(command.ruc());
        vetCenterRepository.save(vetCenter);
        return vetCenter.getId();
    }
}
