package com.careeros.careeros_backend.service.projectanalysis.ai;

public final class EngineeringJsonSchema {

    private EngineeringJsonSchema() {
    }

    public static String schema() {

        return """
Return ONLY valid JSON.

IMPORTANT RULES

1. Every field ending with:
- Score
- Value
- Complexity
- Confidence
- Readiness

MUST be an INTEGER between 0 and 100.

Never return text for these fields.

2. Explanations belong ONLY in fields ending with:
- Review
- Verdict
- Summary
- Recommendation

3. Arrays must always be arrays of strings.

4. Never change field names.

5. Never omit fields.

6. Never add new fields.

7. Never return null.

8. If evidence is insufficient, still return an integer score.

Example:

Correct:
"businessValue": 65

Wrong:
"businessValue": "Good educational value"

Wrong:
"businessValue": "Primarily educational..."

{
  "summary": {

    "projectName": "",

    "projectType": "",

    "domain": "",

    "problemSolved": "",

    "targetUsers": "",

    "estimatedDifficulty": "",

    "estimatedDevelopmentTime": "",

    "overallSummary": ""

  },

  "engineering": {

    

    "engineeringLevel": "",

    "engineeringVerdict": "",

    "architectureStyle": "",

    "architectureReview": "",

    "engineeringReview": "",

    "documentationReview": "",

    "businessValue": "<INTEGER 0-100>",

    "engineeringScore": "<INTEGER 0-100>",

"confidence": "<INTEGER 0-100>",

"architectureScore": "<INTEGER 0-100>",

"codeQuality": "<INTEGER 0-100>",

"repositoryOrganization": "<INTEGER 0-100>",

"maintainability": "<INTEGER 0-100>",

"documentationQuality": "<INTEGER 0-100>",

"dependencyManagement": "<INTEGER 0-100>",

"buildQuality": "<INTEGER 0-100>",

"scalability": "<INTEGER 0-100>",

"securityScore": "<INTEGER 0-100>",

"testingQuality": "<INTEGER 0-100>",

"deploymentReadiness": "<INTEGER 0-100>",

"productionReadiness": "<INTEGER 0-100>",

"innovationScore": "<INTEGER 0-100>",

"businessValue": "<INTEGER 0-100>",

"technicalComplexity": "<INTEGER 0-100>",

"maturityScore": "<INTEGER 0-100>",

"evidenceCoverageScore": "<INTEGER 0-100>",

    "deploymentReview": "",

    "scalabilityReview": "",

    "securityReview": "",

    "testingReview": "",

    "innovationReview": "",

    "strengths": [],

    "risks": [],

    "missingEngineeringPractices": [],

    "engineeringHighlights": [],

    "finalEngineeringReview": "",

    "maturityStage": "",

    "hiringRecommendation": "",

    "portfolioRecommendation": "",

    "evidenceCoverage": "",

  }

}

Return ONLY JSON.

Do not wrap inside markdown.

Do not explain anything.
""";

    }

}