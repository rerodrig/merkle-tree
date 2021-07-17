package com.algorithm.merkletree.controller;

import com.algorithm.merkletree.domain.Hash;
import com.algorithm.merkletree.domain.MerkleTree;
import com.algorithm.merkletree.domain.Piece;
import com.algorithm.merkletree.service.HashNotFoundException;
import com.algorithm.merkletree.service.MerkleTreeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MerkleTreeController {

    private final MerkleTreeService merkleTreeService;

    public MerkleTreeController(MerkleTreeService merkleTreeService) {
        this.merkleTreeService = merkleTreeService;
    }

    public void getHashes(HttpExchange exchange) throws IOException {
        try {
            List<MerkleTree> merkleTrees = merkleTreeService.getMerkleTrees();
            List<Hash> hashes = new ArrayList<>();
            for (MerkleTree tree : merkleTrees) {
                hashes.add(new Hash(tree.getHash(), tree.getPieces()));
            }
            sendResponse(exchange, hashes, 200);
        } catch (Exception e) {
            sendResponse(exchange, e.getMessage(), 500);
        }
    }

    public void getPiece(HttpExchange exchange) throws IOException {
        try {
            String[] pathParams = exchange.getRequestURI().toString().replaceAll("/piece/", "").split("/");
            Piece piece = merkleTreeService.getPiece(pathParams[0], Integer.parseInt(pathParams[1]));
            sendResponse(exchange, piece, 200);
        } catch (HashNotFoundException e) {
            sendResponse(exchange, e.getMessage(), 404);
        } catch (Exception e) {
            sendResponse(exchange, e.getMessage(), 500);
        }
    }

    private static void sendResponse(HttpExchange exchange, Object content, int httpCode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(content);

        exchange.sendResponseHeaders(httpCode, response.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(response.getBytes());
        output.flush();
        exchange.close();
    }
}
