package com.algorithm.merkletree;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.logging.Logger;

public class Main {

    private static final int PIECE_SIZE = 1024;

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        String filePath = args[0];

        try {
            byte[] buffer = new byte[PIECE_SIZE];
            FileInputStream fileInputStream = new FileInputStream(filePath);
            int count = 0;
            while (fileInputStream.read(buffer) != -1) {

                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedHash = digest.digest(buffer);

                LOGGER.info(bytesToHex(encodedHash));

                count++;
            }
            LOGGER.info(String.valueOf(count));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] hash) {
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
