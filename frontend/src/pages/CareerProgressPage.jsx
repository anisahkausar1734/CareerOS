import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function CareerProgressPage() {

  const [data, setData] = useState(null);

  useEffect(() => {
    loadProgress();
  }, []);

  const loadProgress = async () => {

    try {

      const email =
        localStorage.getItem("email");

      const response =
        await axios.get(
          `http://localhost:8080/api/progress/${email}`
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
            Career Progress Tracker
          </h1>

          <div className="bg-white p-6 rounded-xl shadow mb-6">

            <h2 className="text-xl font-semibold mb-4">
              Overall Progress
            </h2>

            <div className="w-full bg-gray-300 rounded-full h-6">

              <div
                className="bg-green-500 h-6 rounded-full"
                style={{
                  width:
                    `${data.overallProgress}%`
                }}
              />

            </div>

            <p className="mt-3 font-bold">
              {data.overallProgress}%
            </p>

          </div>

          <div className="grid md:grid-cols-3 gap-4">

            <div className="bg-blue-100 p-6 rounded-xl">

              <h2 className="font-semibold">
                Resources Completed
              </h2>

              <p className="text-3xl font-bold">
                {data.resourcesCompleted}
              </p>

            </div>

            <div className="bg-purple-100 p-6 rounded-xl">

              <h2 className="font-semibold">
                Projects Completed
              </h2>

              <p className="text-3xl font-bold">
                {data.projectsCompleted}
              </p>

            </div>

            <div className="bg-yellow-100 p-6 rounded-xl">

              <h2 className="font-semibold">
                Certifications Completed
              </h2>

              <p className="text-3xl font-bold">
                {data.certificationsCompleted}
              </p>

            </div>

          </div>

        </div>

      </div>
    </>
  );
}

export default CareerProgressPage;