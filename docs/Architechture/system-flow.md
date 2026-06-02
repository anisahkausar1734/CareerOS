# CareerOS System Flow

## 1. Complete User Journey

Home Page
      │
      ▼
Register / Login
      │
      ▼
Create Profile
      │
      ▼
Select Career Goal
      │
      ▼
Add Skills
      │
      ▼
Skill Gap Analysis
      │
      ▼
Career Readiness Score
      │
      ▼
Learning Roadmap
      │
      ▼
Resume Analysis
      │
      ▼
Dashboard
      │
      ▼
AI Recommendations
      │
      ▼
Progress Tracking
```

---

## 2. Authentication Flow

User
 │
 ▼
Register / Login
 │
 ▼
Backend Validation
 │
 ▼
JWT Authentication
 │
 ▼
Dashboard Access
```

---

## 3. Profile Creation Flow


User Information
      │
      ▼
Profile Setup
      │
      ▼
Education Details
      │
      ▼
Skills
      │
      ▼
Career Goals
      │
      ▼
Profile Created
```

---

## 4. Skill Gap Analysis Flow

Current Skills
      │
      ▼
Target Career Role
      │
      ▼
Gemini AI Analysis
      │
      ▼
Missing Skills
      │
      ▼
Readiness Score
```

---

## 5. Learning Roadmap Flow

Skill Gap Report
      │
      ▼
Gemini AI
      │
      ▼
Roadmap Generation
      │
      ▼
Milestones
      │
      ▼
Progress Tracking
```

---

## 6. Resume Intelligence Flow

Resume Upload
      │
      ▼
Resume Parsing
      │
      ▼
Gemini AI Analysis
      │
      ▼
ATS Evaluation
      │
      ▼
Improvement Suggestions
```

---

## 7. Dashboard Flow

Profile Data
      │
      ├────────────┐
      ▼            ▼
Skill Analysis  Resume Analysis
      │            │
      └─────┬──────┘
            ▼
     Career Dashboard
```

---

## 8. AI Recommendation Flow

Career Goal
      │
      ▼
Skills
      │
      ▼
Roadmap Progress
      │
      ▼
Gemini AI
      │
      ▼
Personalized Recommendations
```

---

## 9. Progress Tracking Flow

Milestone Completed
       │
       ▼
Progress Updated
       │
       ▼
Readiness Recalculated
       │
       ▼
Dashboard Updated
```

---

## 10. System Architecture Flow

User
 │
 ▼
React Frontend
 │
 ▼
Spring Boot Backend
 │
 ┌─────┴─────┐
 ▼           ▼
MySQL    Gemini AI
 │           │
 └─────┬─────┘
       ▼
Frontend Dashboard
       │
       ▼
User
```
## User

- Register
- Login
- Create Profile
- Select Career Goal
- Add Skills
- Upload Resume
- Track Progress

## React Frontend

- Authentication Pages
- Career Dashboard
- Profile Management
- Skill Assessment Forms
- Learning Roadmaps
- Resume Analysis View
- AI Recommendations View
- API Communication

## Spring Boot Backend

- Authentication & Authorization
- Business Logic
- Career Intelligence Engine
- Resume Processing
- Roadmap Management
- Progress Tracking
- REST API Layer
- Gemini Integration

## MySQL Database

- User Accounts
- User Profiles
- Skills
- Career Goals
- Learning Roadmaps
- Progress Records
- Resume Reports

## Gemini AI

- Skill Gap Analysis
- Career Readiness Assessment
- Learning Roadmap Generation
- Resume Intelligence
- Career Recommendations
- Learning Suggestions

## Dashboard

- Career Readiness Score
- Skill Gap Insights
- Roadmap Progress
- Resume Score
- AI Recommendations
- Career Analytics
