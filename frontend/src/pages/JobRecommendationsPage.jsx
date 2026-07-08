import { useEffect, useState } from "react";
import axios from "axios";

import Sidebar from "../components/Sidebar";
import CareerSnapshot from "../components/jobs/CareerSnapshot";
import JobCard from "../components/jobs/JobCard";
import JobSkeleton from "../components/jobs/JobSkeleton";

function JobRecommendationsPage() {

  const [profile, setProfile] =
    useState(null);

  const [data, setData] =
    useState(null);

  const [jobMatches, setJobMatches] =
    useState({});

  const [loading, setLoading] =
    useState(false);

  const [role, setRole] =
    useState("");

  const [city, setCity] =
    useState("");

  const [workMode, setWorkMode] =
    useState("");

  const [experience, setExperience] =
    useState("");

  const [dreamCompany, setDreamCompany] =
    useState("");

  const [analyzingJob, setAnalyzingJob] =
  useState(null);

  const suggestedRoles = [
    "AI Engineer",
    "ML Engineer",
    "Data Scientist",
    "Backend Developer",
    "Java Developer",
    "Frontend Developer"
  ];

  useEffect(() => {

    fetchProfile();

  }, []);

  const fetchProfile = async () => {

    try {

      const email =
        localStorage.getItem(
          "email"
        );

      const response =
        await axios.get(
          `http://localhost:8080/api/student-profile/${email}`
        );

      setProfile(
        response.data
      );

    } catch (error) {

      console.log(error);
    }
  };

 const searchJobs = async () => {

  try {

    setLoading(true);

    const response =
      await axios.post(
        "http://localhost:8080/api/jobs/search",
        {
          role,
          city,
          workMode,
          experience,
          dreamCompany
        }
      );

    setData(response.data);

  } catch (error) {

    console.log(error);

  } finally {

    setLoading(false);

  }
};

const analyzeJob = async (
  jobTitle
) => {

  try {

    setAnalyzingJob(jobTitle);

    const email =
      localStorage.getItem(
        "email"
      );

    const response =
      await axios.post(
        "http://localhost:8080/api/job-match",
        null,
        {
          params: {
            email,
            jobTitle
          }
        }
      );

    setJobMatches(
      prev => ({
        ...prev,
        [jobTitle]: response.data
      })
    );

  } catch (error) {

    console.log(error);

  } finally {

    setAnalyzingJob(null);

  }
};

  return (
   <>
    <div className="flex bg-slate-100 min-h-screen">

        <Sidebar />

        <div className="ml-72 flex-1 p-8">

            <div className="max-w-7xl mx-auto">
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
    Opportunity Intelligence
  </p>

  <h1
    className="
      text-4xl
      font-bold
      mb-3
    "
  >
    💼 Real Job Recommendations
  </h1>

  <p
    className="
      text-white/90
      max-w-3xl
    "
  >
    Discover personalized job opportunities
    aligned with your skills, resume profile
    and career goals.
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
      🎯 Personalized Matching
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
      🤖 AI Job Analysis
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
      📈 Career Growth
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
      🚀 Hiring Readiness
    </span>

  </div>

</div>

          {/* Career Snapshot */}

          <CareerSnapshot
            profile={profile}
          />

          {/* Search Section */}

          <div
            className="
    bg-white/90
    backdrop-blur-md
    rounded-[32px]
    shadow-xl
    border
    border-slate-200
    p-8
    mb-8
"
          >

            <h2
              className="
                text-xl
                font-bold
                mb-4
              "
            >
              Find Your Next Opportunity
            </h2>

            {/* Suggested Roles */}

            <div className="mb-5">

              <p
                className="
                  text-sm
                  text-gray-500
                  mb-2
                "
              >
                Suggested Roles
              </p>

              <div
                className="
                  flex
                  flex-wrap
                  gap-2
                "
              >

                {suggestedRoles.map(
                  (
                    suggestedRole
                  ) => (

                    <button
                      key={
                        suggestedRole
                      }
                      onClick={() =>
                        setRole(
                          suggestedRole
                        )
                      }
                      className="
                        px-3
                        py-2
                        bg-blue-100
                        text-blue-700
                        rounded-full
                        text-sm
                        hover:bg-blue-200
                      "
                    >
                      {
                        suggestedRole
                      }
                    </button>

                  )
                )}

              </div>

            </div>

            <div
              className="
                grid
                md:grid-cols-2
                gap-4
              "
            >

              <input
                type="text"
                placeholder="Role"
                value={role}
                onChange={(e) =>
                  setRole(
                    e.target.value
                  )
                }
                className="
                  border
                  p-3
                  rounded-lg
                "
              />

              <input
                type="text"
                placeholder="City"
                value={city}
                onChange={(e) =>
                  setCity(
                    e.target.value
                  )
                }
                className="
                  border
                  p-3
                  rounded-lg
                "
              />

              <select
                value={workMode}
                onChange={(e) =>
                  setWorkMode(
                    e.target.value
                  )
                }
                className="
                  border
                  p-3
                  rounded-lg
                "
              >
                <option value="">
                  Work Mode
                </option>

                <option value="Remote">
                  Remote
                </option>

                <option value="Hybrid">
                  Hybrid
                </option>

                <option value="Onsite">
                  Onsite
                </option>

              </select>

              <select
                value={experience}
                onChange={(e) =>
                  setExperience(
                    e.target.value
                  )
                }
                className="
                  border
                  p-3
                  rounded-lg
                "
              >
                <option value="">
                  Experience
                </option>

                <option value="Fresher">
                  Fresher
                </option>

                <option value="1-3 Years">
                  1-3 Years
                </option>

                <option value="3-5 Years">
                  3-5 Years
                </option>

                <option value="5+ Years">
                  5+ Years
                </option>

              </select>

              <input
                type="text"
                placeholder="Dream Company"
                value={dreamCompany}
                onChange={(e) =>
                  setDreamCompany(
                    e.target.value
                  )
                }
                className="
                  border
                  p-3
                  rounded-lg
                  md:col-span-2
                "
              />

            </div>

            <button
              onClick={searchJobs}
              className="
    mt-6
    w-full
    bg-gradient-to-r
    from-indigo-600
    to-purple-600
    hover:from-indigo-700
    hover:to-purple-700
    text-white
    py-4
    rounded-2xl
    font-semibold
    text-lg
    shadow-lg
    transition
"
            >
              Search Jobs
            </button>

          </div>

          {/* Loading */}

          {loading && (

            <div
              className="
                grid
                md:grid-cols-2
                gap-4
              "
            >

              <JobSkeleton />
              <JobSkeleton />
              <JobSkeleton />
              <JobSkeleton />

            </div>

          )}

          {/* Results */}

          {!loading &&
            data?.jobs?.length > 0 && (

            <div
              className="
                grid
                md:grid-cols-2
                gap-5
              "
            >

              {data.jobs.map(
                (
                  job,
                  index
                ) => (

                  <JobCard
  key={index}
  job={job}
  matchData={
    jobMatches[
      job.title
    ]
  }
  onAnalyze={
    analyzeJob
  }
  analyzing={
    analyzingJob ===
    job.title
  }
/>
                )
              )}

            </div>

          )}

          {/* Empty State */}

          {!loading &&
            data &&
            data.jobs &&
            data.jobs.length === 0 && (

            <div
              className="
                bg-white
                rounded-2xl
                shadow
                p-10
                text-center
              "
            >

              <h2
                className="
                  text-2xl
                  font-bold
                  mb-2
                "
              >
                🔍 No Jobs Found
              </h2>

              <p
                className="
                  text-gray-600
                "
              >
                Try searching
                another role,
                city or company.
              </p>

            </div>

          )}

          {/* Initial State */}

          {!loading &&
            !data && (

            <div
              className="
                bg-white
                rounded-2xl
                shadow
                p-12
                text-center
              "
            >

              <h2
                className="
                  text-2xl
                  font-bold
                  mb-2
                "
              >
                🚀 Find Your Next Opportunity
              </h2>

              <p
                className="
                  text-gray-600
                "
              >
                Search real jobs
                powered by Adzuna
                and analyze how
                well they match
                your CareerOS profile.
              </p>

            </div>

          )}

        </div>
</div>
      </div>
    </>
  );
}

export default JobRecommendationsPage;