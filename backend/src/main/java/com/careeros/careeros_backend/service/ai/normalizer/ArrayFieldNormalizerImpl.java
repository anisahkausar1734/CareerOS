package com.careeros.careeros_backend.service.ai.normalizer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ArrayFieldNormalizerImpl
        implements ArrayFieldNormalizer {

    private static final Set<String> ARRAY_FIELDS = Set.of(

            "strengths",

            "risks",

            "missingEngineeringPractices",

            "engineeringHighlights"

    );

    @Override
    public void normalize(
            JsonNode root
    ) {

        traverse(root);

    }

    private void traverse(
            JsonNode node
    ) {

        if (node == null) {
            return;
        }

        if (node.isObject()) {

            ObjectNode object =
                    (ObjectNode) node;

            object.fieldNames().forEachRemaining(field -> {

                JsonNode value =
                        object.get(field);

                if (ARRAY_FIELDS.contains(field)) {

                    normalizeArray(
                            object,
                            field,
                            value
                    );

                }

                traverse(value);

            });

        }

        if (node.isArray()) {

            node.forEach(this::traverse);

        }

    }

    private void normalizeArray(

            ObjectNode object,

            String field,

            JsonNode value

    ) {

        if (value == null || value.isNull()) {

            object.set(

                    field,

                    JsonNodeFactory.instance.arrayNode()

            );

            return;

        }

        if (value.isArray()) {
            return;
        }

        ArrayNode array =
                JsonNodeFactory.instance.arrayNode();

        array.add(
                value.asText()
        );

        object.set(
                field,
                array
        );

    }

}