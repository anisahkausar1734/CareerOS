import { useEffect, useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";
import { useNavigate }
from "react-router-dom";

function CareerIntelligencePage() {

  const [career, setCareer] = useState(null);
  const [resume, setResume] = useState(null);
  const [job, setJob] = useState(null);
  const [internship, setInternship] = useState(null);
  const [refreshing, setRefreshing] =
  useState(false);
  const [showAllSkills, setShowAllSkills] =
  useState(false);
const [insight, setInsight] =
  useState(null);
const navigate = useNavigate();
  const email = localStorage.getItem("email");

  useEffect(() => {

    fetchData();

  }, []);

async function fetchData() {

  const token = localStorage.getItem("token");

  const config = {
    headers: {
      Authorization: `Bearer ${token}`
    }
  };

  // CAREER

  try {

    const careerRes =
      await axios.get(
        `http://localhost:8080/api/career-analysis/${email}`,
        config
      );

    console.log("Career OK");

    setCareer(
      careerRes.data
    );

  }
  catch (e) {

    console.log(
      "Career Error",
      e
    );

  }

  // RESUME

  try {

    const resumeRes =
      await axios.get(
        `http://localhost:8080/api/resumes/analyze/${email}`,
        config
      );

    console.log("Resume OK");

    setResume(
      resumeRes.data
    );

    console.log(
      "Resume Response",
      resumeRes.data
    );

  }
  catch (e) {

    console.log(
      "Resume Error",
      e
    );

  }

  // JOB

  try {

    const jobRes =
      await axios.get(
        `http://localhost:8080/api/job-readiness/${email}`,
        config
      );

    console.log("Job OK");

    setJob(
      jobRes.data
    );

  }
  catch (e) {

    console.log(
      "Job Error",
      e
    );

  }

  // INTERNSHIP

  try {

    const internshipRes =
      await axios.get(
        `http://localhost:8080/api/internship/${email}`,
        config
      );

    console.log("Internship OK");

    setInternship(
      internshipRes.data
    );

  }
  catch (e) {

    console.log(
      "Internship Error",
      e
    );

  }



  // INSIGHT

  try {

    const insightRes =
      await axios.get(
        `http://localhost:8080/api/career-insight/${email}`,
        config
      );

    console.log("Insight OK");

    setInsight(
      insightRes.data
    );

  }
  catch (e) {

    console.log(
      "Insight Error",
      e
    );

  }

}


const regenerateInsight = async () => {

  setRefreshing(true);

  const token =
    localStorage.getItem("token");

  const config = {
    headers: {
      Authorization:
        `Bearer ${token}`
    }
  };

  try {

    const response =
      await axios.post(
        `http://localhost:8080/api/career-insight/regenerate/${email}`,
        {},
        config
      );

    setInsight(
      response.data
    );

  }
  catch(error)
  {
    console.error(error);
  }

  setRefreshing(false);

};

if (
  !career ||
  !resume ||
  !job ||
  !internship ||
  !insight
)
{
  return (
    <div className="p-10">
      Loading Career Intelligence...
    </div>
  );
}



 
  return (

    <div className="flex">

  <Sidebar />

  <div className="flex-1 ml-72 p-8">

    <div className="max-w-7xl mx-auto">

      {/* Hero Section */}

      <div
        className="
          bg-gradient-to-r
          from-[#7367F0]
          to-[#9D8DFF]
          text-white
          rounded-3xl
          p-6
          mb-6
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
          Career Intelligence
        </p>

       <h1
  className="
    text-4xl
    font-bold
    mb-3
  "
>
  🧠 Career Intelligence
</h1>

<div
  className="
    flex
    flex-wrap
    gap-4
    mt-6
  "
>

  <div
    className="
      bg-white/20
      rounded-2xl
      px-5
      py-3
    "
  >
    <p className="text-xs text-white/70">
      Career Readiness
    </p>

    <p className="text-2xl font-bold">
      {career.careerReadinessScore}%
    </p>
  </div>

  
  <div
    className="
      bg-white/20
      rounded-2xl
      px-5
      py-3
    "
  >
    <p className="text-xs text-white/70">
      Target Role
    </p>

    <p className="text-2xl font-bold">
{resume.dreamRole}
    </p>
  </div>


</div>

        <p
          className="
            text-white/90
            max-w-3xl
          "
        >
         {insight.careerSummary}
        </p>
<div className="mt-5">

  <button
  onClick={regenerateInsight}
  disabled={refreshing}
  className="
    bg-white
    text-[#7367F0]
    px-5
    py-3
    rounded-xl
    font-semibold
  "
>
  {
    refreshing
      ? "Refreshing..."
      : "🔄 Refresh Insights"
  }
</button>
</div>
       
      </div>


      {/* Existing Cards */}

      <div className="grid grid-cols-4 gap-6">

          <div
  className="
    bg-white
    rounded-3xl
    border
    border-[#E8E6EF]
    p-6
    mt-8
  "
>

            <h2 className="text-gray-500">
              Resume Score
            </h2>

            <h1 className="text-4xl font-bold">
              {resume.resumeScore}
            </h1>

          </div>

          <div className="bg-white rounded-xl p-6 shadow">

            <h2 className="text-gray-500">
              Internship Readiness
            </h2>

            <h1 className="text-4xl font-bold">
              {internship.readinessScore}
            </h1>

          </div>

          <div className="bg-white rounded-xl p-6 shadow">

            <h2 className="text-gray-500">
              Job Readiness
            </h2>

            <h1 className="text-4xl font-bold">
              {job.readinessScore}
            </h1>

          </div>




         <div
  className="
    bg-white
    rounded-2xl
    p-6
    shadow-md
  "
>

  <h2 className="text-gray-500">
    Project Score
  </h2>

  <h1 className="text-4xl font-bold text-[#7367F0]">
    {resume.projectStrengthScore}
  </h1>

</div>
        
</div>


<div
  className="
    bg-white
    rounded-3xl
    border
    border-[#E8E6EF]
    p-8
    mt-8
    mb-8
  "
>

  <h2
    className="
      text-2xl
      font-bold
      mb-6
    "
  >
    🧠 CareerOS Insight
  </h2>

  <div
  className="
    bg-slate-50
    rounded-2xl
    p-6
    mb-6
  "
>
  <p
    className="
      text-slate-700
      leading-relaxed
    "
  >
    {insight.careerSummary}
  </p>
</div>

<div className="grid md:grid-cols-2 gap-6 mb-6">

  <div className="bg-green-50 rounded-2xl p-6">

    <h3 className="font-bold text-green-700 mb-2">
      🚀 Strongest Area
    </h3>

    <p>
      {insight.strongestArea}
    </p>

  </div>

  <div className="bg-red-50 rounded-2xl p-6">

    <h3 className="font-bold text-red-700 mb-2">
      ⚠ Weakest Area
    </h3>

    <p>
      {insight.weakestArea}
    </p>

  </div>

</div>

 <div className="grid md:grid-cols-2 gap-6 mt-6">

  <div className="bg-blue-50 rounded-2xl p-6">
    <h3 className="font-bold text-blue-700 mb-2">
      💡 Key Insight
    </h3>

    <p>
      {insight.keyInsight}
    </p>
  </div>

  <div className="bg-red-50 rounded-2xl p-6">
    <h3 className="font-bold text-red-700 mb-2">
      ⚠ Biggest Risk
    </h3>

    <p>
      {insight.biggestRisk}
    </p>
  </div>

  <div className="bg-green-50 rounded-2xl p-6">
    <h3 className="font-bold text-green-700 mb-2">
      🚀 Biggest Opportunity
    </h3>

    <p>
      {insight.biggestOpportunity}
    </p>
  </div>

  </div>



<div className="mt-8">
          <div className="bg-white rounded-xl p-6 shadow">

            <h2 className="font-bold mb-4">
              Resume Insights
            </h2>

           <div className="mb-4">

  <div className="flex justify-between">

    <span>ATS Score</span>

    <span>{resume.atsScore}%</span>

  </div>

  <div className="w-full bg-gray-200 rounded-full h-3 mt-2">

    <div
      className="bg-indigo-600 h-3 rounded-full"
      style={{
        width: `${resume.atsScore}%`
      }}
    />

  </div>

</div>



<div className="mb-4">

  <div className="flex justify-between">

    <span>Skills Coverage</span>

    <span>{resume.skillsCoverageScore}%</span>

  </div>

  <div className="w-full bg-gray-200 rounded-full h-3 mt-2">

    <div
      className="bg-indigo-600 h-3 rounded-full"
      style={{
        width: `${resume.skillsCoverageScore}%`
      }}
    />

  </div>

</div>


<div className="mb-4">

  <div className="flex justify-between">

    <span> Role Alignment</span>

    <span>{resume.roleAlignmentScore}%</span>

  </div>

  <div className="w-full bg-gray-200 rounded-full h-3 mt-2">

    <div
      className="bg-indigo-600 h-3 rounded-full"
      style={{
        width: `${resume.roleAlignmentScore}%`
      }}
    />

  </div>

</div>



 <div className="mb-4">

  <div className="flex justify-between">

    <span> Project Strength </span>

    <span>{resume.projectStrengthScore}%</span>

  </div>

  <div className="w-full bg-gray-200 rounded-full h-3 mt-2">

    <div
      className="bg-indigo-600 h-3 rounded-full"
      style={{
        width: `${resume.projectStrengthScore}%`
      }}
    />

  </div>

</div>          

</div>
          </div>

        </div>







</div>

      </div>

    </div>
  );
}

export default CareerIntelligencePage;