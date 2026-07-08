import {
  LayoutDashboard,
  User,
  Target,
  Map,
  BookOpen,
  FolderGit2,
  FileText,
  PenSquare,
  BarChart,
  Briefcase,
  ClipboardList,
  SearchCheck,
  Mic,
  FileSearch,
  TrendingUp,
  Brain,
  Bot,
  MessageSquare,
  LogOut,
  Sparkles
} from "lucide-react";


import {
  Link,
  useLocation,
  useNavigate
} from "react-router-dom";

function Sidebar() {

  const navigate = useNavigate();

  const location = useLocation();

  const role =
    localStorage.getItem("role");

  const handleLogout = () => {

    localStorage.clear();

    navigate("/");

  };

  const menuClass = (path) => `
flex
items-center
gap-3
px-3
py-2.5
rounded-xl
transition-all
duration-200
text-[14px]
font-medium

${
location.pathname === path
? `
bg-[#F4F2FF]
text-[#7367F0]
`
: `
text-slate-600
hover:bg-slate-50
hover:text-slate-900
`
}
`;

  return (

    <div
className="
fixed
left-0
top-0
h-screen
w-[240px]
overflow-y-auto
bg-white
border-r
border-[#ECEAF5]
px-4
py-4
"
>

      {/* LOGO */}

      <div
className="
flex
items-center
gap-2
px-2
pb-5
border-b
border-[#F0EEF8]
mb-5
"
>

       <div
className="
w-8
h-8
rounded-xl
bg-[#7367F0]
flex
items-center
justify-center
text-white
"
>

          <Sparkles size={20} />

        </div>

        <h1 className="
          text-lg
font-semibold
text-slate-800
          font-bold
        ">
          CareerOS
        </h1>

      </div>


      {/* CORE */}

      <Section title="CORE" />

<div className="space-y-2">

  <Link
    to="/dashboard"
    className={menuClass("/dashboard")}
  >
    <LayoutDashboard size={16}
strokeWidth={1.8}/>
    Dashboard
  </Link>

  <Link
    to="/profile"
    className={menuClass("/profile")}
  >
    <User size={16}
strokeWidth={1.8}/>
    My Profile
  </Link>

  <Link
    to="/career-intelligence"
    className={menuClass("/career-intelligence")}
  >
    <Brain size={16}
strokeWidth={1.8}/>
    Career Intelligence
  </Link>

  <Link
    to="/copilot"
    className={menuClass("/copilot")}
  >
    <Bot size={16}
strokeWidth={1.8}/>
    Career Copilot
  </Link>

</div>

      {/* CAREER PLANNING */}

      <Section title="CAREER PLANNING" />

      <div className="space-y-2">

        <Link
          to="/skill-gap"
          className={menuClass("/skill-gap")}
        >
          <Target size={16}
strokeWidth={1.8}/>
          Skill Gap Analysis
        </Link>

        <Link
          to="/roadmap"
          className={menuClass("/roadmap")}
        >
          <Map size={16}
strokeWidth={1.8}/>
          Career Roadmap
        </Link>

        <Link
          to="/resources"
          className={menuClass("/resources")}
        >
          <BookOpen size={16}
strokeWidth={1.8}/>
          Learning Resources
        </Link>

      </div>



      {/* CAREER READINESS */}

      <Section title="PROJECTS & RESUME" />

<div className="space-y-2">

  <Link
    to="/projects"
    className={menuClass("/projects")}
  >
    <FolderGit2 size={16}
strokeWidth={1.8}/>
    Project Portfolio
  </Link>

  <Link
    to="/resume-center"
    className={menuClass("/resume-center")}
  >
    <FileText size={16}
strokeWidth={1.8}/>
    Resume Center
  </Link>

  <Link
    to="/resume-refinement"
    className={menuClass("/resume-refinement")}
  >
    <PenSquare size={16}
strokeWidth={1.8}/>
    Resume Refinement
  </Link>

  <Link
    to="/ats"
    className={menuClass("/ats")}
  >
    <BarChart size={16}
strokeWidth={1.8}/>
    ATS Analysis
  </Link>

</div>


      {/* OPPORTUNITIES */}

     <Section title="OPPORTUNITIES" />

<div className="space-y-2">

  <Link
    to="/jobs"
    className={menuClass("/jobs")}
  >
    <Briefcase size={16}
strokeWidth={1.8}/>
    Job Recommendations
  </Link>

  <Link
    to="/internships"
    className={menuClass("/internships")}
  >
    <ClipboardList size={16}
strokeWidth={1.8}/>
    Internship Opportunities
  </Link>

  <Link
    to="/internship-analyzer"
    className={menuClass("/internship-analyzer")}
  >
    <SearchCheck size={16}
strokeWidth={1.8}/>
    Internship Analyzer
  </Link>

  <Link
    to="/application-tracker"
    className={menuClass("/application-tracker")}
  >
    <FileSearch size={16}
strokeWidth={1.8}/>
    Application Tracker
  </Link>

</div>



{/* INTERVIEW SECTION  */}




<Section title="INTERVIEW PREPARATION" />

<div className="space-y-2">

  <Link
    to="/interview"
    className={menuClass("/interview")}
  >
    <Mic size={16}
strokeWidth={1.8}/>
    Mock Interview
  </Link>

  <Link
    to="/interview-prep"
    className={menuClass("/interview-prep")}
  >
    <MessageSquare size={16}
strokeWidth={1.8}/>
    Interview Prep
  </Link>

</div>



      {/* TRACKING */}

      <Section title="CAREER READINESS" />

<div className="space-y-2">

  <Link
    to="/progress"
    className={menuClass("/progress")}
  >
    <TrendingUp size={16}
strokeWidth={1.8}/>
    Career Progress
  </Link>


</div>
      {/* AI */}

     <Section title="AI TOOLS" />

<div className="space-y-2">

  <Link
    to="/ai-mentor"
    className={menuClass("/ai-mentor")}
  >
    <MessageSquare size={16}
strokeWidth={1.8}/>
    AI Career Mentor
  </Link>

</div>

<div
  className="
    mt-8
    rounded-2xl
    bg-gradient-to-br
    from-[#F7F5FF]
    to-[#EEF2FF]
    p-4
  "
>
  <div className="mb-3">

    <p
      className="
        font-semibold
        text-sm
        text-slate-800
      "
    >
      AI Career Copilot
    </p>

    <p
      className="
        text-xs
        text-slate-500
        mt-1
        leading-relaxed
      "
    >
      Get personalized career guidance,
      roadmaps and interview support.
    </p>

  </div>

  <button
    onClick={() => navigate("/copilot")}
    className="
      w-full
      bg-[#7367F0]
      text-white
      py-2
      rounded-xl
      text-xs
      font-medium
    "
  >
    Chat Now
  </button>

</div>

      {
        role === "ADMIN" && (

          <>

            <Link
              to="/admin"
              className={menuClass("/admin")}
            >
              <User size={16}
strokeWidth={1.8}/>
              Admin Dashboard
            </Link>
          </>
        )
      }

     

    </div>

  );

}

function Divider() {

  return (
    <hr className="
      my-7
      border-white/10
    " />
  );

}

function Section({ title }) {
  return (
    <p
      className="
      px-3
      mt-6
      mb-2
      text-[10px]
      font-semibold
      uppercase
      tracking-[0.18em]
      text-slate-400
      "
    >
      {title}
    </p>
  );
}

export default Sidebar;