package com.org.vetconnect.platform.profiles.application.commandServices;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.domain.model.commands.CreatePetOwnerCommand;
import com.org.vetconnect.platform.profiles.domain.model.commands.UpdatePetOwnerCommand;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.PetOwnerDNI;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.PetOwnerEmail;
import com.org.vetconnect.platform.profiles.domain.services.PetOwnerCommandService;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class PetOwnerCommandServiceImpl implements PetOwnerCommandService {

    private final PetOwnerRepository petOwnerRepository;

    public PetOwnerCommandServiceImpl(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public Long handle(CreatePetOwnerCommand command) {
        // validate that the pet owner has a unique email
        var email = new PetOwnerEmail(command.email());

        petOwnerRepository.findPetOwnerByPetOwnerEmail(email)
                .map(petOwner -> {
                    throw new IllegalArgumentException("PetOwner already exists with email "
                            + email.email() + "");
                });

        // validate that the pet owner has a unique dni
        var dni = new PetOwnerDNI(command.dni());

        petOwnerRepository.findPetOwnerByPetOwnerDNI(dni)
                .map(petOwner -> {
                    throw new IllegalArgumentException("PetOwner already exists with dni "
                            + dni.petOwnerDNI() + "");
                });

        // create a new pet owner and save it
        var petOwner = new PetOwner(
                command.name(),
                command.email(),
                command.dni(),
                command.phone(),
                command.photo()
        );

        petOwnerRepository.save(petOwner);
        return petOwner.getId();
    }

    @Override
    public Long handle(UpdatePetOwnerCommand command) {
        var petOwner = petOwnerRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("PetOwner with id " + command.id() + " does not exist"));
        petOwner.setName(command.name());
        petOwner.setEmail(command.email());
        petOwner.setPhone(command.phone());
        petOwner.setPhoto(command.photo());
        petOwnerRepository.save(petOwner);
        return petOwner.getId();
    }
}
