package com.algorithm.merkletree;

import com.algorithm.merkletree.domain.MerkleTree;
import com.algorithm.merkletree.service.MerkleTreeService;
import com.algorithm.merkletree.utils.MerkleTreeFactory;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws Exception {

        LOGGER.info("Starting merkle tree application");

        LOGGER.info("Creating merkle tree from file content");
        MerkleTreeService merkleTreeService = new MerkleTreeService();
        String filePath = args[0];
        MerkleTree merkleTree = MerkleTreeFactory.build(filePath);
        merkleTreeService.addHash(merkleTree);

        LOGGER.info("Starting http server");
        MerkleTreeController merkleTreeController = new MerkleTreeController(merkleTreeService);
        HttpServer server = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);
        server.createContext("/hashes", merkleTreeController::getHashes);
        server.createContext("/piece", merkleTreeController::getPiece); // url may be: /hashes/{hash}/pieces/{piece}
        server.start();

        LOGGER.info("Server started");

    }


}
