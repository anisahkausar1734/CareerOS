# Database Design

# CareerOS

## Overview

The CareerOS database is designed to store user profiles, career goals, skills, learning roadmaps, progress records, resume analysis results, and AI-generated recommendations.

The database follows a relational structure using MySQL.


# Database Structure

```text id="zj7cxq"
Users
 │
 ├── Profile
 │
 ├── Skills
 │
 ├── Career Goals
 │
 ├── Learning Roadmaps
 │
 ├── Progress Records
 │
 ├── Resume Analysis
 │
 └── AI Recommendations
```

# Users

Stores authentication and account information.

### Fields

* user_id (PK)
* full_name
* email
* password
* created_at
* updated_at

### Relationships

* One User → One Profile
* One User → Many Skills
* One User → Many Roadmaps
* One User → Many Progress Records
* One User → Many Resume Reports
* One User → Many Recommendations



# Profile

Stores career-related information.

### Fields

* profile_id (PK)
* user_id (FK)
* education
* university
* graduation_year
* interests
* target_role
* created_at

### Relationship

* One Profile belongs to One User


# Skills

Stores user skills.

### Fields

* skill_id (PK)
* user_id (FK)
* skill_name
* proficiency_level

### Examples

* Java
* Spring Boot
* React
* SQL

### Relationship

* One User → Many Skills


# Career Goals

Stores target career paths.

### Fields

* goal_id (PK)
* user_id (FK)
* target_role
* target_company
* target_date

### Examples

* Full Stack Developer
* Backend Developer
* Data Analyst

### Relationship

* One User → Many Career Goals


# Learning Roadmaps

Stores AI-generated learning plans.

### Fields

* roadmap_id (PK)
* user_id (FK)
* roadmap_title
* generated_date
* status

### Relationship

* One User → Many Roadmaps

# Roadmap Milestones

Stores roadmap tasks and milestones.

### Fields

* milestone_id (PK)
* roadmap_id (FK)
* title
* description
* status
* due_date

### Relationship

* One Roadmap → Many Milestones

# Progress Records

Tracks user progress.

### Fields

* progress_id (PK)
* user_id (FK)
* milestone_id (FK)
* completion_percentage
* completed_at

### Relationship

* One User → Many Progress Records


# Resume Analysis

Stores resume evaluation results.

### Fields

* analysis_id (PK)
* user_id (FK)
* resume_name
* resume_score
* ats_score
* analyzed_at

### Relationship

* One User → Many Resume Reports


# Resume Suggestions

Stores improvement recommendations.

### Fields

* suggestion_id (PK)
* analysis_id (FK)
* suggestion_text

### Relationship

* One Resume Analysis → Many Suggestions


# AI Recommendations

Stores Gemini-generated recommendations.

### Fields

* recommendation_id (PK)
* user_id (FK)
* recommendation_type
* recommendation_text
* generated_at

### Examples

* Skill Recommendation
* Learning Recommendation
* Career Recommendation

### Relationship

* One User → Many Recommendations

# Entity Relationship Diagram

```text id="93l6b8"
Users
 │
 ├── Profile
 │
 ├── Skills
 │
 ├── Career Goals
 │
 ├── Learning Roadmaps
 │        │
 │        └── Roadmap Milestones
 │
 ├── Progress Records
 │
 ├── Resume Analysis
 │        │
 │        └── Resume Suggestions
 │
 └── AI Recommendations
```


# Data Flow

```text id="7gxz0y"
User
 │
 ▼
Profile
 │
 ▼
Skills + Career Goal
 │
 ▼
Gemini Analysis
 │
 ▼
Roadmap Generation
 │
 ▼
Progress Tracking
 │
 ▼
Resume Analysis
 │
 ▼
AI Recommendations
```


# Core Tables Summary

```text id="fg9d5l"
Users
Profile
Skills
Career Goals
Learning Roadmaps
Roadmap Milestones
Progress Records
Resume Analysis
Resume Suggestions
AI Recommendations
------------------
