package com.algorithm.merkletree;

import com.algorithm.merkletree.domain.MerkleTree;
import com.algorithm.merkletree.utils.MerkleTreeFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static final List<MerkleTree> hashes = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws Exception {
        String filePath = args[0];
        MerkleTree merkleTree = MerkleTreeFactory.build(filePath);
        hashes.add(merkleTree);
    }
}
