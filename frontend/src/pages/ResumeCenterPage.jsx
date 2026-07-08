import { useEffect, useState } from "react";
import axios from "axios";
import ResumeHero from "../components/resume-center/ResumeHero";

import {
    Brain,
    Target,
    BadgeCheck,
    Sparkles,
    ShieldCheck,
    TrendingUp,
    UserSearch,
    ClipboardList,
    ArrowRight,
    RefreshCw,
    Download,
    CheckCircle2,
    FileSearch,
    BookOpen,
    Lightbulb
} from "lucide-react";

import Sidebar from "../components/Sidebar";
import ResumeUploadCard from "../components/resume/ResumeCard";



/* ==========================================================
                        SECTION CARD
========================================================== */

function SectionCard({

    title,

    subtitle,

    icon,

    children,

    className = ""

}) {

    return (

        <div
            className={`
                bg-white
                rounded-[30px]
                border
                border-[#ECECF6]
                shadow-sm
                p-8
                ${className}
            `}
        >

            <div
                className="
                    flex
                    justify-between
                    items-start
                    mb-8
                "
            >

                <div>

                    <p
                        className="
                            uppercase
                            tracking-[0.18em]
                            text-[12px]
                            text-slate-400
                            font-semibold
                        "
                    >
                        {subtitle}
                    </p>

                    <h2
                        className="
                            mt-2
                            text-[28px]
                            font-bold
                            text-slate-900
                        "
                    >
                        {title}
                    </h2>

                </div>

                <div
                    className="
                        h-12
                        w-12
                        rounded-2xl
                        bg-[#F3F2FF]
                        flex
                        items-center
                        justify-center
                        text-[#6C63FF]
                    "
                >
                    {icon}
                </div>

            </div>

            {children}

        </div>

    );

}



/* ==========================================================
                        TAG
========================================================== */

function Tag({

    children

}){

    return(

        <div
            className="
                px-4
                py-2
                rounded-full
                bg-[#F3F2FF]
                text-[#6C63FF]
                font-medium
                text-sm
                flex
                items-center
                gap-2
                w-fit
            "
        >

            {children}

        </div>

    );

}

/* ==========================================================
                    SCORE RING
========================================================== */

function ScoreRing({

    title,

    value = 0,

    color = "#6C63FF"

}) {

    const radius = 48;

    const circumference =
        2 * Math.PI * radius;

    const offset =
        circumference -
        (value / 100) * circumference;

    return (

        <div
            className="
                bg-white
                rounded-[28px]
                border
                border-[#ECECF6]
                shadow-sm
                p-7
                text-center
                transition-all
                hover:-translate-y-1
                hover:shadow-lg
            "
        >

            <div
                className="
                    relative
                    h-[130px]
                    w-[130px]
                    mx-auto
                "
            >

                <svg
                    width="130"
                    height="130"
                    className="-rotate-90"
                >

                    <circle
                        cx="65"
                        cy="65"
                        r={radius}
                        stroke="#EEF2FF"
                        strokeWidth="10"
                        fill="none"
                    />

                    <circle
                        cx="65"
                        cy="65"
                        r={radius}
                        stroke={color}
                        strokeWidth="10"
                        fill="none"
                        strokeLinecap="round"
                        strokeDasharray={circumference}
                        strokeDashoffset={offset}
                    />

                </svg>

                <div
                    className="
                        absolute
                        inset-0
                        flex
                        flex-col
                        items-center
                        justify-center
                    "
                >

                    <h2
                        className="
                            text-[34px]
                            font-black
                            text-slate-900
                        "
                    >
                        {value}
                    </h2>

                    <p
                        className="
                            text-xs
                            text-slate-400
                        "
                    >
                        /100
                    </p>

                </div>

            </div>

            <h3
                className="
                    mt-6
                    font-bold
                    text-lg
                    text-slate-900
                "
            >
                {title}
            </h3>

        </div>

    );

}

/* ==========================================================
                        PAGE
========================================================== */

function ResumeCenterPage(){

const [data,setData]=
useState(null);

const [loading,setLoading]=
useState(true);

const [error,setError]=
useState("");

/* ==========================================================
                    LOAD DATA
========================================================== */

useEffect(() => {

    loadResumeAnalysis();

}, []);

const loadResumeAnalysis = async () => {

    try {

        const email =
            localStorage.getItem(
                "email"
            );

        const response =
            await axios.get(
                `http://localhost:8080/api/resume-analysis/${email}`
            );

        setData(
            response.data
        );

    } catch (err) {

        console.error(err);

        setError(
            "Unable to load Resume Intelligence."
        );

    } finally {

        setLoading(false);

    }

};



/* ==========================================================
                    SCORE CARDS
========================================================== */

const scoreCards = [

    {

        title: "Resume Score",

        value: data?.resumeScore,

        icon: <Brain size={18} />,

        color: "#6C63FF"

    },

    {

        title: "ATS Score",

        value: data?.atsScore,

        icon: <ShieldCheck size={18} />,

        color: "#22C55E"

    },

    {

        title: "Role Alignment",

        value: data?.roleAlignmentScore,

        icon: <Target size={18} />,

        color: "#F59E0B"

    },

    {

        title: "Skills Coverage",

        value: data?.skillsCoverageScore,

        icon: <BadgeCheck size={18} />,

        color: "#EF4444"

    },

    {

        title: "Potential",

        value: data?.potentialResumeScore,

        icon: <TrendingUp size={18} />,

        color: "#3B82F6"

    }

];



/* ==========================================================
                    LOADING
========================================================== */

if (loading) {

    return (

        <>

            <Sidebar />

            <div
                className="
                    ml-72
                    min-h-screen
                    bg-[#F7F8FC]
                "
            >


                <div
                    className="
                        flex
                        items-center
                        justify-center
                        h-[85vh]
                    "
                >

                    <div
                        className="
                            text-center
                        "
                    >

                        <Brain
                            className="
                                h-16
                                w-16
                                text-[#6C63FF]
                                animate-pulse
                                mx-auto
                            "
                        />

                        <h2
                            className="
                                mt-6
                                text-3xl
                                font-bold
                                text-slate-900
                            "
                        >
                            Building Resume Intelligence...
                        </h2>

                        <p
                            className="
                                mt-3
                                text-slate-500
                            "
                        >
                            CareerOS AI is analyzing your resume.
                        </p>

                    </div>

                </div>

            </div>

        </>

    );

}



/* ==========================================================
                    ERROR
========================================================== */

if (error) {

    return (

        <>

            <Sidebar />

            <div
                className="
                    ml-72
                    min-h-screen
                    bg-[#F7F8FC]
                "
            >


                <div
                    className="
                        h-[80vh]
                        flex
                        items-center
                        justify-center
                    "
                >

                    <div
                        className="
                            bg-white
                            rounded-[30px]
                            border
                            border-red-100
                            shadow-sm
                            px-10
                            py-12
                            text-center
                        "
                    >

                        <h2
                            className="
                                text-2xl
                                font-bold
                                text-red-500
                            "
                        >
                            {error}
                        </h2>

                    </div>

                </div>

            </div>

        </>

    );

}

/* ================== PAGE  ========================= */
                    
return (

<>

<Sidebar />
   <div
                className="
                    ml-[240px]
                    bg-[#F6F7FB]
                "
            >

                <div
                    className="
                        max-w-[1450px]
                        mx-auto
                        px-10
                        py-10
                    "
                >

  {/* ==========================================================
                    PAGE HEADER
========================================================== */}

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
            Resume Analysis
        </h1>

        <p
            className="
                mt-5
                max-w-3xl
                text-[14px]
                leading-6
                text-slate-500
            "
        >
            Understand what your resume communicates to recruiters,
            identify your strengths, uncover improvement opportunities
            and measure your career readiness using AI.
        </p>

    </div>

    <div
        className="
            flex
            gap-4
        "
    >

        <button
            className="
                h-12
                px-6
                rounded-2xl
                border
                border-[#E8EAF8]
                bg-white
                font-semibold
                flex
                items-center
                gap-3
                hover:shadow-md
                transition-all
            "
        >

            <RefreshCw
                size={18}
            />

            Reanalyze

        </button>

        <button
            className="
                h-12
                px-6
                rounded-2xl
                bg-[#6C63FF]
                text-white
                font-semibold
                flex
                items-center
                gap-3
                shadow-lg
                hover:bg-[#5A51F5]
                transition-all
            "
        >

            <Download
                size={18}
            />

            Download Report

        </button>

    </div>

</div>

<ResumeHero data={data} />

{/* ==========================================================
                    ANALYSIS STATUS
========================================================== */}

<div
    className="
        bg-white
        rounded-[24px]
        border
        border-[#ECECF6]
        shadow-sm
        px-8
        py-5
        flex
        items-center
        justify-between
        mb-8
    "
>

    {/* Left */}

    <div
        className="
            flex
            items-center
            gap-6
        "
    >

        <div
            className="
                flex
                items-center
                gap-3
            "
        >

            <div
                className="
                    h-11
                    w-11
                    rounded-xl
                    bg-[#EEF2FF]
                    flex
                    items-center
                    justify-center
                "
            >

                <CheckCircle2
                    size={20}
                    className="text-[#6C63FF]"
                />

            </div>

            <div>

                <p
                    className="
                        text-xs
                        uppercase
                        tracking-wider
                        text-slate-400
                    "
                >
                    Analysis Status
                </p>

                <h3
                    className="
                        text-lg
                        font-bold
                        text-slate-900
                    "
                >
                    Resume Intelligence Ready
                </h3>

            </div>

        </div>

    </div>


    {/* Center */}

    <div
        className="
            flex
            items-center
            gap-10
        "
    >

        <div>

            <p
                className="
                    text-xs
                    uppercase
                    tracking-wider
                    text-slate-400
                "
            >
                Version
            </p>

            <h4
                className="
                    mt-1
                    font-semibold
                    text-slate-800
                "
            >
                {data?.analysisVersion ?? "v2.0"}
            </h4>

        </div>

        <div>

            <p
                className="
                    text-xs
                    uppercase
                    tracking-wider
                    text-slate-400
                "
            >
                Confidence
            </p>

            <h4
                className="
                    mt-1
                    font-semibold
                    text-slate-800
                "
            >
                {data?.confidenceLevel}
            </h4>

        </div>

    </div>


    {/* Right */}

    <div
        className="
            flex
            items-center
            gap-3
            rounded-full
            bg-green-50
            px-5
            py-2
        "
    >

        <CheckCircle2
            size={18}
            className="text-green-600"
        />

        <span
            className="
                text-green-700
                font-semibold
            "
        >
            Up to Date
        </span>

    </div>

</div>

{/* ==========================================================
                    SCORE OVERVIEW
========================================================== */}

<div
    className="
        mb-10
    "
>

    <div
        className="
            flex
            items-center
            justify-between
            mb-7
        "
    >

        <div>

            <p
                className="
                    uppercase
                    tracking-[0.18em]
                    text-[12px]
                    font-semibold
                    text-slate-400
                "
            >
                AI ANALYTICS
            </p>

            <h2
                className="
                    mt-2
                    text-[34px]
                    font-black
                    text-slate-900
                "
            >
                Resume Performance
            </h2>

        </div>

        <p
            className="
                text-slate-500
                max-w-lg
                text-right
            "
        >
            These scores summarize how your resume performs
            across the most important recruiter evaluation
            criteria.
        </p>

    </div>



    <div
        className="
            grid
            grid-cols-5
            gap-6
        "
    >

      <ScoreRing
    title="Resume Score"
    value={data?.resumeScore ?? 0}
    color="#6C63FF"
/>

<ScoreRing
    title="ATS Score"
    value={data?.atsScore ?? 0}
    color="#10B981"
/>

<ScoreRing
    title="Role Alignment"
    value={data?.roleAlignmentScore ?? 0}
    color="#F59E0B"
/>

<ScoreRing
    title="Skills Coverage"
    value={data?.skillsCoverageScore ?? 0}
    color="#3B82F6"
/>

<ScoreRing
    title="Potential"
    value={data?.potentialResumeScore ?? 0}
    color="#EC4899"
/>

  </div>

</div>

{/* ==========================================================
                AI EXECUTIVE SUMMARY
========================================================== */}

<div
    className="
        grid
        grid-cols-12
        gap-6
        mb-10
    "
>

    {/* Executive Summary */}

    <SectionCard

        title="Executive Summary"

        subtitle="AI OBSERVATION"

        icon={
            <Brain
                size={22}
            />
        }

        className="col-span-8"

    >

        <p
            className="
                text-[17px]
                leading-9
                text-slate-600
            "
        >

            {data?.executiveSummary}

        </p>

    </SectionCard>



    {/* Overall Verdict */}

    <SectionCard

        title="Overall Verdict"

        subtitle="FINAL EVALUATION"

        icon={
            <ShieldCheck
                size={22}
            />
        }

        className="col-span-4"

    >

        <div
            className="
                flex
                flex-col
                justify-center
                h-full
            "
        >

            <h2
                className="
                    text-[32px]
                    leading-tight
                    font-black
                    text-[#6C63FF]
                "
            >

                {data?.overallVerdict}

            </h2>

            <div
                className="
                    mt-6
                    flex
                    items-center
                    gap-3
                "
            >

                <CheckCircle2
                    size={20}
                    className="text-green-500"
                />

                <span
                    className="
                        text-slate-600
                        font-medium
                    "
                >
                    Personalized AI Evaluation
                </span>

            </div>

        </div>

    </SectionCard>

</div>

{/* ==========================================================
                    RESUME INTELLIGENCE
========================================================== */}

<div
    className="
        grid
        grid-cols-12
        gap-6
        mb-10
    "
>

{/* ==========================================================
                    Resume Identity
========================================================== */}

<SectionCard

    title="Resume Identity"

    subtitle="CURRENT POSITION"

    icon={<UserSearch size={22}/>}

    className="col-span-4"

>

    <h3
        className="
            text-2xl
            font-bold
            text-slate-900
        "
    >
        {data?.currentResumeIdentity}
    </h3>

    <div
        className="
            mt-6
            h-2
            rounded-full
            bg-slate-100
            overflow-hidden
        "
    >

        <div
            className="
                h-full
                rounded-full
                bg-[#6C63FF]
            "
            style={{
                width:`${data?.identityAlignmentScore ?? 0}%`
            }}
        />

    </div>

    <div
        className="
            mt-3
            flex
            justify-between
            text-sm
        "
    >

        <span className="text-slate-500">

            Identity Alignment

        </span>

        <span
            className="
                font-bold
                text-[#6C63FF]
            "
        >

            {data?.identityAlignmentScore ?? 0}%

        </span>

    </div>

    <p
        className="
            mt-6
            leading-8
            text-slate-600
        "
    >

        {data?.identityGap}

    </p>

</SectionCard>



{/* ==========================================================
                    Recruiter Perspective
========================================================== */}

<SectionCard

    title="Recruiter Perspective"

    subtitle="FIRST IMPRESSION"

    icon={<ClipboardList size={22}/>}

    className="col-span-4"

>

    <h3
        className="
            text-xl
            font-bold
            text-slate-900
            leading-9
        "
    >

        {data?.recruiterFirstImpression}

    </h3>

    <p
        className="
            mt-5
            text-slate-600
            leading-8
        "
    >

        {data?.recruiterOpinion}

    </p>

</SectionCard>



{/* ==========================================================
                    Growth Potential
========================================================== */}

<SectionCard

    title="Growth Potential"

    subtitle="FUTURE OUTLOOK"

    icon={<TrendingUp size={22}/>}

    className="col-span-4"

>

    <h2
        className="
            text-[40px]
            font-black
            text-[#6C63FF]
        "
    >

        {data?.growthPotential}

    </h2>

    <p
        className="
            mt-5
            leading-8
            text-slate-600
        "
    >

        {data?.careerProjection}

    </p>

</SectionCard>

</div>

{/* ==========================================================
                    STRENGTHS + GAPS
========================================================== */}

<div
    className="
        grid
        grid-cols-12
        gap-6
        mb-10
    "
>

{/* ==========================================================
                    TOP STRENGTHS
========================================================== */}

<SectionCard

    title="Top Strengths"

    subtitle="WHAT STANDS OUT"

    icon={<CheckCircle2 size={22}/>}

    className="col-span-6"

>

    <div
        className="
            space-y-4
        "
    >

        {(data?.topStrengths ?? []).map(
            (item,index)=>(

              <div
    key={index}
    className="
        flex
        items-start
        gap-4
        rounded-2xl
        border
        border-green-100
        bg-green-50
        p-5
        transition-all
        hover:shadow-md
    "
>

    <div
        className="
            h-10
            w-10
            rounded-xl
            bg-green-100
            flex
            items-center
            justify-center
            flex-shrink-0
        "
    >

        <CheckCircle2
            size={18}
            className="text-green-600"
        />

    </div>

    <p
        className="
            leading-7
            text-slate-700
            font-medium
        "
    >

        {item}

    </p>

</div>

))

}

</div>

</SectionCard>



{/* ==========================================================
                    RESUME GAPS
========================================================== */}

<SectionCard

    title="Resume Gaps"

    subtitle="AI DETECTED"

    icon={<Sparkles size={22}/>}

    className="col-span-6"

>

<div
    className="
        space-y-4
    "
>

    {(data?.resumeGaps ?? []).map(
        (item,index)=>(

          <div
    key={index}
    className="
        flex
        items-start
        gap-4
        rounded-2xl
        border
        border-red-100
        bg-red-50
        p-5
        transition-all
        hover:shadow-md
    "
>

    <div
        className="
            h-10
            w-10
            rounded-xl
            bg-red-100
            flex
            items-center
            justify-center
            flex-shrink-0
        "
    >

        <Sparkles
            size={18}
            className="text-red-500"
        />

    </div>

    <p
        className="
            leading-7
            text-slate-700
            font-medium
        "
    >

        {item}

    </p>

</div>

))

}

</div>

</SectionCard>

</div>



{/* ==========================================================
                    STAGE EVALUATION
========================================================== */}

<div
    className="
        grid
        grid-cols-12
        gap-6
        mb-10
    "
>

<SectionCard

    title="Stage Evaluation"

    subtitle="ACADEMIC PROGRESSION"

    icon={<BookOpen size={22}/>}

    className="col-span-6"

>

    <h3
        className="
            text-xl
            font-bold
            text-slate-900
        "
    >

        {data?.currentResumeLevel}

    </h3>

    <p
        className="
            mt-5
            leading-8
            text-slate-600
        "
    >

        {data?.stageEvaluation}

    </p>

</SectionCard>

{/* ==========================================================
                    RESUME NARRATIVE
========================================================== */}

<SectionCard

    title="Resume Narrative"

    subtitle="YOUR STORY"

    icon={<FileSearch size={22}/>}

    className="col-span-6"

>

    <h3
        className="
            text-xl
            font-bold
            text-slate-900
            leading-9
        "
    >
        Resume Story
    </h3>

    <p
        className="
            mt-5
            leading-8
            text-slate-600
        "
    >
        {data?.resumeNarrative}
    </p>

    <div
        className="
            mt-8
            rounded-2xl
            bg-indigo-50
            border
            border-indigo-100
            p-5
        "
    >

        <div
            className="
                flex
                items-start
                gap-4
            "
        >

            <div
                className="
                    h-10
                    w-10
                    rounded-xl
                    bg-white
                    flex
                    items-center
                    justify-center
                    shadow-sm
                "
            >

                <Lightbulb
                    size={18}
                    className="text-[#6C63FF]"
                />

            </div>

            <div>

                <h4
                    className="
                        font-bold
                        text-slate-900
                    "
                >
                    Hidden Potential
                </h4>

                <p
                    className="
                        mt-2
                        leading-7
                        text-slate-600
                    "
                >
                    {data?.hiddenPotential}
                </p>

            </div>

        </div>

    </div>

</SectionCard>

</div>

{/* ==========================================================
                    AI INSIGHTS
========================================================== */}

<div
    className="
        grid
        grid-cols-12
        gap-6
        mb-10
    "
>

{/* ==========================================================
                    KEY INSIGHTS
========================================================== */}

<SectionCard

    title="Key Insights"

    subtitle="AI DISCOVERIES"

    icon={<Brain size={22}/>}

    className="col-span-7"

>

<div
    className="
        space-y-4
    "
>

    {(data?.keyInsights ?? []).map(
        (item,index)=>(

<div
    key={index}
    className="
        flex
        gap-4
        items-start
        rounded-2xl
        bg-indigo-50
        border
        border-indigo-100
        p-5
        transition-all
        hover:shadow-md
    "
>

<div
    className="
        h-10
        w-10
        rounded-xl
        bg-white
        flex
        items-center
        justify-center
        shadow-sm
        flex-shrink-0
    "
>

<Brain
size={18}
className="text-[#6C63FF]"
/>

</div>

<p
className="
leading-7
text-slate-700
font-medium
"
>

{item}

</p>

</div>

))

}

</div>

</SectionCard>



{/* ==========================================================
                MISSING COMPONENTS
========================================================== */}

<SectionCard

title="Missing Resume Components"

subtitle="WHAT'S MISSING"

icon={<ClipboardList size={22}/>}

className="col-span-5"

>

<div
className="
space-y-3
"
>

{(data?.missingResumeComponents ?? []).map(
(component,index)=>(
  <div
    key={index}
    className="
        flex
        items-center
        gap-3
        rounded-xl
        border
        border-amber-100
        bg-amber-50
        px-4
        py-3
        transition-all
        hover:shadow-sm
    "
>

    <div
        className="
            h-8
            w-8
            rounded-lg
            bg-white
            flex
            items-center
            justify-center
            shadow-sm
            flex-shrink-0
        "
    >

        <ClipboardList
            size={16}
            className="text-amber-600"
        />

    </div>

    <span
        className="
            text-slate-700
            font-medium
            leading-6
        "
    >
        {component}
    </span>

</div>

))

}

</div>

</SectionCard>

</div>



{/* ==========================================================
                    FUTURE ROADMAP
========================================================== */}

<div
    className="
        grid
        grid-cols-12
        gap-6
        mb-10
    "
>

{/* ==========================================================
                    CAREER PROJECTION
========================================================== */}

<SectionCard

    title="Career Projection"

    subtitle="AI FORECAST"

    icon={<TrendingUp size={22}/>}

    className="col-span-6"

>

    <h3
        className="
            text-2xl
            font-bold
            text-[#6C63FF]
            leading-tight
        "
    >
        {data?.growthPotential}
    </h3>

    <p
        className="
            mt-5
            leading-8
            text-slate-600
        "
    >
        {data?.careerProjection}
    </p>

</SectionCard>



{/* ==========================================================
                    NEXT MILESTONE
========================================================== */}

<SectionCard

    title="Next Milestone"

    subtitle="WHAT TO DO NEXT"

    icon={<Target size={22}/>}

    className="col-span-6"

>

    <h3
        className="
            text-2xl
            font-bold
            text-slate-900
        "
    >
        {data?.nextMilestone}
    </h3>

    <p
        className="
            mt-5
            leading-8
            text-slate-600
        "
    >
        {data?.guidePreview}
    </p>

</SectionCard>

</div>

{/* ==========================================================
                    RESUME GUIDE CTA
========================================================== */}

<div
    className="
        relative
        overflow-hidden
        rounded-[36px]
        bg-gradient-to-br
        from-[#6558F5]
        via-[#776CFF]
        to-[#948BFF]
        p-12
        text-white
        mb-12
        shadow-xl
    "
>

<div
    className="
        absolute
        -right-24
        -bottom-24
        h-[260px]
        w-[260px]
        rounded-full
        bg-white/10
    "
/>

<div
    className="
        relative
        z-10
        flex
        justify-between
        items-center
        gap-10
    "
>

<div
    className="
        max-w-3xl
    "
>

<p
className="
uppercase
tracking-[0.18em]
text-white/70
text-sm
font-semibold
"
>

NEXT STEP

</p>

<h2
className="
mt-3
text-[46px]
leading-tight
font-black
"
>

Your Resume Analysis is Complete

</h2>

<p
className="
mt-6
text-[18px]
leading-9
text-white/90
"
>

You now understand what your resume communicates today.
The next step is turning these insights into action.

Resume Guide will build a personalized improvement roadmap
based on your current academic stage, dream role, resume
strengths and identified gaps.

</p>

<div
className="
mt-8
flex
items-center
gap-3
"
>

<CheckCircle2
size={20}
className="text-green-300"
/>

<span
className="
text-white/90
font-medium
"
>

Personalized guidance based on your profile

</span>

</div>

</div>

<div>

<button
className="
group
bg-white
text-[#6558F5]
font-bold
rounded-2xl
px-8
py-4
shadow-xl
flex
items-center
gap-3
transition-all
hover:scale-105
"
>

Continue to Resume Guide

<ArrowRight
size={20}
className="
transition-transform
group-hover:translate-x-1
"
/>

</button>

</div>

</div>

</div>



{/* ==========================================================
                    FOOTER
========================================================== */}

<div
    className="
        flex
        justify-between
        items-center
        pt-2
        pb-12
    "
>

    <div>

        <p
            className="
                text-sm
                text-slate-500
            "
        >
            CareerOS Resume Intelligence
        </p>

        <p
            className="
                mt-2
                text-slate-400
            "
        >
            Every insight is personalized using your profile,
            projects, skills, academic stage and resume.
        </p>

    </div>

    <div
        className="
            flex
            items-center
            gap-3
            rounded-full
            bg-green-50
            px-5
            py-3
        "
    >

        <CheckCircle2
            size={18}
            className="text-green-600"
        />

        <span
            className="
                font-semibold
                text-green-700
            "
        >
            Analysis Complete
        </span>

    </div>

</div>

</div>

</div>

</>

);

}

export default ResumeCenterPage;

