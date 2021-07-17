package com.algorithm.merkletree;

import com.algorithm.merkletree.domain.MerkleTree;
import com.algorithm.merkletree.service.MerkleTreeService;
import com.algorithm.merkletree.utils.MerkleTreeFactory;

import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        MerkleTreeService merkleTreeService = new MerkleTreeService();
        String filePath = args[0];
        MerkleTree merkleTree = MerkleTreeFactory.build(filePath);
        merkleTreeService.addHash(merkleTree);
    }
}
