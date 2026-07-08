import { useEffect, useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";
import Topbar from "../components/Topbar";
import StatCard from "../components/StatCard";
import Milestone from "../components/Milestone";

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
<Sidebar />

<div
  className="
    ml-72
    min-h-screen
    bg-[#F5F3F8]
  "
>
  <div className="p-8">
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
    Career Progress Intelligence
  </p>

  <h1
    className="
      text-4xl
      font-bold
      mb-3
    "
  >
    📈 Progress Tracker
  </h1>

  <p
    className="
      text-white/90
      max-w-3xl
    "
  >
    Track your career journey, monitor completed
    milestones and stay on course toward your
    dream role.
  </p>

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
        px-4
        py-2
        rounded-xl
      "
    >
      📈 Progress: {data.executionProgress}%
    </div>

    <div
      className="
        bg-white/20
        px-4
        py-2
        rounded-xl
      "
    >
      ✅ {data.completedTasks}/{data.totalTasks} Tasks
    </div>

    <div
      className="
        bg-white/20
        px-4
        py-2
        rounded-xl
      "
    >
      🚀 {data.status}
    </div>

  </div>

</div>

<div
  className="
    grid
    grid-cols-1
    md:grid-cols-2
    lg:grid-cols-5
    gap-6
    mb-8
  "
>

  <StatCard
    title="Resume Score"
    value={`${data.resumeScore}%`}
  />

  <StatCard
    title="ATS Score"
    value={`${data.atsScore}%`}
  />

  <StatCard
    title="Internship Ready"
    value={`${data.internshipReadiness}%`}
  />

  <StatCard
    title="Job Ready"
    value={`${data.jobReadiness}%`}
  />

  <StatCard
    title="Career Ready"
    value={`${data.careerReadiness}%`}
  />

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
      font-bold
      mb-5
    "
  >
    Execution Progress
  </h2>

  <div
    className="
      h-5
      bg-gray-200
      rounded-full
    "
  >

    <div
      className="
        h-5
        bg-[#7367F0]
        rounded-full
      "
      style={{
        width:
          `${data.executionProgress}%`
      }}
    />

  </div>

  <p
    className="
      mt-4
      font-semibold
    "
  >
    {data.executionProgress}% Completed
  </p>

</div>

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
      font-bold
      mb-6
    "
  >
    Career Milestones
  </h2>

  <div className="space-y-4">

    <Milestone
      title="Skill Gap Analysis"
      completed={true}
    />

    <Milestone
      title="Career Roadmap"
      completed={true}
    />

    <Milestone
      title="Resume Optimization"
      completed={false}
    />

    <Milestone
      title="Interview Preparation"
      completed={false}
    />

    <Milestone
      title="Job Applications"
      completed={false}
    />

  </div>


          </div>

        </div>

      </div>
    </>
  );
}

export default CareerProgressPage;