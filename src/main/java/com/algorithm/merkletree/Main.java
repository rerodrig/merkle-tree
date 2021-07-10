package com.algorithm.merkletree;

import com.algorithm.merkletree.utils.MerkleTreeFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        String filePath = args[0];
        MerkleTreeFactory.build(filePath);
    }
}
