import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import StatCard from "../components/StatCard";
import Sidebar from "../components/Sidebar";
import Topbar from "../components/Topbar";

function RoadmapPage() {

  const [data, setData] =
    useState(null);

  const [loading, setLoading] =
    useState(true);

  const [selectedBlueprint, setSelectedBlueprint] =
  useState(null);

const [showBlueprint, setShowBlueprint] =
  useState(false);

    const navigate = useNavigate();

  useEffect(() => {
    loadRoadmap();
  }, []);

  const loadRoadmap = async () => {

    try {

      const email =
        localStorage.getItem(
          "email"
        );

      const response =
        await axios.get(
          `http://localhost:8080/api/roadmap/${email}`
        );

      setData(
        response.data
      );

    } catch (error) {

      console.log(error);

    } finally {

      setLoading(false);

    }

  };

  const openBlueprint = async (
  projectName
) => {

  try {

    const response =
      await axios.get(
        `http://localhost:8080/api/projects/blueprint/${encodeURIComponent(
          projectName
        )}`
      );

    setSelectedBlueprint(
      response.data
    );

    setShowBlueprint(
      true
    );

  }
  catch(error)
  {
    console.log(error);
  }

};

const addBlueprintToProjects =
  async () => {

    try {

      const email =
        localStorage.getItem(
          "email"
        );

      await axios.post(
        "http://localhost:8080/api/projects",
        {
          email,

          projectName:
            selectedBlueprint.projectName,

          description:
            selectedBlueprint.features
              ?.join(", "),

          techStack:
            selectedBlueprint.techStack,

          githubUrl: "",

          liveUrl: ""
        }
      );

      setShowBlueprint(
        false
      );

    }
    catch(error)
    {
      console.log(error);
    }

};

  const updateRoadmapStep =
  async (
    phase,
    completed
  ) => {

    try {

      const email =
        localStorage.getItem(
          "email"
        );

      const response =
        await axios.post(
          `http://localhost:8080/api/roadmap/step/${email}/${phase}?completed=${completed}`
        );

console.log(
  response.data
);

      setData(
        response.data
      );

    }
    catch(error)
    {
      console.log(error);
    }

};

  if (loading) {

    return (
      <h2 className="text-center mt-10">
        Loading Roadmap...
      </h2>
    );

  }
console.log("ROADMAP DATA", data);

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

          {/* Hero */}

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

  <p className="uppercase text-sm opacity-80">
    Career Planning
  </p>

  <h1
    className="
      text-4xl
      font-bold
      mt-2
    "
  >
    🗺️ Career Roadmap
  </h1>

  <p className="mt-3 opacity-90">
  {
    data?.completionPercentage >= 80
      ? `You're very close to becoming a ${data?.dreamRole}.`
      : data?.completionPercentage >= 50
      ? `You're making strong progress toward becoming a ${data?.dreamRole}.`
      : `Personalized roadmap designed to help you become a ${data?.dreamRole}.`
  }
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
    🎯 Target: {data?.dreamRole}
  </div>

  <div
    className="
      bg-white/20
      px-4
      py-2
      rounded-xl
    "
  >
    📅 {data?.estimatedMonths} Months
  </div>

  <div
    className="
      bg-white/20
      px-4
      py-2
      rounded-xl
    "
  >
    🚀 {data?.completionPercentage || 0}% Complete
  </div>

  <div
    className="
      bg-white/20
      px-4
      py-2
      rounded-xl
    "
  >
    📚 {data?.roadmapSteps?.length || 0} Phases
  </div>

  <div
    className="
      bg-white/20
      px-4
      py-2
      rounded-xl
    "
  >
    ✅ {
      data?.roadmapSteps?.filter(
        step => step.completed
      ).length || 0
    } Completed
  </div>

  <button
    onClick={() =>
      window.location.reload()
    }
    className="
      bg-white
      text-[#7367F0]
      px-4
      py-2
      rounded-xl
      font-semibold
    "
  >
    🔄 Regenerate Roadmap
  </button>

</div>



 <div
  className="
    bg-white/10
    rounded-2xl
    p-5
    mt-6
  "
>

  <div
    className="
      flex
      justify-between
      mb-2
    "
  >

    <span>
      Roadmap Progress
    </span>

    <span>
      {data?.completionPercentage || 0}%
    </span>

  </div>

  <div
    className="
      h-3
      bg-white/20
      rounded-full
      overflow-hidden
    "
  >

    <div
      className="
        h-full
        bg-white
      "
      style={{
        width:
          `${data?.completionPercentage || 0}%`
      }}
    />

  </div>

</div>
</div>

<div
  className="
    flex
    gap-3
    mt-4
    flex-wrap
  "
>

  {
    data?.completionPercentage >= 25 && (
      <span
        className="
          bg-green-100
          text-green-700
          px-3
          py-1
          rounded-xl
        "
      >
        🎯 Roadmap Started
      </span>
    )
  }

  {
    data?.completionPercentage >= 50 && (
      <span
        className="
          bg-blue-100
          text-blue-700
          px-3
          py-1
          rounded-xl
        "
      >
        🚀 Skill Builder
      </span>
    )
  }

  {
    data?.completionPercentage >= 75 && (
      <span
        className="
          bg-purple-100
          text-purple-700
          px-3
          py-1
          rounded-xl
        "
      >
        🏆 Industry Ready
      </span>
    )
  }

  {
    data?.completionPercentage === 100 && (
      <span
        className="
          bg-yellow-100
          text-yellow-700
          px-3
          py-1
          rounded-xl
        "
      >
        👑 Career Launch Ready
      </span>
    )
  }

</div>
     
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

  <p className="uppercase text-sm opacity-80">
    Next Best Action
  </p>

  <h2
    className="
      text-3xl
      font-bold
      mt-2
      mb-3
    "
  >
    {data?.nextAction}
  </h2>

  <p
    className="
      opacity-90
    "
  >
    {data?.actionReason}
  </p>

  <div
  className="
    bg-white/10
    rounded-2xl
    p-5
    mt-5
  "
>

  <h3
    className="
      font-semibold
      mb-2
    "
  >
    Expected Outcome
  </h3>

  <p>
    {data?.expectedOutcome}
  </p>

</div>

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
    🔥 Priority Skills
  </h2>

  <div
    className="
      flex
      flex-wrap
      gap-3
    "
  >

    {
  data?.topPrioritySkills?.map(
    (skill,index) => (

      <div
        key={index}
        onClick={() =>
          navigate(
            `/resources?skill=${encodeURIComponent(skill)}`
          )
        }
        className="
          px-4
          py-3
          rounded-xl
          bg-[#F5F3F8]
          cursor-pointer
          hover:bg-[#ECE8FF]
          transition
        "
      >
        {skill}
      </div>

    )
  )
}
  </div>

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
    Recommended Projects
  </h2>

  <div
    className="
      flex
      flex-wrap
      gap-3
    "
  >

    {data?.recommendedProjects?.map(
      (project, index) => (

      <div
  key={index}
  onClick={() =>
    openBlueprint(project)
  }
  className="
    bg-[#F5F3F8]
    rounded-2xl
    p-5
    min-w-[250px]
    cursor-pointer
    hover:bg-[#ECE8FF]
    transition
  "
>

  <h3
    className="
      font-semibold
      text-lg
    "
  >
    🚀 {project}
  </h3>

  <p
    className="
      text-sm
      text-slate-500
      mt-2
    "
  >
    Recommended for your roadmap
  </p>

</div>



      )
    )}

  </div>

</div>

<div
  className="
    grid
    md:grid-cols-3
    gap-6
    mb-8
  "
>

  <div
    className="
      bg-white
      rounded-3xl
      p-6
    "
  >

    <p className="text-gray-500">
      Completed Steps
    </p>

    <h2
      className="
        text-4xl
        font-bold
        text-green-600
      "
    >
      {
        data?.roadmapSteps?.filter(
          step => step.completed
        ).length
      }
    </h2>

  </div>

  <div
    className="
      bg-white
      rounded-3xl
      p-6
    "
  >

    <p className="text-gray-500">
      Remaining Steps
    </p>

    <h2
      className="
        text-4xl
        font-bold
        text-orange-500
      "
    >
      {
        data?.roadmapSteps?.filter(
          step => !step.completed
        ).length
      }
    </h2>

  </div>

  <div
  className="
    bg-white
    rounded-2xl
    border
    border-[#E8E6EF]
    p-5
  "
>
  <p
    className="
      text-gray-500
      text-sm
    "
  >
    Remaining Duration
  </p>

  <h3
    className="
      text-2xl
      font-bold
      mt-2
    "
  >
    {data?.totalWeeks || 0} weeks
  </h3>
</div>

  <div
    className="
      bg-white
      rounded-3xl
      p-6
    "
  >

   

  </div>

</div>



 
          {/* Roadmap Timeline */}

         <div
  className="
    space-y-6
  "
>

  {data?.roadmapSteps?.map(
    (step) => (

     <div
  key={step.phase}
  className={`
    rounded-2xl
    border-l-8
    shadow-sm
    p-6

    ${
      step.completed
        ? "bg-green-50 border-green-400"
        : step.priority === "HIGH"
        ? "bg-white border-red-500"
        : step.priority === "MEDIUM"
        ? "bg-white border-yellow-500"
        : "bg-white border-green-500"
    }
  `}
>
        <div
          className="
            flex
            gap-3
            mb-4
          "
        >

          <span
            className="
              bg-[#7367F0]
              text-white
              px-3
              py-1
              rounded-lg
              text-sm
            "
          >
            {step.category}
          </span>

          <span
            className={`
              px-3
              py-1
              rounded-lg
              text-sm
              ${
                step.priority === "HIGH"
                  ? "bg-red-100 text-red-600"
                  : step.priority === "MEDIUM"
                  ? "bg-yellow-100 text-yellow-700"
                  : "bg-green-100 text-green-700"
              }
            `}
          >
            {step.priority}
          </span>

        </div>

        <div
          className="
            flex
            justify-between
            items-center
            mb-4
          "
        >

          <h2
            className="
              text-2xl
              font-bold
            "
          >
            Phase {step.phase}
          </h2>

          <span
            className="
              bg-[#F5F3F8]
              px-4
              py-2
              rounded-xl
            "
          >
            {step.estimatedWeeks}
            {" "}Weeks
          </span>

        </div>

      <div
  className="
    flex
    justify-between
    items-start
    mb-2
  "
>

  <h3
    className={`
      text-xl
      font-semibold
      text-[#7367F0]

      ${
        step.completed
          ? "line-through opacity-50"
          : ""
      }
    `}
  >
    {step.title}
  </h3>

  <input
    type="checkbox"
    checked={
      step.completed || false
    }
    onChange={(e) =>
      updateRoadmapStep(
        step.phase,
        e.target.checked
      )
    }
    className="
      h-5
      w-5
      cursor-pointer
    "
  />

</div>
        <p
          className="
            text-gray-600
            mb-4
          "
        >
          {step.description}
        </p>

        <div
          className="
            mb-4
          "
        >

          <span
            className="
              bg-green-100
              text-green-700
              px-4
              py-2
              rounded-xl
              text-sm
              font-semibold
            "
          >
            {step.impact}
          </span>

        </div>

        <p
          className="
            text-sm
            text-gray-500
            mb-5
          "
        >
          {step.reason}
        </p>

        <h4
          className="
            font-semibold
            mb-3
          "
        >
          Recommended Resources
        </h4>

        <div
          className="
            flex
            flex-wrap
            gap-2
          "
        >

          {step.resources?.map(
  (resource,index) => (

    <div
      key={index}
      onClick={() =>
        navigate("/resources")
      }
      className="
        bg-[#F5F3F8]
        px-4
        py-2
        rounded-xl
        cursor-pointer
        hover:bg-[#ECE8FF]
        transition
      "
    >
      {resource}
    </div>

  )
)}

        </div>

      </div>

    )
  )}

  



</div>

</div>

</div>


{
  showBlueprint &&
  selectedBlueprint && (

    <div
      className="
        fixed
        inset-0
        bg-black/50
        flex
        items-center
        justify-center
        z-50
      "
    >

      <div
        className="
          bg-white
          rounded-3xl
          p-8
          w-full
          max-w-3xl
          mx-4
          max-h-[90vh]
          overflow-y-auto
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
              text-3xl
              font-bold
            "
          >
            🚀 {
              selectedBlueprint.projectName
            }
          </h2>

          <button
            onClick={() =>
              setShowBlueprint(false)
            }
            className="
              text-2xl
            "
          >
            ✕
          </button>

        </div>

        <div className="space-y-6">

          <div>

            <h3 className="font-semibold">
              Difficulty
            </h3>

            <p>
              {
                selectedBlueprint.difficulty
              }
            </p>

          </div>

          <div>

            <h3 className="font-semibold">
              Duration
            </h3>

            <p>
              {
                selectedBlueprint.duration
              }
            </p>

          </div>

          <div>

            <h3 className="font-semibold">
              Career Impact
            </h3>

            <p>
              {
                selectedBlueprint.careerImpact
              }
            </p>

          </div>

          <div>

            <h3 className="font-semibold">
              Resume Impact
            </h3>

            <p>
              {
                selectedBlueprint.resumeImpact
              }
            </p>

          </div>

          <div>

            <h3 className="font-semibold">
              Internship Impact
            </h3>

            <p>
              {
                selectedBlueprint.internshipImpact
              }
            </p>

          </div>

<button
  onClick={() =>
    addBlueprintToProjects()
  }
  className="
    w-full
    bg-[#7367F0]
    text-white
    py-4
    rounded-2xl
    font-semibold
    mt-6
  "
>
  ➕ Add To My Projects
</button>

          <div>

  <h3 className="font-semibold">
    🛠 Tech Stack
  </h3>

  <div
    className="
      flex
      flex-wrap
      gap-2
      mt-3
    "
  >

    {
      selectedBlueprint
        ?.techStack
        ?.map(
          (
            tech,
            index
          ) => (

            <span
              key={index}
              className="
                bg-[#F5F3F8]
                px-3
                py-1
                rounded-xl
                text-sm
              "
            >
              {tech}
            </span>

          )
        )
    }

  </div>

</div>

<div>

  <h3 className="font-semibold">
    ✨ Features
  </h3>

  <ul
    className="
      mt-3
      space-y-2
    "
  >

    {
      selectedBlueprint
        ?.features
        ?.map(
          (
            feature,
            index
          ) => (

            <li
              key={index}
            >
              🚀 {feature}
            </li>

          )
        )
    }

  </ul>

</div>

<div>

  <h3 className="font-semibold">
    🗺 Implementation Roadmap
  </h3>

  <div
    className="
      bg-[#F8F7FC]
      rounded-2xl
      p-4
      mt-3
    "
  >

    <p>
      {
        selectedBlueprint
          ?.roadmap
      }
    </p>

  </div>

</div>

        </div>

      </div>

    </div>

  )
}

</>

);

}

export default RoadmapPage;