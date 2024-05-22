package com.org.vetconnect.platform.profiles.interfaces.rest.transform;

import com.org.vetconnect.platform.profiles.domain.model.commands.CreatePetOwnerCommand;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.CreatePetOwnerResource;

public class CreatePetOwnerCommandFromResourceAssembler {

    public static CreatePetOwnerCommand toCommandFromResource(CreatePetOwnerResource resource) {
        return new CreatePetOwnerCommand(
                resource.name(),
                resource.email(),
                resource.dni(),
                resource.phone(),
                resource.photo()
        );
    }
}
