package com.algorithm.merkletree.domain;

import java.util.Base64;

public class Piece {
    private final String content;
    private final String[] proof;

    public Piece(byte[] content, String[] proof) {
        this.content = Base64.getEncoder().encodeToString(content);
        this.proof = proof;
    }

    public String getContent() {
        return content;
    }

    public String[] getProof() {
        return proof;
    }
}
