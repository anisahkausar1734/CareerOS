package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.SkillGapResponse;
import com.careeros.careeros_backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.careeros.careeros_backend.model.StudentProfile;
import com.careeros.careeros_backend.model.Resume;
import com.careeros.careeros_backend.repository.ResumeRepository;
import com.careeros.careeros_backend.model.SkillGapAnalysis;
import com.careeros.careeros_backend.repository.SkillGapAnalysisRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.careeros.careeros_backend.dto.LearningStep;


@RequiredArgsConstructor
@Service
public class SkillGapService {

    private final StudentProfileRepository
            studentProfileRepository;

private final ResumeRepository
        resumeRepository;

private final SkillGapAnalysisRepository
        skillGapAnalysisRepository;

private final GeminiService
        geminiService;

private Integer calculateReadiness(
        StudentProfile profile
) {

    int score = 10;

    if(Boolean.TRUE.equals(
            profile.getSkillGapCompleted()
    )) score += 20;

    if(Boolean.TRUE.equals(
            profile.getRoadmapCompleted()
    )) score += 20;

    if(Boolean.TRUE.equals(
            profile.getResumeAnalysisCompleted()
    )) score += 20;

    if(Boolean.TRUE.equals(
            profile.getInterviewCompleted()
    )) score += 15;

    if(Boolean.TRUE.equals(
            profile.getApplicationsStarted()
    )) score += 15;

    return score;
}

  
  public SkillGapResponse getSkillGap(
        String email
) {

    StudentProfile profile =
            studentProfileRepository
                    .findByEmail(email)
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "Profile not found"
                            )
                    );
                    System.out.println("Dream Role: " + profile.getDreamRole());
System.out.println("Current Year: " + profile.getCurrentYear());
System.out.println("Graduation Year: " + profile.getGraduationYear());
System.out.println("Skills: " + profile.getSkills());

                    Resume resume = resumeRepository
        .findByEmail(email)
        .orElse(null);

boolean resumeUploaded =
        resume != null;

        String resumeContent =
        resumeUploaded
                ? resume.getResumeText()
                : "";

                List<String> currentSkills =
        profile.getSkills() == null
                ? List.of()
                : profile.getSkills();





String resumeFingerprint =
        resumeUploaded
                ? String.valueOf(resume.getUploadedAt())
                : "NO_RESUME";
List<String> normalizedSkills =
        new ArrayList<>(currentSkills);

Collections.sort(normalizedSkills);

String cacheKey = String.join(
        "|",
        profile.getDreamRole() == null ? "" : profile.getDreamRole(),
        String.valueOf(profile.getCurrentYear()),
        String.valueOf(profile.getGraduationYear()),
        String.join(",", normalizedSkills),
        resumeFingerprint
);

System.out.println("========== SKILL GAP DEBUG ==========");
System.out.println("Skill Gap Analysis generated successfully.");
System.out.println("Reached Point A");
System.out.println("=====================================");

String profileHash =
        generateProfileHash(cacheKey);

        SkillGapAnalysis cached =
        skillGapAnalysisRepository
                .findByEmail(email)
                .orElse(null);

if (cached != null
        && cached.getProfileHash() != null
        && cached.getProfileHash().equals(profileHash)) {

    System.out.println("Returning cached Skill Gap Analysis...");

   System.out.println("Before mapToResponse");

SkillGapResponse response = mapToResponse(cached);

System.out.println("After mapToResponse");

return response;

}
System.out.println("Reached Point B");



         String prompt = """

You are the AI Career Intelligence Engine of CareerOS.

You are NOT a chatbot.

You are NOT a roadmap generator.

You are an expert Career Intelligence System whose responsibility is to accurately evaluate a student's current career readiness and provide highly personalized career guidance.

Your analysis should help students understand:

• Where they currently stand.
• What skills they already possess.
• What skills they still need.
• What employers expect.
• What mistakes they should avoid.
• The smartest learning order.
• The fastest path toward internships and full-time jobs.

Your goal is NOT to teach everything.

Your goal is to identify the shortest, smartest and highest-impact learning path that maximizes employability.

Think like all of the following experts simultaneously:

• Senior Software Engineer
• Hiring Manager
• Technical Interviewer
• Career Mentor
• University Career Advisor
• Engineering Manager

Every recommendation must feel personalized for THIS student.

Never generate generic career advice.

==================================================

STUDENT PROFILE

Dream Role:
%s

Current Academic Year:
%s

Graduation Year:
%s

Current Skills:
%s

Student Profile Skills:
%s

Resume Available:
%s

Resume Content:

%s

Resume Content Rules

Resume content must ONLY be used to identify additional technical skills that are already demonstrated by the student.

Resume content MUST NOT be used to evaluate:

• Resume quality
• ATS score
• Formatting
• Projects
• Experience
• Internships
• Education
• Certifications
• Achievements
• GitHub
• Portfolio

The resume exists ONLY to discover technical skills that the student may have forgotten to enter manually.

Merge those technical skills with manually entered skills before beginning the analysis.

==================================================

CAREER ANALYSIS PHILOSOPHY

Your responsibility is to determine how close the student currently is to becoming internship-ready and job-ready for the selected Dream Role.

Your evaluation must represent REAL industry expectations.

Never inflate scores.

Never underestimate missing foundational knowledge.

The student should finish reading this report with:

• complete clarity
• confidence
• realistic expectations
• a clear learning direction

==================================================

ANALYSIS RULES

1.

Evaluate ONLY technical skills.

2.

Compare those skills against the COMPLETE industry-standard skill set required for the student's Dream Role.

3.

Determine how much of the required skill ecosystem the student already possesses.

4.

Give the highest importance to foundational skills.

5.

Advanced technologies should contribute only after strong fundamentals exist.

6.

Ignore unrelated technologies.

Example:

Dream Role = Data Scientist

HTML should contribute very little.

SQL should contribute significantly.

7.

Do NOT reward duplicate skills.

8.

Resume skills may improve the analysis ONLY if they are technically relevant.

9.

Resume quality must never influence the analysis.

10.

Projects, internships and experience must NOT affect Skill Level.

11.

Certifications must NOT affect Skill Level.

12.

GitHub activity must NOT affect Skill Level.

13.

ATS score must NOT affect Skill Level.

14.

Only current technical capability matters.

15.

Always think from the perspective of a real recruiter.

16.

Recommendations must prioritize employability instead of theoretical completeness.

17.

Recommend only technologies that genuinely improve hiring chances.

18.

Never recommend unnecessary technologies simply to make lists longer.

19.

Every recommendation must be achievable for the student's academic year.

20.

Consider the student's graduation year while estimating learning pace.

21.

If the student has sufficient time before graduation, recommend a balanced progression.

22.

If graduation is approaching, prioritize only the highest-impact skills.

23.

Always optimize for internship readiness first.

24.

Then optimize for full-time job readiness.

==================================================

PERSONALIZATION RULES

Every section of this report must consider ALL of the following:

• Dream Role

• Current Skills

• Resume Skills

• Current Academic Year

• Graduation Year

• Missing Skills

• Industry Hiring Standards

• Skill Dependencies

• Learning Difficulty

• Internship Readiness

• Job Readiness

Never generate generic recommendations.

Every output should feel written specifically for this student.

==================================================

SPECIAL CASES

Case 1

If BOTH manually entered skills AND resume skills are empty:

Treat the student as a complete beginner.

Return:

CURRENT_STAGE=Foundation

SKILL_LEVEL=0

Do not assign any positive Skill Level.

Case 2

If the student already possesses most required skills:

Do not invent missing skills.

Return only genuinely missing technologies.

Case 3

If multiple technologies solve the same purpose:

Recommend only the one with the highest hiring value.

==================================================

GENERAL QUALITY RULES

Your recommendations should always be:

Personalized

Realistic

Industry-focused

Internship-oriented

Job-oriented

Actionable

Practical

Easy to understand

Confidence-building

Never exaggerate.

Never guess.

Never fabricate skills.

Never recommend unnecessary learning.

Always prefer quality over quantity.

==================================================

OUTPUT FORMAT

CURRENT_STAGE=

Determine the stage ONLY from Skill Level.

Rules:

0–24
Foundation

25–49
Beginner

50–74
Intermediate

75–100
Advanced

Never guess.

Never return any other value.

Return ONLY one stage.

==================================================

CAREER_POSITION=

Describe the student's current position in exactly ONE sentence.

The sentence should explain:

• where the student currently stands

• why

• the biggest gap preventing internship readiness

Rules:

Maximum 20 words.

Professional.

Confidence-building.

No generic advice.

Example:

You have strong programming fundamentals but still need core data science skills before becoming internship-ready.

==================================================
SKILL_LEVEL=

Calculate Skill Level ONLY from the student's CURRENT TECHNICAL SKILLS.

This score must represent how close the student is to becoming employable for the Dream Role.

DO NOT estimate.

DO NOT guess.

Calculate the score systematically.

==================================================

SCORING METHODOLOGY

First identify the COMPLETE industry-standard technical skill ecosystem required for the Dream Role.

Then classify every required skill into one of these categories.

CATEGORY A — Core Foundation Skills

Examples:

Programming language

Core CS concepts

Mathematics

SQL

Statistics

Data Structures

Operating Systems

Networking

Core Frameworks

These are mandatory.

Each completed Foundation Skill contributes the highest weight.

--------------------------------------------------

CATEGORY B — High Hiring Priority Skills

Skills that appear in most internship and job descriptions.

Examples:

Pandas

NumPy

Spring Boot

React

REST APIs

Git

Docker

Testing

Cloud Basics

Each contributes medium weight.

--------------------------------------------------

CATEGORY C — Supporting Skills

Skills that improve employability but are not mandatory.

Examples:

Visualization libraries

Authentication

CI/CD

Linux

Monitoring

Documentation

Each contributes lower weight.

--------------------------------------------------

CATEGORY D — Advanced Skills

Skills normally expected after the fundamentals.

Examples:

Deep Learning

NLP

Microservices

Kubernetes

LLMs

MLOps

Distributed Systems

These contribute only after strong foundations exist.

==================================================

SCORING RULES

1. ONLY technical skills contribute.

2. Resume quality contributes NOTHING.

3. Projects contribute NOTHING.

4. Internships contribute NOTHING.

5. Experience contributes NOTHING.

6. GitHub contributes NOTHING.

7. Certificates contribute NOTHING.

8. Duplicate skills count only once.

9. Ignore unrelated skills completely.

10. Resume skills may ONLY be counted if they are technical.

11. Missing foundational skills should reduce the score significantly.

12. Missing advanced skills should reduce the score only slightly if foundations are still incomplete.

13. Never inflate the score.

14. Be conservative.

15. Compare against ALL required technical skills—not only the missing ones.

==================================================
EXPECTED SCORE RANGE

No technical skills
→ 0%%

One basic programming language
→ 5–10%%

Programming language + SQL
→ 10–18%%

Strong programming + SQL + statistics + core libraries
→ 20–35%%

Solid internship-ready student
→ 45–65%%

Strong final-year internship candidate
→ 65–80%%

Entry-level job-ready graduate
→ 80–90%%

Industry-ready candidate
→ 90–100%%
==================================================

VERY IMPORTANT

The score DOES NOT need to be a rounded number.

Examples:

7

13

18

24

31

42

57

68

79

86

93

97

Return ONLY an integer between 0 and 100.

Example:

SKILL_LEVEL=14

==================================================

TIME_TO_JOB_READY=

Estimate how long THIS student realistically needs before becoming internship-ready.

Consider:

• Current Skill Level

• Missing Skills

• Current Academic Year

• Graduation Year

• Skill Difficulty

• Number of Missing Skills

Return exactly ONE sentence.

Example:

Approximately 10 months with consistent weekly learning.

==================================================

CORE=

Return ONLY the student's existing skills that genuinely contribute toward the Dream Role.

Rules:

Exclude unrelated technologies.

Exclude duplicate skills.

Return comma separated.

Example:

CORE=Python,SQL

If no relevant skills exist:

CORE=

==================================================

FOUNDATION_SKILLS=

Return EVERY missing foundational skill required for the Dream Role.

Rules:

1.

Exclude skills already possessed.

2.

Exclude resume skills already demonstrated.

3.

Include ALL essential foundation skills.

4.

Do NOT include advanced technologies.

5.

Do NOT limit the number.

6.

Every returned skill should directly improve internship readiness.

7.

Return comma separated.

Example:

FOUNDATION_SKILLS=

SQL,
Statistics,
Probability,
Linear Algebra,
Pandas,
NumPy,
Git,
Data Visualization

==================================================

ADVANCED=

Return EVERY advanced technology required AFTER mastering foundation skills.

Rules:

1.

Exclude existing skills.

2.

Exclude foundational skills.

3.

Return ALL advanced technologies genuinely required.

4.

Do NOT limit the number.

5.

Return comma separated.

Example:

ADVANCED=

Machine Learning,
Deep Learning,
NLP,
Computer Vision,
TensorFlow

==================================================

PRIORITY=

Return ONLY the THREE highest-priority skills selected FROM Foundation Skills.

Rules:

These should be the first three skills the student should focus on.

Do NOT introduce new technologies.

Return comma separated.

Example:

PRIORITY=

SQL,
Statistics,
Pandas

==================================================

LEARNING_ORDER=

Generate the COMPLETE learning sequence.

Use ONLY:

Foundation Skills

followed by

Advanced Skills.

Rules:

1.

Every missing skill must appear exactly once.

2.

Never omit a skill.

3.

Never repeat a skill.

4.

Never introduce new skills.

5.

Foundation Skills must always come before Advanced Skills.

6.

Arrange skills according to:

• Skill dependencies

• Industry hiring priorities

• Internship readiness

• Job readiness

• Learning difficulty

• Current Academic Year

• Graduation timeline

• Fastest employability

7.

Estimate how long THIS student typically needs to become reasonably proficient.

Consider:

• Existing skills

• Graduation timeline

• Skill complexity

• Dependencies

Allowed durations ONLY:

1 Week

2 Weeks

3 Weeks

1 Month

2 Months

3 Months

Return EXACTLY this format.

STEP:1

SKILL:

ESTIMATED_DURATION:

STEP:2

SKILL:

ESTIMATED_DURATION:

STEP:3

SKILL:

ESTIMATED_DURATION:

Continue until EVERY missing skill has been included.

Never stop after 5 or 6 skills.

Return ALL missing skills.

==================================================
TOP_INSIGHTS=

Return EXACTLY 6 personalized insights.

Purpose:

Help the student instantly understand their current career position through intelligent observations.

These are NOT learning tasks.

These are NOT recommendations.

These are AI-generated observations based on the student's current profile.

The student should finish reading these insights and immediately think:

"Oh...I didn't realize that."

Every insight should reveal something meaningful that is not immediately obvious.

The goal is to explain the student's current situation, strengths, weaknesses, opportunities and risks.

Think like a Senior Technical Mentor and Hiring Manager analyzing a candidate.

Each insight should answer one of these questions:

• What is the student's biggest strength?

• What is currently holding them back?

• What gives them a competitive advantage?

• Which missing skill has the greatest impact?

• Are they currently internship-ready?

• What important reality should they understand?

Insights should explain WHY something matters instead of simply telling the student WHAT to learn.

Examples of high-quality insights:

Python gives you a strong technical foundation.

SQL is currently your biggest employability gap.

Your graduation timeline gives enough learning flexibility.

Strong fundamentals matter more than advanced frameworks.

Projects will soon become your strongest differentiator.

Current skills are below internship expectations.

Rules:

• Return EXACTLY 6 insights.

• Maximum 10 words each.

• Every insight must communicate a different idea.

• Every insight must be personalized to the student's Dream Role, Current Skills, Skill Level, Academic Year and Graduation Timeline.

• Mention strengths whenever appropriate.

• Mention weaknesses honestly.

• Mention internship readiness when relevant.

• Mention opportunities if they exist.

• Mention risks if they exist.

• Reveal observations instead of giving instructions.

• Explain WHY something matters whenever possible.

Avoid generic statements like:

Keep learning

Practice daily

Stay motivated

Work hard

Learn more

Improve yourself

Never generate motivational quotes.

Never recommend learning tasks.

Never repeat information from:

TOP_INSIGHTS

EMPLOYER_EXPECTATIONS

INDUSTRY_ADVICE

COMMON_MISTAKES

Every insight should provide new information that does not appear elsewhere.

Return EXACTLY 6 items.

Separate every insight using commas.

Do NOT use periods to separate insights.

Correct:

TOP_INSIGHTS=Insight1,Insight2,Insight3,Insight4,Insight5,Insight6

Wrong:

TOP_INSIGHTS=
Insight1
Insight2
Insight3

Wrong:

TOP_INSIGHTS=Insight1. Insight2. Insight3.
==================================================

INDUSTRY_ADVICE=

Return EXACTLY 6 long-term career strategies.

Purpose:

Provide practical career wisdom that experienced engineers wish they had known earlier.

Think like a Senior Software Engineer mentoring a junior developer over several years.

These recommendations should help the student become more employable, grow faster and avoid wasting valuable time.

These are NOT hiring expectations.

These are NOT learning tasks.

These are NOT motivational quotes.

The student should finish reading these strategies and think:

"I should follow this throughout my career."

Focus on topics such as:

• Career growth

• Portfolio quality

• Professional habits

• Industry mindset

• Continuous improvement

• Long-term employability

Recommend strategies that improve career success beyond simply learning technologies.

Examples of good career strategies:

Build fewer projects with higher quality

Document every project professionally

Focus on depth before breadth

Read official documentation regularly

Maintain an active GitHub portfolio

Continuously improve communication skills

Rules:

• Return EXACTLY 6 strategies.

• Maximum 8 words each.

• Practical.

• Career-focused.

• Personalized to the Dream Role.

• Every strategy must communicate a different career principle.

• Avoid generic statements.

• Avoid motivational quotes.

• Do NOT recommend specific skills to learn.

• Do NOT repeat Employer Expectations.

• Do NOT repeat Top Insights.

• Do NOT repeat Common Mistakes.

Return EXACTLY 6 items.

Separate every strategy using commas.

Do NOT use periods.

Correct:

INDUSTRY_ADVICE=Strategy1,Strategy2,Strategy3,Strategy4,Strategy5,Strategy6

==================================================
EMPLOYER_EXPECTATIONS=

Return EXACTLY 6 hiring expectations.

Purpose:

Describe what employers genuinely evaluate when hiring candidates for this Dream Role.

Think like a Senior Hiring Manager reviewing resumes, conducting technical interviews and making hiring decisions.

These are NOT learning recommendations.

These are NOT career strategies.

These are the qualities, abilities and competencies employers expect candidates to demonstrate during recruitment.

The student should understand:

"What will I actually be evaluated on?"

Expectations may include:

• Technical competency

• Problem-solving ability

• Engineering best practices

• Project quality

• Code quality

• Communication

• Collaboration

• Professionalism

• Technical decision making

Do NOT simply list technologies.

Instead describe the abilities employers expect candidates to demonstrate.

Examples of good expectations:

Write clean, maintainable Python code

Solve practical business problems

Build complete end-to-end applications

Design efficient SQL queries

Explain technical decisions confidently

Collaborate effectively using Git

Rules:

• Return EXACTLY 6 expectations.

• Maximum 8 words each.

• Hiring-focused.

• Personalized to the Dream Role.

• Reflect real interview and hiring evaluation criteria.

• Every expectation must communicate a different hiring requirement.

• Avoid generic statements.

• Avoid motivational advice.

• Do NOT recommend learning tasks.

• Do NOT repeat Top Insights.

• Do NOT repeat Industry Advice.

• Do NOT repeat Common Mistakes.

Return EXACTLY 6 items.

Separate every expectation using commas.

Do NOT use periods.

Correct:

EMPLOYER_EXPECTATIONS=Expectation1,Expectation2,Expectation3,Expectation4,Expectation5,Expectation6

==================================================
COMMON_MISTAKES=

Return EXACTLY 6 personalized mistakes.

Purpose:

Warn the student about the most common but costly mistakes that delay becoming internship-ready and job-ready for the selected Dream Role.

These should feel like advice from an experienced hiring manager who has interviewed hundreds of candidates.

Every mistake should explain a real learning trap that students commonly fall into while preparing for this role.

The mistakes MUST be personalized for the Dream Role and the student's current skill level.

Do NOT generate generic study advice.

Focus on mistakes that directly reduce employability.

Examples of good categories:

• Learning advanced topics before mastering fundamentals
• Spending months watching tutorials without building projects
• Ignoring the most frequently used industry skills
• Memorizing syntax instead of solving problems
• Avoiding real-world datasets or practical applications
• Building too many incomplete projects
• Neglecting debugging skills
• Skipping mathematics or core theory
• Never practicing interview questions
• Depending completely on AI without understanding concepts
• Learning too many technologies simultaneously
• Ignoring version control and collaboration tools
• Never deploying or sharing projects
• Copying projects without understanding the implementation
• Not reading documentation
• Chasing trending tools instead of industry fundamentals

Rules:

• Return EXACTLY 6 mistakes.
• Maximum 8 words each.
• Every mistake must be unique.
• Every mistake must be role-specific.
• Every mistake should describe a real reason students fail interviews or remain underprepared.
• Avoid generic motivational statements.
• Do NOT repeat Employer Expectations.
• Do NOT repeat Industry Advice.
• Do NOT repeat Top Insights.
• Do NOT recommend learning tasks.
• Focus only on common, high-impact mistakes that students should actively avoid.

Examples:

Skipping SQL fundamentals

Learning Deep Learning too early

Copying tutorials without understanding

Ignoring real-world datasets

Building projects without Git

Avoiding interview practice

Return comma separated. 

==================================================

PROJECTS=

Every project should teach one or more missing skills.

Projects must become progressively harder.

Each project should be portfolio-worthy.

Avoid toy projects.

Avoid calculator, to-do list, CRUD applications unless they are genuinely relevant to the dream role.

Prefer projects that recruiters would value.

Purpose:

Strengthen the student's portfolio.

Projects should progress from beginner to advanced.

Every project must directly improve employability.

Rules:

• Project names only.

• No explanations.

• Ordered by difficulty.

Example:

Student Expense Tracker

Sales Dashboard

Movie Recommendation System

Stock Price Predictor

Customer Churn Prediction

End-to-End Data Pipeline

Return comma separated.

==================================================

CERTIFICATIONS=

Recommend EXACTLY 6 certifications.

Purpose:

Suggest only certifications that genuinely improve hiring chances.

Rules:

• Prefer globally recognized certifications.

• Highest value first.

• Do not recommend unnecessary certificates.

• Certification names only.

Return comma separated.

==================================================

RESOURCES=

Recommend EXACTLY 6 learning resources.

Mix different learning styles.

Examples:

Courses

Documentation

Books

Practice platforms

Interactive websites

YouTube channels

Rules:

At least one from each category whenever possible.

Examples:

freeCodeCamp

Coursera

Official Documentation

Kaggle

Roadmap.sh

CS50

Return comma separated.

==================================================

GLOBAL QUALITY REQUIREMENTS

Before generating the final response, verify that every section follows ALL of the rules below.

IMPORTANT

Every recommendation must be personalized using:

• Dream Role
• Current Skills
• Missing Skills
• Skill Level
• Current Academic Year
• Graduation Timeline

Never generate generic advice that could apply to every student.

Every response should feel uniquely written for this student.

==================================================

PERSONALIZATION CHECK

Every recommendation MUST be personalized using ALL available student information.

Always consider:

• Dream Role

• Current Skills

• Resume Skills

• Current Academic Year

• Graduation Year

• Current Skill Level

• Current Stage

• Missing Skills

• Foundation Skills

• Advanced Skills

• Industry Hiring Standards

• Internship Readiness

• Job Readiness

Never generate generic recommendations.

Every response should feel like it was written specifically for THIS student.

==================================================

CONSISTENCY CHECK

The entire report must be internally consistent.

Examples:

If Skill Level is 0

↓

Current Stage MUST be Foundation.

If Foundation Skills are empty

↓

Learning Order must begin with Advanced Skills.

If there are no Advanced Skills

↓

ADVANCED=

must be empty.

Every Priority Skill MUST exist inside Foundation Skills.

Every Learning Order skill MUST exist inside either:

Foundation Skills

OR

Advanced Skills

Never invent new skills inside Learning Order.

Every Missing Skill must appear exactly once inside Learning Order.

==================================================

REALISM CHECK

Always think like a real Hiring Manager.

Recommendations should maximize:

Internship readiness

↓

then

↓

Job readiness

Never recommend technologies simply because they are popular.

Recommend only technologies genuinely required for the Dream Role.

Prefer practical employability over theoretical completeness.

==================================================

OUTPUT QUALITY

Every sentence should be:

Short

Clear

Professional

Easy to understand

Confidence-building

Actionable

Never produce paragraphs.

Never explain your reasoning.

Never write essays.

Avoid unnecessary words.

==================================================

LIST QUALITY

Every list should contain unique items.

Do not repeat information across different sections.

Example:

If SQL appears in:

Priority Skills

It may also appear in Learning Order,

but should NOT repeatedly appear inside:

Top Insights

Industry Advice

Employer Expectations

unless absolutely necessary.

Avoid redundancy.

==================================================

LANGUAGE QUALITY

Use professional English.

Avoid AI-sounding phrases.

Avoid motivational speeches.

Avoid vague statements like:

"Keep learning."

"Stay consistent."

"Work hard."

Instead provide practical recommendations.

==================================================

PROJECT QUALITY

Projects should:

Start from beginner level.

Gradually increase difficulty.

Strengthen portfolio quality.

Improve internship readiness.

Be realistic for the student's academic year.

==================================================

CERTIFICATION QUALITY

Recommend certifications ONLY if they genuinely improve employability.

Do not recommend certifications that provide little hiring value.

Prefer globally recognized certifications.

==================================================

RESOURCE QUALITY

Recommend a balanced mix of:

Official Documentation

Courses

Interactive Practice

Books

Coding Platforms

Video Learning

Avoid recommending six resources from the same website.

==================================================

QUALITY REQUIREMENTS

Every recommendation must provide unique value.

No two recommendations should communicate the same idea.

Avoid repeating skills across different sections unless absolutely necessary.

Top Insights = AI observations.

Focus Now = Immediate actions.

Employer Expectations = Hiring requirements.

Industry Advice = Long-term career strategies.

Common Mistakes = Things that delay career growth.

Projects = Portfolio building.

Every section should answer a different question and provide information that cannot be inferred from another section.

FINAL VALIDATION

Before returning the response, verify:

✓ Skill Level is realistic.

✓ Current Stage matches Skill Level.

✓ Career Position matches Skill Level.

✓ Foundation Skills contain ALL required basics.

✓ Advanced Skills contain ONLY advanced topics.

✓ Priority Skills come from Foundation Skills.

✓ Learning Order contains EVERY missing skill.

✓ Learning Order begins with Foundation Skills.

✓ Every Learning Step has an Estimated Duration.

✓ Top Insights contains EXACTLY 6 items.

✓ Focus Now contains EXACTLY 6 items.

✓ Employer Expectations contains EXACTLY 6 items.

✓ Industry Advice contains EXACTLY 6 items.

✓ Common Mistakes contains EXACTLY 6 items.

✓ Projects contains EXACTLY 6 items.

✓ Certifications contains EXACTLY 6 items.

✓ Resources contains EXACTLY 6 items.

==================================================

OUTPUT RULES

Return ONLY the requested fields.

Never add headings.

Never add explanations.

Never add markdown.

Never number sections.

Never include notes.

Never include introductory text.

Never include concluding text.

Never wrap the response inside code blocks.

If a value is unavailable,

return an empty value.

Return ONLY the fields requested in the specified format.

==================================================

CareerOS Philosophy

The purpose of this analysis is not to impress the student.

The purpose is to provide an honest, personalized and industry-aligned assessment.

The student should finish reading the report knowing:

• Where they stand today.

• What employers expect.

• What skills they already possess.

• What skills they are missing.

• What they should learn first.

• How long it may realistically take.

• What mistakes to avoid.

• Which projects to build.

• Which certifications are worth pursuing.

• Which resources will help them learn faster.

The analysis should reduce confusion, increase confidence and give the student a clear direction towards becoming internship-ready and job-ready.

""".formatted(

profile.getDreamRole(),

profile.getCurrentYear(),

profile.getGraduationYear(),

currentSkills.isEmpty()
        ? "No skills provided"
        : String.join(", ", currentSkills),

profile.getSkills() == null || profile.getSkills().isEmpty()
        ? "No skills added yet"
        : String.join(", ", profile.getSkills()),

resumeUploaded
        ? "YES"
        : "NO",

resumeContent

);

System.out.println("Reached Point C");

   String response;

try {

    System.out.println("Calling Gemini...");

    response =
            geminiService.askGeminiCustom(prompt);

    System.out.println("Gemini call completed.");

    System.out.println(response);
    System.out.println(
"========================");

System.out.println(response);

System.out.println(
"========================");

}
catch (Exception e) {

    System.out.println("Gemini failed!");

    e.printStackTrace();

    throw e;

}
System.out.println("========== GEMINI RESPONSE ==========");
System.out.println(response);
System.out.println("=====================================");
System.out.println("Reached Point D");

           if (
        response == null
        ||
        response.isBlank()
        ||
        response.contains("temporarily unavailable")
        ||
        response.contains("parsing failed")
)  {



        
    profile.setSkillGapCompleted(
            true
    );

    profile.setCareerReadiness(
            calculateReadiness(
                    profile
            )
    );

   profile.setCurrentStage(
        "SKILL GAP COMPLETED"
);
profile.setLatestMissingSkills(List.of());

profile.setLatestPrioritySkills(List.of());


    studentProfileRepository
            .save(profile);

    return SkillGapResponse.builder()

            .targetRole(
                    profile.getDreamRole()
            )

            .currentYear(profile.getCurrentYear())

.graduationYear(profile.getGraduationYear())

            .currentSkills(
        profile.getSkills() == null
                ? List.of()
                : profile.getSkills()
)
           .recommendedResources(List.of())

            .skillMatchPercentage(
                    60
            )
            .readinessScore(
                    60
            )
            .prioritySkills(List.of())

            .recommendedResources(List.of())

          .resumeConsidered(resumeUploaded)

      .currentStage("Foundation")

.careerPosition("Career analysis unavailable")

.timeToJobReady("")

.learningOrder(List.of())

.topInsights(List.of())

.focusNow(List.of())

.employerExpectations(List.of())

.commonMistakes(List.of())

.industryAdvice(List.of())    
            .build();
}

   Integer skillLevel = 0;

String currentStage = "";

String careerPosition = "";

String timeToJobReady = "";

List<String> topInsights = List.of();

List<String> focusNow = List.of();

List<String> employerExpectations = List.of();

List<String> commonMistakes = List.of();

List<String> industryAdvice = List.of();

List<String> coreSkills = List.of();

List<String> foundationSkills = new ArrayList<>();

List<String> advancedSkills = new ArrayList<>();

List<String> missingSkills = new ArrayList<>();

List<String> prioritySkills = new ArrayList<>();

List<LearningStep> learningOrder = new ArrayList<>();


List<String> recommendedProjects = List.of();

List<String> recommendedCertifications = List.of();

List<String> resources = List.of();

  try {

    if (
            response == null
            ||
            response.isBlank()
    ) {

        throw new RuntimeException(
                "Gemini returned an empty response."
        );

    }

    String[] lines =
            response
                    .replace("\r", "")
                    .split("\n");

for (int i = 0; i < lines.length; i++) {

    String line =
            lines[i].trim();
      
        String matchValue =
                getValue(
        line,
        "SKILL_LEVEL"
);

        if (!matchValue.isBlank()) {

            try {

                skillLevel =
                        Integer.parseInt(
                                matchValue.replaceAll(
                                        "[^0-9]",
                                        ""
                                )
                        );

            }

            catch (Exception ignored) {}

        }
skillLevel = Math.max(0, Math.min(100, skillLevel));
      
     
        String coreValue =
                getValue(
                        line,
                        "CORE"
                );

        if (!coreValue.isBlank()) {

            coreSkills =
                    Arrays.stream(
                            coreValue.split(",")
                    )
                    .map(String::trim)
                    .filter(skill -> !skill.isBlank())
                    .toList();

        }


String priorityValue = "";

if (line.startsWith("PRIORITY=")) {

    priorityValue =
            getValue(line, "PRIORITY")
            + getMultilineValue(lines, i);

    prioritySkills =
            Arrays.stream(priorityValue.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();

}

String foundationValue = "";

if (line.startsWith("FOUNDATION_SKILLS=")) {

    foundationValue =
            getValue(line, "FOUNDATION_SKILLS")
            + getMultilineValue(lines, i);

    foundationSkills =
            Arrays.stream(foundationValue.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();

}
foundationSkills =
foundationSkills.stream()
.distinct()
.toList();


String currentStageValue =
        getValue(
                line,
                "CURRENT_STAGE"
        );

if (skillLevel <= 24) {

    currentStage = "Foundation";

}
else if (skillLevel <= 49) {

    currentStage = "Beginner";

}
else if (skillLevel <= 74) {

    currentStage = "Intermediate";

}
else {

    currentStage = "Advanced";

}

String careerPositionValue =
        getValue(
                line,
                "CAREER_POSITION"
        );

if (!careerPositionValue.isBlank()) {

    careerPosition =
            careerPositionValue;

}

String timeValue =
        getValue(
                line,
                "TIME_TO_JOB_READY"
        );

if (!timeValue.isBlank()) {

    timeToJobReady =
            timeValue;

}

if (line.startsWith("STEP:")) {

    LearningStep step = new LearningStep();

    step.setStep(
            Integer.parseInt(
                    line.replace("STEP:", "").trim()
            )
    );

    if (i + 1 < lines.length &&
            lines[i + 1].startsWith("SKILL:")) {

      step.setSkill(
        lines[++i]
                .replace("SKILL:", "")
                .trim()
                .replace(".", "")
);

    }

    if (i + 1 < lines.length &&
            lines[i + 1].startsWith("ESTIMATED_DURATION:")) {

       step.setEstimatedDuration(
        lines[++i]
                .replace("ESTIMATED_DURATION:", "")
                .trim()
);
    }

    learningOrder.add(step);

 System.out.println(step.getSkill());   

}

        
String insightsValue = "";

if(line.startsWith("TOP_INSIGHTS=")){

    insightsValue =
            getValue(line,"TOP_INSIGHTS");

    insightsValue += getMultilineValue(lines,i);

}

if (!insightsValue.isBlank()) {

    topInsights =
            Arrays.stream(
                    insightsValue.split(",|\\.\\s+")
            )
            .map(String::trim)
            .filter(s -> !s.isBlank())
            .toList();

}
System.out.println("TOP_INSIGHTS RAW:");
System.out.println(insightsValue);

String focusValue = "";

if (line.startsWith("FOCUS_NOW=")) {

    focusValue =
            getValue(line, "FOCUS_NOW")
            + getMultilineValue(lines, i);

    focusNow =
            Arrays.stream(focusValue.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();

}

String employerValue = "";

if (line.startsWith("EMPLOYER_EXPECTATIONS=")) {

    employerValue =
            getValue(line, "EMPLOYER_EXPECTATIONS")
            + getMultilineValue(lines, i);

    employerExpectations =
            Arrays.stream(employerValue.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();

}

String mistakesValue = "";

if (line.startsWith("COMMON_MISTAKES=")) {

    mistakesValue =
            getValue(line, "COMMON_MISTAKES")
            + getMultilineValue(lines, i);

    commonMistakes =
            Arrays.stream(mistakesValue.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();

}


String adviceValue = "";

if (line.startsWith("INDUSTRY_ADVICE=")) {

    adviceValue =
            getValue(line, "INDUSTRY_ADVICE")
            + getMultilineValue(lines, i);

    industryAdvice =
            Arrays.stream(adviceValue.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();

}



String advancedValue = "";

if (line.startsWith("ADVANCED=")) {

    advancedValue =
            getValue(line, "ADVANCED")
            + getMultilineValue(lines, i);

    advancedSkills =
            Arrays.stream(advancedValue.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();

}

advancedSkills =
advancedSkills.stream()
.distinct()
.toList();

missingSkills = new ArrayList<>();

for (String skill : foundationSkills) {

    if (!missingSkills.contains(skill)) {

        missingSkills.add(skill);

    }

}

for (String skill : advancedSkills) {

    if (!missingSkills.contains(skill)) {

        missingSkills.add(skill);

    }

}

System.out.println("Missing = " + missingSkills);

String projectValue = "";

if (line.startsWith("PROJECTS=")) {

    projectValue =
            getValue(line, "PROJECTS")
            + getMultilineValue(lines, i);

    recommendedProjects =
            Arrays.stream(projectValue.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();

}

        recommendedProjects =
recommendedProjects
.stream()
.distinct()
.limit(6)
.toList();

String certificationValue = "";

if (line.startsWith("CERTIFICATIONS=")) {

    certificationValue =
            getValue(line, "CERTIFICATIONS")
            + getMultilineValue(lines, i);

    recommendedCertifications =
            Arrays.stream(certificationValue.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();

}      

String resourceValue = "";

if (line.startsWith("RESOURCES=")) {

    resourceValue =
            getValue(line, "RESOURCES")
            + getMultilineValue(lines, i);

    resources =
            Arrays.stream(resourceValue.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();

}      
    }


    topInsights =
        topInsights.stream()
                .distinct()
                .limit(6)
                .toList();

focusNow =
        focusNow.stream()
                .distinct()
                .limit(6)
                .toList();

employerExpectations =
        employerExpectations.stream()
                .distinct()
                .limit(6)
                .toList();

industryAdvice =
        industryAdvice.stream()
                .distinct()
                .limit(6)
                .toList();

commonMistakes =
        commonMistakes.stream()
                .distinct()
                .limit(6)
                .toList();

recommendedProjects =
        recommendedProjects.stream()
                .distinct()
                .limit(6)
                .toList();

recommendedCertifications =
        recommendedCertifications.stream()
                .distinct()
                .limit(6)
                .toList();

resources =
        resources.stream()
                .distinct()
                .limit(6)
                .toList();

final List<String> parsedMissingSkills = missingSkills;

if (!parsedMissingSkills.isEmpty()) {

    learningOrder.removeIf(step ->
            !parsedMissingSkills.contains(step.getSkill())
            
    );

    learningOrder.removeIf(step ->
        step.getSkill()==null
                || step.getSkill().isBlank());

    int nextStep = learningOrder.size() + 1;

    for (String skill : parsedMissingSkills) {

        boolean exists = learningOrder.stream()
                .anyMatch(s ->
                        s.getSkill().equalsIgnoreCase(skill));

        if (!exists) {

            LearningStep step = new LearningStep();

            step.setStep(nextStep++);
            step.setSkill(skill);
            step.setEstimatedDuration("");

            learningOrder.add(step);
System.out.println(step.getSkill());

        }

    }

    for (int j = 0; j < learningOrder.size(); j++) {
        learningOrder.get(j).setStep(j + 1);
    }
    List<LearningStep> uniqueSteps = new ArrayList<>();

for (LearningStep step : learningOrder) {

    boolean exists = uniqueSteps.stream()
            .anyMatch(s ->
                    s.getSkill().equalsIgnoreCase(step.getSkill()));

    if (!exists) {

        uniqueSteps.add(step);

    }

}

learningOrder = uniqueSteps;

for (int j = 0; j < learningOrder.size(); j++) {

    learningOrder.get(j).setStep(j + 1);

}

}

if(coreSkills.isEmpty()){

    skillLevel=0;

    currentStage="Foundation";

    careerPosition=
        "No relevant skills found yet.";

}

    System.out.println("========== Parsed Skill Gap ==========");

    System.out.println("Skill Level : " + skillLevel);

    System.out.println("Core Skills : " + coreSkills);

    System.out.println("Priority Skills : " + prioritySkills);

    System.out.println("Missing Skills : " + missingSkills);

    System.out.println("Projects : " + recommendedProjects);

    System.out.println("Certifications : " + recommendedCertifications);

    System.out.println("Resources : " + resources);

    System.out.println("======================================");

}

catch (Exception e) {

    e.printStackTrace();

    throw new RuntimeException(
            "Failed to parse Skill Gap response.",
            e
    );

}

    profile.setSkillGapCompleted(true);

    profile.setCareerReadiness(
            calculateReadiness(profile)
    );

    profile.setCurrentStage(
            "SKILL GAP COMPLETED"
    );

profile.setLatestMissingSkills(
        missingSkills
);

profile.setLatestPrioritySkills(
        prioritySkills
);

System.out.println(
        "Saving missing skills: "
                + missingSkills
);

    studentProfileRepository
            .save(profile);


System.out.println("========== FINAL PARSED DATA ==========");

System.out.println("Foundation : " + foundationSkills);

System.out.println("Advanced : " + advancedSkills);

System.out.println("Missing : " + missingSkills);

System.out.println("Learning Order : " + learningOrder.size());

System.out.println("Top Insights : " + topInsights.size());

System.out.println("Employer Expectations : " + employerExpectations.size());

System.out.println("Industry Advice : " + industryAdvice.size());

System.out.println("Common Mistakes : " + commonMistakes.size());

System.out.println("Projects : " + recommendedProjects.size());

System.out.println("Resources : " + resources.size());

System.out.println("======================================");            

SkillGapAnalysis analysis = mapToAnalysis(

        email,

        profile,

        profileHash,

        skillLevel,

        currentStage,

        careerPosition,

        timeToJobReady,

        learningOrder,

        topInsights,

        focusNow,

missingSkills,

foundationSkills,

prioritySkills,

coreSkills,

advancedSkills,

        recommendedProjects,

        recommendedCertifications,

        resources,

        employerExpectations,

        commonMistakes,

        industryAdvice,

        resumeUploaded

);

skillGapAnalysisRepository
        .findByEmail(email)
        .ifPresent(existing ->
                analysis.setId(existing.getId())
        );

skillGapAnalysisRepository.save(analysis);



if (learningOrder.isEmpty() && !missingSkills.isEmpty()) {

    learningOrder = new ArrayList<>();

    int stepNumber = 1;

    for (String skill : missingSkills) {

        LearningStep step = new LearningStep();

        step.setStep(stepNumber++);
        step.setSkill(skill);
        step.setEstimatedDuration("");

        learningOrder.add(step);
        System.out.println(step.getSkill());

    }

}

   return SkillGapResponse.builder()

        .targetRole(
                profile.getDreamRole()
        )

        .currentSkills(
                profile.getSkills() == null
                        ? List.of()
                        : profile.getSkills()
        )

        .missingSkills(
                missingSkills
        )

        .prioritySkills(
                prioritySkills
        )

        .coreSkills(
                coreSkills
        )

        .advancedSkills(
                advancedSkills
        )

        .foundationSkills(foundationSkills)

        .skillMatchPercentage(skillLevel
        )

        .readinessScore(
                skillLevel
        )

        .careerPosition(
                careerPosition
        )

        .currentStage(
                currentStage
        )

        .timeToJobReady(
                timeToJobReady
        )

        .learningOrder(
                learningOrder
        )

        .topInsights(
                topInsights
        )

        .focusNow(
                focusNow
        )

        .recommendedProjects(
                recommendedProjects
        )

        .recommendedCertifications(
                recommendedCertifications
        )

        .recommendedResources(
                resources
        )

        .employerExpectations(
                employerExpectations
        )

        .commonMistakes(
                commonMistakes
        )

        .industryAdvice(
                industryAdvice
        )

        .resumeConsidered(
                resumeUploaded
        )

        .build();
}



private String generateProfileHash(String data) {

    try {

        MessageDigest digest =
                MessageDigest.getInstance("SHA-256");

        byte[] hash =
                digest.digest(
                        data.getBytes(StandardCharsets.UTF_8)
                );

        StringBuilder builder =
                new StringBuilder();

        for (byte b : hash) {

            builder.append(
                    String.format("%02x", b)
            );

        }

        return builder.toString();

    }

    catch (NoSuchAlgorithmException e) {

        throw new RuntimeException(e);

    }

}

private SkillGapResponse mapToResponse(
        SkillGapAnalysis analysis
) {

    return SkillGapResponse.builder()

            .targetRole(
                    analysis.getTargetRole()
            )

            .currentSkills(
                    analysis.getCurrentSkills()
            )

            .missingSkills(
                    analysis.getMissingSkills()
            )

            .prioritySkills(
                    analysis.getPrioritySkills()
            )

            .skillMatchPercentage(
                    analysis.getSkillMatchPercentage()
            )

            .readinessScore(
                    analysis.getReadinessScore()
            )
            .recommendedResources(
                    analysis.getRecommendedResources()
            )
            .foundationSkills(
        analysis.getFoundationSkills()
)
            
            .advancedSkills(
                    analysis.getAdvancedSkills()
            )

            .recommendedProjects(
                    analysis.getRecommendedProjects()
            )

            .recommendedCertifications(
                    analysis.getRecommendedCertifications()
            )

           
            .currentStage(
        analysis.getCurrentStage()
)

.careerPosition(
        analysis.getCareerPosition()
)

.timeToJobReady(
        analysis.getTimeToJobReady()
)

.learningOrder(
        analysis.getLearningOrder()
)

.topInsights(
        analysis.getTopInsights()
)

.focusNow(
        analysis.getFocusNow()
)

.employerExpectations(
        analysis.getEmployerExpectations()
)

.commonMistakes(
        analysis.getCommonMistakes()
)

.industryAdvice(
        analysis.getIndustryAdvice()
)
            .resumeConsidered(
                    analysis.getResumeConsidered()
            )
            

            .build();

}

private SkillGapAnalysis mapToAnalysis(

        String email,

        StudentProfile profile,

        String profileHash,

        Integer skillLevel,

        String currentStage,

        String careerPosition,

        String timeToJobReady,

        List<LearningStep> learningOrder,

        List<String> topInsights,

        List<String> focusNow,

        List<String> missingSkills,
        
        List<String> foundationSkills,

        List<String> prioritySkills,

        List<String> coreSkills,

        List<String> advancedSkills,

        List<String> recommendedProjects,

        List<String> recommendedCertifications,

        List<String> resources,

        List<String> employerExpectations,

        List<String> commonMistakes,

        List<String> industryAdvice,

        Boolean resumeUploaded
) {

  return SkillGapAnalysis.builder()

    .email(email)

    .targetRole(profile.getDreamRole())

    .currentSkills(
        profile.getSkills() == null
                ? List.of()
                : profile.getSkills()
    )

    .skillMatchPercentage(skillLevel)

    .readinessScore(skillLevel)

    .currentStage(currentStage)

    .careerPosition(careerPosition)

    .timeToJobReady(timeToJobReady)

    .learningOrder(learningOrder)

    .topInsights(topInsights)

    .focusNow(focusNow)

    .missingSkills(missingSkills == null ? List.of() : missingSkills)

    .prioritySkills(prioritySkills == null ? List.of() : prioritySkills)

    .coreSkills(coreSkills == null ? List.of() : coreSkills)

    .advancedSkills(advancedSkills == null ? List.of() : advancedSkills)

    .foundationSkills(
        foundationSkills == null
                ? List.of()
                : foundationSkills
)

    .recommendedProjects(recommendedProjects)

    .recommendedCertifications(recommendedCertifications)

    .recommendedResources(resources)

    .employerExpectations(employerExpectations)

    .commonMistakes(commonMistakes)

    .industryAdvice(industryAdvice)

    .resumeConsidered(resumeUploaded)

    .profileHash(profileHash)

    .generatedAt(java.time.LocalDateTime.now())

    .build();
}

private String getValue(
        String line,
        String key
) {

    if (line == null) {

        return "";

    }

    line = line.trim();

    int equalsIndex =
            line.indexOf("=");

    if (equalsIndex == -1) {

        return "";

    }

    String parsedKey =
            line.substring(
                    0,
                    equalsIndex
            ).trim();

    if (
            parsedKey.equalsIgnoreCase(
                    key
            )
    ) {

        return line.substring(
                equalsIndex + 1
        ).trim();

    }

    return "";

}

private String getMultilineValue(
        String[] lines,
        int start
) {

    StringBuilder builder =
            new StringBuilder();

    for (int i = start + 1; i < lines.length; i++) {

        String current =
                lines[i].trim();

        if(current.contains("="))
            break;

        if(current.isBlank())
            continue;

        builder.append(current);

        builder.append(",");

    }

    return builder.toString();

}

}