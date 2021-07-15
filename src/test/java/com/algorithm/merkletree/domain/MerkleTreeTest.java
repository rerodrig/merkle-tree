package com.algorithm.merkletree.domain;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MerkleTreeTest {

    @Test
    public void shouldCreateValidMerkleTree() throws NoSuchAlgorithmException {
        AtomicReference<List<Node>> leafNodesRef = new AtomicReference<>(new ArrayList<>());
        IntStream.rangeClosed(1, 4).forEach(i -> {
            Node node = null;
            try {
                node = new Node(String.valueOf(i).getBytes(StandardCharsets.UTF_8));
            } catch (NoSuchAlgorithmException e) {
                fail("Error creating merkle tree", e);
            }
            leafNodesRef.get().add(node);
        });

        MerkleTree merkleTree = new MerkleTree(leafNodesRef.get());

        assertEquals(4, merkleTree.getPieces());
        assertEquals("cd53a2ce68e6476c29512ea53c395c7f5d8fbcb4614d89298db14e2a5bdb5456", merkleTree.getHash());

        assertEquals(7, merkleTree.getNodes().size());
        assertEquals(4, merkleTree.getLeafNodes().size());

        assertEquals(merkleTree.getLeafNodes().get(0).getParent(), merkleTree.getLeafNodes().get(1).getParent());
        assertEquals(merkleTree.getLeafNodes().get(2).getParent(), merkleTree.getLeafNodes().get(3).getParent());

        assertEquals(merkleTree.getLeafNodes().get(0).getParent().getParent(), merkleTree.getLeafNodes().get(2).getParent().getParent());
        assertEquals(merkleTree.getLeafNodes().get(0).getParent().getParent(), merkleTree.getRootNode());
    }
}
