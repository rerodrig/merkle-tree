package com.algorithm.merkletree.domain;

public class Hash {
    private final String hash;
    private final long pieces;

    public Hash(String hash, long pieces) {
        this.hash = hash;
        this.pieces = pieces;
    }

    public String getHash() {
        return hash;
    }

    public long getPieces() {
        return pieces;
    }

}
