import { useEffect, useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";

function ProjectsPage() {

  const email =
    localStorage.getItem("email");

  const [projects, setProjects] =
    useState([]);

  const [intelligence, setIntelligence] =
    useState(null);
  
  const [roadmap, setRoadmap] =
  useState(null);


  const [selectedBlueprint, setSelectedBlueprint] =
  useState(null);

const [showBlueprint, setShowBlueprint] =
  useState(false);

  const [loading, setLoading] =
    useState(true);

  useEffect(() => {

    loadProjects();

  }, []);

  const loadProjects = async () => {

    try {

      const projectsResponse =
        await axios.get(
          `http://localhost:8080/api/projects/${email}`
        );

      const intelligenceResponse =
        await axios.get(
          `http://localhost:8080/api/projects/intelligence/${email}`
        );

      const roadmapResponse =
  await axios.get(
    `http://localhost:8080/api/roadmap/${email}`
  );
  
  
      setProjects(
        projectsResponse.data
      );

      setIntelligence(
        intelligenceResponse.data
      );

      
  setRoadmap(
  roadmapResponse.data
);


    } catch(error) {

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

      loadProjects();

    }
    catch(error)
    {
      console.log(error);
    }

};

  const deleteProject = async (
    projectId
  ) => {

    const confirmed =
      window.confirm(
        "Delete this project?"
      );

    if(!confirmed) {
      return;
    }

    try {

      await axios.delete(
        `http://localhost:8080/api/projects/${projectId}`
      );

      loadProjects();

    } catch(error) {

      console.log(error);
    }

  };

  if(
  loading ||
  !intelligence ||
  !roadmap
)
{
  return (
    <div>
      Loading...
    </div>
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

            <p
              className="
                uppercase
                text-sm
                opacity-80
              "
            >
              Project Intelligence
            </p>

            <h1
              className="
                text-4xl
                font-bold
                mt-2
              "
            >
              🚀 Project Portfolio
            </h1>

            <p
              className="
                mt-3
                opacity-90
              "
            >
              Analyze how your projects
              impact resumes, internships
              and jobs.
            </p>

          </div>

          {/* Intelligence Summary */}

          <div
            className="
              grid
              md:grid-cols-5
              gap-4
              mb-8
            "
          >

            <SummaryCard
              title="Project Score"
              value={
                intelligence?.overallProjectScore
              }
            />

            <SummaryCard
              title="Engineering"
              value={
                intelligence?.averageEngineeringQuality
              }
            />

            <SummaryCard
              title="Role Alignment"
              value={
                intelligence?.averageRoleAlignment
              }
            />

            <SummaryCard
              title="Production"
              value={
                intelligence?.averageProductionReadiness
              }
            />

            <SummaryCard
              title="Projects"
              value={
                intelligence?.projectCount
              }
            />

          </div>


          <div
  className="
    bg-white
    rounded-3xl
    border
    border-[#E8E6EF]
    p-6
    mb-8
  "
>

  <h2
    className="
      text-2xl
      font-bold
      mb-4
    "
  >
    🚀 Best Project
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
          text-xl
          font-semibold
        "
      >
        {intelligence?.bestProject}
      </h3>

      <p
        className="
          text-slate-500
          mt-2
        "
      >
        Highest impact project in your portfolio.
      </p>

    </div>


{
  roadmap?.recommendedProjects?.length > 0 && (

    <div
      className="
        bg-white
        rounded-3xl
        border
        border-[#E8E6EF]
        p-6
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
        🚀 Recommended Projects
      </h2>

      <div
        className="
          grid
          md:grid-cols-3
          gap-4
        "
      >

        {
          roadmap.recommendedProjects
            .slice(0, 6)
            .map(
              (
                project,
                index
              ) => (

                <div
  key={index}
  onClick={() =>
    openBlueprint(project)

    
  }
  className="
    bg-[#F5F3F8]
    rounded-2xl
    p-5
    cursor-pointer
    hover:bg-[#ECE8FF]
    transition
  "
>

                  <h3
                    className="
                      font-semibold
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
                    Recommended for
                    {" "}
                    {roadmap.dreamRole}
                  </p>

                </div>

              )
            )
        }

      </div>

    </div>

  )

  
}





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
      tracking-widest
      text-sm
      text-white/80
      mb-2
    "
  >
    Project Growth
  </p>

  <h2
    className="
      text-3xl
      font-bold
      mb-3
    "
  >
    🎯 Improve Portfolio Score
  </h2>

  <p
    className="
      text-white/90
    "
  >
    Add more production-ready projects,
    deploy existing applications and
    strengthen AI-focused project work
    to improve career readiness.
  </p>

</div>

    <div
      className="
        bg-[#7367F0]
        text-white
        px-5
        py-3
        rounded-2xl
      "
    >
      ⭐ Portfolio Highlight
    </div>

  </div>

</div>



{
  projects.length < 3 && (

    <div
      className="
        bg-yellow-50
        border
        border-yellow-200
        rounded-3xl
        p-6
        mb-8
      "
    >

      <h2
        className="
          text-xl
          font-bold
          text-yellow-700
        "
      >
        ⚠ Portfolio Improvement Needed
      </h2>

      <p
        className="
          mt-2
          text-slate-600
        "
      >
        Students targeting
        {` ${roadmap?.dreamRole} `}
        typically need at least
        3-5 strong projects.
      </p>

    </div>

  )
}

          {/* Projects */}

          <div
            className="
              grid
              lg:grid-cols-2
              gap-6
            "
          >

            {projects.map(
              (project) => (

                <div
                  key={project.id}
                  className="
                    bg-white
                    rounded-3xl
                    border
                    border-[#E8E6EF]
                    p-6
                  "
                >

                  <div
                    className="
                      flex
                      justify-between
                      items-start
                      mb-4
                    "
                  >

                    <div>

                      <h2
                        className="
                          text-2xl
                          font-bold
                        "
                      >
                        {project.projectName}
                      </h2>

<div
  className="
    flex
    gap-2
    mt-3
    flex-wrap
  "
>

  <span
    className="
      bg-[#F5F3F8]
      px-3
      py-1
      rounded-xl
      text-sm
    "
  >
    Resume +{project.resumeImpact}
  </span>

  <span
    className="
      bg-[#F5F3F8]
      px-3
      py-1
      rounded-xl
      text-sm
    "
  >
    Internship +{project.internshipImpact}
  </span>

  <span
    className="
      bg-[#F5F3F8]
      px-3
      py-1
      rounded-xl
      text-sm
    "
  >
    Job +{project.jobImpact}
  </span>

</div>

                      <p
                        className="
                          text-gray-500
                          mt-2
                        "
                      >
                        {project.description}
                      </p>

                    </div>

                    <div
                      className="
                        bg-[#7367F0]
                        text-white
                        px-4
                        py-2
                        rounded-xl
                      "
                    >
                      {project.projectScore || 0}
                    </div>

                  </div>

                  <div
                    className="
                      grid
                      grid-cols-2
                      gap-3
                      mb-6
                    "
                  >

                    <MetricCard
                      title="Engineering"
                      value={
                        project.engineeringQuality
                      }
                    />

                    <MetricCard
                      title="Role Alignment"
                      value={
                        project.roleAlignment
                      }
                    />

                    <MetricCard
                      title="Production"
                      value={
                        project.productionReadiness
                      }
                    />

                   <MetricCard
  title="Job Impact"
  value={
    project.jobImpact
  }
/>
                  </div>

                 
                  <div
                    className="
                      flex
                      flex-wrap
                      gap-3
                    "
                  >

                    {project.githubUrl && (

                      <a
                        href={
                          project.githubUrl
                        }
                        target="_blank"
                        rel="noreferrer"
                        className="
                          bg-[#F5F3F8]
                          px-4
                          py-2
                          rounded-xl
                        "
                      >
                        Github
                      </a>

                    )}

                    {project.liveUrl && (

                      <a
                        href={
                          project.liveUrl
                        }
                        target="_blank"
                        rel="noreferrer"
                        className="
                          bg-[#F5F3F8]
                          px-4
                          py-2
                          rounded-xl
                        "
                      >
                        Live Demo
                      </a>

                    )}

                    <button
                      onClick={() =>
                        deleteProject(
                          project.id
                        )
                      }
                      className="
                        bg-red-100
                        text-red-600
                        px-4
                        py-2
                        rounded-xl
                      "
                    >
                      Delete
                    </button>

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

function SummaryCard({
  title,
  value
}) {

  return (

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
        {title}
      </p>

      <h3
        className="
          text-2xl
          font-bold
          mt-2
        "
      >
        {value}
      </h3>

    </div>

  );

}

function MetricCard({
  title,
  value
}) {

  return (

    <div
      className="
        bg-[#F5F3F8]
        rounded-xl
        p-4
      "
    >

      <p
        className="
          text-sm
          text-gray-500
        "
      >
        {title}
      </p>

      <h3
        className="
          font-bold
          mt-1
        "
      >
        {value || 0}
      </h3>

    </div>

  );


}

export default ProjectsPage;