import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

import ScoreCard from "../components/ScoreCard";
import Navbar from "../components/Navbar";

function DashboardPage() {
  const fullName = localStorage.getItem("fullName");
  const email = localStorage.getItem("email");

  const [atsScore, setAtsScore] = useState(0);
  const [internshipScore, setInternshipScore] = useState(0);

  useEffect(() => {
    loadDashboardData();
  }, []);

  const loadDashboardData = async () => {
    try {
      const atsResponse = await axios.get(
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

    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <Navbar />

      <div className="p-6">

        <h1 className="text-3xl font-bold">
          Welcome {fullName}
        </h1>

        <p>{email}</p>

        <hr className="my-4" />

        <h2 className="text-2xl font-semibold mb-4">
          CareerOS Dashboard
        </h2>

        <ScoreCard
          title="ATS Score"
          score={atsScore}
        />

        <ScoreCard
          title="Internship Readiness"
          score={internshipScore}
        />

        <ScoreCard
          title="Project Analysis"
          score="Analyze"
        />

        <div className="mt-6 flex gap-4">

          <Link
            to="/ats"
            className="bg-blue-500 text-white px-4 py-2 rounded"
          >
            ATS Analysis
          </Link>

          <Link
            to="/internship"
            className="bg-green-500 text-white px-4 py-2 rounded"
          >
            Internship Readiness
          </Link>

          <Link
            to="/projects"
            className="bg-purple-500 text-white px-4 py-2 rounded"
          >
            Project Analysis
          </Link>

        </div>

      </div>
    </>
  );
}

export default DashboardPage;