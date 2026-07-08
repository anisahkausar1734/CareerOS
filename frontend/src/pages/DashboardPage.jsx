import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

import {
  ChevronDown,
  LogOut,
  Sparkles,
  SquarePen
} from "lucide-react";

import {
  useMemo,
  useRef
} from "react";

import Sidebar from "../components/Sidebar";
import Topbar from "../components/Topbar";
import {
  Search,
  Bell,
  User,
Brain,
Rocket,
Map,
} from "lucide-react";

import {
  Compass,
  BookOpen,
  FileText,
  Briefcase,
  TrendingUp,
  ArrowRight
} from "lucide-react";

import {
  Target,
  Bot,
  BarChart3,
  Award,
  BrainCircuit,
  ShieldCheck
} from "lucide-react";




function DashboardPage() {

const navigate = useNavigate();  

const [profile, setProfile] =
  useState(null);

const [roadmap, setRoadmap] =
  useState(null);

const [projectIntel, setProjectIntel] =
  useState(null);

const [resumeAnalysis, setResumeAnalysis] =
  useState(null);

const [internshipScore, setInternshipScore] =
  useState(0);

const [search, setSearch] = useState("");

const [showResults, setShowResults] = useState(false);

const [showMenu, setShowMenu] = useState(false);

const searchRef = useRef(null);

const menuRef = useRef(null);

const fullName =
  profile?.fullName ||
  localStorage.getItem("fullName") ||
  "CareerOS User";

const firstName =
  fullName.split(" ")[0];


const currentStage = (() => {

    if (!profile) {
        return "🌱 Explorer";
    }

    const hasResume = profile.hasResume;

    const hasSkills =
        profile.skills &&
        profile.skills.length > 0;

    if (!hasResume && !hasSkills) {
        return "🌱 Explorer";
    }

    if (!hasResume && hasSkills) {
        return " Builder";
    }

    return "💼 Candidate";

})();

const currentMilestone = (() => {

    if (!profile?.skills?.length)
        return "Discover your career direction";

    if (!roadmap)
        return "Generate your personalized roadmap";

    if (!profile?.hasResume)
        return "Upload your resume";

    if (!resumeAnalysis)
        return "Improve your ATS score";

    return "Start applying for opportunities";

})();


  const dreamRole =
  profile?.dreamRole || "your dream career";

const academicStage =
  profile?.currentYear
    ? `${profile.currentYear} Student`
    : "Student";

const hasSkills =
  profile?.skills?.length > 0;

const hasResume =
  profile?.hasResume === true;


const pages = [

  {
    name: "Dashboard",
    path: "/dashboard"
  },

  {
    name: "Edit Profile",
    path: "/profile/edit"
  },

  {
    name: "Career Intelligence",
    path: "/career-intelligence"
  },

  {
    name: "Career Copilot",
    path: "/copilot"
  },

  {
    name: "Skill Gap Analysis",
    path: "/skill-gap"
  },

  {
    name: "Career Roadmap",
    path: "/roadmap"
  },

  {
    name: "Learning Resources",
    path: "/resources"
  },

  {
    name: "Project Portfolio",
    path: "/projects"
  },

  {
    name: "Resume Center",
    path: "/resume-center"
  },

  {
    name: "Resume Refinement",
    path: "/resume-refinement"
  },

  {
    name: "ATS Analysis",
    path: "/ats"
  },

  {
    name: "Job Recommendations",
    path: "/jobs"
  },

  {
    name: "Internship Opportunities",
    path: "/internships"
  },

  {
    name: "Internship Analyzer",
    path: "/internship-analyzer"
  },

  {
    name: "Application Tracker",
    path: "/application-tracker"
  },

  {
    name: "Mock Interview",
    path: "/interview"
  },

  {
    name: "Career Progress",
    path: "/progress"
  },

  {
    name: "AI Career Mentor",
    path: "/ai-mentor"
  }

];  

const filteredPages = useMemo(() => {

  if (!search.trim()) return [];

  return pages.filter(page =>
    page.name
      .toLowerCase()
      .includes(search.toLowerCase())
  );

}, [search]);

const journeyCards = [

  {
    number: "1",
    icon: <Compass size={22} />,
    title: "Explore",
    description: "Understand your strengths and discover career paths.",
    status:
      profile?.currentStage === "PROFILE_COMPLETED"
        ? "Current Stage"
        : "Completed",
    progress: profile?.careerReadiness ?? 0,
    active: profile?.currentStage === "PROFILE_COMPLETED",
    route: "/career-intelligence"
  },

  {
    number: "2",
    icon: <Target size={22} />,
    title: "Plan",
    description: "Analyze skill gaps and build personalized roadmap.",
    status:
      profile?.skillGapCompleted
        ? "Completed"
        : "Recommended",
    progress: profile?.careerReadiness ?? 0,
    active: profile?.currentStage === "SKILL_GAP_COMPLETED",
    route: "/skill-gap"
  },

  {
    number: "3",
    icon: <BookOpen size={22} />,
    title: "Learn",
    description: "Gain industry-ready skills through guided resources.",
    status:
      roadmap?.completionPercentage
        ? `${roadmap.completionPercentage}% Complete`
        : "Not Started",
    progress: roadmap?.completionPercentage ?? 0,
    active: profile?.currentStage === "ROADMAP_COMPLETED",
    route: "/roadmap"
  },

  {
    number: "4",
    icon: <FileText size={22} />,
    title: "Prepare",
    description: "Strengthen projects, resume and ATS score.",
    status:
      `${resumeAnalysis?.atsScore ?? 0}% ATS`,
    progress: resumeAnalysis?.atsScore ?? 0,
    active: profile?.currentStage === "RESUME_COMPLETED",
    route: "/resume-center"
  },

  {
    number: "5",
    icon: <Briefcase size={22} />,
    title: "Apply",
    description: "Discover internships and jobs aligned with your profile.",
    status:
      `${internshipScore}% Ready`,
    progress: internshipScore,
    active: profile?.currentStage === "JOBS_COMPLETED",
    route: "/jobs"
  },

  {
    number: "6",
    icon: <TrendingUp size={22} />,
    title: "Grow",
    description: "Track progress and continuously improve.",
    status:
      `${profile?.careerReadiness ?? 0}% Progress`,
    progress: profile?.careerReadiness ?? 0,
    active: profile?.currentStage === "CAREER_READY",
    route: "/progress"
  }

];

 const careerInsights = [

    {
        icon: "💪",
        title: "Strongest Area",
        value:
            resumeAnalysis?.strengths?.[0] ??
            "Complete your profile",
        color: "emerald"
    },

    {
        icon: "⚠️",
        title: "Needs Attention",
        value:
            resumeAnalysis?.weaknesses?.[0] ??
            "No weaknesses detected",
        color: "amber"
    },

    {
        icon: "🎯",
        title: "Next Best Action",
        value:
            roadmap?.nextAction ??
            "Generate Career Roadmap",
        color: "violet"
    },

    {
        icon: "🚀",
        title: "Top Priority Skill",
        value:
            roadmap?.topPrioritySkills?.[0] ??
            "Skill analysis pending",
        color: "blue"
    }

];




  useEffect(() => {

    loadDashboard();

  }, []);

  useEffect(() => {

  function handleClickOutside(e) {

    if (
      searchRef.current &&
      !searchRef.current.contains(e.target)
    ) {
      setShowResults(false);
    }

    if (
      menuRef.current &&
      !menuRef.current.contains(e.target)
    ) {
      setShowMenu(false);
    }

  }

  document.addEventListener(
    "mousedown",
    handleClickOutside
  );

  return () =>
    document.removeEventListener(
      "mousedown",
      handleClickOutside
    );

}, []);

 const loadDashboard = async () => {

  try {

    const email =
      localStorage.getItem(
        "email"
      );


     const results = await Promise.allSettled([
    axios.get(`http://localhost:8080/api/student-profile/${email}`),
    axios.get(`http://localhost:8080/api/resume-analysis/cached/${email}`),
    axios.get(`http://localhost:8080/api/roadmap/cached/${email}`),
    axios.get(`http://localhost:8080/api/internship/${email}`),
    axios.get(`http://localhost:8080/api/projects/intelligence/${email}`)
]);

const [
    profileResponse,
    resumeResponse,
    roadmapResponse,
    internshipResponse,
    projectResponse
] = results;


console.log("Profile Loaded:", profileResponse.data);
   if (profileResponse.status === "fulfilled") {

    setProfile(profileResponse.value.data);

}
    console.log("setProfile executed");

   if (resumeResponse.status === "fulfilled") {

    setResumeAnalysis(resumeResponse.value.data);

}

   if (roadmapResponse.status === "fulfilled") {

    setRoadmap(roadmapResponse.value.data);

}

   if (projectResponse.status === "fulfilled") {

    setProjectIntel(projectResponse.value.data);

}

   if (internshipResponse.status === "fulfilled") {

    setInternshipScore(
        internshipResponse.value.data?.readinessScore || 0
    );
  }

  }
  catch(error)
  {
    console.log(error);
  }
  
};


  
const careerFocus = (() => {

    if (!profile)
        return "Getting started";

    if (!profile.skills?.length)
        return `Building strong foundations for ${profile.dreamRole}`;

    if (!profile.hasResume)
        return `Developing practical skills for ${profile.dreamRole}`;

    return `Preparing for opportunities in ${profile.dreamRole}`;

})();


  return (

    <>
      <Sidebar />

      <div
        className="
ml-[240px]
          min-h-screen
          bg-[#F5F3F8]
        "
      >

<div
  className="
    px-10
    pt-3
    pb-8
    max-w-[1280px]
    mx-auto
  "
>
      {/* ======================= TOPBAR ======================= */}

<div
  className="
    bg-white
    border
    border-[#ECEAF5]
    rounded-3xl
    h-[72px]
    px-6
    flex
    items-center
    justify-between
    mb-4
  "
>

  {/* SEARCH */}

  <div
    ref={searchRef}
    className="
      relative
      flex-1
      max-w-[650px]
    "
  >

    <div
      className="
        flex
        items-center
        gap-3
        h-11
        px-4
        border
        border-[#ECEAF5]
        rounded-xl
        bg-white
      "
    >

      <Search
        size={18}
        className="text-slate-400"
      />

      <input
        type="text"
        value={search}
        placeholder="Search CareerOS..."
        onChange={(e) => {

          setSearch(e.target.value);

          setShowResults(true);

        }}
        onFocus={() =>
          setShowResults(true)
        }
        className="
          flex-1
          bg-transparent
          outline-none
          text-sm
        "
      />

      <div
        className="
          text-[11px]
          px-2
          py-1
          rounded-lg
          bg-slate-100
          text-slate-500
        "
      >
        Ctrl /
      </div>

    </div>

    {
      showResults &&
      search.trim() &&
      (

        <div
          className="
            absolute
            top-14
            left-0
            w-full
            bg-white
            border
            border-[#ECEAF5]
            rounded-2xl
            shadow-xl
            overflow-hidden
            z-50
          "
        >

          {
            filteredPages.length > 0

              ?

              filteredPages.map((page) => (

                <button

                  key={page.path}

                  onClick={() => {

                    navigate(page.path);

                    setSearch("");

                    setShowResults(false);

                  }}

                  className="
                    w-full
                    px-5
                    py-3
                    text-left
                    text-sm
                    hover:bg-violet-50
                    transition
                  "
                >

                  {page.name}

                </button>

              ))

              :

              <div
                className="
                  px-8
                  py-3
                  text-sm
                  text-slate-500
                "
              >

                No results found

              </div>

          }

        </div>

      )
    }

  </div>

  {/* RIGHT SIDE */}

  <div
    className="
      flex
      items-center
      gap-5
    "
  >

    {/* BELL */}

    <button
      className="
        w-10
        h-10
        rounded-xl
        hover:bg-slate-100
        flex
        items-center
        justify-center
        transition
      "
    >

      <Bell
        size={19}
        className="text-slate-600"
      />

    </button>

        {/* PROFILE */}

    <div
      ref={menuRef}
      className="relative"
    >

      <button
        onClick={() =>
          setShowMenu(!showMenu)
        }
        className="
          flex
          items-center
          gap-3
          rounded-xl
          px-2
          py-1
          hover:bg-slate-50
          transition
        "
      >

        <div
          className="
            w-10
            h-10
            rounded-full
            bg-[#7367F0]
            text-white
            flex
            items-center
            justify-center
            font-semibold
          "
        >
          {firstName.charAt(0).toUpperCase()}
        </div>

        <div className="text-left">

          <p
            className="
              text-sm
              font-semibold
              text-slate-800
            "
          >
            {fullName}
          </p>

          <p className="text-xs text-slate-500">
  {profile?.dreamRole || "Student"}
</p>

        </div>

        <ChevronDown
          size={16}
          className="text-slate-500"
        />

      </button>

      {
        showMenu && (

          <div
            className="
              absolute
              right-0
              top-14
              w-56
              bg-white
              border
              border-[#ECEAF5]
              rounded-2xl
              shadow-xl
              overflow-hidden
              z-50
            "
          >

            <button
             onClick={() => {

    console.log("Edit button clicked");

    navigate("/profile/edit");

    setShowMenu(false);

}}
              className="
                w-full
                flex
                items-center
                gap-3
                px-5
                py-3
                text-sm
                hover:bg-violet-50
                transition
              "
            >

              <SquarePen size={18} />

              Edit Profile

            </button>

            <div className="border-t border-slate-100" />

            <button
              onClick={() => {

                localStorage.clear();

                navigate("/");

              }}
              className="
                w-full
                flex
                items-center
                gap-3
                px-5
                py-3
                text-sm
                text-red-600
                hover:bg-red-50
                transition
              "
            >

              <LogOut size={18} />

              Logout

            </button>

          </div>

        )
      }

    </div>

  </div>

</div>

{/* ==================== END TOPBAR ==================== */}


 {/* ================= HERO ================= */}

<div
   className="
    relative
    overflow-hidden
    bg-white
    border
    border-[#ECEAF5]
    rounded-[28px]
    px-10
    py-4
    mb-8
    flex
    justify-between
    items-center
    gap-8
"
>

    {/* Soft Glow */}

   {/* Main Glow */}


{/* Secondary Soft Glow */}

<div
    className="
        absolute
        right-20
        top-16
        w-[220px]
        h-[220px]
        rounded-full
        bg-indigo-300/20
        blur-[110px]
    "
/>

    {/* LEFT SIDE */}

    <div
        className="
            relative
            z-10
            max-w-[560px]
        "
    >

        {/* Badge */}

       {/* Badge */}

<div
    className="
        inline-flex
        items-center
        gap-2
        rounded-full
        bg-violet-50
        text-violet-600
        px-4
        py-2
        text-[13px]
        font-medium
        mt-4
        mb-6
    "
>

    ✨

    Your Career Journey Starts Here

</div>





        {/* Heading */}

       <h1
    className="
        text-[30px]
        leading-[40px]
        font-semibold
        tracking-[-0.03em]
        text-slate-900
    "
>

    Welcome,

    {" "}

    <span className="text-[#7367F0]">

        {firstName}

    </span>

    

</h1>





        {/* Subtitle */}

       <h2
    className="
        mt-5
        text-[24px]
        leading-8
        font-semibold
        tracking-[-0.03em]
        text-slate-700
    "
>
    Let's build your journey towards{" "}

    <span
        className="
            text-[#7367F0]
            font-bold
        "
    >
        {profile?.dreamRole || "your Dream Career"}
    </span>
</h2>





        {/* Description */}

     <p
    className="
        mt-3
        text-[16px]
        leading-6
        text-slate-500
        max-w-[520px]
    "
>

    You've already taken the first step by setting your career goal.

    <br />
    <br />

    Now let's replace uncertainty with clarity.

    Let's understand where you are today and build a plan toward your dream career.

</p>

<div
    className="
        mt-4
        rounded-2xl
        border
        border-violet-100
        bg-violet-50
        px-4
        py-3
    "
>

    <div className="flex items-start gap-4">

       <Compass
    size={18}
    className="text-[#7367F0]"
/>

        <div>

            <p
                className="
                    text-[15px]
                    font-semibold
                    text-slate-800
                "
            >
                Recommended Step
            </p>

            <p
                className="
                    mt-1
                    text-sm
                    text-slate-500
                    leading-6
                "
            >
                Explore Career Intelligence to understand your current stage,
                identify what to focus on next and receive personalized guidance.
            </p>

        </div>

    </div>

</div>




        {/* Button */}

      <div
    className="
        flex
        items-center
        gap-4
        mt-5
    "
>

    <button

        onClick={() =>
            navigate("/career-intelligence")
        }

        className="
            bg-[#7367F0]
            hover:bg-[#6658EA]
            transition-all
            duration-300
            text-white
            rounded-xl
            px-6
            py-3
            shadow-sm
            flex
            items-center
            gap-3
        "
    >

       <Sparkles
    size={20}
    className="shrink-0"
 />

        <div className="text-left">

            <p
                className="
                    text-sm
                    font-semibold
                    leading-none
                "
            >
                Explore Career Intelligence
            </p>

            <p
                className="
                    mt-1
                    text-[12px]
                    text-violet-200
                "
            >
                Takes about 3–5 minutes
            </p>

        </div>

    </button>

</div>
    </div>

{/* ================= RIGHT SIDE ================= */}

{/* ================= SNAPSHOT ================= */}

<div
    className="
        hidden
        xl:flex
        justify-center
        items-center
        w-[320px]
    "
>

    <div
        className="
            w-full
            rounded-3xl
            border
            border-[#ECEAF5]
            bg-white
            shadow-sm
            p-5
        "
    >

      <div
    className="
        inline-flex
        items-center
        gap-2
        rounded-full
        bg-violet-50
        text-violet-600
        px-4
        py-2
        text-[13px]
        font-medium
        mt-3
        mb-6
    "
>

    

    Your Snapshot

</div>

        <div className="mt-2 space-y-3">

            <div>

    <p className="text-xs text-slate-400
uppercase
tracking-wider
text-[11px]
font-medium">

        Career Goal

    </p>

   <p
        className="
            mt-1
            font-semibold
            text-slate-900
            leading-6
        "
    >
    {profile?.dreamRole || "Not selected yet"}
</p>

</div>

<hr className="border-[#F1F2F7]" />

<div>

    <p className="text-xs text-slate-400
uppercase
tracking-wider
text-[11px]
font-medium">

        Current Focus

    </p>

    <p
        className="
            mt-1
            font-semibold
            text-slate-900
            leading-6
        "
    >

       {careerFocus}

    </p>

</div>

<hr className="border-[#F1F2F7]" />
            <div>

                <p className="text-xs text-slate-400
uppercase
tracking-wider
text-[11px]
font-medium">
                    Current Stage
                </p>

                <p className="mt-1 font-semibold text-slate-900">
                    {currentStage}
                </p>

            </div>

            <hr className="border-[#F1F2F7]" />

            <div>

                <p className="text-xs text-slate-400
uppercase
tracking-wider
text-[11px]
font-medium">
                    Next Step
                </p>

                <div
    className="
        mt-2
        rounded-xl
        bg-violet-50
        border
        border-violet-100
        px-4
        py-3
    "
>

    <p
        className="
            text-sm
            font-semibold
            text-[#7367F0]
        "
    >
        {currentMilestone}
    </p>



</div>

            </div>

        </div>

    
        <p
            className="
                mt-3
                text-xs
                text-slate-400
                leading-6
            "
        >
            Your guidance becomes smarter as your profile grows.
        </p>

    </div>

</div>

</div>

    

{/* VALUE PREPOSITION */}


<div
  className="
    bg-white
    border
    border-[#ECEAF5]
    rounded-2xl
    px-7
    py-5
    mb-8
  "
>
  <div className="grid xl:grid-cols-5
md:grid-cols-3
grid-cols-1 gap-8">

    <ValueItem
      icon={<Target size={18} />}
      title="Personalized"
      subtitle="Tailored for you"
    />

    <ValueItem
      icon={<Bot size={18} />}
      title="AI-Powered"
      subtitle="Smart guidance"
    />

    <ValueItem
      icon={<BarChart3 size={18} />}
      title="Data-Driven"
      subtitle="Real insights"
    />

    <ValueItem
      icon={<Award size={18} />}
      title="Industry Aligned"
      subtitle="Market relevant"
    />

    <ValueItem
      icon={<ShieldCheck size={18} />}
      title="End-to-End"
      subtitle="Career support"
    />

  </div>
</div>





{/* NEXT ACTIONS */}

<div className="mb-10">

  <div className="mb-6">

    <h2
      className="
        text-2xl
        font-bold
        text-slate-900
      "
    >
      Recommended Next Actions
    </h2>

    <p
      className="
        text-slate-500
        mt-1
      "
    >
      Personalized recommendations based on your current progress.
    </p>

  </div>

  <div
    className="
      grid
      xl:grid-cols-3
      gap-4
    "
  >
    <ActionCard

title={
roadmap?.nextAction ||
"Resume Center"
}

description={
roadmap?.actionReason ||
"Improve your career profile."
}

buttonText="Continue"

route="/roadmap"

/>


<ActionCard
  title="Continue Your Roadmap"
  description="
Follow the next learning milestones towards your dream role.
"
  buttonText="Open Roadmap"
  route="/roadmap"
/>

<ActionCard
  title="Practice Mock Interview"
  description="
Prepare for technical and HR interviews with AI guidance.
"
  buttonText="Start Interview"
  route="/interview"
/>

  </div>

</div>





{/* AI CAREER INSIGHTS */}

<div
    className="
        mt-10
        bg-white
        rounded-3xl
        border
        border-[#ECEAF5]
        p-8
    "
>

    <div className="mb-8">

        <div className="flex items-center gap-3">

            <div
                className="
                    w-12
                    h-12
                    rounded-2xl
                    bg-violet-100
                    flex
                    items-center
                    justify-center
                    text-2xl
                "
            >
                🧠
            </div>

            <div>

                <h2
                    className="
                        text-2xl
                        font-bold
                        text-slate-900
                    "
                >
                    AI Career Insights
                </h2>

                <p
                    className="
                        text-slate-500
                        mt-1
                    "
                >
                    Personalized recommendations generated from your CareerOS profile.
                </p>

            </div>

        </div>

    </div>

    <div
        className="
            grid
            grid-cols-1
            md:grid-cols-2
            xl:grid-cols-4
            gap-6
        "
    >

        {

            careerInsights.map((item) => (

                <InsightCard
                    key={item.title}
                    {...item}
                />

            ))

        }

    </div>

</div>

{/* CAREER JOURNEY */}

<div className="mb-10">

  <div className="mb-6">

    <h2
      className="
        text-2xl
        font-bold
        text-slate-900
      "
    >
      Where are you in your career journey?
    </h2>

    <p
      className="
        text-slate-500
        mt-1
      "
    >
      Follow the path used by successful students to become industry-ready.
    </p>

  </div>

  <div
    className="
      grid
xl:grid-cols-6
md:grid-cols-3
grid-cols-1
      gap-4
    "
  >

   {
  journeyCards.map((card) => (

    <JourneyCard
      key={card.number}
      number={card.number}
      icon={card.icon}
      title={card.title}
      description={card.description}
      status={card.status}
      progress={card.progress}
      active={card.active}
      route={card.route}
    />

  ))
}
  </div>

</div>



{/* WHAT CAREEROS CAN DO FOR YOU */}

<div className="mb-10">

  <div className="flex items-center justify-between mb-6">

    <div>

      <h2
        className="
          text-2xl
          font-bold
          text-slate-900
        "
      >
        What CareerOS Can Do For You
      </h2>

      <p
        className="
          text-slate-500
          mt-1
        "
      >
        Powerful tools designed to accelerate your career growth.
      </p>

    </div>

    <button
      className="
        text-violet-600
        font-medium
        text-sm
      "
    >
      Explore All Features →
    </button>

  </div>

  <div
    className="
      grid
      xl:grid
grid-cols-1
md:grid-cols-2
xl:grid-cols-4
gap-6

md:grid-cols-2

grid-cols-1
      gap-4
    "
  >

   <FeatureCard

icon={<Target size={22}/>}

badge="AI"

title="Skill Gap Analysis"

description="
Identify missing skills and
understand what employers expect.
"

route="/skill-gap"

/>

<FeatureCard

icon={<Map size={22}/>}

badge="AI"

title="Career Roadmap"

description="
Receive a personalized AI roadmap
to achieve your dream role.
"

route="/roadmap"

/>

<FeatureCard

icon={<BookOpen size={22}/>}

badge="Resources"

title="Learning Resources"

description="
Curated courses, tutorials
and learning paths.
"

route="/resources"

/>


<FeatureCard

icon={<Rocket size={22}/>}

badge="Projects"

title="Project Portfolio"

description="
Build industry-grade projects
and improve project intelligence.
"

route="/projects"

/>

<FeatureCard

icon={<FileText size={22}/>}

badge="Resume"

title="Resume Center"

description="
Resume analysis,
ATS optimization
and refinement.
"

route="/resume-center"

/>

<FeatureCard

icon={<User size={22}/>}

badge="Interview"

title="Mock Interview"

description="
Practice technical
and HR interviews
with AI.
"

route="/interview"

/>


<FeatureCard

icon={<Briefcase size={22}/>}

badge="Jobs"

title="Opportunities"

description="
Discover internships
and jobs aligned
with your profile.
"

route="/jobs"

/>



<FeatureCard

icon={<Brain size={22}/>}

badge="Insights"

title="Career Intelligence"

description="
Track strengths,
readiness and
career insights.
"

route="/career-intelligence"

/>

  </div>

</div>


{/* CAREER SNAPSHOT */}

<div className="mb-10">

  <div className="mb-6">

    <h2
      className="
        text-2xl
        font-bold
        text-slate-900
      "
    >
      Your Career Snapshot
    </h2>

    <p
      className="
        text-slate-500
        mt-1
      "
    >
      A quick overview of your current career readiness.
    </p>

  </div>

  <div
    className="
      grid
      xl:grid-cols-5
      md:grid-cols-2
      grid-cols-1
      gap-4
    "
  >

  
<SnapshotCard

title="ATS Score"

value={resumeAnalysis?.atsScore ?? 0}

icon={<BarChart3 size={22}/>}

iconBg="bg-blue-50"
iconColor="text-blue-600"
progressColor="bg-blue-500"

subtitle="Compatibility with ATS systems."

/>

<SnapshotCard

title="Resume Score"

value={resumeAnalysis?.resumeScore ?? 0}

icon={<FileText size={22}/>}

iconBg="bg-violet-50"
iconColor="text-violet-600"
progressColor="bg-violet-500"

subtitle="AI evaluation of your resume."

/>



<SnapshotCard

title="Project Score"

value={projectIntel?.overallProjectScore ?? 0}

icon={<Rocket size={22}/>}

iconBg="bg-emerald-50"
iconColor="text-emerald-600"
progressColor="bg-emerald-500"

subtitle="Overall quality of your projects."

/>

<SnapshotCard

title="Internship Readiness"

value={internshipScore ?? 0}

icon={<Briefcase size={22}/>}

iconBg="bg-amber-50"
iconColor="text-amber-600"
progressColor="bg-amber-500"

subtitle="Likelihood of internship success."

/>

<SnapshotCard

title="Role Alignment"

value={projectIntel?.averageRoleAlignment ?? 0}

icon={<Target size={22}/>}

iconBg="bg-pink-50"
iconColor="text-pink-600"
progressColor="bg-pink-500"

subtitle="Alignment with your dream role."

/>



  </div>

</div>



        </div>

      </div>

    </>

  );

}

function ValueItem({
  icon,
  title,
  subtitle
}) {
  return (
    <div className="flex items-center gap-3">

      <div
        className="
          w-10
          h-10
          rounded-xl
          bg-violet-50
          text-violet-600
          flex
          items-center
          justify-center
        "
      >
        {icon}
      </div>

      <div>
        <p className="text-sm font-semibold">
          {title}
        </p>

        <p className="text-xs text-slate-500">
          {subtitle}
        </p>
      </div>

    </div>
  );
}


function JourneyCard({

  number,

  icon,

  title,

  description,

  status,

  progress,

  active,

  route

}){

  const navigate = useNavigate();

  return (

    <div

onClick={() => navigate(route)}

className={`
        relative
        bg-white
        border
        rounded-2xl
        p-5
        cursor-pointer
        transition-all
duration-300
hover:-translate-y-1
hover:shadow-lg

        ${
          active
            ? "border-violet-300 shadow-sm"
            : "border-[#ECEAF5]"
        }
      `}
    >

      <div
        className="
          absolute
          top-3
          left-3
          w-6
          h-6
          rounded-full
          bg-violet-100
          text-violet-600
          text-xs
          font-semibold
          flex
          items-center
          justify-center
        "
      >
        {number}
      </div>

      <div
        className="
          mt-6
          mb-4
          w-12
          h-12
          rounded-2xl
          bg-violet-50
          text-violet-600
          flex
          items-center
          justify-center
        "
      >
        {icon}
      </div>

      <h3
        className="
          font-semibold
          text-slate-900
          mb-2
        "
      >
        {title}
      </h3>

      <p
        className="
          text-xs
          leading-6
          text-slate-500
          min-h-[70px]
        "
      >
        {description}
      </p>

     <div className="mt-5">

  <div
    className="
      flex
      items-center
      justify-between
      text-xs
      mb-2
    "
  >

    <span
      className={`
        px-2
        py-1
        rounded-full
        font-medium

        ${
          active

          ?

          "bg-violet-100 text-violet-700"

          :

          "bg-slate-100 text-slate-600"

        }
      `}
    >

      {status}

    </span>

    <span
      className="
        font-semibold
        text-slate-600
      "
    >

      {progress}%

    </span>

  </div>

  <div
    className="
      h-2
      bg-slate-100
      rounded-full
      overflow-hidden
    "
  >

  <div

className="
    h-full
    bg-[#7367F0]
    rounded-full
    transition-all
    duration-700
"

      style={{
        width: `${progress}%`
      }}

    />

  </div>

</div>

    </div>

  );

}


function FeatureCard({

icon,

title,

description,

route,

badge = "AI"

}) {

  const navigate = useNavigate();

  return (

    <div

      onClick={() => navigate(route)}

      className="
        group
        bg-white
        border
        border-[#ECEAF5]
        rounded-3xl
        p-6
        cursor-pointer
        transition-all
        duration-300
        hover:-translate-y-1
        hover:shadow-xl
        hover:border-[#7367F0]/30
      "
    >

      {/* TOP */}

      <div className="flex items-start justify-between">

       <div
  className="
    w-12
    h-12
    rounded-2xl
    bg-violet-50
    text-[#7367F0]
    flex
    items-center
    justify-center
  "
>

  {icon}

</div>

        <span
          className="
            text-[11px]
            font-semibold
            uppercase
            tracking-wide
            px-3
            py-1
            rounded-full
            bg-violet-50
            text-[#7367F0]
          "
        >
          {badge}
        </span>

      </div>

      {/* TITLE */}

      <h3
        className="
          mt-6
          text-lg
          font-semibold
          text-slate-900
        "
      >
        {title}
      </h3>

      {/* DESCRIPTION */}

      <p
        className="
          mt-3
          text-sm
          leading-6
          text-slate-500
          min-h-[72px]
        "
      >
        {description}
      </p>

      {/* FOOTER */}

      <div
        className="
          mt-6
          flex
          items-center
          justify-between
        "
      >

        <span
          className="
            text-sm
            text-slate-400
          "
        >
          Open Feature
        </span>

        <div
          className="
            w-9
            h-9
            rounded-xl
            bg-slate-100
            flex
            items-center
            justify-center
            transition-all
            duration-300
            group-hover:bg-[#7367F0]
            group-hover:text-white
          "
        >
          →
        </div>

      </div>

    </div>

  );

}


function SnapshotCard({

title,

value,

icon,

subtitle,

iconBg,

iconColor,

progressColor

})

{

    const getStatus = () => {

        if (value >= 85)
            return "Excellent";

        if (value >= 70)
            return "Good";

        if (value >= 55)
            return "On Track";

        return "Needs Work";

    };



    const getStatusColor = () => {

        if (value >= 85)
            return "bg-emerald-100 text-emerald-700";

        if (value >= 70)
            return "bg-blue-100 text-blue-700";

        if (value >= 55)
            return "bg-amber-100 text-amber-700";

        return "bg-red-100 text-red-700";

    };

    return (

        <div
            className="
                bg-white
                border
                border-[#ECEAF5]
                rounded-3xl
                p-6
                hover:shadow-lg
                transition-all
                duration-300
            "
        >

            <div
                className="
                    flex
                    items-center
                    justify-between
                    mb-5
                "
            >

               <div
    className={`
        w-12
        h-12
        rounded-2xl
        flex
        items-center
        justify-center

        ${iconBg}
        ${iconColor}
    `}
>
                    {icon}
                </div>

                <span
                    className={`
                        px-3
                        py-1
                        rounded-full
                        text-xs
                        font-semibold
                        ${getStatusColor()}
                    `}
                >
                    {getStatus()}
                </span>

            </div>

            <p
                className="
                    text-sm
                    text-slate-500
                "
            >
                {title}
            </p>

            <h2
                className="
                    mt-2
                    text-4xl
                    font-bold
                    text-slate-900
                "
            >
                {value}%
            </h2>

            <div
                className="
                    mt-5
                    h-2
                    rounded-full
                    bg-slate-100
                    overflow-hidden
                "
            >

                <div

                    style={{
                        width: `${value}%`
                    }}

                    className={`
    h-full
    rounded-full
    transition-all
    duration-700

    ${progressColor}
`}
                />

            </div>

            <p
                className="
                    mt-4
                    text-sm
                    text-slate-500
                "
            >
                {subtitle}
            </p>

        </div>

    );

}



function InsightCard({

    icon,

    title,

    value,

    color

}) {

    const colors = {

        emerald:
            "bg-emerald-50 text-emerald-600",

        amber:
            "bg-amber-50 text-amber-600",

        violet:
            "bg-violet-50 text-violet-600",

        blue:
            "bg-blue-50 text-blue-600"

    };

    return (

        <div
            className="
                border
                border-[#ECEAF5]
                rounded-2xl
                p-6
                hover:shadow-lg
                transition-all
                duration-300
            "
        >

            <div
                className={`
                    w-12
                    h-12
                    rounded-2xl
                    flex
                    items-center
                    justify-center
                    text-xl
                    mb-5

                    ${colors[color]}
                `}
            >
                {icon}
            </div>

            <p
                className="
                    text-sm
                    text-slate-500
                "
            >
                {title}
            </p>

            <h3
                className="
                    mt-2
                    font-semibold
                    text-slate-900
                    leading-7
                "
            >
                {value}
            </h3>

        </div>

    );

}

function ActionCard({
  title,
  description,
  buttonText,
  route
}) {

  const navigate = useNavigate();

 

  return (

    <div
      className="
        bg-white
        border
        border-[#ECEAF5]
        rounded-2xl
        p-6
      "
    >

      <h3
        className="
          text-lg
          font-semibold
          mb-3
        "
      >
        {title}
      </h3>

      <p
        className="
          text-slate-500
          text-sm
          leading-6
          mb-5
        "
      >
        {description}
      </p>

      <button
        onClick={() =>
          navigate(route)
        }
        className="
          bg-[#7367F0]
          text-white
          px-4
          py-2
          rounded-xl
          text-sm
          font-medium
        "
      >
        {buttonText}
      </button>

    </div>

  );

}


export default DashboardPage;