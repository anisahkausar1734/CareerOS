import { useEffect, useState } from "react";

import HeroSection from "./components/HeroSection";
import AnalyzeProjectCard from "./components/AnalyzeProjectCard";
import ProjectStats from "./components/ProjectStats";
import ProjectLibrary from "./components/ProjectLibrary";
import Sidebar from "../../components/Sidebar";
import { reAnalyzeProject } from "../../services/projectIntelligenceService";
import { useNavigate } from "react-router-dom";

import {
    createAndAnalyzeProject,
    getProjects

} from "../../services/projectIntelligenceService";

export default function ProjectIntelligencePage() {

    const navigate = useNavigate();

    const [projects, setProjects] = useState([]);

    const [analysisResult, setAnalysisResult] =
    useState(null);

    const [loading, setLoading] = useState(false);

    const [analyzing, setAnalyzing] = useState(false);

    const loadProjects = async () => {

    try {

        setLoading(true);

        const email =
    localStorage.getItem("email");

if (!email) {

    alert("Please login again.");

    return;

}

        if (!email) {
            return;
        }

        const data = await getProjects(email);

        setProjects(data);

    } catch (error) {

        console.error("Failed to load projects", error);

    } finally {

        setLoading(false);

    }

};

const handleReAnalyze = async (projectId) => {

    try {

        setAnalyzing(true);

        const analysis = await reAnalyzeProject(projectId);

        console.log("Re-analysis:", analysis);

        await loadProjects();

        navigate(`/project-intelligence/${projectId}`);

    } catch (error) {

        console.error(error);

        alert("Re-analysis failed.");

    } finally {

        setAnalyzing(false);

    }

};

const handleAnalyzeProject = async (data) => {
    try {
        setAnalyzing(true);

        const email = localStorage.getItem("email");

        if (!email) {
            alert("Please login again.");
            return;
        }

        const analysis = await createAndAnalyzeProject({
            email,
            githubUrl: data.githubUrl,
            liveUrl: data.liveUrl,
        });

        console.log("Analysis Response:", analysis);

        setAnalysisResult(analysis);

        alert("Project analyzed successfully!");

        await loadProjects();

    } catch (error) {
        console.error("Project Analysis Error:", error);

        alert(
            error.response?.data?.message ||
            "Project analysis failed."
        );

    } finally {
        setAnalyzing(false);
    }
};

   
useEffect(() => {

    loadProjects();

}, []);

   return (

    <div className="min-h-screen bg-[#F7F7FB] flex">

        <Sidebar />

        <main
            className="
                ml-[235px]
                min-h-screen
                flex-1
                bg-[#F7F8FC]
                overflow-y-auto
            "
        >

          <div
    className="
        w-full
        max-w-[1700px]
        mx-auto
        px-10
        xl:px-14
        pt-8
        pb-12
        space-y-10
    "
>
                {/* ================= HERO ================= */}

                <HeroSection />

                {/* ================= ANALYZE PROJECT ================= */}

                <AnalyzeProjectCard
                    analyzing={analyzing}
                    onAnalyze={handleAnalyzeProject}
                />

                {/* ================= PROJECT STATS ================= */}

                <ProjectStats
                    projects={projects}
                />

                {/* ================= PROJECT LIBRARY ================= */}

               <ProjectLibrary
    projects={projects}
    loading={loading}
    onReAnalyze={handleReAnalyze}
/>

            </div>

        </main>

    </div>

);

}