package com.careeros.careeros_backend.controller;

import com.careeros.careeros_backend.dto.*;
import com.careeros.careeros_backend.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/interview")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping("/start")
    public StartInterviewResponse startInterview(
            @RequestBody
            StartInterviewRequest request
    ) {
        return interviewService
                .startInterview(request);
    }

    @PostMapping("/answer")
    public InterviewTurnResponse submitAnswer(
            @RequestBody
            SubmitAnswerRequest request
    ) {
        return interviewService
                .submitAnswer(request);
    }

    @PostMapping("/end")
public InterviewReportResponse endInterview(
        @RequestBody
        EndInterviewRequest request
) {

    return interviewService.generateReport(
            request.getSessionId()
    );
}
@GetMapping("/history")
public List<InterviewHistoryResponse>
getInterviewHistory() {

    return interviewService
            .getInterviewHistory();
}

}