package com.org.vetconnect.platform.iam.application.internal.outboundservices.hashing;

public interface HashingService {
    // encriptar la contraseña en texto plano
    String encode(CharSequence rawPassword);

    // comparar la contraseña en texto plano con la contraseña encriptada
    boolean matches(CharSequence rawPassword, String encodedPassword);
}