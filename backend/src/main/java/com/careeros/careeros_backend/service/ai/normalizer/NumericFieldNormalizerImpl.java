package com.careeros.careeros_backend.service.ai.normalizer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class NumericFieldNormalizerImpl
        implements NumericFieldNormalizer {

    /*
     * Every field that MUST always be numeric.
     * Easy to maintain and reusable across CareerOS.
     */
    private static final Set<String> INTEGER_FIELDS = Set.of(

            "engineeringScore",
            "confidence",
            "architectureScore",
            "codeQuality",
            "repositoryOrganization",
            "maintainability",
            "documentationQuality",
            "dependencyManagement",
            "buildQuality",
            "scalability",
            "securityScore",
            "testingQuality",
            "deploymentReadiness",
            "productionReadiness",
            "innovationScore",
            "businessValue",
            "technicalComplexity",
            "maturityScore",
            "evidenceCoverageScore",

            "overallCareerScore",
            "resumeImpact",
            "internshipImpact",
            "jobImpact",
            "researchImpact",
            "startupImpact",
            "openSourceImpact",
            "roleAlignment",
            "industryDemand",
            "hiringSignal"
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

            ObjectNode objectNode =
                    (ObjectNode) node;

            objectNode.fieldNames().forEachRemaining(field -> {

                JsonNode value =
                        objectNode.get(field);

                if (INTEGER_FIELDS.contains(field)) {

                    normalizeInteger(
                            objectNode,
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

    private void normalizeInteger(

            ObjectNode objectNode,

            String field,

            JsonNode value

    ) {

        if (value == null || value.isNull()) {

            objectNode.put(field, 0);
            return;

        }

        if (value.isInt()) {
            return;
        }

        if (value.isLong()) {
            return;
        }

        if (value.isDouble()) {

            objectNode.put(
                    field,
                    value.asInt()
            );

            return;

        }

        if (value.isTextual()) {

            try {

                int parsed =
                        Integer.parseInt(
                                value.asText()
                        );

                objectNode.put(
                        field,
                        parsed
                );

            }

            catch (Exception ignored) {

                objectNode.put(
                        field,
                        0
                );

            }

        }

    }

}