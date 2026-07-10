package com.careeros.careeros_backend.service.projectanalysis.ai;

public final class EngineeringJsonSchema {

    private EngineeringJsonSchema() {
    }

    public static String schema() {

        return """
Return ONLY valid JSON.

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

    "engineeringScore": 0,

    "engineeringLevel": "",

    "engineeringVerdict": "",

    "confidence": 0,

    "architectureScore": 0,

    "architectureStyle": "",

    "architectureReview": "",

    "codeQuality": 0,

    "repositoryOrganization": 0,

    "maintainability": 0,

    "engineeringReview": "",

    "documentationQuality": 0,

    "documentationReview": "",

    "dependencyManagement": 0,

    "buildQuality": 0,

    "scalability": 0,

    "securityScore": 0,

    "testingQuality": 0,

    "deploymentReadiness": 0,

    "productionReadiness": 0,

    "innovationScore": 0,

    "businessValue": 0,

    "technicalComplexity": 0,

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

    "maturityScore": 0,

    "hiringRecommendation": "",

    "portfolioRecommendation": "",

    "evidenceCoverage": "",

    "evidenceCoverageScore": 0

  }

}

Return ONLY JSON.

Do not wrap inside markdown.

Do not explain anything.
""";

    }

}