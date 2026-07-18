package com.careeros.careeros_backend.service.ai.normalizer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StringFieldNormalizerImpl
        implements StringFieldNormalizer {

    private static final Set<String> STRING_FIELDS = Set.of(

            "engineeringLevel",

            "engineeringVerdict",

            "architectureStyle",

            "architectureReview",

            "engineeringReview",

            "documentationReview",

            "deploymentReview",

            "scalabilityReview",

            "securityReview",

            "testingReview",

            "innovationReview",

            "finalEngineeringReview",

            "maturityStage",

            "hiringRecommendation",

            "portfolioRecommendation",

            "evidenceCoverage"

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

                if (STRING_FIELDS.contains(field)) {

                    normalizeString(
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

    private void normalizeString(

            ObjectNode object,

            String field,

            JsonNode value

    ) {

        if (value == null || value.isNull()) {

            object.put(field, "");

            return;

        }

        if (value.isTextual()) {
            return;
        }

        object.put(
                field,
                value.asText()
        );

    }

}