import { useState, useEffect } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

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
      <Navbar />

      <div className="p-6">

        <h1 className="text-3xl font-bold mb-6">
          Resume Management
        </h1>

        <input
          type="file"
          accept=".pdf,.doc,.docx,.txt"
          onChange={(e) =>
            setFile(
              e.target.files[0]
            )
          }
        />

        <br /><br />

        <button
          onClick={uploadResume}
          className="bg-blue-500 text-white px-4 py-2 rounded"
        >
          Upload Resume
        </button>

        <hr className="my-6" />

        {resume && (

          <div>

            <h2 className="text-2xl font-semibold">
              Uploaded Resume
            </h2>

            <p>
              File:
              {" "}
              {resume.resumeFileName}
            </p>

            <p>
              Uploaded:
              {" "}
              {resume.uploadedAt}
            </p>

            <a
              href={resume.resumeUrl}
              target="_blank"
              rel="noreferrer"
              className="text-blue-600 underline"
            >
              View Resume
            </a>

          </div>
        )}

      </div>
    </>
  );
}

export default ResumeAnalyzer;
