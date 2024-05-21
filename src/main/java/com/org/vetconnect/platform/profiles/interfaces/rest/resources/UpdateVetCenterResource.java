package com.org.vetconnect.platform.profiles.interfaces.rest.resources;

public record UpdateVetCenterResource(Long id, String name, String email, Long ruc, Long phone) {
}
