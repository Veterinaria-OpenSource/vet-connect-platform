package com.org.vetconnect.platform.profiles.interfaces.rest;

import com.org.vetconnect.platform.profiles.domain.model.queries.GetVetCenterByIdQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetVetCenterByNameQuery;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterName;
import com.org.vetconnect.platform.profiles.domain.services.VetCenterCommandService;
import com.org.vetconnect.platform.profiles.domain.services.VetCenterQueryService;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.CreateVetCenterResource;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenterResource;
import com.org.vetconnect.platform.profiles.interfaces.rest.transform.CreateVetCenterCommandFromResourceAssembler;
import com.org.vetconnect.platform.profiles.interfaces.rest.transform.UpdateVetCenterCommandFromResourceAssembler;
import com.org.vetconnect.platform.profiles.interfaces.rest.transform.VetCenterResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/vet-centers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Vet Centers", description = "Vet Centers Management Endpoints")
public class VetCenterController {

    private final VetCenterQueryService vetCenterQueryService;
    private final VetCenterCommandService vetCenterCommandService;

    public VetCenterController(VetCenterQueryService vetCenterQueryService, VetCenterCommandService vetCenterCommandService) {
        this.vetCenterQueryService = vetCenterQueryService;
        this.vetCenterCommandService = vetCenterCommandService;
    }

    // crear centro veterinario
    @PostMapping
    public ResponseEntity<VetCenterResource> createVetCenter(@RequestBody CreateVetCenterResource resource) {
        var createVetCenterCommand = CreateVetCenterCommandFromResourceAssembler.toCommandFromResource(resource);
        var vetCenterId = vetCenterCommandService.handle(createVetCenterCommand);
        if (vetCenterId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getVetCenterByIdQuery = new GetVetCenterByIdQuery(vetCenterId);
        var vetCenter = vetCenterQueryService.handle(getVetCenterByIdQuery);
        if (vetCenter.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var vetCenterResource = VetCenterResourceFromEntityAssembler.toResourceFromEntity(vetCenter.get());
        return new ResponseEntity<>(vetCenterResource, HttpStatus.CREATED);
    }

    // obtener centro veterinario por id
    @GetMapping("/{vetCenterId}")
    public ResponseEntity<VetCenterResource> getVetCenterById(@PathVariable Long vetCenterId) {
        var getVetCenterByIdQuery = new GetVetCenterByIdQuery(vetCenterId);
        var vetCenter = vetCenterQueryService.handle(getVetCenterByIdQuery);
        if (vetCenter.isEmpty()) return ResponseEntity.badRequest().build();
        var vetCenterResource = VetCenterResourceFromEntityAssembler.toResourceFromEntity(vetCenter.get());
        return ResponseEntity.ok(vetCenterResource);
    }

    // obtener centro veterinario por nombre
    @GetMapping("/name/{vetCenterName}")
    public ResponseEntity<VetCenterResource> getVetCenterByName(@PathVariable String vetCenterName) {
        var getVetCenterByNameQuery = new GetVetCenterByNameQuery(new VetCenterName(vetCenterName));
        var vetCenter = vetCenterQueryService.handle(getVetCenterByNameQuery);
        if (vetCenter.isEmpty()) return ResponseEntity.badRequest().build();
        var vetCenterResource = VetCenterResourceFromEntityAssembler.toResourceFromEntity(vetCenter.get());
        return ResponseEntity.ok(vetCenterResource);
    }

    // actualizar centro veterinario
    @PutMapping("/{vetCenterId}")
    public ResponseEntity<VetCenterResource> updateVetCenter(
            @PathVariable Long vetCenterId,
            @RequestBody CreateVetCenterResource resource) {

        // 1. Obtener el centro veterinario existente
        var getVetCenterByIdQuery = new GetVetCenterByIdQuery(vetCenterId);
        var existingVetCenter = vetCenterQueryService.handle(getVetCenterByIdQuery);

        // 2. Comprobar si se encuentra
        if (existingVetCenter.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 3. Crear UpdateVetCenterCommand
        var updateVetCenterCommand = UpdateVetCenterCommandFromResourceAssembler.toCommandFromResource(resource, vetCenterId);

        // 4. Actualizar el centro veterinario existente
        vetCenterCommandService.handle(updateVetCenterCommand);

        // 5. Recuperar el centro veterinario actualizado (opcional)
        var updatedVetCenter = existingVetCenter.get(); // Usar el objeto existente si no se recupera en el servicio
        var vetCenterResource = VetCenterResourceFromEntityAssembler.toResourceFromEntity(updatedVetCenter);

        return new ResponseEntity<>(vetCenterResource, HttpStatus.OK);
    }

}
