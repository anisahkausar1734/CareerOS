import { useState } from "react";
import axios from "axios";

import Sidebar from "../components/Sidebar";
import Topbar from "../components/Topbar";

function InternshipAnalyzerPage() {

  const [targetRole, setTargetRole] =
    useState("");

  const [targetCompany, setTargetCompany] =
    useState("");

  const [customRequirements, setCustomRequirements] =
    useState("");

  const [loading, setLoading] =
    useState(false);

  const [result, setResult] =
    useState(null);

  const analyzeInternship =
    async () => {

      if (!targetRole) {

        alert(
          "Target Role is required"
        );

        return;
      }

      try {

        setLoading(true);

        const email =
          localStorage.getItem(
            "email"
          );

      const response =
  await axios.post(
    "http://localhost:8080/api/internship-analysis/analyze",
    {
      email,
      targetRole,
      targetCompany,
      customRequirements
    }
  );

console.log(
  "INTERNSHIP RESPONSE:",
  response.data
);

setResult(response.data);

      } catch (error) {

        console.log(error);

        alert(
          "Failed to analyze internship readiness"
        );

      } finally {

        setLoading(false);
      }
    };

  const resumeFileName =
    localStorage.getItem(
      "resumeFileName"
    );

  return (
    <>
      <Sidebar />

      <div
        className="
          ml-72
          min-h-screen
          bg-[#F5F3F8]
        "
      >


        <div className="p-8">

          {/* Header */}

         <div
  className="
    bg-gradient-to-r
    from-[#7367F0]
    to-[#9D8DFF]
    text-white
    rounded-3xl
    p-8
    mb-8
  "
>

  <p
    className="
      uppercase
      tracking-wider
      text-sm
      mb-2
      text-white/80
      font-medium
    "
  >
    Internship Intelligence
  </p>

  <h1
    className="
      text-4xl
      font-bold
      mb-3
    "
  >
    🎯 Internship Analyzer
  </h1>

  <p
    className="
      text-white/90
      max-w-3xl
    "
  >
    Analyze your resume against internship
    requirements, identify missing skills,
    measure hiring probability and discover
    exactly what recruiters expect.
  </p>

  <div
    className="
      flex
      flex-wrap
      gap-3
      mt-5
    "
  >

    <span
      className="
        bg-white/20
        px-4
        py-2
        rounded-xl
        text-sm
      "
    >
      🎯 Skill Match Analysis
    </span>

    <span
      className="
        bg-white/20
        px-4
        py-2
        rounded-xl
        text-sm
      "
    >
      📈 Hiring Probability
    </span>

    <span
      className="
        bg-white/20
        px-4
        py-2
        rounded-xl
        text-sm
      "
    >
      🚀 Internship Readiness
    </span>

  </div>

</div>

          {/* Current Resume */}

          <div
            className="
              bg-white
              rounded-3xl
              border
              border-[#E8E6EF]
              p-8
              mb-8
            "
          >

            <h2
              className="
                text-xl
                font-semibold
                mb-4
              "
            >
              Current Resume
            </h2>

            <p className="text-gray-600">

              {resumeFileName
                || "Uploaded Resume"}

            </p>

            <p
              className="
                text-sm
                text-gray-400
                mt-2
              "
            >
              Uses the resume
              uploaded in Resume Center
            </p>

          </div>

          {/* Input Card */}

          <div
            className="
              bg-white
              rounded-3xl
              border
              border-[#E8E6EF]
              p-8
              mb-8
            "
          >

            <h2
              className="
                text-2xl
                font-semibold
                mb-6
              "
            >
              Internship Analysis
            </h2>

            <div className="mb-6">

              <label
                className="
                  block
                  font-medium
                  mb-2
                "
              >
                Target Internship Role *
              </label>

              <input
                type="text"
                value={targetRole}
                onChange={(e) =>
                  setTargetRole(
                    e.target.value
                  )
                }
                placeholder="AI Engineer Intern"
                className="
                  w-full
                  border
                  rounded-xl
                  p-4
                "
              />

            </div>

            <div className="mb-6">

              <label
                className="
                  block
                  font-medium
                  mb-2
                "
              >
                Target Company
              </label>

              <input
                type="text"
                value={targetCompany}
                onChange={(e) =>
                  setTargetCompany(
                    e.target.value
                  )
                }
                placeholder="Google"
                className="
                  w-full
                  border
                  rounded-xl
                  p-4
                "
              />

            </div>

            <div className="mb-6">

              <label
                className="
                  block
                  font-medium
                  mb-2
                "
              >
                Custom Requirements
              </label>

              <textarea
                rows="5"
                value={customRequirements}
                onChange={(e) =>
                  setCustomRequirements(
                    e.target.value
                  )
                }
                placeholder="LangChain, RAG, Vector Databases..."
                className="
                  w-full
                  border
                  rounded-xl
                  p-4
                "
              />

            </div>

            <button
              onClick={
                analyzeInternship
              }
              disabled={loading}
              className="
                bg-[#7367F0]
                text-white
                px-8
                py-3
                rounded-xl
              "
            >

              {loading
                ? "Analyzing..."
                : "Analyze Internship Readiness"}

            </button>

          </div>

          {result && (

            <>

            <div className="bg-white p-6 mb-6 rounded-xl">
  <pre>
    {JSON.stringify(
      result,
      null,
      2
    )}
  </pre>
</div>
              {/* Metrics */}

              <div
                className="
                  grid
                  md:grid-cols-5
                  gap-6
                  mb-8
                "
              >

                <MetricCard
                  title="Internship Score"
                  value={
                    result.internshipScore
                  }
                />

                <MetricCard
                  title="Hiring Probability"
                  value={
                    result.hiringProbability
                  }
                />

                <MetricCard
                  title="Role Alignment"
                  value={
                    result.roleAlignment
                  }
                />

                <MetricCard
                  title="Project Strength"
                  value={
                    result.projectStrength
                  }
                />

                <MetricCard
                  title="Skill Readiness"
                  value={
                    result.skillReadiness
                  }
                />

              </div>

              {/* Verdict */}

              <div
                className="
                  bg-white
                  rounded-3xl
                  border
                  border-[#E8E6EF]
                  p-8
                  mb-8
                "
              >

                <h2
                  className="
                    text-xl
                    font-semibold
                    mb-3
                  "
                >
                  Verdict
                </h2>

                <p
                  className="
                    text-2xl
                    font-bold
                    text-[#7367F0]
                  "
                >
                  {result.verdict}
                </p>

              </div>

              <SectionCard
                title="Strengths"
                items={
                  result.strengths
                }
              />

              <SectionCard
                title="Missing Skills"
                items={
                  result.missingSkills
                }
              />

              <SectionCard
                title="Missing Projects"
                items={
                  result.missingProjects
                }
              />

              <SectionCard
                title="Missing Certifications"
                items={
                  result.missingCertifications
                }
              />

              <SectionCard
                title="Missing Tools"
                items={
                  result.missingTools
                }
              />

              <SectionCard
                title="Company Expectations"
                items={
                  result.companyExpectations
                }
              />

              <SectionCard
                title="Recommendations"
                items={
                  result.recommendations
                }
              />

            </>
          )}

        </div>

      </div>
    </>
  );
}

function MetricCard({
  title,
  value
}) {

  return (
    <div
      className="
        bg-white
        rounded-3xl
        border
        border-[#E8E6EF]
        p-6
      "
    >

      <p className="text-gray-500">
        {title}
      </p>

      <h3
        className="
          text-3xl
          font-bold
          mt-3
          text-[#7367F0]
        "
      >
        {value}%
      </h3>

    </div>
  );
}

function SectionCard({
  title,
  items
}) {

  return (
    <div
      className="
        bg-white
        rounded-3xl
        border
        border-[#E8E6EF]
        p-8
        mb-8
      "
    >

      <h2
        className="
          text-2xl
          font-semibold
          mb-6
        "
      >
        {title}
      </h2>

      <div
        className="
          flex
          flex-wrap
          gap-3
        "
      >

        {items?.map(
          (item, index) => (

            <span
              key={index}
              className="
                bg-[#F5F3F8]
                px-4
                py-2
                rounded-xl
              "
            >
              {item}
            </span>
          )
        )}

      </div>

    </div>
  );
}

export default InternshipAnalyzerPage;