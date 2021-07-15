package com.algorithm.merkletree.domain;

import com.algorithm.merkletree.utils.FillerNode;
import com.algorithm.merkletree.utils.HashUtils;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class Node {
    private Node parent;
    private Node childLeft;
    private Node childRight;
    private byte[] hash;
    private byte[] content;
    private boolean isFiller;

    public Node(Node leftNode, Node rightNode) throws NoSuchAlgorithmException {
        this.childLeft = leftNode;
        this.childRight = rightNode;
        this.hash = HashUtils.createHash(leftNode.hash, rightNode.hash);
    }

    public Node(byte[] bytes) throws NoSuchAlgorithmException {
        this.hash = HashUtils.createHash(bytes);
        this.content = bytes;
        this.isFiller = this.hash == FillerNode.CONTENT;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getChildLeft() {
        return childLeft;
    }

    public Node getChildRight() {
        return childRight;
    }

    public byte[] getHash() {
        return hash;
    }

    public byte[] getContent() {
        return content;
    }

    public boolean isFiller() {
        return isFiller;
    }

}
