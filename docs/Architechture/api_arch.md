# Backend API Architecture

# CareerOS

## Architecture Overview

```text
Controller Layer
       │
       ▼
Service Layer
       │
       ▼
Repository Layer
       │
       ▼
MySQL Database
```

### Request Flow

```text
React Frontend
       │
       ▼
Controller
       │
       ▼
Service
       │
       ▼
Repository
       │
       ▼
MySQL / Gemini AI
       │
       ▼
Response
```

---

# 1. Authentication Module

## AuthController

### APIs

```http
POST /api/auth/register
POST /api/auth/login
```

### Responsibilities

* User Registration
* User Login
* JWT Generation
* Authentication Validation

### Related Classes

```text
AuthController
    │
    ▼
AuthService
    │
    ▼
UserRepository
```

### Database Tables

```text
users
```

---

# 2. Profile Module

## ProfileController

### APIs

```http
GET    /api/profile
POST   /api/profile
PUT    /api/profile
```

### Responsibilities

* Create Profile
* View Profile
* Update Profile

### Related Classes

```text
ProfileController
       │
       ▼
ProfileService
       │
       ▼
ProfileRepository
```

### Database Tables

```text
profiles
```

---

# 3. Skills Module

## SkillController

### APIs

```http
POST   /api/skills
GET    /api/skills
DELETE /api/skills/{id}
```

### Responsibilities

* Add Skills
* View Skills
* Delete Skills

### Related Classes

```text
SkillController
      │
      ▼
SkillService
      │
      ▼
SkillRepository
```

### Database Tables

```text
skills
```

---

# 4. Career Goal Module

## GoalController

### APIs

```http
POST /api/goals
GET  /api/goals
PUT  /api/goals
```

### Responsibilities

* Set Career Goal
* Update Career Goal
* View Career Goal

### Database Tables

```text
career_goals
```

---

# 5. Skill Gap Analysis Module

## AnalysisController

### APIs

```http
POST /api/analysis/skills
```

### Responsibilities

* Analyze Current Skills
* Calculate Readiness
* Identify Missing Skills

### Flow

```text
Skills
   │
   ▼
Analysis Service
   │
   ▼
Gemini AI
   │
   ▼
Gap Report
```

### Database Tables

```text
skills
career_goals
```

---

# 6. Learning Roadmap Module

## RoadmapController

### APIs

```http
POST /api/roadmaps/generate
GET  /api/roadmaps
GET  /api/roadmaps/{id}
```

### Responsibilities

* Generate Roadmap
* Fetch Roadmaps
* View Roadmap Details

### Flow

```text
Skill Gaps
     │
     ▼
Gemini AI
     │
     ▼
Roadmap
     │
     ▼
Database
```

### Database Tables

```text
roadmaps
roadmap_milestones
```

---

# 7. Progress Tracking Module

## ProgressController

### APIs

```http
GET /api/progress
PUT /api/milestones/{id}/complete
```

### Responsibilities

* Track Progress
* Complete Milestones
* Update Readiness Score

### Database Tables

```text
progress_records
roadmap_milestones
```

---

# 8. Resume Intelligence Module

## ResumeController

### APIs

```http
POST /api/resume/upload
POST /api/resume/analyze
GET  /api/resume/reports
```

### Responsibilities

* Upload Resume
* Analyze Resume
* Fetch Resume Reports

### Flow

```text
Resume
   │
   ▼
Resume Service
   │
   ▼
Gemini AI
   │
   ▼
Resume Report
```

### Database Tables

```text
resume_analysis
resume_suggestions
```

---

# 9. AI Recommendation Module

## RecommendationController

### APIs

```http
POST /api/recommendations
GET  /api/recommendations
```

### Responsibilities

* Generate AI Suggestions
* Store Recommendations
* Retrieve Recommendations

### Database Tables

```text
ai_recommendations
```

---

# 10. Dashboard Module

## DashboardController

### APIs

```http
GET /api/dashboard
```

### Responsibilities

* Aggregate Platform Data
* Generate Dashboard View

### Data Sources

```text
profiles
skills
career_goals
roadmaps
progress_records
resume_analysis
ai_recommendations
```

---

# Complete API List

## Authentication

```http
POST /api/auth/register
POST /api/auth/login
```

## Profile

```http
GET  /api/profile
POST /api/profile
PUT  /api/profile
```

## Skills

```http
POST   /api/skills
GET    /api/skills
DELETE /api/skills/{id}
```

## Career Goals

```http
POST /api/goals
GET  /api/goals
PUT  /api/goals
```

## Skill Analysis

```http
POST /api/analysis/skills
```

## Roadmaps

```http
POST /api/roadmaps/generate
GET  /api/roadmaps
GET  /api/roadmaps/{id}
```

## Progress

```http
GET /api/progress
PUT /api/milestones/{id}/complete
```

## Resume

```http
POST /api/resume/upload
POST /api/resume/analyze
GET  /api/resume/reports
```

## Recommendations

```http
POST /api/recommendations
GET  /api/recommendations
```

## Dashboard

```http
GET /api/dashboard
```
