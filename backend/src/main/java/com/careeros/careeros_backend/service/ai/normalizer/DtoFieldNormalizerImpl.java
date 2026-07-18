package com.careeros.careeros_backend.service.ai.normalizer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.Field;
import java.util.List;

@Service
public class DtoFieldNormalizerImpl
        implements DtoFieldNormalizer {

    @Override
    public void normalize(

            JsonNode root,

            Class<?> dtoClass

    ) {

        if (root == null || !root.isObject()) {
            return;
        }

        normalizeObject(
                (ObjectNode) root,
                dtoClass
        );

    }

    private void normalizeObject(

            ObjectNode node,

            Class<?> dtoClass

    ) {

        Field[] fields =
        dtoClass.getDeclaredFields();

for (Field field : fields) {

            String fieldName =
                    field.getName();

            JsonNode value =
                    node.get(fieldName);

            Class<?> type =
                    field.getType();

            if (value == null) {
                continue;
            }

            normalizeValue(

                    node,

                    fieldName,

                    value,

                    type,

                    field

            );

        }

    }

    private void normalizeValue(

            ObjectNode parent,

            String fieldName,

            JsonNode value,

            Class<?> type,

            Field field

    ) {

        /*
         * Integer
         */

        if (Integer.class.equals(type)
                || int.class.equals(type)) {

            if (!value.isNumber()) {

                try {

                    parent.put(

                            fieldName,

                            Integer.parseInt(
                                    value.asText()
                            )

                    );

                }

                catch (Exception ignored) {

                    parent.put(
                            fieldName,
                            0
                    );

                }

            }

            return;

        }

        /*
         * String
         */

        if (String.class.equals(type)) {

            if (!value.isTextual()) {

                parent.put(

                        fieldName,

                        value.asText()

                );

            }

            return;

        }

       /*
 * List
 */

if (List.class.isAssignableFrom(type)) {

    ArrayNode array;

    if (!value.isArray()) {

        array = parent.arrayNode();

        array.add(
                value.asText()
        );

        parent.set(
                fieldName,
                array
        );

    }

    else {

        array = (ArrayNode) value;

    }

    Type genericType =
            field.getGenericType();

    if (genericType instanceof ParameterizedType parameterizedType) {

        Type actualType =
                parameterizedType
                        .getActualTypeArguments()[0];

        if (actualType instanceof Class<?> elementType) {

            if (!String.class.equals(elementType)) {

                for (JsonNode child : array) {

                    if (child.isObject()) {

                        normalizeObject(

                                (ObjectNode) child,

                                elementType

                        );

                    }

                }

            }

        }

    }

    return;

}
        

        /*
         * Nested DTO
         */

        if (value.isObject()) {

            normalizeObject(

                    (ObjectNode) value,

                    type

            );

        }

    }

}