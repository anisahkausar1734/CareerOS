package com.careeros.careeros_backend.service.projectanalysis.ai;

public final class EngineeringRubric {

    private EngineeringRubric() {
    }

    public static String getRubric() {

        return """
==========================
ENGINEERING SCORING RUBRIC
==========================

Use the following rubric for EVERY score.

A score MUST be justified by engineering evidence.

Never guess.

If evidence is missing, lower confidence instead of assuming the feature exists.

--------------------------------------------------

ARCHITECTURE SCORE

90-100
• Highly modular architecture
• Excellent separation of concerns
• Clean layering
• Production-ready design
• Easily extensible

70-89
• Good architecture
• Minor coupling
• Mostly modular
• Suitable for medium-scale production

50-69
• Functional architecture
• Some modularity
• Learning-level project
• Limited scalability

30-49
• Basic architecture
• Minimal separation
• Tight coupling
• Educational project

0-29
• Poor structure
• No architecture visible

--------------------------------------------------

CODE QUALITY

90-100
• Clean project organization
• Consistent naming
• Strong engineering practices
• Easy to maintain

70-89
• Mostly clean
• Minor inconsistencies

50-69
• Acceptable organization
• Some duplicated structure

30-49
• Difficult to navigate
• Weak organization

0-29
• Poor repository organization

--------------------------------------------------

DOCUMENTATION

90-100
• Excellent README
• Installation guide
• Usage examples
• Contribution guide
• License
• Changelog

70-89
• Good README
• Installation
• Basic usage

50-69
• README only

30-49
• Minimal documentation

0-29
• No documentation

--------------------------------------------------

BUILD & DEPENDENCY MANAGEMENT

90-100
• Proper build configuration
• Organized dependencies
• Version management
• Reproducible builds

70-89
• Good dependency management

50-69
• Basic build system

30-49
• Weak build configuration

0-29
• No clear build process

--------------------------------------------------

DEPLOYMENT READINESS

90-100
• Production deployment
• Docker
• CI/CD
• Environment configuration

70-89
• Docker
• Partial deployment

50-69
• Deployable with manual setup

30-49
• Prototype deployment

0-29
• No deployment evidence

--------------------------------------------------

TESTING MATURITY

90-100
• Comprehensive tests
• Unit tests
• Integration tests
• Automation

70-89
• Good test coverage

50-69
• Basic testing

30-49
• Very limited tests

0-29
• No testing evidence

--------------------------------------------------

SECURITY

90-100
• Authentication
• Authorization
• Secure configuration
• Secrets management

70-89
• Good security practices

50-69
• Basic authentication

30-49
• Weak security

0-29
• No visible security practices

--------------------------------------------------

SCALABILITY

90-100
• Highly scalable architecture
• Clear layering
• Easy horizontal growth

70-89
• Good scalability

50-69
• Moderate scalability

30-49
• Limited scalability

0-29
• Educational architecture

--------------------------------------------------

MAINTAINABILITY

90-100
• Easily maintainable
• Well documented
• Clean structure

70-89
• Mostly maintainable

50-69
• Moderate maintainability

30-49
• Difficult to maintain

0-29
• High technical debt

--------------------------------------------------

INNOVATION

90-100
• Novel engineering
• Complex real-world solution
• High technical depth

70-89
• Strong implementation

50-69
• Good portfolio project

30-49
• Standard CRUD project

0-29
• Very basic project

--------------------------------------------------

PRODUCTION READINESS

90-100
Enterprise-ready

70-89
Startup-ready

50-69
Portfolio-ready

30-49
Learning project

0-29
Prototype only

--------------------------------------------------

OVERALL ENGINEERING LEVEL

90-100
Senior Engineer Quality

80-89
Industry Ready

70-79
Strong Portfolio

60-69
Internship Ready

40-59
Learning Stage

0-39
Beginner

--------------------------------------------------

CONFIDENCE

High
Evidence strongly supports conclusions.

Medium
Most conclusions supported.

Low
Limited evidence available.

""";

    }

}