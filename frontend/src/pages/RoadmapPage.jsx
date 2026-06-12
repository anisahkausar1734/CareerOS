import { useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function RoadmapPage() {

  const [targetRole, setTargetRole] =
    useState("");

  const [roadmap, setRoadmap] =
    useState("");

  const [loading, setLoading] =
    useState(false);

  const generateRoadmap = async () => {

    if (!targetRole.trim()) {
      return;
    }

    setLoading(true);

    try {

      const response =
        await axios.post(
          "http://localhost:8080/api/roadmap",
          {
            targetRole
          }
        );

      setRoadmap(
        response.data.roadmap
      );

    } catch (error) {

      console.log(error);

    } finally {

      setLoading(false);
    }
  };

  return (
    <>
      <Navbar />

      <div className="p-8 bg-gray-100 min-h-screen">

        <div className="max-w-4xl mx-auto">

          <h1 className="text-3xl font-bold mb-2">
            Career Roadmap Generator
          </h1>

          <p className="text-gray-600 mb-6">
            Generate an AI roadmap for your dream role.
          </p>

          <div className="bg-white p-6 rounded-xl shadow">

            <input
              type="text"
              value={targetRole}
              onChange={(e) =>
                setTargetRole(
                  e.target.value
                )
              }
              placeholder="Example: Java Backend Developer"
              className="w-full border p-3 rounded-lg mb-4"
            />

            <button
              onClick={generateRoadmap}
              className="bg-blue-600 text-white px-6 py-3 rounded-lg"
            >
              Generate Roadmap
            </button>

          </div>

          {loading && (

            <div className="mt-6 bg-white p-6 rounded-xl shadow">

              Generating roadmap...

            </div>

          )}

          {roadmap && (

            <div className="mt-6 bg-white p-6 rounded-xl shadow">

              <h2 className="text-2xl font-semibold mb-4">
                Your Roadmap
              </h2>

              <div className="whitespace-pre-line leading-8">
                {roadmap}
              </div>

            </div>

          )}

        </div>

      </div>
    </>
  );
}

export default RoadmapPage;