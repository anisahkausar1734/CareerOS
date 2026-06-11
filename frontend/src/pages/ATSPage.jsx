import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function ATSPage() {
  const [atsData, setAtsData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchATS();
  }, []);

  const fetchATS = async () => {
    try {
      const email = localStorage.getItem("email");

const response = await axios.get(
  `http://localhost:8080/api/ats/${email}`
);

      setAtsData(response.data);
    } catch (error) {
      console.error("ATS Error:", error);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
  return (
    <>
      <Navbar />
      <h2 className="text-center mt-10">
        Loading ATS Analysis...
      </h2>
    </>
  );
}

 return (
  <>
    <Navbar />

    <div className="p-6 max-w-5xl mx-auto">

      <h1 className="text-3xl font-bold mb-6">
        ATS Resume Analysis
      </h1>

      {/* ATS Score */}
      <div className="bg-blue-100 p-5 rounded-lg shadow mb-6">
        <h2 className="text-xl font-semibold">
          ATS Score
        </h2>

        <p className="text-5xl font-bold mt-3">
          {atsData?.atsScore}/100
        </p>
      </div>

      {/* Feedback */}
      <div className="bg-white shadow rounded-lg p-5 mb-6">
        <h2 className="text-xl font-semibold mb-2">
          Feedback
        </h2>

        <p>{atsData?.feedback}</p>
      </div>

      {/* Missing Keywords */}
      <div className="bg-white shadow rounded-lg p-5 mb-6">
        <h2 className="text-xl font-semibold mb-3">
          Missing Keywords
        </h2>

        <ul className="list-disc ml-6">
          {atsData?.missingKeywords?.map((keyword, index) => (
            <li key={index}>{keyword}</li>
          ))}
        </ul>
      </div>

      {/* Improvements */}
      <div className="bg-white shadow rounded-lg p-5 mb-6">
        <h2 className="text-xl font-semibold mb-3">
          Improvements
        </h2>

        {atsData?.improvements?.map((item, index) => (
          <div
            key={index}
            className="border rounded p-3 mb-3"
          >
            <p>
              <strong>Issue:</strong> {item.issue}
            </p>

            <p>
              <strong>Suggestion:</strong> {item.suggestion}
            </p>
          </div>
        ))}
      </div>

      {/* Section Status */}
      <div className="bg-white shadow rounded-lg p-5">
        <h2 className="text-xl font-semibold mb-3">
          Section Status
        </h2>

        {atsData?.sectionStatus?.map((section, index) => (
          <div
            key={index}
            className="flex justify-between border-b py-2"
          >
            <span>{section.sectionName}</span>

            <span>
              {section.present ? "✅ Present" : "❌ Missing"}
            </span>
          </div>
        ))}
      </div>

    </div>
     </>
  );
}

export default ATSPage;