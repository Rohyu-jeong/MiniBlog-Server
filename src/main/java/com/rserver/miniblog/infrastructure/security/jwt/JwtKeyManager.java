package com.rserver.miniblog.infrastructure.security.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@Getter
public class JwtKeyManager {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public JwtKeyManager (
            @Value("${jwt.private-key-path}") String privateKeyPath,
            @Value("${jwt.public-key-path}") String publicKeyPath
    ) {
        try {
            this.privateKey = loadPrivateKey(privateKeyPath);
            this.publicKey = loadPublicKey(publicKeyPath);
        } catch (Exception e) {
            throw new RuntimeException("JWT 키 로딩 실패", e);
        }
    }

    private PrivateKey loadPrivateKey(String path) throws Exception {
        ClassPathResource resource = new ClassPathResource(path.replace("classpath:", ""));
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] keyBytes = inputStream.readAllBytes();
            String key = new String(keyBytes)
                    .replaceAll("-----BEGIN (.*)-----", "")
                    .replaceAll("-----END (.*)-----", "")
                    .replaceAll("\\s", "");
            byte[] decoded = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
            return KeyFactory.getInstance("RSA").generatePrivate(spec);
        }
    }

    private PublicKey loadPublicKey(String path) throws Exception {
        ClassPathResource resource = new ClassPathResource(path.replace("classpath:", ""));
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] keyBytes = inputStream.readAllBytes();
            String key = new String(keyBytes)
                    .replaceAll("-----BEGIN (.*)-----", "")
                    .replaceAll("-----END (.*)-----", "")
                    .replaceAll("\\s", "");
            byte[] decoded = Base64.getDecoder().decode(key);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            return KeyFactory.getInstance("RSA").generatePublic(spec);
        }
    }

}
