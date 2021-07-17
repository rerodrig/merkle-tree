package com.algorithm.merkletree.service;

import com.algorithm.merkletree.domain.MerkleTree;
import com.algorithm.merkletree.domain.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MerkleTreeService {

    // keeping "repo" here for simplicity
    private static final List<MerkleTree> HASHES = Collections.synchronizedList(new ArrayList<>());

    public void addHash(MerkleTree merkleTree) {
        HASHES.add(merkleTree);
    }

    public List<MerkleTree> getMerkleTrees() {
        return Collections.unmodifiableList(HASHES);
    }

    public Piece getPiece(String hash, int piece) {
        MerkleTree merkleTree = HASHES.stream().filter(mt -> mt.getHash().equals(hash))
                .findFirst().orElseThrow(() -> new HashNotFoundException("Hash not found: " + hash));

        return merkleTree.getPiece(piece);
    }
}
