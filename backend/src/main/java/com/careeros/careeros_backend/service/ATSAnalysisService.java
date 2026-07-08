package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ATSAnalysisResponse;
import com.careeros.careeros_backend.model.Resume;
import com.careeros.careeros_backend.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ATSAnalysisService {

    private final ResumeRepository
            resumeRepository;

    private final GeminiService
            geminiService;

    public ATSAnalysisResponse analyzeResume(
            String email
    ) {

        Resume resume =
                resumeRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Resume not found"
                                        )
                        );

      String prompt = """
You are an enterprise-grade Applicant Tracking System (ATS) used by top technology companies.

Act simultaneously as:

• ATS Resume Parser
• Technical Recruiter
• Campus Hiring Recruiter
• Software Engineering Hiring Manager
• Resume Screening Specialist

Your responsibility is ONLY to evaluate ATS compatibility.

Do NOT evaluate career guidance.

Do NOT recommend technologies to learn.

Do NOT generate a learning roadmap.

Do NOT evaluate future potential.

Judge ONLY what is written in the resume.

--------------------------------------------------
ANALYSIS OBJECTIVE
--------------------------------------------------

Evaluate whether this resume would successfully pass an ATS screening and reach a human recruiter.

Assume this resume is being submitted for Software Engineering internships or entry-level software engineering roles.

--------------------------------------------------
EVALUATION CRITERIA
--------------------------------------------------

1. ATS Compatibility

Evaluate overall ATS friendliness.

Score:
0-100

--------------------------------------------------

2. Resume Parsing

Evaluate whether an ATS can successfully parse this resume.

Consider:

• Tables
• Multiple columns
• Icons
• Images
• Fancy formatting
• Text extraction
• PDF compatibility
• Heading consistency

Score:
0-100

--------------------------------------------------

3. Keyword Optimization

Evaluate keyword relevance.

Consider:

Programming Languages

Frameworks

Libraries

Databases

Cloud

DevOps

Tools

Software Engineering terminology

Return:

Detected Keywords

Missing Keywords

Keyword Score

--------------------------------------------------

4. Contact Information

Check whether the resume includes:

Email

Phone

GitHub

LinkedIn

Portfolio

Location

Score:
0-100

--------------------------------------------------

5. Section Completeness

Evaluate presence and quality of:

Professional Summary

Education

Technical Skills

Projects

Experience

Certifications

Achievements

Leadership

Score:
0-100

Return missing sections.

--------------------------------------------------

6. Formatting

Evaluate:

Spacing

Consistency

Bullet formatting

Headings

Alignment

Professional appearance

ATS compatibility

Score:
0-100

--------------------------------------------------

7. Readability

Evaluate:

Grammar

Sentence clarity

Bullet quality

Scanning

Action verbs

Professional language

Score:
0-100

--------------------------------------------------

8. Skills Match

Evaluate how well technical skills are represented.

Consider:

Breadth

Depth

Technology grouping

Industry relevance

Score:
0-100

--------------------------------------------------

9. Projects

Evaluate:

Project titles

Descriptions

Technologies

Technical depth

GitHub links

Deployment links

Business impact

Score:
0-100

--------------------------------------------------

10. Achievements

Evaluate:

Measurable impact

Numbers

Percentages

Results

Action verbs

Score:
0-100

--------------------------------------------------

11. ATS Risks

Identify the biggest issues likely to reduce ATS or recruiter success.

--------------------------------------------------

12. Quick Wins

Suggest only the highest-impact improvements that can be implemented immediately.

--------------------------------------------------
OUTPUT FORMAT
--------------------------------------------------

Return ONLY the following format.

ATS_SCORE=

PARSING_SCORE=

KEYWORD_SCORE=

CONTACT_SCORE=

SECTION_SCORE=

FORMATTING_SCORE=

READABILITY_SCORE=

SKILLS_MATCH_SCORE=

PROJECT_SCORE=

ACHIEVEMENT_SCORE=

DETECTED_KEYWORDS=item1|item2|item3

MISSING_KEYWORDS=item1|item2|item3

MISSING_SECTIONS=item1|item2|item3

PARSING_ISSUES=item1|item2|item3

ACTION_VERBS_FOUND=item1|item2|item3

WEAK_ACTION_VERBS=item1|item2|item3

QUANTIFIED_ACHIEVEMENTS=item1|item2|item3

ATS_RISKS=item1|item2|item3

QUICK_WINS=item1|item2|item3

STRENGTHS=item1|item2|item3

IMPROVEMENTS=item1|item2|item3

RESUME_TYPE=

ATS_SUMMARY=

VERDICT=

--------------------------------------------------
RULES
--------------------------------------------------

Return ONLY the requested format.

No markdown.

No numbering.

No explanations.

Every score must be between 0 and 100.

Use "|" to separate list items.

If an item does not exist return NONE.

Keep ATS_SUMMARY under 80 words.

VERDICT should be one concise sentence.

--------------------------------------------------
RESUME
--------------------------------------------------

%s
"""
.formatted(resume.getResumeText());


        String response =
                geminiService
                        .askGeminiCustom(
                                prompt
                        );

        System.out.println(
                "ATS RESPONSE:\n"
                + response
        );

       Integer atsScore = 0;
Integer parsingScore = 0;
Integer keywordScore = 0;
Integer contactScore = 0;
Integer sectionScore = 0;
Integer formattingScore = 0;
Integer readabilityScore = 0;
Integer skillsMatchScore = 0;
Integer projectScore = 0;
Integer achievementScore = 0;

String resumeType = "";
String atsSummary = "";
String verdict = "";

List<String> detectedKeywords = new ArrayList<>();
List<String> missingKeywords = new ArrayList<>();
List<String> missingSections = new ArrayList<>();
List<String> parsingIssues = new ArrayList<>();
List<String> atsRisks = new ArrayList<>();
List<String> quickWins = new ArrayList<>();
List<String> actionVerbsFound = new ArrayList<>();
List<String> weakActionVerbs = new ArrayList<>();
List<String> quantifiedAchievements = new ArrayList<>();

List<String> strengths = new ArrayList<>();
List<String> improvements = new ArrayList<>();
        try {

            String[] lines =
                    response.split("\n");

            for(String line : lines) {

                if(line.startsWith(
                        "ATS_SCORE="
                )) {

                   atsScore =
        parseScore(
                line.replace(
                        "ATS_SCORE=",
                        ""
                )
        );
                }

                else if(line.startsWith(
                        "KEYWORD_SCORE="
                )) {

                    keywordScore =
        parseScore(
                line.replace(
                        "KEYWORD_SCORE=",
                        ""
                )
        );
                            
                }

                else if(line.startsWith(
                        "FORMATTING_SCORE="
                )) {

                    formattingScore =
                             parseScore(
                                    line.replace(
                                            "FORMATTING_SCORE=",
                                            ""
                                    )
                            );
                }

                else if(line.startsWith(
                        "SECTION_SCORE="
                )) {

                    sectionScore =
                            parseScore(
                                    line.replace(
                                            "SECTION_SCORE=",
                                            ""
                                    )
                            );
                }

                else if(line.startsWith(
                        "READABILITY_SCORE="
                )) {

                    readabilityScore =
                            parseScore(
                                    line.replace(
                                            "READABILITY_SCORE=",
                                            ""
                                    )
                            );
                }

                else if(line.startsWith(
                        "MISSING_KEYWORDS="
                )) {

                   missingKeywords = parseList(
        line.replace("MISSING_KEYWORDS=", "")
);
                }

                else if(line.startsWith(
                        "STRENGTHS="
                )) {

                    strengths =
                            parseList(
                                    line.replace(
                                            "STRENGTHS=",
                                            ""
                                    )
                            );
                }

                else if(line.startsWith(
                        "IMPROVEMENTS="
                )) {

                    improvements =
                            parseList(
                                    line.replace(
                                            "IMPROVEMENTS=",
                                            ""
                                    )
                            );
                }

                else if(line.startsWith(
                        "VERDICT="
                )) {

                    verdict =
                            line.replace(
                                    "VERDICT=",
                                    ""
                            ).trim();
                }
                else if(line.startsWith("PARSING_SCORE=")) {

    parsingScore =
            parseScore(
                    line.replace(
                            "PARSING_SCORE=",
                            ""
                    )
            );
}
else if(line.startsWith("CONTACT_SCORE=")) {

    contactScore =
            parseScore(
                    line.replace(
                            "CONTACT_SCORE=",
                            ""
                    )
            );
}
else if(line.startsWith("SKILLS_MATCH_SCORE=")) {

    skillsMatchScore =
            parseScore(
                    line.replace(
                            "SKILLS_MATCH_SCORE=",
                            ""
                    )
            );
}
else if(line.startsWith("PROJECT_SCORE=")) {

    projectScore =
            parseScore(
                    line.replace(
                            "PROJECT_SCORE=",
                            ""
                    )
            );
}
else if(line.startsWith("ACHIEVEMENT_SCORE=")) {

    achievementScore =
            parseScore(
                    line.replace(
                            "ACHIEVEMENT_SCORE=",
                            ""
                    )
            );
}

else if(line.startsWith("DETECTED_KEYWORDS=")) {

    detectedKeywords =
            parseList(
                    line.replace(
                            "DETECTED_KEYWORDS=",
                            ""
                    )
            );
}

else if(line.startsWith("MISSING_SECTIONS=")) {

    missingSections =
            parseList(
                    line.replace(
                            "MISSING_SECTIONS=",
                            ""
                    )
            );
}

else if(line.startsWith("PARSING_ISSUES=")) {

    parsingIssues =
            parseList(
                    line.replace(
                            "PARSING_ISSUES=",
                            ""
                    )
            );
}

else if(line.startsWith("ATS_RISKS=")) {

    atsRisks =
            parseList(
                    line.replace(
                            "ATS_RISKS=",
                            ""
                    )
            );
}

else if(line.startsWith("QUICK_WINS=")) {

    quickWins =
            parseList(
                    line.replace(
                            "QUICK_WINS=",
                            ""
                    )
            );
}

else if(line.startsWith("ACTION_VERBS_FOUND=")) {

    actionVerbsFound =
            parseList(
                    line.replace(
                            "ACTION_VERBS_FOUND=",
                            ""
                    )
            );
}

else if(line.startsWith("WEAK_ACTION_VERBS=")) {

    weakActionVerbs =
            parseList(
                    line.replace(
                            "WEAK_ACTION_VERBS=",
                            ""
                    )
            );
}

else if(line.startsWith("QUANTIFIED_ACHIEVEMENTS=")) {

    quantifiedAchievements =
            parseList(
                    line.replace(
                            "QUANTIFIED_ACHIEVEMENTS=",
                            ""
                    )
            );
}

else if(line.startsWith("RESUME_TYPE=")) {

    resumeType =
            line.replace(
                    "RESUME_TYPE=",
                    ""
            ).trim();
}

else if(line.startsWith("ATS_SUMMARY=")) {

    atsSummary =
            line.replace(
                    "ATS_SUMMARY=",
                    ""
            ).trim();
}


            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return ATSAnalysisResponse
                .builder()
                .atsScore(
                        atsScore
                )
                .keywordScore(
                        keywordScore
                )
                .formattingScore(
                        formattingScore
                )
                .sectionScore(
                        sectionScore
                )
                .readabilityScore(
                        readabilityScore
                )
                .missingKeywords(
                        missingKeywords
                )
                .strengths(
                        strengths
                )
                .improvements(
                        improvements
                )
                .verdict(
                        verdict
                )
                .parsingScore(parsingScore)

.contactScore(contactScore)

.skillsMatchScore(skillsMatchScore)

.projectScore(projectScore)

.achievementScore(achievementScore)

.detectedKeywords(detectedKeywords)

.missingSections(missingSections)

.parsingIssues(parsingIssues)

.atsRisks(atsRisks)

.quickWins(quickWins)

.actionVerbsFound(actionVerbsFound)

.weakActionVerbs(weakActionVerbs)

.quantifiedAchievements(quantifiedAchievements)

.resumeType(resumeType)

.atsSummary(atsSummary)
                .build();
    }

private Integer parseScore(String value) {

    try {

        value = value
                .replace("%", "")
                .replace("/100", "")
                .trim();

        return parseScore(value);

    } catch (Exception e) {

        return 0;
    }
}

  private List<String> parseList(String value) {

    if(value == null)
        return new ArrayList<>();

    value = value.trim();

    if(value.isBlank())
        return new ArrayList<>();

    if(value.equalsIgnoreCase("NONE"))
        return new ArrayList<>();

    return Arrays.stream(value.split("\\|"))
            .map(String::trim)
            .filter(s -> !s.isBlank())
            .toList();
}


}