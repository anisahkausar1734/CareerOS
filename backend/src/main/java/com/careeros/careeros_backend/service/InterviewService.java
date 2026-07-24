package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.*;
import com.careeros.careeros_backend.model.InterviewEvaluation;
import com.careeros.careeros_backend.model.InterviewSession;
import com.careeros.careeros_backend.repository.InterviewSessionRepository;
import com.careeros.careeros_backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor



public class InterviewService {
private final StudentProfileRepository
        studentProfileRepository;

private final StudentProfileService
        studentProfileService;
        
private final GeminiService geminiService;

private final InterviewSessionRepository interviewSessionRepository;

private EvaluationResult evaluateAnswer(
        InterviewSession session,
        String answer
) {

    String prompt = """
            You are an expert interview evaluator.

            Company:
            %s

            Role:
            %s

            Candidate Answer:
            %s

            Evaluate the answer.

            Return ONLY in this format:

            TECHNICAL=number

            COMMUNICATION=number

            PROBLEM_SOLVING=number

            CONFIDENCE=number

            STRENGTHS=text

            IMPROVEMENTS=text

            Rules:

            - Scores between 1 and 10
            - No markdown
            - No extra text
            """
            .formatted(
                    session.getCompany(),
                    session.getRole(),
                    answer
            );

    String response =
            geminiService.askGeminiCustom(prompt);

    EvaluationResult result =
            new EvaluationResult();

    try {

        String[] lines =
                response.split("\n");

        for (String line : lines) {

            if(line.startsWith("TECHNICAL="))
                result.setTechnicalScore(
                        Double.parseDouble(
                                line.replace(
                                        "TECHNICAL=",
                                        ""
                                ).trim()
                        )
                );

            if(line.startsWith("COMMUNICATION="))
                result.setCommunicationScore(
                        Double.parseDouble(
                                line.replace(
                                        "COMMUNICATION=",
                                        ""
                                ).trim()
                        )
                );

            if(line.startsWith("PROBLEM_SOLVING="))
                result.setProblemSolvingScore(
                        Double.parseDouble(
                                line.replace(
                                        "PROBLEM_SOLVING=",
                                        ""
                                ).trim()
                        )
                );

            if(line.startsWith("CONFIDENCE="))
                result.setConfidenceScore(
                        Double.parseDouble(
                                line.replace(
                                        "CONFIDENCE=",
                                        ""
                                ).trim()
                        )
                );

            if(line.startsWith("STRENGTHS="))
                result.setStrengths(
                        line.replace(
                                "STRENGTHS=",
                                ""
                        ).trim()
                );

            if(line.startsWith("IMPROVEMENTS="))
                result.setImprovements(
                        line.replace(
                                "IMPROVEMENTS=",
                                ""
                        ).trim()
                );
        }

    } catch (Exception e) {

        result.setTechnicalScore(5);
        result.setCommunicationScore(5);
        result.setProblemSolvingScore(5);
        result.setConfidenceScore(5);
        result.setStrengths("Evaluation unavailable");
        result.setImprovements("Evaluation unavailable");
    }

    return result;
}

    public StartInterviewResponse startInterview(
            StartInterviewRequest request
    ) {

        InterviewSession session =
                new InterviewSession();

        session.setCompany(request.getCompany());
        session.setRole(request.getRole());
        session.setResume(request.getResume());
        session.setInterviewType(request.getInterviewType());
        session.setCustomPrompt(request.getCustomPrompt());

        String prompt = """
                You are a professional interviewer.

                Company:
                %s

                Role:
                %s

                Interview Type:
                %s

                Resume:
                %s

                Additional Instructions:
                %s

                Rules:

                - Act like a real interviewer
                - Ask only ONE question
                - Do not introduce yourself
                - Do not explain anything
                - Do not provide answers
                - Ask the first interview question
                - Return ONLY the question
                """
                .formatted(
                        request.getCompany(),
                        request.getRole(),
                        request.getInterviewType(),
                        request.getResume(),
                        request.getCustomPrompt()
                );

        String firstQuestion =
                geminiService.askGeminiCustom(prompt);

        session.setEmail(
        request.getEmail()
);        

        session.getConversationHistory()
                .add("INTERVIEWER: " + firstQuestion);

        session.setQuestionCount(1);

        interviewSessionRepository.save(session);

        return new StartInterviewResponse(
                session.getId(),
                firstQuestion
        );
    }

    public InterviewTurnResponse submitAnswer(
            SubmitAnswerRequest request
    ) {

        InterviewSession session =
                interviewSessionRepository
                        .findById(request.getSessionId())
                        .orElseThrow();

        session.getConversationHistory()
                .add("CANDIDATE: " + request.getAnswer());

                EvaluationResult evaluation =
        new EvaluationResult();

evaluation.setTechnicalScore(7);
evaluation.setCommunicationScore(7);
evaluation.setProblemSolvingScore(7);
evaluation.setConfidenceScore(7);

evaluation.setStrengths(
        "Interview completed successfully"
);

evaluation.setImprovements(
        "Detailed evaluation generated at the end"
);

        InterviewEvaluation storedEvaluation =
        new InterviewEvaluation();

storedEvaluation.setStrengths(
        evaluation.getStrengths()
);

storedEvaluation.setImprovements(
        evaluation.getImprovements()
);

storedEvaluation.setTechnicalScore(
        evaluation.getTechnicalScore()
);

storedEvaluation.setCommunicationScore(
        evaluation.getCommunicationScore()
);

storedEvaluation.setProblemSolvingScore(
        evaluation.getProblemSolvingScore()
);

storedEvaluation.setConfidenceScore(
        evaluation.getConfidenceScore()
);



session.getEvaluations()
        .add(storedEvaluation);



        double averageScore =
        (
                evaluation.getTechnicalScore()
                + evaluation.getCommunicationScore()
                + evaluation.getProblemSolvingScore()
                + evaluation.getConfidenceScore()
        ) / 4.0;

session.setTotalScore(
        session.getTotalScore()
                + averageScore
);

        var allHistory =
        session.getConversationHistory();

int start =
        Math.max(
                0,
                allHistory.size() - 25
        );

var recentHistory =
        allHistory.subList(
                start,
                allHistory.size()
        );

StringBuilder history =
        new StringBuilder();

for(String message :
        recentHistory) {

    history.append(message)
            .append("\n");
}

        String prompt = """
                You are conducting a real interview.

                Company:
                %s

                Role:
                %s

                Interview Type:
                %s

                Resume:
                %s

                Additional Instructions:
                %s

                Previous Conversation:

                %s

                Rules:

You are conducting a real interview.

Interview behavior:

- Continuously analyze the candidate's answers
- Ask follow-up questions when answers are vague
- Dig deeper into projects mentioned
- Challenge assumptions
- Test technical depth
- Test problem-solving ability
- Explore technologies mentioned by the candidate
- Ask behavioral questions when appropriate
- Ask system design questions when appropriate
- Adapt difficulty dynamically
- Do not repeatedly ask similar questions
- Avoid asking the same technology repeatedly
- Gradually move to new areas
- If resume is provided, thoroughly explore projects, technologies and decisions mentioned in the resume
- Progress naturally from introductory questions to technical depth, architecture, problem solving and behavioral assessment

Company-specific behavior:

- If company is Amazon, focus on Leadership Principles, ownership, scalability and decision making
- If company is Google, focus on problem solving, optimization and technical depth
- If company is Microsoft, focus on collaboration, architecture and communication
- If company is a startup, focus on practical engineering and ownership
- If company is unknown, behave like a professional interviewer

Important:

- Never reveal evaluation
- Never reveal score
- Never provide hints
- Never provide solutions
- Never break character
- Ask only ONE question
- Return only the next interview question
- Return ONLY the question text
- Do not start with INTERVIEWER:
- Do not use labels
- No markdown

                """
                .formatted(
                        session.getCompany(),
                        session.getRole(),
                        session.getInterviewType(),
                        session.getResume(),
                        session.getCustomPrompt(),
                        history
                );

        String nextQuestion =
        geminiService.askGeminiCustom(prompt);

nextQuestion = nextQuestion
        .replace("INTERVIEWER:", "")
        .trim();

        session.getConversationHistory()
                .add(
                        "INTERVIEWER: "
                                + nextQuestion
                );

        session.setQuestionCount(
                session.getQuestionCount() + 1
        );



        interviewSessionRepository.save(session);

        return new InterviewTurnResponse(
                nextQuestion
        );
         
    }
    public InterviewReportResponse generateReport(
        String sessionId
) {

    InterviewSession session =
            interviewSessionRepository
                    .findById(sessionId)
                    .orElseThrow();

                    if(session.getQuestionCount() < 5) {

    throw new RuntimeException(
            "Complete at least 5 interview questions before generating a report."
    );
}

    if(session.getEvaluations().isEmpty()) {

        return new InterviewReportResponse(
                0,0,0,0,0,
                "No interview data",
                "No interview data",
                "Interview too short"
        );
    }

   String interviewHistory =
        String.join(
                "\n",
                session.getConversationHistory()
        );

String evaluationPrompt =
        """
        You are an expert technical interviewer.

        Analyze the COMPLETE interview.

        Interview Transcript:

        %s

        Return ONLY in this format:

        TECHNICAL=number

        COMMUNICATION=number

        PROBLEM_SOLVING=number

        CONFIDENCE=number

        STRENGTHS=text

        IMPROVEMENTS=text

        Rules:

        - Scores between 1 and 10
        - No markdown
        - No extra text
        """
        .formatted(
                interviewHistory
        );

String evaluationResponse =
        geminiService.askGeminiCustom(
                evaluationPrompt
        );

double technical = 7;
double communication = 7;
double problemSolving = 7;
double confidence = 7;

String strengths =
        "Interview completed";

String improvements =
        "No improvements generated";
        try {

    String[] lines =
            evaluationResponse.split("\n");

    for(String line : lines) {

        if(line.startsWith("TECHNICAL="))
            technical =
                    Double.parseDouble(
                            line.replace(
                                    "TECHNICAL=",
                                    ""
                            ).trim()
                    );

        if(line.startsWith("COMMUNICATION="))
            communication =
                    Double.parseDouble(
                            line.replace(
                                    "COMMUNICATION=",
                                    ""
                            ).trim()
                    );

        if(line.startsWith("PROBLEM_SOLVING="))
            problemSolving =
                    Double.parseDouble(
                            line.replace(
                                    "PROBLEM_SOLVING=",
                                    ""
                            ).trim()
                    );

        if(line.startsWith("CONFIDENCE="))
            confidence =
                    Double.parseDouble(
                            line.replace(
                                    "CONFIDENCE=",
                                    ""
                            ).trim()
                    );

        if(line.startsWith("STRENGTHS="))
            strengths =
                    line.replace(
                            "STRENGTHS=",
                            ""
                    ).trim();

        if(line.startsWith("IMPROVEMENTS="))
            improvements =
                    line.replace(
                            "IMPROVEMENTS=",
                            ""
                    ).trim();
    }

} catch (Exception e) {

    System.out.println(
            "Final evaluation parsing failed"
    );
}
    double overall =
(
        technical
        + communication
        + problemSolving
        + confidence
) / 4.0 * 10;

    String recommendationPrompt =
            """
            Candidate Interview Summary

            Technical Score:
            %s

            Communication Score:
            %s

            Problem Solving Score:
            %s

            Confidence Score:
            %s

            Strengths:
            %s

            Improvements:
            %s

            Generate a short hiring recommendation.

            Keep it under 100 words.
            """
                    .formatted(
                            technical,
                            communication,
                            problemSolving,
                            confidence,
                            strengths,
                            improvements
                    );

    String recommendation =
            geminiService
                    .askGeminiCustom(
                            recommendationPrompt
                    );
                    

                    session.setFinalScore(
        overall
);

session.setFinalRecommendation(
        recommendation
);

session.setFinalStrengths(
        strengths
);

session.setFinalImprovements(
        improvements
);



    session.setActive(false);
    session.setEndedAt(
        LocalDateTime.now()
);

    interviewSessionRepository.save(
            session
    );

    if(session.getEmail() != null) {

    studentProfileRepository
            .findByEmail(
                    session.getEmail()
            )
            .ifPresent(profile -> {

                profile.setInterviewCompleted(
                        true
                );

                profile.setCareerReadiness(
                        studentProfileService
                                .calculateReadiness(
                                        profile
                                )
                );

                profile.setCurrentStage(
                        "INTERVIEW_COMPLETED"
                );

                studentProfileRepository
                        .save(profile);
            });
}

    return new InterviewReportResponse(
            overall,
            technical,
            communication,
            problemSolving,
            confidence,
            strengths.toString(),
            improvements.toString(),
            recommendation
    );    
}

public List<InterviewHistoryResponse>
getInterviewHistory() {

    return interviewSessionRepository
            .findByActiveFalseOrderByEndedAtDesc()
            .stream()
            .map(session ->
                    new InterviewHistoryResponse(
                            session.getId(),
                            session.getRole(),
                            session.getCompany(),
                            session.getFinalScore(),
                            session.getEndedAt()
                    )
            )
            .toList();
}

}