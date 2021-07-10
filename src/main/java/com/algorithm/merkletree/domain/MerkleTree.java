package com.algorithm.merkletree.domain;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MerkleTree {

    private static final Logger LOGGER = Logger.getLogger(MerkleTree.class.getName());
    private static final byte[] FILLER = "0".getBytes(StandardCharsets.UTF_8);

    private final List<Node> leafNodes = new ArrayList<>();
    private final List<Node> nodes = new ArrayList<>();
    private Node rootNode;

    public void addLeafNode(Node leafNode) {
        leafNodes.add(leafNode);
        nodes.add(leafNode);
    }

    public void buildParents() throws NoSuchAlgorithmException {
        int fillerNodesQty = getFillerNodesQty();

        for (int i = 0; i < fillerNodesQty; i++) {
            Node fillerNode = new Node(FILLER);
            leafNodes.add(fillerNode);
            nodes.add(fillerNode);
        }

        buildParents(leafNodes);
    }

    private int getFillerNodesQty() {
        int toComplete = -1;
        int n = 1;
        int leafSize = leafNodes.size();
        while (toComplete == -1) {
            if (leafSize <= n) {
                toComplete = n - leafSize;
            }
            n *= 2;
        }
        return toComplete;
    }

    public void buildParents(List<Node> n) throws NoSuchAlgorithmException {
        if (n.size() == 1) {
            rootNode = n.get(0);
        } else {
            List<Node> parentNodes = new ArrayList<>();
            for (int i = 0; i < n.size(); i += 2) {
                Node left = n.get(i);
                Node right = n.get(i + 1);
                Node parent = new Node(left, right);
                parentNodes.add(parent);
                nodes.add(parent);
            }
            buildParents(parentNodes);
        }
    }

    public void print() {
        LOGGER.info("Root node: " + rootNode.hash);
        leafNodes.forEach(node -> LOGGER.info(node.hash));
        LOGGER.info(String.valueOf(nodes.size()));
    }
}
