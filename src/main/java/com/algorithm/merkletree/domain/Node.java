package com.algorithm.merkletree.domain;

import com.algorithm.merkletree.utils.HashUtils;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class Node {
    Node parent;
    Node childLeft;
    Node childRight;
    byte[] hash;
    byte[] content;
    boolean isFiller;

    public Node(Node leftNode, Node rightNode) throws NoSuchAlgorithmException {
        this.childLeft = leftNode;
        this.childRight = rightNode;
        this.hash = HashUtils.createHash(leftNode.hash, rightNode.hash);
    }

    public Node(byte[] bytes) throws NoSuchAlgorithmException {
        this.hash = HashUtils.createHash(bytes);
        this.content = bytes;
        this.isFiller = this.hash == "0".getBytes(StandardCharsets.UTF_8);
    }

}
