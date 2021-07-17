package com.algorithm.merkletree.utils;

import com.algorithm.merkletree.domain.MerkleTree;
import com.algorithm.merkletree.domain.Node;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MerkleTreeFactory {

    private static final int PIECE_SIZE = 1024;
    private static final Logger LOGGER = Logger.getLogger(MerkleTreeFactory.class.getName());

    public static MerkleTree build(String filePath) throws Exception {

        try {

            byte[] buffer = new byte[PIECE_SIZE];
            FileInputStream fileInputStream = new FileInputStream(filePath);

            List<Node> leafNodes = new ArrayList<>();
            while (fileInputStream.read(buffer) != -1) {
                Node node = new Node(buffer);
                leafNodes.add(node);
            }

            MerkleTree merkleTree = new MerkleTree(leafNodes);
            merkleTree.print();

            return merkleTree;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating merkle tree", e);
            throw e;
        }
    }


}
