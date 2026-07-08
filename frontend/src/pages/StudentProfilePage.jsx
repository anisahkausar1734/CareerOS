import { useEffect, useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";
import ResumeCard from "../components/resume/ResumeCard";
import { useNavigate } from "react-router-dom";
import {
  Mail,
  Phone,
  GraduationCap,
  User,
  Info,
  Trophy,
  Target,
  CheckCircle2,
  Check,
  Circle,
  Map,
  FileText,
  BriefcaseBusiness,
  TrendingUp,
  ArrowRight,
  Zap,
  Users,
  Briefcase,
  Brain,
  CircleUserRound
} from "lucide-react";

import {
    University,
    BookOpen,
    GitBranch,
    CalendarDays
} from "lucide-react";

function ProfilePage() {

    const [profile, setProfile] = useState(null);

    const email = localStorage.getItem("email");

    const navigate = useNavigate();

    useEffect(() => {
        fetchProfile();
    }, []);

    const fetchProfile = async () => {

        try {

            const response = await axios.get(
                `http://localhost:8080/api/student-profile/${email}`
            );

            setProfile(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    if (!profile) {

        return (

            <div className="min-h-screen flex items-center justify-center">

                Loading...

            </div>

        );

    }


    const profileCompletion = [

    true,                               // Personal Information

    !!profile?.dreamRole,               // Career Goal

    (profile?.skills?.length || 0) > 0, // Skills

    !!profile?.hasResume                // Resume Uploaded

];

const completionPercentage = Math.round(

    (profileCompletion.filter(Boolean).length / profileCompletion.length) * 100

);

    return (

        <div className="min-h-screen bg-[#F7F7FB] flex">

            <Sidebar />
<main
className="
ml-[235px]
min-h-screen
bg-[#F7F8FC]
overflow-y-auto
"
>

<div
className="
max-w-[1350px]
px-8
pt-7
pb-10
"
>
    
                       {/* ================= PAGE HEADER ================= */}

<div
    className="
        flex
        items-start
        justify-between
        mb-2
    "
>

    <div>

       
       <h1
            className="
                text-[34px]
                leading-none
                tracking-[-0.03em]
                font-bold
                tracking-tight
                text-slate-900
            "
        >
            My Profile
        </h1>

        <p
            className="
                mt-4
                text-[16px]
                leading-7
                text-slate-500
                max-w-3xl
            "
        >
            Manage your personal information, academic details and career
            preferences that personalize your CareerOS experience.
        </p>

    </div>

    <button

       onClick={() =>
    navigate("/profile/edit")
}

        className="
            h-11
            px-6
            rounded-xl
            bg-[#7367F0]
            hover:bg-[#6558EA]
            text-white
            font-medium
            shadow-sm
            transition
        "
    >

        Edit Profile

    </button>

</div>
                   {/* ================= HERO CARD ================= */}

<div
    className="
        relative
        overflow-hidden
        rounded-[26px]
        border
        border-[#ECEAF5]
        bg-white
        px-10
        py-5
        space-y-6
    "
>

    {/* Glow */}

    <div
        className="
            absolute
            right-0
            top-0
            w-[260px]
            h-[260px]
            rounded-full
            bg-violet-300/20
            blur-[90px]
        "
    />

    <div
className="
grid
grid-cols-1
lg:grid-cols-[1.0fr_1fr_0.95fr]
divide-y
lg:divide-y-0
lg:divide-x
divide-[#ECEAF5]
"
>

        

       {/* ================= LEFT ================= */}

<div
className="
flex
items-center
gap-6
pr-10
py-2
"
>

    {/* Avatar */}

    <div
        className="
            w-[90px]
            h-[90px]
            rounded-full
            bg-gradient-to-br
            from-[#7367F0]
            to-[#8E6CF7]
            flex
            items-center
            justify-center
            text-white
            text-[38px]
            font-bold
            shadow-lg
            shrink-0
        "
    >

        {profile?.fullName?.charAt(0)?.toUpperCase()}

    </div>

    {/* Details */}

    <div className="min-w-0">

        <h2
            className="
                text-[20px]
                font-bold
                text-[#1E2340]
                leading-none
                tracking-[-0.02em]
            "
        >
            {profile?.fullName}
        </h2>

        <p
            className="
                mt-2
                text-[15px]
                font-semibold
                text-[#5B46E5]
            "
        >
            {profile?.dreamRole || "Dream Role"}
        </p>

        {/* Email */}

        <div
            className="
                flex
                items-center
                gap-2
                mt-5
            "
        >

            <Mail
                size={15}
                strokeWidth={2}
                className="text-slate-700"
            />

            <p
                className="
                    text-[14px]
                    text-slate-800
                    
                "
            >
                {profile?.email}
            </p>

        </div>

        {/* Phone */}

        <div
            className="
                flex
                items-center
                gap-2
                mt-2
            "
        >

            <Phone
                size={15}
                strokeWidth={2}
                className="text-slate-700"
            />

            <p
                className="
                    text-[14px]
                    text-slate-800 
                "
            >
                {profile?.phoneNumber || "Not Added"}
            </p>

        </div>

    </div>

</div>
        {/* CENTER */}

        <div className="px-10">

    <div className="flex items-center gap-2">

          <div
        className="
            w-8
            h-8
            rounded-xl
            bg-violet-100
            flex
            items-center
            justify-center
        "
    >
          <CheckCircle2   size={16}
            strokeWidth={2}
            className="text-[#6B5CF6]" />
    </div>

        <p
             className="
                        text-[18px]
                        font-medium
                        text-slate-500
                    "
        >
            Career Readiness
        </p>

      

    </div>

    <h3
        className="
            mt-4
            text-[30px]
            leading-none
            font-bold
            text-[#6B5CF6]
        "
    >
        {profile?.careerReadiness || 0}%
    </h3>

    <div
        className="
            mt-4
            h-[7px]
            rounded-full
            bg-[#ECEEF7]
            overflow-hidden
        "
    >

        <div
            className="
                h-full
                rounded-full
                bg-gradient-to-r
                from-[#6B5CF6]
                to-[#8C72FF]
            "
            style={{
                width: `${profile?.careerReadiness || 0}%`
            }}
        />

    </div>

    <p
       className="
                        mt-1
                        text-[14px]
                        font-medium
                        text-[#1E2340]
                        break-all
                    "
    >
        Keep going, you're doing great! 🚀
    </p>

</div>

        {/* RIGHT */}

        <div className="pl-10">



            <div className="flex items-center gap-2">

    <div
        className="
            w-8
            h-8
            rounded-xl
            bg-violet-100
            flex
            items-center
            justify-center
        "
    >
        <Trophy
            size={16}
            strokeWidth={2}
            className="text-[#6B5CF6]"
        />
    </div>

    <p
         className="
                        text-[18px]
                        font-medium
                        text-slate-500
                    "
    >
        Current Stage
    </p>

</div>
          <h3
    className="
        mt-4
        text-[16px]
        font-semibold
        tracking-[-0.02em]
        text-slate-800
        leading-tight
    "

>
    {
        profile?.currentStage
            ?.replaceAll("_", " ")
            ?.toLowerCase()
            ?.replace(/\b\w/g, c => c.toUpperCase())
        || "Getting Started"
    }
</h3>

            <span
                className="
                    inline-flex
                    mt-5
                    rounded-full
                    bg-violet-100
                    text-violet-700
                    px-3
                    py-1.5
                    text-sm
                    font-medium
                "
            >
                Career Journey Active
            </span>

        </div>

    </div>

</div>


{/* ================= PROFILE INFORMATION ================= */}

<div
   className="
mt-4
grid
grid-cols-1
xl:grid-cols-3
gap-4
items-start
"
>

  {/* ================= PERSONAL INFORMATION ================= */}

<div
    className="
        bg-white
        border
        border-[#ECEAF5]
        rounded-[18px]
        px-6
        py-5
        h-[260px]
    "
>

    {/* Header */}

    <div className="flex items-center gap-2.5">

        <div
            className="
                w-7
                h-7
                rounded-lg
                bg-[#F4F1FF]
                flex
                items-center
                justify-center
            "
        >
            <User
                size={15}
                strokeWidth={2.2}
                className="text-[#6B5CF6]"
            />
        </div>

        <h3
             className="
                    text-[20px]
                    font-semibold
                    text-[#1E2340]
                "
        >
            Personal Information
        </h3>

    </div>

    {/* Information */}

    <div className="mt-7 space-y-5">

        {/* Email */}

        <div className="flex items-start gap-3">

            <Mail
                size={16}
                strokeWidth={2}
                className="text-[#6B5CF6] mt-[2px] shrink-0"
            />

            <div>

                <p
                    className="
                        text-[13px]
                        font-medium
                        text-slate-500
                    "
                >
                    Email
                </p>

                <p
                    className="
                        mt-1
                        text-[14px]
                        font-medium
                        text-[#1E2340]
                        break-all
                    "
                >
                    {profile?.email || "Not Added"}
                </p>

            </div>

        </div>

        {/* Phone */}

        <div className="flex items-start gap-3">

            <Phone
                size={16}
                strokeWidth={2}
                className="text-[#6B5CF6] mt-[2px] shrink-0"
            />

            <div>

                <p
                   className="
                        text-[13px]
                        font-medium
                        text-slate-500
                    "
                >
                    Phone
                </p>

                <p
                   className="
                        mt-1
                        text-[14px]
                        font-medium
                        text-[#1E2340]
                        break-all
                    "
                >
                    {profile?.phoneNumber || "Not Added"}
                </p>

            </div>

        </div>

    </div>

</div>

    {/* ACADEMIC */}

    <div
       className="
bg-white
border
border-[#ECEAF5]
rounded-[18px]
px-6
py-5
h-[260px]
"
    >

{/* Header */}

<div className="flex items-center gap-2.5">

    <div
        className="
            w-7
            h-7
            rounded-lg
            bg-[#F4F1FF]
            flex
            items-center
            justify-center
        "
    >
        <GraduationCap
            size={15}
            strokeWidth={2.2}
            className="text-[#6B5CF6]"
        />
    </div>

    <h3
        className="
                    text-[20px]
                    font-semibold
                    text-[#1E2340]
                "
    >
        Academic Information
    </h3>

</div>

{/* Rows */}

<div className="mt-2.5">

    {/* College */}

    <div className="
flex
items-center
justify-between
h-10
border-b
border-[#F1F2F7]
">

        <div className="flex items-center gap-1.5">

            <University
                size={15}
                className="text-slate-500"
            />

            <span className="
                        text-[13px]
                        font-medium
                        text-slate-500
                    ">
                College
            </span>

        </div>

        <span className="
                        mt-1
                        text-[14px]
                        font-medium
                        text-[#1E2340]
                        break-all
                    ">
            {profile.collegeName || "--"}
        </span>

    </div>

    {/* Degree */}

    <div className="
flex
items-center
justify-between
h-10
border-b
border-[#F1F2F7]
">

        <div className="flex items-center gap-1.5">

            <BookOpen
                size={15}
                className="text-slate-500"
            />

            <span className="
                        text-[13px]
                        font-medium
                        text-slate-500
                    ">
                Degree
            </span>

        </div>

        <span className="
                        mt-1
                        text-[14px]
                        font-medium
                        text-[#1E2340]
                        break-all
                    ">
            {profile.degree || "--"}
        </span>

    </div>

    {/* Branch */}

    <div className="
flex
items-center
justify-between
h-10
border-b
border-[#F1F2F7]
">

        <div className="flex items-center gap-1.5">

            <GitBranch
                size={15}
                className="text-slate-500"
            />

            <span className="
                        text-[13px]
                        font-medium
                        text-slate-500
                    ">
                Branch
            </span>

        </div>

        <span className="
                        mt-1
                        text-[14px]
                        font-medium
                        text-[#1E2340]
                        break-all
                    ">
            {profile.branch || "--"}
        </span>

    </div>

     <div className="
flex
items-center
justify-between
h-10
border-b
border-[#F1F2F7]
">

    <div className="flex items-center gap-1.5">

        <GraduationCap
            size={15}
            className="text-slate-500"
        />

        <span className="
                        text-[13px]
                        font-medium
                        text-slate-500
                    ">
            Graduation Year
        </span>

    </div>

    <span className="
                        mt-1
                        text-[14px]
                        font-medium
                        text-[#1E2340]
                        break-all
                    ">
        {profile.graduationYear || "--"}
    </span>

</div>

    {/* Current Year */}

    <div className="flex items-center justify-between h-10">

        <div className="flex items-center gap-1.5">

            <CalendarDays
                size={15}
                className="text-slate-500"
            />

            <span className="
                        text-[13px]
                        font-medium
                        text-slate-500
                    ">
                Current Year
            </span>

        </div>

        <span className="
                        mt-1
                        text-[14px]
                        font-medium
                        text-[#1E2340]
                        break-all
                    ">
            {profile.currentYear || "--"}
        </span>

    </div>

   

</div>
       
    </div>



    
{/* ================= CAREER PROFILE ================= */}

<div
    className="
        bg-white
        border
        border-[#ECEAF5]
        rounded-[18px]
        px-6
        py-5
        h-[260px]
    "
>

    {/* Header */}

    <div className="flex items-center gap-2.5">

        <div
            className="
                w-7
                h-7
                rounded-lg
                bg-[#F4F1FF]
                flex
                items-center
                justify-center
            "
        >
            <Target
                size={15}
                strokeWidth={2.2}
                className="text-[#6B5CF6]"
            />
        </div>

        <h3
            className="
                    text-[20px]
                    font-semibold
                    text-[#1E2340]
                "
        >
            Career Profile
        </h3>

    </div>

    {/* Content */}

    <div className="mt-5">

        {/* Dream Role */}

        <div
            className="
                flex
                items-center
                justify-between
                h-10
                border-b
                border-[#F1F2F7]
            "
        >

            <div className="flex items-center gap-2">

                <Briefcase
                    size={14}
                    className="text-slate-700"
                />

                <span className="
                        text-[13px]
                        font-medium
                        text-slate-500
                    ">
                    Dream Role
                </span>

            </div>

            <span
               className="
                        mt-1
                        text-[14px]
                        font-medium
                        text-[#1E2340]
                        break-all
                    "
            >
                {profile.dreamRole || "--"}
            </span>

        </div>

        {/* Skills */}

        <div
            className="
                flex
                items-center
                justify-between
                h-10
                border-b
                border-[#F1F2F7]
            "
        >

            <div className="flex items-center gap-2">

                <Users
                    size={14}
                    className="text-slate-700"
                />

                <span className="
                        text-[13px]
                        font-medium
                        text-slate-500
                    ">
                    Skills
                </span>

            </div>

            {profile.skills?.length ? (

                <span
                    className="
                        px-2.5
                        py-1
                        rounded-full
                        bg-violet-50
                        text-[#6B5CF6]
                        text-[13px]
                        font-semibold
                    "
                >
                    {profile.skills.length} Skills Added
                </span>

            ) : (

                <span
                   className="
                        mt-1
                        text-[14px]
                        font-medium
                        text-[#1E2340]
                        break-all
                    "
                >
                    No Skills Added
                </span>

            )}

        </div>

        {/* Resume Status */}

       <div
    className="
        flex
        items-center
        justify-between
        h-10
    "
>

    <div className="flex items-center gap-2">

        <FileText
            size={14}
            className="text-slate-700"
        />

        <span
            className="
                text-[13px]
                font-medium
                text-slate-500
            "
        >
            Resume Status
        </span>

    </div>

    <span
        className={`
            px-2.5
            py-1
            rounded-full
            text-[11px]
            font-semibold
            ${
                profile?.hasResume
                    ? "bg-emerald-50 text-emerald-700"
                    : "bg-amber-50 text-amber-700"
            }
        `}
    >

        {profile?.hasResume ? "Uploaded" : "Not Uploaded"}

    </span>

</div>
    </div>

</div>

</div>


{/* ================= CAREER JOURNEY ================= */}

<div
    className="
        mt-6
        bg-white
        border
        border-[#ECEAF5]
        rounded-[20px]
        px-6
        py-5
    "
>

    {/* Header */}

    <div className="flex items-center justify-between">

        <div className="flex items-center gap-3">

             <div
                        className="
                            w-10
                            h-10
                            rounded-2xl
                            bg-violet-50
                            flex
                            items-center
                            justify-center
                        "
                    >

                         <TrendingUp
                size={20}
                className="text-[#6B5CF6]"
            />

                    </div>

            <h3
                className="
                    text-[20px]
                    font-semibold
                    text-[#1E2340]
                "
            >
                Career Journey Progress
            </h3>

        </div>

        <button
            className="
                flex
                items-center
                gap-1
                text-[14px]
                font-medium
                text-[#6B5CF6]
                hover:text-[#5B46E5]
            "
        >
            View Full Journey

            <ArrowRight size={15} />
        </button>

    </div>

    {/* Timeline */}

    <div className="relative mt-6">

        {/* Connecting Line */}

        <div
            className="
                absolute
                left-[9%]
                right-[9%]
                top-[30px]
                border-t-2
                border-dashed
                border-[#D9D6F9]
            "
        />

        <div
            className="
                relative
                flex
                items-start
                justify-between
            "
        >

            <JourneyStep
                title="Skill Gap"
                completed={profile.skillGapCompleted}
                first
            />

            <JourneyStep
                title="Roadmap"
                completed={profile.roadmapCompleted}
            />

            <JourneyStep
                title="Resume"
                completed={profile.resumeAnalysisCompleted}
            />

            <JourneyStep
                title="Interview"
                completed={profile.interviewCompleted}
            />

            <JourneyStep
                title="Applications"
                completed={profile.applicationsStarted}
                last
            />

        </div>

    </div>

</div>

{/* ================= RESUME ================= */}

<div
    className="
        mt-6
        bg-white
        border
        border-[#ECEAF5]
        rounded-[20px]
        px-6
        py-5
    "
>

    <ResumeCard
        email={email}
        compact
    />

</div>

{/* ================= BOTTOM SECTION ================= */}

<div
    className="
        mt-6
        grid
        grid-cols-1
        xl:grid-cols-[1.15fr_1fr]
        gap-5
        items-stretch
    "
>

   {/* ================= PROFILE COMPLETION ================= */}

<div
   className="
    h-full
    bg-white
    border
    border-[#ECEAF5]
    rounded-[22px]
    px-7
    py-6
    flex
    flex-col
"
>

    {/* Header */}

    <div className="flex items-start gap-5">

        {/* Progress Ring */}

        <div className="relative shrink-0">

            <svg
                width="84"
                height="84"
                className="-rotate-90"
            >

                <circle
                    cx="42"
                    cy="42"
                    r="34"
                    stroke="#EEF0F7"
                    strokeWidth="7"
                    fill="none"
                />

                <circle
                    cx="42"
                    cy="42"
                    r="34"
                    stroke="#7367F0"
                    strokeWidth="7"
                    fill="none"
                    strokeLinecap="round"
                    strokeDasharray={214}
                    strokeDashoffset={
                        214 -
                        (completionPercentage / 100) * 214
                    }
                />

            </svg>

            <div
                className="
                    absolute
                    inset-0
                    flex
                    items-center
                    justify-center
                    text-[18px]
                    font-bold
                    text-[#1E2340]
                "
            >
                {completionPercentage}%
            </div>

        </div>

        {/* Text */}

        <div>

            <h3
                className="
                    text-[20px]
                    font-semibold
                    text-[#1E2340]
                "
            >
                Profile Completion
            </h3>

            <p
                className="
                    mt-2
                    text-[14px]
                    leading-6
                    text-slate-600
                    max-w-md
                "
            >
                Complete the remaining items to unlock the full CareerOS experience and AI features.
            </p>

        </div>

    </div>

    {/* Checklist */}

    <div
        className="
            mt-8
            grid
            grid-cols-2
            gap-x-10
            gap-y-5
        "
    >

        <CompletionCheck
            title="Personal Information"
            done={true}
        />

        <CompletionCheck
            title="Career Goal"
            done={!!profile.dreamRole}
        />

        <CompletionCheck
            title="Skills Added"
            done={profile.skills?.length > 0}
        />

        <CompletionCheck
            title="Resume Uploaded"
            done={profile.hasResume}
        />

    </div>

    <div
    className="
        mt-8
        rounded-2xl
        border
        border-violet-100
        bg-violet-50/40
        px-6
        py-5
        flex
        items-center
        justify-between
    "
>

    <div>

        <p
            className="
                text-[12px]
                uppercase
                tracking-[0.12em]
                font-medium
                text-violet-600
            "
        >
            Complete Your Profile
        </p>

        <h4
            className="
                mt-1
                text-[16px]
                font-semibold
                text-[#1E2340]
            "
        >
            You're {completionPercentage}% done
        </h4>

        <p
            className="
                mt-1
                text-[14px]
                text-slate-500
            "
        >
            Complete the remaining steps to unlock all CareerOS features.
        </p>

    </div>

    <button

        onClick={() => navigate("/profile/edit")}

        className="
mt-8
inline-flex
items-center
justify-center
px-7
h-11
rounded-xl
bg-[#7367F0]
hover:bg-[#6558EA]
text-white
text-[15px]
font-medium
shadow-sm
transition-all
duration-200
hover:shadow-md
"
    >

        Complete Profile

    </button>

</div>

</div>
    {/* QUICK ACTIONS */}

   {/* ================= QUICK ACTIONS ================= */}

<div
    className="
        bg-white
        border
        border-[#ECEAF5]
        rounded-[22px]
        px-7
        py-6
    "
>

    {/* Header */}

    <div className="flex items-center gap-3">

        <div
            className="
                w-11
                h-11
                rounded-2xl
                bg-violet-50
                flex
                items-center
                justify-center
            "
        >
            <Zap
                size={20}
                className="text-[#6B5CF6]"
            />
        </div>

        <div>

            <h3
                 className="
                    text-[20px]
                    font-semibold
                    text-[#1E2340]
                "
            >
                Quick Actions
            </h3>

            <p
                className="
                    mt-1
                    text-[14px]
                    text-slate-600
                "
            >
                Jump to your most-used CareerOS modules.
            </p>

        </div>

    </div>

    {/* Actions */}

<div
    className="
        mt-7
        flex-1
        flex
        flex-col
        justify-between
    "
>
    <div className="space-y-3">


        <QuickActionCard
            icon={<FileText size={18} />}
            title="Resume Center"
            subtitle="Manage your resume & ATS"
            path="/resume-center"
        />

        <QuickActionCard
            icon={<Brain size={18} />}
            title="Career Intelligence"
            subtitle="AI insights & recommendations"
            path="/career-intelligence"
        />

        <QuickActionCard
            icon={<BookOpen size={18} />}
            title="Learning Resources"
            subtitle="Courses & learning paths"
            path="/resources"
        />

        </div>

    </div>

</div>

</div>

</div>

</main>

</div>



);

}



function InfoRow({

    label,

    value

}) {

    return (

        <div
            className="
                grid
                grid-cols-[140px_1fr]
                gap-4
                items-center
                py-3
                border-b
                border-[#F1F2F6]
                last:border-none
            "
        >

            <p
                className="
                    text-slate-500
                    text-[15px]
font-medium
                "
            >
                {label}
            </p>

            <p
                className="
                    text-[#1E2340]
                    text-[15px]
font-semibold
                    break-words
                "
            >
                {value || "Not Added"}
            </p>

        </div>

    );

}

function JourneyStep({

    title,
    completed,
    first,
    last

}) {

    const getIcon = () => {

        switch (title) {

            case "Skill Gap":
                return <Target size={16} />;

            case "Roadmap":
                return <Map size={16} />;

            case "Resume":
                return <FileText size={16} />;

            case "Interview":
                return <Users size={16} />;

            default:
                return <BriefcaseBusiness size={16} />;

        }

    };

    return (

        <div
            className="
                relative
                flex-1
                flex
                justify-center
            "
        >

            {/* Connector */}

            {!last && (

                <div
                    className="
                        absolute
                        top-[34px]
                        left-[70%]
                        w-full
                        border-t-2
                        border-dashed
                        border-[#DDD8FF]
                    "
                />

            )}

            {/* Tile */}

            <div
                className={`
                    relative
                    z-10
                    w-[138px]
                    h-[82px]
                    rounded-xl
                    border
                    px-3
                    py-3
                    transition-all
                    ${
                        completed
                            ? "bg-[#F2FFF8] border-[#D8F5E6]"
                            : "bg-white border-[#ECEAF5]"
                    }
                `}
            >

                <div className="flex items-center gap-2">

                    <div
                        className={`
                            w-8
                            h-8
                            rounded-lg
                            flex
                            items-center
                            justify-center
                            ${
                                completed
                                    ? "bg-emerald-100 text-emerald-600"
                                    : "bg-violet-100 text-[#6B5CF6]"
                            }
                        `}
                    >

                        {

                            completed

                                ? <CheckCircle2 size={16} />

                                : getIcon()

                        }

                    </div>

                    <div className="min-w-0">

                        <p
                            className="
                                text-[16px]
                                font-semibold
                                text-[#1E2340]
                                truncate
                            "
                        >
                            {title}
                        </p>

                        <p
                            className={`
                                mt-1
                                text-[13px]
                                font-medium
                                ${
                                    completed
                                        ? "text-emerald-600"
                                        : "text-slate-500"
                                }
                            `}
                        >

                            {

                                completed

                                    ? "Completed"

                                    : "Pending"

                            }

                        </p>

                    </div>

                </div>

            </div>

        </div>

    );

}

function CompletionCheck({

    title,
    done

}) {

    return (

        <div className="flex items-center gap-3">

            <div
                className={`
                    w-8
                    h-8
                    rounded-full
                    flex
                    items-center
                    justify-center

                    ${
                        done

                            ? "bg-emerald-100"

                            : "bg-slate-100"

                    }
                `}
            >

                {

                    done

                    ?

                    <Check
                        size={16}
                        className="text-emerald-600"
                    />

                    :

                    <Circle
                        size={16}
                        className="text-slate-400"
                    />

                }

            </div>

            <div>

                <p
                    className="
                        mt-1
                        text-[14px]
                        font-medium
                        text-[#1E2340]
                        break-all
                    "
                >
                    {title}
                </p>

                <p
                    className={`
                         mt-1
                                text-[13px]
                                font-medium

                        ${
                            done

                            ?

                            "text-emerald-600"

                            :

                            "text-slate-500"

                        }
                    `}
                >

                    {

                        done

                        ?

                        "Completed"

                        :

                        "Pending"

                    }

                </p>

            </div>

        </div>

    );

}

function QuickActionCard({

    icon,
    title,
    subtitle,
    path

}) {

    const navigate = useNavigate();

    return (

        <button

            onClick={() => navigate(path)}

            className="
                w-full
                flex
                items-center
                justify-between
                rounded-2xl
                border
                border-[#ECEAF5]
                px-5
                py-4
                transition-all
                duration-200
                hover:border-[#D8D1FF]
                hover:bg-violet-50/40
                hover:shadow-sm
                group
            "

        >

            <div className="flex items-center gap-4">

                <div
                    className="
                        w-10
                        h-10
                        rounded-xl
                        bg-violet-50
                        flex
                        items-center
                        justify-center
                        text-[#6B5CF6]
                        group-hover:bg-violet-100
                        transition
                    "
                >

                    {icon}

                </div>

                <div className="text-left">

                    <h4
                        className="
                            text-[15px]
                            font-semibold
                            text-[#1E2340]
                        "
                    >
                        {title}
                    </h4>

                    <p
                         className="
                        text-[14px]
                        font-medium
                        text-slate-500
                    "
                    >
                        {subtitle}
                    </p>

                </div>

            </div>

            <ArrowRight
                size={18}
                className="
                    text-[#6B5CF6]
                    transition-transform
                    group-hover:translate-x-1
                "
            />

        </button>

    );

}

export default ProfilePage;