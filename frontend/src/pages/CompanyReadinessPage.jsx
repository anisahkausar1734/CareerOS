import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function CompanyReadinessPage() {

  const [data, setData] = useState(null);

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {

    try {

      const email =
        localStorage.getItem("email");

      const response =
        await axios.get(
          `http://localhost:8080/api/career/company-readiness/${email}`
        );

      setData(response.data);

    } catch (error) {

      console.log(error);
    }
  };

  if (!data) {

    return (
      <h2 className="text-center mt-10">
        Loading...
      </h2>
    );
  }

  return (
    <>
      <Navbar />

      <div className="p-8 bg-gray-100 min-h-screen">

        <div className="max-w-5xl mx-auto">

          <h1 className="text-3xl font-bold mb-6">
            Company Readiness
          </h1>

          <div className="bg-white p-6 rounded-xl shadow mb-6">

            <h2 className="text-xl font-semibold">
              Dream Company
            </h2>

            <p className="mt-2 text-lg">
              {data.company}
            </p>

          </div>

          <div className="bg-blue-100 p-6 rounded-xl mb-6">

            <h2 className="text-xl font-semibold">
              Readiness Score
            </h2>

            <p className="text-4xl font-bold">
              {data.readinessPercentage}%
            </p>

          </div>

          <div className="bg-green-100 p-6 rounded-xl mb-6">

            <h2 className="text-xl font-semibold mb-3">
              Strengths
            </h2>

            <div className="flex flex-wrap gap-2">

              {data.strengths?.map(
                (skill, index) => (
                  <span
                    key={index}
                    className="bg-green-500 text-white px-3 py-1 rounded"
                  >
                    {skill}
                  </span>
                )
              )}

            </div>

          </div>

          <div className="bg-red-100 p-6 rounded-xl mb-6">

            <h2 className="text-xl font-semibold mb-3">
              Missing Skills
            </h2>

            <div className="flex flex-wrap gap-2">

              {data.missingSkills?.map(
                (skill, index) => (
                  <span
                    key={index}
                    className="bg-red-500 text-white px-3 py-1 rounded"
                  >
                    {skill}
                  </span>
                )
              )}

            </div>

          </div>

          <div className="bg-yellow-100 p-6 rounded-xl">

            <h2 className="text-xl font-semibold">
              Feedback
            </h2>

            <p className="mt-2">
              {data.feedback}
            </p>

          </div>

        </div>

      </div>
    </>
  );
}

export default CompanyReadinessPage;