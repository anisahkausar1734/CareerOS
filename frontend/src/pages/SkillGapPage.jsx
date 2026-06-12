import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function SkillGapPage() {

  const [data, setData] =
    useState(null);

  const [loading, setLoading] =
    useState(true);

  useEffect(() => {
    loadSkillGap();
  }, []);

  const loadSkillGap = async () => {

    try {

      const email =
        localStorage.getItem("email");

      const response =
        await axios.get(
          `http://localhost:8080/api/career/skill-gap/${email}`
        );

      setData(response.data);

    } catch (error) {

      console.log(error);

    } finally {

      setLoading(false);
    }
  };

  if (loading) {

    return (
      <h2 className="text-center mt-10">
        Loading Skill Gap Analysis...
      </h2>
    );
  }

  return (
    <>
      <Navbar />

      <div className="p-8 bg-gray-100 min-h-screen">

        <div className="max-w-5xl mx-auto">

          <h1 className="text-3xl font-bold mb-6">
            Skill Gap Analysis
          </h1>

          <div className="bg-white rounded-xl shadow p-6 mb-6">

            <h2 className="text-xl font-semibold">
              Target Role
            </h2>

            <p className="mt-2">
              {data.targetRole}
            </p>

          </div>

          <div className="bg-green-100 rounded-xl p-6 mb-6">

            <h2 className="text-xl font-semibold">
              Skill Match
            </h2>

            <p className="text-4xl font-bold mt-2">
              {data.skillMatchPercentage}%
            </p>

          </div>

          <div className="bg-white rounded-xl shadow p-6 mb-6">

            <h2 className="text-xl font-semibold mb-3">
              Current Skills
            </h2>

            <div className="flex flex-wrap gap-2">

              {data.currentSkills?.map(
                (skill, index) => (
                  <span
                    key={index}
                    className="bg-blue-100 px-3 py-1 rounded"
                  >
                    {skill}
                  </span>
                )
              )}

            </div>

          </div>

          <div className="bg-white rounded-xl shadow p-6">

            <h2 className="text-xl font-semibold mb-3">
              Missing Skills
            </h2>

            <div className="flex flex-wrap gap-2">

              {data.missingSkills?.map(
                (skill, index) => (
                  <span
                    key={index}
                    className="bg-red-100 px-3 py-1 rounded"
                  >
                    {skill}
                  </span>
                )
              )}

            </div>

          </div>

        </div>

      </div>
    </>
  );
}

export default SkillGapPage;
