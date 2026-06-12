import { useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function InterviewQuestionsPage() {

  const [role, setRole] =
    useState("Backend Developer");

  const [level, setLevel] =
    useState("Beginner");

  const [questions, setQuestions] =
    useState("");

  const [loading, setLoading] =
    useState(false);

  const generateQuestions =
    async () => {

      try {

        setLoading(true);

        const response =
          await axios.post(
            "http://localhost:8080/api/interview/generate",
            {
              role,
              level
            }
          );

        setQuestions(
          response.data.questions
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

        <div className="max-w-5xl mx-auto">

          <h1 className="text-3xl font-bold mb-6">
            AI Interview Questions
          </h1>

          <div className="bg-white p-6 rounded-xl shadow mb-6">

            <label className="block mb-2 font-semibold">
              Role
            </label>

            <input
              type="text"
              value={role}
              onChange={(e) =>
                setRole(e.target.value)
              }
              className="border p-2 w-full rounded mb-4"
            />

            <label className="block mb-2 font-semibold">
              Level
            </label>

            <select
              value={level}
              onChange={(e) =>
                setLevel(e.target.value)
              }
              className="border p-2 w-full rounded"
            >
              <option>
                Beginner
              </option>

              <option>
                Intermediate
              </option>

              <option>
                Advanced
              </option>

            </select>

            <button
              onClick={generateQuestions}
              className="bg-blue-600 text-white px-4 py-2 rounded mt-4"
            >
              Generate Questions
            </button>

          </div>

          {loading && (

            <div className="bg-white p-6 rounded-xl shadow">
              Generating Questions...
            </div>

          )}

          {questions && (

            <div className="bg-white p-6 rounded-xl shadow">

              <h2 className="text-2xl font-semibold mb-4">
                Questions
              </h2>

              <pre className="whitespace-pre-wrap">
                {questions}
              </pre>

            </div>

          )}

        </div>

      </div>
    </>
  );
}

export default InterviewQuestionsPage;