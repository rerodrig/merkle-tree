package com.algorithm.merkletree.service;

public class HashNotFoundException extends RuntimeException {
    public HashNotFoundException(String message) {
        super(message);
    }
}
