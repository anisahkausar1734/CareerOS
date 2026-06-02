# Product Requirements Document (PRD)

# CareerOS

Version: 1.0
Status: MVP Development
Author: Anisah
Last Updated: June 2026

---

# 1. Executive Summary

CareerOS is an AI-powered career intelligence platform designed to help students identify skill gaps, build personalized learning roadmaps, improve resume quality, and track career readiness.

The platform serves as a centralized career operating system that transforms career aspirations into structured, measurable development plans. By leveraging data-driven insights and AI-assisted recommendations, CareerOS enables students to make informed decisions regarding skill acquisition, internship preparation, and long-term professional growth.

---

# 2. Problem Statement

Students today have access to an overwhelming number of learning resources, courses, certifications, and career guidance platforms. Despite this abundance of information, many students struggle to answer fundamental career questions:

* What skills are required for my target role?
* Which skills am I currently lacking?
* What should I learn next?
* Is my resume industry-ready?
* How can I measure my career progress over time?

Existing solutions typically address only one aspect of the problem, forcing students to use multiple disconnected tools.

The lack of a unified career development system often results in inefficient learning, poor career planning, and reduced employability.

---

# 3. Product Vision

To become the operating system for student career development by providing personalized, intelligent, and measurable career guidance through a single integrated platform.

---

# 4. Goals

## Primary Goals

* Help students identify skill gaps for target roles.
* Generate personalized learning roadmaps.
* Improve resume quality and ATS compatibility.
* Track career readiness and progress.
* Centralize career development activities within one platform.

## Business Goals

* Increase student career preparedness.
* Improve internship and placement success rates.
* Create a scalable platform capable of supporting multiple career domains.

---

# 5. Target Users

## Primary Users

### College Students

Students preparing for internships, placements, and entry-level roles.

### Final-Year Students

Students actively applying for internships and full-time opportunities.

### Self-Learners

Individuals seeking structured career guidance and skill development plans.

---

# 6. User Personas

## Persona 1: Aspiring Software Engineer

Age: 20

Challenges:

* Unsure which technologies to learn.
* Follows random tutorials.
* Lacks a structured learning plan.

Goals:

* Secure a software engineering internship.
* Build industry-relevant skills.
* Create a strong resume.

---

## Persona 2: Placement-Focused Student

Age: 22

Challenges:

* Limited understanding of industry requirements.
* Difficulty measuring career readiness.

Goals:

* Improve placement outcomes.
* Identify and close skill gaps.

---

# 7. Core Features

## 7.1 Authentication & User Management

### Description

Provides secure user registration, authentication, and profile management.

### Functional Requirements

* User Registration
* User Login
* JWT Authentication
* Password Encryption
* Profile Management
* Session Management

---

## 7.2 Career Profile Management

### Description

Allows users to maintain a professional profile containing skills, education, interests, and career goals.

### Functional Requirements

* Personal Information
* Skill Management
* Career Goal Selection
* Education Information

---

## 7.3 Skill Gap Analysis

### Description

Analyzes the gap between a user's current skill set and the skills required for a selected target role.

### Functional Requirements

* Target Role Selection
* Skill Comparison Engine
* Missing Skill Identification
* Readiness Scoring

### Output

* Existing Skills
* Missing Skills
* Readiness Percentage
* Recommended Next Steps

---

## 7.4 Personalized Learning Roadmaps

### Description

Generates structured learning plans tailored to user goals and skill gaps.

### Functional Requirements

* Roadmap Generation
* Milestone Creation
* Progress Tracking
* Learning Recommendations

### Output

* Weekly Learning Plan
* Monthly Milestones
* Completion Progress

---

## 7.5 Resume Intelligence

### Description

Evaluates resumes and provides actionable recommendations.

### Functional Requirements

* Resume Upload
* Resume Parsing
* Skill Extraction
* ATS Compatibility Assessment
* Resume Improvement Suggestions

### Output

* ATS Score
* Skill Coverage Analysis
* Suggested Improvements

---

## 7.6 Career Readiness Dashboard

### Description

Provides a centralized dashboard displaying career progress and readiness metrics.

### Functional Requirements

* Progress Visualization
* Roadmap Tracking
* Readiness Score
* Activity Monitoring

---

# 8. Non-Functional Requirements

## Performance

* Page load time below 2 seconds.
* API response time below 500ms.

## Security

* JWT Authentication
* Password Hashing (BCrypt)
* Input Validation
* Secure API Access

## Scalability

* Modular Architecture
* Service-Based Design
* Future Microservice Compatibility

## Reliability

* Error Handling
* Logging
* Data Validation

---

# 9. MVP Scope

## Included

* Authentication
* User Profiles
* Skill Gap Analysis
* Learning Roadmap Generation
* Resume Analysis
* Dashboard

## Excluded

* Mobile Application
* Interview Simulation
* Job Recommendation Engine
* LinkedIn Integration
* Real-Time Collaboration

---

# 10. Future Enhancements

## Phase 2

* AI Career Assistant
* Smart Recommendations
* Certification Tracking

## Phase 3

* Internship Recommendation Engine
* Mock Interview Platform
* LinkedIn Profile Analysis

## Phase 4

* Advanced Career Analytics
* Community Features
* Mentor Network

---

# 11. Technology Stack

## Frontend

* React
* Vite
* Tailwind CSS
* React Router
* Axios

## Backend

* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA

## Database

* MySQL

## Development Tools

* Git
* GitHub
* Maven

---

# 12. Success Metrics

The success of CareerOS will be measured using:

* User registration growth
* Roadmap completion rate
* Resume analysis usage
* Skill gap assessment completion rate
* Career readiness score improvements
* Internship and placement success indicators

---

# 13. Release Plan

## Phase 1

Project Foundation & Authentication

## Phase 2

Career Profile & Skill Gap Analysis

## Phase 3

Learning Roadmap Generation

## Phase 4

Resume Intelligence

## Phase 5

Career Dashboard & Analytics

---

# 14. Conclusion

CareerOS aims to simplify and structure the career development journey for students by combining skill assessment, roadmap generation, resume intelligence, and progress tracking into a single platform. The MVP focuses on providing immediate value through actionable insights while establishing a scalable foundation for future AI-driven career services.
