package com.algorithm.merkletree.domain;

import com.algorithm.merkletree.utils.FillerNode;
import com.algorithm.merkletree.utils.HashUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Node {
    private Node parent;
    private Node childLeft;
    private Node childRight;
    private final byte[] hash;
    private byte[] content;
    private boolean isFiller;

    public Node(Node childLeftNode, Node childRightNode) throws NoSuchAlgorithmException {
        this.childLeft = childLeftNode;
        this.childRight = childRightNode;

        if (this.childRight.isFiller()) {
            this.hash = HashUtils.createHash(childLeftNode.hash, childLeftNode.hash);
        } else {
            this.hash = HashUtils.createHash(childLeftNode.hash, childRightNode.hash);
        }
    }

    public Node(byte[] bytes) throws NoSuchAlgorithmException {
        this.hash = HashUtils.createHash(bytes);
        this.content = bytes;
        this.isFiller = Arrays.equals(this.content, FillerNode.CONTENT);
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

    public Node getSibling() {
        Node siblingNode;
        Node parent = this.parent;
        if (Arrays.equals(parent.getChildLeft().getHash(), this.hash)) {
            siblingNode = parent.getChildRight();
        } else {
            siblingNode = parent.getChildLeft();
        }
        return siblingNode;
    }

    public Node getUncle() {
        return this.parent.getSibling();
    }

}
