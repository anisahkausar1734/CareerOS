package com.careeros.careeros_backend.service.projectanalysis.ai;

import com.careeros.careeros_backend.dto.projectanalysis.context.EngineeringContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EngineeringPromptBuilderImpl
        implements EngineeringPromptBuilder {

    @Override
    public String buildPrompt(
            EngineeringContext context
    ) {

        StringBuilder prompt =
                new StringBuilder();

             

        /*
         * Prompt will be built here.
         */
prompt.append(
        EngineeringRubric.getRubric()
);

prompt.append(
        EngineeringJsonSchema.schema()
);



        return prompt.toString();

    }

}