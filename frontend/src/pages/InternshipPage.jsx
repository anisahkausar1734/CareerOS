import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function InternshipPage() {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetchReadiness();
  }, []);

  const fetchReadiness = async () => {
    try {
      const email = localStorage.getItem("email");

      const response = await axios.get(
        `http://localhost:8080/api/internship/${email}`
      );

      setData(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  if (!data) {
    return (
      <>
        <Navbar />
        <h2 className="p-6">Loading...</h2>
      </>
    );
  }

  return (
    <>
      <Navbar />

      <div className="p-6 max-w-5xl mx-auto">

        <h1 className="text-3xl font-bold mb-6">
          Internship Readiness
        </h1>

        <div className="bg-green-100 p-5 rounded-lg shadow mb-6">
          <h2 className="text-xl font-semibold">
            Readiness Score
          </h2>

          <p className="text-5xl font-bold mt-3">
            {data.readinessScore}/100
          </p>
        </div>

        <div className="bg-white shadow rounded-lg p-5 mb-6">
          <h2 className="text-xl font-semibold">
            Status
          </h2>

          <p className="mt-2">
            {data.status}
          </p>
        </div>

        <div className="bg-white shadow rounded-lg p-5 mb-6">
          <h2 className="text-xl font-semibold mb-3">
            Strengths
          </h2>

          <ul className="list-disc ml-6">
            {data.strengths?.map((item, index) => (
              <li key={index}>{item}</li>
            ))}
          </ul>
        </div>

        <div className="bg-white shadow rounded-lg p-5">
          <h2 className="text-xl font-semibold mb-3">
            Weaknesses
          </h2>

          <ul className="list-disc ml-6">
            {data.weaknesses?.map((item, index) => (
              <li key={index}>{item}</li>
            ))}
          </ul>
        </div>

      </div>
    </>
  );
}

export default InternshipPage;