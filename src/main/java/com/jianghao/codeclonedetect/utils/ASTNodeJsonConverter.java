package com.jianghao.codeclonedetect.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.io.IOException;

/**
 * @description:用于将JavaParser解析出的AST树转换为JSON字符串的工具类
 * @author: jianghao
 * @date: 2023-02-19 21:48
 **/
public class ASTNodeJsonConverter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String convert(Node node) {
        ObjectNode objectNode = convertNode(node);
        ArrayNode childNodes = convertChildNodes(node);
        objectNode.set("childNodes", childNodes);
        try {
            return objectMapper.writeValueAsString(objectNode);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert AST to JSON", e);
        }
    }

    private ObjectNode convertNode(Node node) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("type", node.getClass().getSimpleName());
        objectNode.put("range", node.getRange().toString());
        if (node instanceof TypeDeclaration) {
            TypeDeclaration<?> typeDeclaration = (TypeDeclaration<?>) node;
            objectNode.put("name", typeDeclaration.getNameAsString());
        }
        return objectNode;
    }

    private ArrayNode convertChildNodes(Node node) {
        ArrayNode childNodes = objectMapper.createArrayNode();
        for (Node childNode : node.getChildNodes()) {
            ObjectNode childObjectNode = convertNode(childNode);
            ArrayNode grandchildren = convertChildNodes(childNode);
            childObjectNode.set("childNodes", grandchildren);
            childNodes.add(childObjectNode);
        }
        return childNodes;
    }
}