import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Sidebar from "../../components/Sidebar";
import { getProjectById } from "../../services/projectIntelligenceService";

import ReportHero from "./components/ReportHero";

import ScoreOverview from "./components/ScoreOverview";

import EngineeringOverview from "./components/EngineeringOverview";

import EngineeringReview from "./components/EngineeringReview";

import EngineeringInsights from "./components/EngineeringInsights";

import CareerImpactSection from "./components/CareerImpactSection";

import FinalRecommendation from "./components/FinalRecommendation";

export default function ProjectReportPage() {

    const { projectId } = useParams();

    const [project, setProject] = useState(null);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadProject();

    }, [projectId]);

    const loadProject = async () => {

        try {

            const data = await getProjectById(projectId);

            console.log("Project Report", data);

            setProject(data);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    };

    if (loading) {

        return (
            <div className="min-h-screen flex items-center justify-center">
                Loading Project Intelligence...
            </div>
        );

    }

    if (!project) {

        return (
            <div className="min-h-screen flex items-center justify-center">
                Project not found.
            </div>
        );

    }

    return (

<div className="flex min-h-screen overflow-x-hidden bg-[#F7F7FB]">
    
            <Sidebar />

    <main
    className="
        ml-[235px]
        flex-1
        min-w-0
        overflow-x-hidden
        bg-[#F7F8FC]
    "
>
    <div
        className="
            mx-auto
            w-full
            max-w-[1700px]
            space-y-10
            px-10
            py-8
        "
    >

                  <ReportHero project={project} />
                  <ScoreOverview project={project} />
                  <EngineeringOverview project={project} />
                  <EngineeringReview project={project} />
                  <EngineeringInsights project={project} />
                  <CareerImpactSection project={project} />
                  <FinalRecommendation project={project} />

                </div>

            </main>

        </div>

    );

}