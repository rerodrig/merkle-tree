package com.algorithm.merkletree.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    public static byte[] createHash(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(bytes);
    }

    public static byte[] createHash(byte[] b1, byte[] b2) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[b1.length + b2.length];
        System.arraycopy(b1, 0, bytes, 0, b1.length);
        System.arraycopy(b2, 0, bytes, b1.length, b2.length);

        return createHash(bytes);
    }

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
