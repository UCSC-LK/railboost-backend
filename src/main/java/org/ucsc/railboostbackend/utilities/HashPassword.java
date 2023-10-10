package org.ucsc.railboostbackend.utilities;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class HashPassword {

    private SecretKeyFactory factory;
    private final int iterations = 65536;
    private final int desiredKeyLen = 256;

    public HashPassword() {
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error Hashing password!!\n" + e.getMessage());
        }
    }

    public String hash(String password, String salt) {
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        String hashed = null;

        try {
            hashed = Base64.getEncoder().encodeToString(
                    factory.generateSecret(new PBEKeySpec(password.toCharArray(), saltBytes , iterations, desiredKeyLen)).getEncoded()
            );
        } catch (InvalidKeySpecException e) {
            System.out.println("Error : Invalid key.\n" + e.getMessage());
        }

        return hashed;
    }


    public Map<String, String> hash(String password) throws InvalidKeySpecException {
        Map<String, String> results = new HashMap<>();

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        results.put("salt", Base64.getEncoder().encodeToString(salt));

        results.put(
                "hash",
                Base64.getEncoder().encodeToString(factory.generateSecret(new PBEKeySpec(password.toCharArray(), salt, iterations, desiredKeyLen)).getEncoded())
        );

        return results;
    }
}
