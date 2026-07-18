import { BrowserRouter, Routes, Route } from "react-router-dom";

import ProtectedRoute from "../components/ProtectedRoute";

import LoginPage from "../pages/LoginPage";
import RegisterPage from "../pages/RegisterPage";
import ATSPage from "../pages/ATSPage";
import InternshipAnalyzerPage from "../pages/InternshipAnalyzerPage";
import ProjectIntelligencePage from "../pages/ProjectIntelligence/ProjectIntelligencePage";
import ResumeAnalyzer from "../pages/ResumeAnalyzer";
import ResumeAnalysisPage from "../pages/ResumeAnalysisPage";
import AICareerMentor from "../pages/AICareerMentor";
import RoadmapPage from "../pages/RoadmapPage";
import SkillGapPage from "../pages/SkillGapPage";
import CompanyReadinessPage from "../pages/CompanyReadinessPage";
import InterviewQuestionsPage from "../pages/InterviewQuestionsPage";
import JobRecommendationsPage from "../pages/JobRecommendationsPage";
import LearningResourcesPage from "../pages/LearningResourcesPage";
import CareerProgressPage from "../pages/CareerProgressPage";
import AdminDashboardPage from "../pages/AdminDashboardPage";
import ApplicationTrackerPage from "../pages/ApplicationTrackerPage";
import CopilotPage from "../pages/CopilotPage";
import InterviewPage from "../pages/InterviewPage";
import InterviewReportPage from "../pages/InterviewReportPage";
import StudentProfilePage from "../pages/StudentProfilePage";
import ResumeCenterPage from "../pages/ResumeCenterPage";
import ResumeRefinementPage from "../pages/ResumeRefinementPage";
import InternshipPage from "../pages/InternshipRecommendationsPage";
import CareerIntelligencePage from "../pages/CareerIntelligencePage";
import ProfileEditPage from "../pages/ProfileEditPage";
import DashboardPage from "../pages/DashboardPage";
import ProfileSetupPage from "../pages/ProfileSetupPage";
import ProjectReportPage from "../pages/ProjectIntelligence/ProjectReportPage";



function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>

        <Route
          path="/"
          element={<LoginPage />}
        />

        <Route
          path="/register"
          element={<RegisterPage />}
        />

         <Route
        path="/project-intelligence/:projectId"
        element={<ProjectReportPage />}
    />




<Route
    path="/dashboard"
    element={
        <ProtectedRoute>
            <DashboardPage />
        </ProtectedRoute>
    }
/>



        <Route
          path="/profile"
          element={
            <ProtectedRoute>
              <StudentProfilePage />
            </ProtectedRoute>
          }
        />



<Route
  path="/profile-setup"
  element={
    <ProtectedRoute>
      <ProfileSetupPage />
    </ProtectedRoute>
  }
/>

       
<Route
  path="/skill-gap"
  element={
    <ProtectedRoute>
      <SkillGapPage />
    </ProtectedRoute>
  }
/>

<Route
  path="/resume-refinement"
  element={<ResumeRefinementPage />}
/>

<Route
  path="/resume-center"
  element={<ResumeCenterPage />}
/>

<Route
    path="/interview"
    element={<InterviewPage />}
/>

<Route
    path="/interview-report"
    element={<InterviewReportPage />}
/>



<Route
    path="/ats"
    element={
        <ProtectedRoute>
            <ATSPage />
        </ProtectedRoute>
    }
/>

<Route
    path="/copilot"
    element={<CopilotPage />}
/>

<Route
  path="/admin"
  element={
    <ProtectedRoute>
      <AdminDashboardPage />
    </ProtectedRoute>
  }
/>

<Route
    path="/application-tracker"
    element={<ApplicationTrackerPage />}
/>

<Route
  path="/progress"
  element={
    <ProtectedRoute>
      <CareerProgressPage />
    </ProtectedRoute>
  }
/>

<Route
  path="/resources"
  element={
    <ProtectedRoute>
      <LearningResourcesPage />
    </ProtectedRoute>
  }
/>

       <Route
  path="/internship-analyzer"
  element={<InternshipAnalyzerPage />}
/>

     <Route
    path="/project-intelligence"
    element={<ProjectIntelligencePage />}
/>

        <Route
  path="/roadmap"
  element={
    <ProtectedRoute>
      <RoadmapPage />
    </ProtectedRoute>
  }
/>

<Route
  path="/interview"
  element={
    <ProtectedRoute>
      <InterviewQuestionsPage />
    </ProtectedRoute>
  }
/>

<Route
  path="/jobs"
  element={
    <ProtectedRoute>
      <JobRecommendationsPage />
    </ProtectedRoute>
  }
/>

<Route
  path="/internships"
  element={
    <ProtectedRoute>
  <InternshipPage />
    </ProtectedRoute>
  }
/>


<Route
    path="/profile/edit"
    element={
        <ProfileEditPage />
    }
/>



<Route
  path="/career-intelligence"
  element={
    <ProtectedRoute>
      <CareerIntelligencePage />
    </ProtectedRoute>
  }
/>


        <Route
          path="/resume"
          element={
            <ProtectedRoute>
              <ResumeAnalyzer />
            </ProtectedRoute>
          }
        />

        <Route
          path="/resume-analysis"
          element={
            <ProtectedRoute>
              <ResumeAnalysisPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/ai-mentor"
          element={
            <ProtectedRoute>
              <AICareerMentor />
            </ProtectedRoute>
          }
        />
  <Route
  path="/company-readiness"
  element={
    <ProtectedRoute>
      <CompanyReadinessPage />
    </ProtectedRoute>
  }
/>
      </Routes>
    </BrowserRouter>
  );
}

export default AppRoutes;