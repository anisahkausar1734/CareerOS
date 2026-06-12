import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function ResumeAnalysisPage() {

  const [analysis, setAnalysis] =
    useState(null);

  const [loading, setLoading] =
    useState(true);

  useEffect(() => {
    loadAnalysis();
  }, []);

  const loadAnalysis = async () => {

    try {

      const email =
        localStorage.getItem("email");

      const response =
        await axios.get(
          `http://localhost:8080/api/resumes/analyze/${email}`
        );

      setAnalysis(response.data);

    } catch (error) {

      console.log(error);

    } finally {

      setLoading(false);
    }
  };

  if (loading) {

    return (
      <h2 className="text-center mt-10">
        Loading Resume Analysis...
      </h2>
    );
  }

  return (
    <>
      <Navbar />

      <div className="p-6 max-w-5xl mx-auto">

        <h1 className="text-3xl font-bold mb-6">
          Resume Analysis
        </h1>

        <div className="bg-blue-100 p-6 rounded-lg shadow mb-6">

          <h2 className="text-xl font-semibold">
            Resume Score
          </h2>

          <p className="text-5xl font-bold mt-3">
            {analysis?.resumeScore}/100
          </p>

        </div>

        <div className="bg-white shadow rounded-lg p-5 mb-6">

          <h2 className="text-xl font-semibold mb-3">
            Education
          </h2>

          <p>
            {analysis?.education}
          </p>

        </div>

        <div className="bg-white shadow rounded-lg p-5 mb-6">

          <h2 className="text-xl font-semibold mb-3">
            Projects
          </h2>

          <p>
            Total Projects:
            {" "}
            {analysis?.projectCount}
          </p>

        </div>

        <div className="bg-white shadow rounded-lg p-5">

          <h2 className="text-xl font-semibold mb-3">
            Skills
          </h2>

          <div className="flex flex-wrap gap-2">

            {analysis?.skills?.map(
              (skill, index) => (
                <span
                  key={index}
                  className="bg-green-100 px-3 py-1 rounded"
                >
                  {skill}
                </span>
              )
            )}

          </div>

        </div>

      </div>
    </>
  );
}

export default ResumeAnalysisPage;
