# AI Flow Design

# CareerOS

## Overview

Gemini AI serves as the intelligence engine of CareerOS.

It does not directly communicate with users or access the database.

Instead, the Spring Boot backend collects user data, creates structured prompts, sends them to Gemini, processes the response, stores relevant results, and displays them through the frontend.

---

# AI Architecture

```text
User
 │
 ▼
React Frontend
 │
 ▼
Spring Boot Backend
 │
 ▼
MySQL Database
 │
 ▼
Collect User Context
 │
 ▼
Prompt Builder
 │
 ▼
Gemini API
 │
 ▼
AI Response
 │
 ▼
Response Processor
 │
 ▼
Database Storage
 │
 ▼
Frontend Dashboard
 │
 ▼
User
```

---

# Complete AI Lifecycle

```text
User Action
     │
     ▼
Frontend Request
     │
     ▼
Backend API
     │
     ▼
Fetch User Data
     │
     ▼
Build Prompt
     │
     ▼
Gemini AI
     │
     ▼
Receive Response
     │
     ▼
Process Response
     │
     ▼
Store Results
     │
     ▼
Display Insights
```

---

# AI Feature 1: Skill Gap Analysis

## Objective

Identify the difference between a user's current skills and the skills required for their target role.

### Flow

```text
Current Skills
      │
      ▼
Target Career Goal
      │
      ▼
Backend Fetches Data
      │
      ▼
Prompt Generation
      │
      ▼
Gemini AI
      │
      ▼
Skill Gap Analysis
      │
      ▼
Readiness Score
```

### Example Input

```text
Current Skills:
Java
HTML
CSS

Target Role:
Backend Developer
```

### Backend Prompt

```text
Analyze the user's skills for a Backend Developer role.

Current Skills:
- Java
- HTML
- CSS

Provide:
- Existing strengths
- Missing skills
- Learning priorities
- Readiness score
```

### AI Output

```text
Strengths:
- Java

Missing Skills:
- Spring Boot
- SQL
- REST APIs
- JWT
- Git

Readiness Score:
40%
```

### Stored Results

```text
Skill Gap Report
Missing Skills
Readiness Score
```

---

# AI Feature 2: Career Readiness Assessment

## Objective

Measure how prepared a user is for their chosen career path.

### Inputs

```text
Profile Data
Current Skills
Roadmap Progress
Resume Score
```

### Flow

```text
User Data
     │
     ▼
Readiness Evaluation
     │
     ▼
Gemini AI
     │
     ▼
Career Assessment
```

### Output

```text
Readiness Score
Strengths
Weaknesses
Improvement Areas
```

---

# AI Feature 3: Learning Roadmap Generation

## Objective

Generate a personalized learning plan based on identified skill gaps.

### Flow

```text
Skill Gap Report
       │
       ▼
Prompt Builder
       │
       ▼
Gemini AI
       │
       ▼
Roadmap Generation
       │
       ▼
Milestones Creation
```

### Example Input

```text
Target Role:
Backend Developer

Missing Skills:
- Spring Boot
- SQL
- REST APIs
- JWT
- Git
```

### Example Output

```text
Week 1-2
SQL

Week 3-5
Spring Boot

Week 6
REST APIs

Week 7
JWT

Week 8
Git

Week 9-12
Backend Project
```

### Stored Results

```text
Roadmap
Milestones
Learning Sequence
```

---

# AI Feature 4: Resume Intelligence

## Objective

Analyze resumes and provide improvement suggestions.

### Flow

```text
Resume Upload
      │
      ▼
PDF Parsing
      │
      ▼
Resume Text
      │
      ▼
Prompt Builder
      │
      ▼
Gemini AI
      │
      ▼
Resume Evaluation
```

### Example Prompt

```text
Analyze this resume for a Backend Developer role.

Evaluate:
- ATS Score
- Missing Keywords
- Resume Quality
- Improvement Suggestions
```

### Example Output

```text
ATS Score: 68%

Missing Keywords:
- Spring Boot
- SQL
- REST APIs

Suggestions:
- Add Backend Projects
- Add Quantified Achievements
```

### Stored Results

```text
Resume Score
ATS Score
Suggestions
```

---

# AI Feature 5: Personalized Recommendations

## Objective

Provide users with the next best actions based on their current progress.

### Flow

```text
Profile
Skills
Roadmap Progress
Resume Analysis
      │
      ▼
Prompt Builder
      │
      ▼
Gemini AI
      │
      ▼
Recommendations
```

### Example Prompt

```text
User Goal:
Backend Developer

Current Readiness:
51%

Completed:
- SQL

Pending:
- Spring Boot
- REST APIs

Recommend next actions.
```

### Example Output

```text
1. Learn Spring Boot
2. Build a REST API Project
3. Improve Resume with Backend Projects
```

### Stored Results

```text
Career Recommendations
Learning Suggestions
Project Recommendations
```

---

# Prompt Engineering Flow

## Purpose

Convert user data into meaningful prompts before sending them to Gemini.

### Flow

```text
User Data
     │
     ▼
Backend Prompt Builder
     │
     ▼
Structured Prompt
     │
     ▼
Gemini AI
```

### User Data Sources

```text
Profile
Skills
Career Goals
Roadmaps
Progress Records
Resume Analysis
```

---

# Response Processing Flow

## Purpose

Convert Gemini responses into structured data that can be stored and displayed.

### Flow

```text
Gemini Response
       │
       ▼
Response Parser
       │
       ▼
Structured Data
       │
       ▼
Database Storage
       │
       ▼
Frontend Display
```

---

# AI Data Sources

```text
Users
Profiles
Skills
Career Goals
Roadmaps
Progress Records
Resume Reports
```

---

# AI Output Categories

Skill Gap Analysis

Career Readiness Assessment

Learning Roadmaps

Resume Intelligence

Career Recommendations

Learning Suggestions

Project Recommendations
```

---

# End-to-End AI Flow

```text
User
 │
 ▼
Create Profile
 │
 ▼
Add Skills
 │
 ▼
Select Career Goal
 │
 ▼
Backend Collects Data
 │
 ▼
Prompt Builder
 │
 ▼
Gemini AI
 │
 ▼
Analysis & Recommendations
 │
 ▼
Database Storage
 │
 ▼
Dashboard
 │
 ▼
User
```

---

# Core Principle

Gemini is the intelligence layer of CareerOS.

CareerOS provides the data, context, and business logic.

Gemini analyzes that information and generates personalized insights that help students understand:

* Where they are today
* What they are missing
* What they should do next
