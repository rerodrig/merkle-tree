package com.algorithm.merkletree.domain;

import com.algorithm.merkletree.utils.HashUtils;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class MerkleTree {

    private static final Logger LOGGER = Logger.getLogger(MerkleTree.class.getName());
    private static final byte[] FILLER = "0".getBytes(StandardCharsets.UTF_8);

    private final List<Node> leafNodes;
    private final List<Node> nodes;
    private Node rootNode;

    public MerkleTree(List<Node> leafNodes) throws NoSuchAlgorithmException {
        this.leafNodes = new ArrayList<>(leafNodes);
        this.nodes = new ArrayList<>(this.leafNodes);
        this.buildParents();
    }

    public String getHash() {
        return HashUtils.bytesToHex(this.rootNode.hash);
    }

    public long getPieces() {
        return this.leafNodes.stream().filter(n -> !n.isFiller).count();
    }

    public Node getRootNode() {
        return this.rootNode;
    }

    public List<Node> getNodes() {
        return Collections.unmodifiableList(this.nodes);
    }

    public List<Node> getLeafNodes() {
        return Collections.unmodifiableList(this.leafNodes);
    }

    private void buildParents() throws NoSuchAlgorithmException {
        int fillerNodesQty = getFillerNodesQty();

        for (int i = 0; i < fillerNodesQty; i++) {
            Node fillerNode = new Node(FILLER);
            this.leafNodes.add(fillerNode);
            this.nodes.add(fillerNode);
        }

        buildParents(this.leafNodes);
    }

    private int getFillerNodesQty() {
        int toComplete = -1;
        int n = 1;
        int leafSize = this.leafNodes.size();
        while (toComplete == -1) {
            if (leafSize <= n) {
                toComplete = n - leafSize;
            }
            n *= 2;
        }
        return toComplete;
    }

    private void buildParents(List<Node> nodes) throws NoSuchAlgorithmException {
        if (nodes.size() == 1) {
            this.rootNode = nodes.get(0);
        } else {
            List<Node> parentNodes = new ArrayList<>();
            for (int i = 0; i < nodes.size(); i += 2) {
                Node leftChild = nodes.get(i);
                Node rightChild = nodes.get(i + 1);
                Node parent = new Node(leftChild, rightChild);
                leftChild.parent = parent;
                rightChild.parent = parent;
                parentNodes.add(parent);
                this.nodes.add(parent);
            }
            buildParents(parentNodes);
        }
    }

    public void print() {
        LOGGER.info("Root node: " + HashUtils.bytesToHex(this.rootNode.hash));
        this.leafNodes.forEach(node -> {
            LOGGER.info(HashUtils.bytesToHex(node.hash));
        });
        LOGGER.info(String.valueOf(this.nodes.size()));
    }
}
