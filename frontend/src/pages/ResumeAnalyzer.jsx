import { useState, useEffect } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";
import Topbar from "../components/Topbar";

function ResumeAnalyzer() {

  const email =
    localStorage.getItem("email");

  const [file, setFile] =
    useState(null);

  const [resume, setResume] =
    useState(null);

  useEffect(() => {
    loadResume();
  }, []);

  const loadResume = async () => {

    try {

      const response =
        await axios.get(
          `http://localhost:8080/api/resumes/${email}`
        );

      setResume(response.data);

    } catch (error) {

      console.log(error);
    }
  };

  const uploadResume = async () => {

    if (!file) {

      alert("Please select a file");

      return;
    }

    try {

      const formData =
        new FormData();

      formData.append(
        "email",
        email
      );

      formData.append(
        "file",
        file
      );

      const response =
        await axios.post(
          "http://localhost:8080/api/resumes/upload",
          formData,
          {
            headers: {
              "Content-Type":
                "multipart/form-data"
            }
          }
        );

      setResume(
        response.data
      );

      alert(
        "Resume Uploaded Successfully"
      );

    } catch (error) {

      console.log(error);

      alert(
        "Upload Failed"
      );
    }
  };

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

    <Topbar />

    <div className="p-8">

      {/* Header */}

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

        <p
          className="
            uppercase
            tracking-wider
            text-[#7367F0]
            text-sm
            mb-2
          "
        >
          Resume Management
        </p>

        <h1
          className="
            text-4xl
            font-bold
            text-[#4A4A4A]
          "
        >
          Resume Center
        </h1>

        <p
          className="
            text-gray-500
            mt-2
          "
        >
          Upload your resume to unlock
          AI-powered analysis,
          ATS optimization and
          personalized recommendations.
        </p>

      </div>

      {/* Upload Card */}

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
          Upload Resume
        </h2>

        <input
          type="file"
          accept=".pdf,.doc,.docx,.txt"
          onChange={(e) =>
            setFile(
              e.target.files[0]
            )
          }
          className="
            w-full
            p-4
            border
            border-[#E8E6EF]
            rounded-xl
            mb-6
          "
        />

        <button
          onClick={uploadResume}
          className="
            bg-[#7367F0]
            hover:bg-[#6355e8]
            text-white
            px-8
            py-3
            rounded-xl
            transition
          "
        >
          Upload Resume
        </button>

      </div>

      {/* Resume Info */}

      {resume && (

        <div
          className="
            bg-white
            rounded-3xl
            border
            border-[#E8E6EF]
            p-8
          "
        >

          <h2
            className="
              text-2xl
              font-semibold
              mb-6
            "
          >
            Uploaded Resume
          </h2>

          <div
            className="
              grid
              md:grid-cols-2
              gap-6
            "
          >

            <div>

              <p className="text-gray-500">
                File Name
              </p>

              <h3
                className="
                  font-semibold
                  text-lg
                "
              >
                {resume.resumeFileName}
              </h3>

            </div>

            <div>

              <p className="text-gray-500">
                Uploaded At
              </p>

              <h3
                className="
                  font-semibold
                  text-lg
                "
              >
                {resume.uploadedAt}
              </h3>

            </div>

          </div>

          <a
            href={resume.resumeUrl}
            target="_blank"
            rel="noreferrer"
            className="
              inline-block
              mt-6
              bg-[#F5F3F8]
              px-6
              py-3
              rounded-xl
              hover:bg-[#ECEAF5]
            "
          >
            View Resume
          </a>

        </div>

      )}

    </div>

  </div>
</>
  );
}

export default ResumeAnalyzer;
