package com.org.vetconnect.platform.profiles.interfaces.rest.resources;

public record CreateVetCenterResource(String name, String email, Long ruc, Long phone) {
}
