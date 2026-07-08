import { useEffect, useState } from "react";
import axios from "axios";

import Sidebar from "../components/Sidebar";
import CareerSnapshot from "../components/jobs/CareerSnapshot";
import JobCard from "../components/jobs/JobCard";
import JobSkeleton from "../components/jobs/JobSkeleton";

function InternshipRecommendationsPage() {

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

  const [selectedMatch, setSelectedMatch] =
  useState(null);

const [loadingMatch, setLoadingMatch] =
  useState(false);

  const [preferredCompany, setPreferredCompany] =
    useState("");

  const [analyzingJob, setAnalyzingJob] =
  useState(null);

 const suggestedRoles = [
 "AI Engineer Intern",
 "Machine Learning Intern",
 "Data Science Intern",
 "Software Engineer Intern",
 "Backend Developer Intern",
 "Frontend Developer Intern",
 "Full Stack Developer Intern",
 "Cloud Intern"
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

 const findInternships = async () => {

  try {

    setLoading(true);

    const response =
      await axios.post(
  "http://localhost:8080/api/internships/search",
  {
    role,
    city,
    workMode,
    dreamCompany: preferredCompany
  }
);

    setData(response.data);

  } catch (error) {

    console.log(error);

  } finally {

    setLoading(false);

  }
};

const analyzeInternship = async (
  internship
) => {

   console.log(
    "INTERNSHIP OBJECT:"
  );

  console.log(
    internship
  );
  

  try {

    setAnalyzingJob(
      internship.title
    );

    const email =
      localStorage.getItem(
        "email"
      );

    const response =
      await axios.post(
        "http://localhost:8080/api/internship-match/analyze",
        {
          email,
          company:
            internship.company,
          role:
            internship.title,
          description:
            internship.description
        }
      );

    setJobMatches(
      prev => ({
        ...prev,
        [internship.title]:
          response.data
      })
    );

  } catch (error) {

    console.log(error);

  } finally {

    setAnalyzingJob(null);

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
    Internship Intelligence
  </p>

  <h1
    className="
      text-4xl
      font-bold
      mb-3
    "
  >
    🎓 Internship Opportunities
  </h1>

  <p
    className="
      text-white/90
      max-w-3xl
    "
  >
    Explore internships tailored to your
    skills, career goals and academic journey.
    Gain real-world experience and accelerate
    your professional growth.
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
      🎯 Skill-Based Matching
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
      💼 Industry Experience
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
      🚀 Internship Readiness
    </span>

  </div>

</div>

          {/* Internship Readiness Snapshot */}

          <CareerSnapshot
  profile={profile}
/>

          {/* Search Section */}

          <div
            className="
              bg-white
              rounded-2xl
              shadow
              p-6
              mb-6
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

              <input
                type="text"
                placeholder="Preferred Company"
                value={preferredCompany}
                onChange={(e) =>
                  setPreferredCompany(
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


              

            </div>

            <button
              onClick={findInternships}
              className="
                mt-5
                bg-blue-600
                hover:bg-blue-700
                text-white
                px-6
                py-3
                rounded-lg
                font-medium
              "
            >
              Find Internships
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
  matchData={jobMatches[job.title]}
  onAnalyze={analyzeInternship}
  analyzing={analyzingJob === job.title}
  passFullJob={true}
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
                🔍 No Internships Found
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
                Search real internships
powered by Adzuna
and discover opportunities
aligned with your CareerOS profile.
              </p>

            </div>

          )}

        </div>
</div>

      </div>
    </>
  );
}

export default InternshipRecommendationsPage;