import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

import ScoreCard from "../components/ScoreCard";
import Navbar from "../components/Navbar";

function DashboardPage() {

  const fullName =
    localStorage.getItem("fullName");

  const email =
    localStorage.getItem("email");

  const [atsScore, setAtsScore] =
    useState(0);

  const [internshipScore,
    setInternshipScore] =
    useState(0);

  const [resumeScore,
    setResumeScore] =
    useState(0);

  useEffect(() => {
    loadDashboardData();
  }, []);

  const loadDashboardData = async () => {

    try {

      const atsResponse =
        await axios.get(
          `http://localhost:8080/api/ats/${email}`
        );

      setAtsScore(
        atsResponse.data.atsScore || 0
      );

      const internshipResponse =
        await axios.get(
          `http://localhost:8080/api/internship/${email}`
        );

      setInternshipScore(
        internshipResponse.data.readinessScore || 0
      );

      const resumeResponse =
        await axios.get(
          `http://localhost:8080/api/resumes/analyze/${email}`
        );

      setResumeScore(
        resumeResponse.data.resumeScore || 0
      );

      

    } catch (error) {

      console.log(error);
    }

  };

  return (
    <>
      <Navbar />

      <div className="p-8 bg-gray-100 min-h-screen">

        <div className="bg-gradient-to-r from-blue-600 to-purple-600 text-white p-8 rounded-xl shadow-lg mb-8">

          <h1 className="text-4xl font-bold mb-2">
            Welcome {fullName}
          </h1>

          <p className="text-lg">
            {email}
          </p>

          <p className="mt-3 text-blue-100">
            Track your career growth, resume strength and internship readiness.
          </p>

        </div>

        <h2 className="text-2xl font-semibold mb-6">
          CareerOS Dashboard
        </h2>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">

          <ScoreCard
            title="ATS Score"
            score={atsScore}
          />

          <ScoreCard
            title="Internship Readiness"
            score={internshipScore}
          />

          <ScoreCard
            title="Resume Score"
            score={resumeScore}
          />

        </div>

        <div className="mt-10">

          <h2 className="text-2xl font-semibold mb-4">
            Quick Actions
          </h2>

          <div className="flex flex-wrap gap-4">

            <Link
              to="/ats"
              className="bg-blue-500 hover:bg-blue-600 text-white px-5 py-3 rounded-lg shadow"
            >
              ATS Analysis
            </Link>

            <Link
              to="/internship"
              className="bg-green-500 hover:bg-green-600 text-white px-5 py-3 rounded-lg shadow"
            >
              Internship Readiness
            </Link>

            <Link
              to="/projects"
              className="bg-purple-500 hover:bg-purple-600 text-white px-5 py-3 rounded-lg shadow"
            >
              Project Analysis
            </Link>

            <Link
              to="/resume-analysis"
              className="bg-orange-500 hover:bg-orange-600 text-white px-5 py-3 rounded-lg shadow"
            >
              Resume Analysis
            </Link>

            <Link
  to="/roadmap"
  className="bg-cyan-500 hover:bg-cyan-600 text-white px-5 py-3 rounded-lg shadow"
>
  Career Roadmap
</Link>

            <Link
              to="/resume"
              className="bg-indigo-500 hover:bg-indigo-600 text-white px-5 py-3 rounded-lg shadow"
            >
              Resume Upload
            </Link>

            <Link
  to="/ai-mentor"
  className="bg-pink-500 hover:bg-pink-600 text-white px-5 py-3 rounded-lg shadow"
>
  AI Career Mentor
</Link>

<Link
  to="/skill-gap"
  className="bg-red-500 hover:bg-red-600 text-white px-5 py-3 rounded-lg shadow"
>
  Skill Gap Analysis
</Link>

          </div>

        </div>

      </div>
    </>
  );
}

export default DashboardPage;