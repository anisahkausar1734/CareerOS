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


function AppRoutes() {
  return (
    
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
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
  path="/projects"
  element={
    <ProtectedRoute>
      <ProjectAnalysisPage />
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
      </Routes>
    </BrowserRouter>
    
  );
  <Route
  path="/internship"
  element={
    <ProtectedRoute>
      <InternshipPage />
    </ProtectedRoute>
  }
/>
}

export default AppRoutes;