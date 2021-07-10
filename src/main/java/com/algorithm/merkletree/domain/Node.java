package com.algorithm.merkletree.domain;

import com.algorithm.merkletree.utils.HashUtils;

import java.security.NoSuchAlgorithmException;

public class Node {
    Node parent;
    Node childLeft;
    Node childRight;
    String hash;

    public Node(Node leftNode, Node rightNode) throws NoSuchAlgorithmException {
        this.childLeft = leftNode;
        this.childRight = rightNode;
        this.hash = HashUtils.createHash((leftNode.hash + rightNode.hash).getBytes());
    }

    public Node(byte[] bytes) throws NoSuchAlgorithmException {
        this.hash = HashUtils.createHash(bytes);
    }
}
