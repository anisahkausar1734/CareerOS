import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function JobRecommendationsPage() {

  const [data, setData] = useState(null);

  useEffect(() => {
    loadRecommendations();
  }, []);

  const loadRecommendations = async () => {

    try {

      const email =
        localStorage.getItem("email");

      const response =
        await axios.get(
          `http://localhost:8080/api/jobs/${email}`
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
            Job Recommendations
          </h1>

          <div className="bg-white p-6 rounded-xl shadow mb-6">

            <h2 className="text-xl font-semibold">
              Target Role
            </h2>

            <p className="mt-2 text-lg">
              {data.targetRole}
            </p>

          </div>

          <div className="bg-white p-6 rounded-xl shadow">

            <h2 className="text-xl font-semibold mb-4">
              Recommended Jobs
            </h2>

            <div className="grid md:grid-cols-2 gap-4">

              {data.recommendedJobs?.map(
                (job, index) => (
                  <div
                    key={index}
                    className="p-4 bg-blue-100 rounded-lg"
                  >
                    {job}
                  </div>
                )
              )}

            </div>

          </div>

        </div>

      </div>
    </>
  );
}

export default JobRecommendationsPage;