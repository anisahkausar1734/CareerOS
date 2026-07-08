import { useEffect, useState } from "react";
import axios from "axios";

import Sidebar from "../components/Sidebar";
import Topbar from "../components/Topbar";

function ResumeRefinementPage() {

  const [companyName, setCompanyName] =
    useState("");

  const [jobDescription, setJobDescription] =
    useState("");

  const [customPrompt, setCustomPrompt] =
    useState("");

  const [loading, setLoading] =
    useState(false);

  const [result, setResult] =
    useState(null);

  const [history, setHistory] =
  useState([]);
 
  const [compareA, setCompareA] =
  useState(null);

const [compareB, setCompareB] =
  useState(null);

  const [resume,setResume] =
  useState(null);  
  
  const loadResume = async () => {

  try {

    const email =
      localStorage.getItem(
        "email"
      );

    const response =
      await axios.get(
        `http://localhost:8080/api/resumes/${email}`
      );

    setResume(
      response.data
    );

  } catch (error) {

    console.log(error);

  }

};

const loadHistory =
  async () => {

    try {

      const email =
        localStorage.getItem(
          "email"
        );

      const response =
        await axios.get(
          `http://localhost:8080/api/resume-refinement/history/${email}`
        );

      setHistory(
        response.data
      );

    } catch (error) {

      console.log(error);

    }

  };


  const generateRefinement =
    async () => {

      try {

        setLoading(true);

        const email =
          localStorage.getItem(
            "email"
          );

        const response =
          await axios.post(
            "http://localhost:8080/api/resume-refinement/generate",
            {
              email,
              companyName,
              jobDescription,
              customPrompt
            }
          );

        setResult(
          response.data
        );

      } catch (error) {

  console.log(error);

  console.log(
    error.response?.data
  );

  alert(
    error.response?.data ||
    "Failed to generate refinement"
  );

} finally {

        setLoading(false);

      }

    };

  useEffect(() => {

  loadResume();

  loadHistory();

}, []);

const loadVersion =
  async (id) => {

    try {

      const response =
        await axios.get(
          `http://localhost:8080/api/resume-refinement/history/version/${id}`
        );

      setResult(
        response.data
      );

    } catch (error) {

      console.log(error);

    }

  };

   const loadCompareVersion =
  async (
    id,
    side
  ) => {

    try {

      const response =
        await axios.get(
          `http://localhost:8080/api/resume-refinement/history/version/${id}`
        );

      if(side === "A") {

        setCompareA(
          response.data
        );

      } else {

        setCompareB(
          response.data
        );

      }

    } catch(error) {

      console.log(error);

    }

  };

const downloadPdf =
  async () => {

    try {

      const response =
        await axios.post(
          "http://localhost:8080/api/resume-pdf/download",
          {
            refinedResume:
              result.refinedResume
          },
          {
            responseType:
              "blob"
          }
        );

      const url =
        window.URL.createObjectURL(
          new Blob(
            [response.data]
          )
        );

      const link =
        document.createElement(
          "a"
        );

      link.href = url;

      link.setAttribute(
        "download",
        "refined_resume.pdf"
      );

      document.body.appendChild(
        link
      );

      link.click();

      link.remove();

    } catch (error) {

      console.log(error);

      alert(
        "PDF download failed"
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


<div className="p-8">

{/* Header */}

<div
  className="
    bg-gradient-to-r
    from-[#7367F0]
    to-[#9D8DFF]
    text-white
    rounded-3xl
    p-8
    mb-8
  "
>

  <p
    className="
      uppercase
      tracking-wider
      text-sm
      mb-2
      text-white/80
      font-medium
    "
  >
    AI Resume Optimization
  </p>

  <h1
    className="
      text-4xl
      font-bold
      mb-3
    "
  >
    ✨ Resume Refinement
  </h1>

  <p
    className="
      text-white/90
      max-w-3xl
    "
  >
    Tailor your resume for companies,
    job descriptions and hiring requirements
    using AI-powered suggestions.
  </p>

  <div
    className="
      flex
      flex-wrap
      gap-3
      mt-5
    "
  >

    <span
      className="
        bg-white/20
        px-4
        py-2
        rounded-xl
        text-sm
      "
    >
      🎯 ATS Optimization
    </span>

    <span
      className="
        bg-white/20
        px-4
        py-2
        rounded-xl
        text-sm
      "
    >
      📝 Resume Enhancement
    </span>

    <span
      className="
        bg-white/20
        px-4
        py-2
        rounded-xl
        text-sm
      "
    >
      🚀 Job Targeting
    </span>

  </div>

</div>

{resume && (

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
        mb-4
      "
    >
      Current Resume
    </h2>

    <div
      className="
        flex
        justify-between
        items-center
      "
    >

      <div>

        <h3
          className="
            font-semibold
            text-lg
          "
        >
          {resume.resumeFileName}
        </h3>

        <p className="text-gray-500">
          Uploaded:
          {" "}
          {resume.uploadedAt}
        </p>

      </div>

      <a
        href={resume.resumeUrl}
        target="_blank"
        rel="noreferrer"
        className="
          bg-[#F5F3F8]
          px-5
          py-2
          rounded-xl
        "
      >
        View Resume
      </a>

    </div>

  </div>

)}

         
         
          {/* Inputs */}

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
              Refinement Inputs
            </h2>

            {/* Company */}

            <div className="mb-6">

              <label
                className="
                  block
                  font-medium
                  mb-2
                "
              >
                Company Name
              </label>

              <input
                type="text"
                value={companyName}
                onChange={(e) =>
                  setCompanyName(
                    e.target.value
                  )
                }
                placeholder="Google, Amazon, Microsoft..."
                className="
                  w-full
                  p-4
                  border
                  border-[#E8E6EF]
                  rounded-xl
                "
              />

            </div>

            {/* Job Description */}

            <div className="mb-6">

              <label
                className="
                  block
                  font-medium
                  mb-2
                "
              >
                Job Description
              </label>

              <textarea
                rows="8"
                value={jobDescription}
                onChange={(e) =>
                  setJobDescription(
                    e.target.value
                  )
                }
                placeholder="Paste job description here..."
                className="
                  w-full
                  p-4
                  border
                  border-[#E8E6EF]
                  rounded-xl
                "
              />

            </div>

            {/* Custom Prompt */}

            <div className="mb-6">

              <label
                className="
                  block
                  font-medium
                  mb-2
                "
              >
                Custom Instructions
              </label>

              <textarea
                rows="5"
                value={customPrompt}
                onChange={(e) =>
                  setCustomPrompt(
                    e.target.value
                  )
                }
                placeholder="Example: Rewrite for an AI startup and emphasize LLM projects."
                className="
                  w-full
                  p-4
                  border
                  border-[#E8E6EF]
                  rounded-xl
                "
              />

            </div>

            <button
              onClick={generateRefinement}
              disabled={loading}
              className="
                bg-[#7367F0]
                hover:bg-[#6355E8]
                text-white
                px-8
                py-3
                rounded-xl
              "
            >
              {
                loading
                  ? "Generating..."
                  : "Generate Refined Resume"
              }
            </button>

          </div>

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
    Refinement History
  </h2>

  <div className="space-y-3">

    {history.map(
      (item) => (

        <div
  key={item.id}
  className="
    p-4
    rounded-xl
    bg-[#F5F3F8]
  "
>

  <div
    className="
      flex
      justify-between
      items-center
    "
  >

    <div>

      <div
        className="
          font-semibold
        "
      >
        {
          item.companyName
          || "Custom Resume"
        }
      </div>

      <div
        className="
          text-sm
          text-gray-500
        "
      >
        {
          new Date(
            item.createdAt
          ).toLocaleString()
        }
      </div>

    </div>

    <div className="flex gap-2">

      <button
        onClick={() =>
          loadVersion(
            item.id
          )
        }
        className="
          bg-[#7367F0]
          text-white
          px-3
          py-2
          rounded-lg
        "
      >
        Open
      </button>

      <button
        onClick={() =>
          loadCompareVersion(
            item.id,
            "A"
          )
        }
        className="
          bg-green-600
          text-white
          px-3
          py-2
          rounded-lg
        "
      >
        Compare A
      </button>

      <button
        onClick={() =>
          loadCompareVersion(
            item.id,
            "B"
          )
        }
        className="
          bg-orange-500
          text-white
          px-3
          py-2
          rounded-lg
        "
      >
        Compare B
      </button>

    </div>

  </div>

</div>
      )
    )}

  </div>

</div>


{compareA && compareB && (

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

    <div
  className="
    flex
    justify-between
    items-center
    mb-6
  "
>

  <h2
    className="
      text-2xl
      font-semibold
    "
  >
    Resume Comparison
  </h2>

  <button
    onClick={() => {

      setCompareA(null);

      setCompareB(null);

    }}
    className="
      bg-red-500
      text-white
      px-4
      py-2
      rounded-lg
    "
  >
    Clear
  </button>

</div>

    <div
      className="
        grid
        md:grid-cols-2
        gap-6
      "
    >

      <div>

        <h3
          className="
            text-xl
            font-semibold
            mb-4
          "
        >
          Version A
        </h3>

        <p
          className="
            mb-4
            text-gray-600
          "
        >
          {compareA.summary}
        </p>

        <div className="space-y-2">

<h4
  className="
    font-semibold
    mb-3
  "
>
  ATS Keywords
</h4>

<div
  className="
    flex
    flex-wrap
    gap-2
    mb-6
  "
>

  {compareA.atsKeywords?.map(
    (keyword,index) => (

      <span
        key={index}
        className="
          bg-[#F5F3F8]
          px-3
          py-1
          rounded-full
          text-sm
        "
      >
        {keyword}
      </span>

    )
  )}

</div>

          {compareA.changesMade?.map(
            (change,index) => (

              <div
                key={index}
                className="
                  bg-green-50
                  text-green-700
                  px-3
                  py-2
                  rounded-lg
                "
              >
                {change}
              </div>

            )
          )}

          <div
  className="
    mt-6
    p-4
    bg-[#F9F9FB]
    rounded-xl
    max-h-64
    overflow-y-auto
  "
>

  <pre
    className="
      whitespace-pre-wrap
      text-xs
    "
  >
    {compareA.refinedResume}
  </pre>

</div>

        </div>

      </div>

      <div>

        <h3
          className="
            text-xl
            font-semibold
            mb-4
          "
        >
          Version B
        </h3>

        <p
          className="
            mb-4
            text-gray-600
          "
        >
          {compareB.summary}
        </p>

        <div className="space-y-2">

<h4
  className="
    font-semibold
    mb-3
  "
>
  ATS Keywords
</h4>

<div
  className="
    flex
    flex-wrap
    gap-2
    mb-6
  "
>

  {compareB.atsKeywords?.map(
    (keyword,index) => (

      <span
        key={index}
        className="
          bg-[#F5F3F8]
          px-3
          py-1
          rounded-full
          text-sm
        "
      >
        {keyword}
      </span>

    )
  )}

  <div
  className="
    mt-6
    p-4
    bg-[#F9F9FB]
    rounded-xl
    max-h-64
    overflow-y-auto
  "
>

  <pre
    className="
      whitespace-pre-wrap
      text-xs
    "
  >
    {compareB.refinedResume}
  </pre>

</div>

</div>

          {compareB.changesMade?.map(
            (change,index) => (

              <div
                key={index}
                className="
                  bg-blue-50
                  text-blue-700
                  px-3
                  py-2
                  rounded-lg
                "
              >
                {change}
              </div>

            )
          )}

        </div>

      </div>

    </div>

  </div>

)}

          {/* Results */}

          {result && (

            <>
              {/* Summary */}

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
                    mb-4
                  "
                >
                  Refinement Summary
                </h2>

                <p>
                  {result.summary}
                </p>

              </div>

              {/* ATS Keywords */}

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
                    mb-4
                  "
                >
                  ATS Keywords
                </h2>

                <div
                  className="
                    flex
                    flex-wrap
                    gap-3
                  "
                >

                  {result.atsKeywords?.map(
                    (keyword, index) => (

                      <span
                        key={index}
                        className="
                          bg-[#F5F3F8]
                          px-4
                          py-2
                          rounded-full
                        "
                      >
                        {keyword}
                      </span>

                    )
                  )}

                </div>

              </div>

              {/* Changes */}

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
                    mb-4
                  "
                >
                  Changes Made
                </h2>

                <div className="space-y-3">

                  {result.changesMade?.map(
                    (change, index) => (

                      <div
                        key={index}
                        className="
                          bg-green-50
                          text-green-700
                          px-4
                          py-3
                          rounded-xl
                        "
                      >
                        {change}
                      </div>

                    )
                  )}

                </div>

              </div>

              {/* Refined Resume */}

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
                    mb-4
                  "
                >
                  Refined Resume
                </h2>

<button
  onClick={downloadPdf}
  className="
    mb-6
    bg-[#7367F0]
    hover:bg-[#6355E8]
    text-white
    px-6
    py-3
    rounded-xl
  "
>
  Download PDF
</button>

                <pre
                  className="
                    whitespace-pre-wrap
                    text-sm
                    leading-7
                  "
                >
                  {result.refinedResume}
                </pre>

              </div>

            </>
          )}

        </div>

      </div>

    </>
  );

}

export default ResumeRefinementPage;