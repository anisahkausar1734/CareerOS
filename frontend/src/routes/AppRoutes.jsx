import { BrowserRouter, Routes, Route } from "react-router-dom";

import ProtectedRoute from "../components/ProtectedRoute";

import LoginPage from "../pages/LoginPage";
import RegisterPage from "../pages/RegisterPage";
import DashboardPage from "../pages/DashboardPage";
import ProfilePage from "../pages/ProfilePage";
import ATSPage from "../pages/ATSPage";
import InternshipPage from "../pages/InternshipPage";
import ProjectAnalysisPage from "../pages/ProjectAnalysisPage";
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
              <ProfilePage />
            </ProtectedRoute>
          }
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
  path="/skill-gap"
  element={
    <ProtectedRoute>
      <SkillGapPage />
    </ProtectedRoute>
  }
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
          path="/internship"
          element={
            <ProtectedRoute>
              <InternshipPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/projects"
          element={
            <ProtectedRoute>
              <ProjectAnalysisPage />
            </ProtectedRoute>
          }
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