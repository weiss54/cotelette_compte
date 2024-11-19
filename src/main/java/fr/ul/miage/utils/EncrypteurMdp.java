package fr.ul.miage.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrypteurMdp {

    public static String encryptPassword(String password) {
        try {
            // Créer une instance de MessageDigest pour SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Convertir le mot de passe en tableau de bytes
            byte[] encodedHash = digest.digest(password.getBytes());

            // Convertir le tableau de bytes en une représentation hexadécimale
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

}

