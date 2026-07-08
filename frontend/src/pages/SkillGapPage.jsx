import { useEffect, useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";
import Topbar from "../components/Topbar";
import { useNavigate } from "react-router-dom";

import {
BrainCircuit,
FolderGit2,
Map,
Lightbulb,
BadgeCheck,
BookOpen,
Check,
Sparkles,
TrendingUp,
GraduationCap,
Clock3,
ArrowRight,
ChevronRight,
Award,
Briefcase,
TriangleAlert,
 CircleCheck,
 Plus,
 Dot,
 Puzzle,
Target,
Brain,
Play,
FileText
} from "lucide-react";



const getSkillLevelMessage = (level) => {

    if (level < 25) {

        return {
            title: "Building Foundation",
subtitle: "Start with core skills.",
            color: "#EF4444"
        };

    }

    if (level < 50) {

        return {
            title: "Making Progress",
subtitle: "Keep building momentum.",
            color: "#F59E0B"
        };

    }

    if (level < 75) {

        return {
           title: "Growing Strong",
subtitle: "Focus on advanced skills.",
            color: "#3B82F6"
        };

    }

    return {

        title: "Nearly Ready",
subtitle: "Polish with projects.",
        color: "#22C55E"

    };

};

const getCurrentLevel = (skillLevel) => {

   if (skillLevel < 25)
    return {
        title: "Foundation",
        subtitle: "Build your foundation.",
        badge: "Level 1 of 5",
        badgeBg: "#F3F4F6",
        badgeText: "#6B7280"
    };

    if (skillLevel < 50)
        return {
            title: "Foundation",
            subtitle: "Core skills are taking shape.",
            badge: "Level 2 of 5",
            badgeBg: "#EAF2FF",
            badgeText: "#2563EB"
        };

    if (skillLevel < 75)
        return {
            title: "Intermediate",
            subtitle: "You're becoming job capable.",
            badge: "Level 3 of 5",
            badgeBg: "#F3EEFF",
            badgeText: "#7C3AED"
        };

    if (skillLevel < 90)
        return {
            title: "Advanced",
            subtitle: "You're almost industry ready.",
            badge: "Level 4 of 5",
            badgeBg: "#E8F8EE",
            badgeText: "#16A34A"
        };

    return {
        title: "Industry Ready",
        subtitle: "Ready to compete for top roles.",
        badge: "Level 5 of 5",
        badgeBg: "#FFF7E8",
        badgeText: "#D97706"
    };

};


const getLearningPace = (skillLevel, timeToJobReady) => {

    if (skillLevel < 25)
        return {
            title: "Accelerated",
            subtitle: "Focus on core foundations",
            badge: "High Priority",
            badgeBg: "#FEF3C7",
            badgeText: "#D97706"
        };

    if (skillLevel < 50)
        return {
            title: "Balanced",
            subtitle: "Steady weekly progress",
            badge: "On Track",
            badgeBg: "#DBEAFE",
            badgeText: "#2563EB"
        };

    if (skillLevel < 75)
        return {
            title: "Consistent",
            subtitle: "Continue building projects",
            badge: "Good Progress",
            badgeBg: "#F3E8FF",
            badgeText: "#7C3AED"
        };

    if (skillLevel < 90)
        return {
            title: "Advanced",
            subtitle: "Focus on specialization",
            badge: "Excellent",
            badgeBg: "#DCFCE7",
            badgeText: "#16A34A"
        };

    return {
        title: "Industry Ready",
        subtitle: "Ready for internships and placements",
        badge: "Outstanding",
        badgeBg: "#FFF7ED",
        badgeText: "#EA580C"
    };

};


const getMissingSkillStatus = (count) => {

    if (count <= 2)
        return {
            title: "Excellent",
            subtitle: "Almost complete.",
            badge: "Nearly Complete",
            badgeBg: "#DCFCE7",
            badgeText: "#16A34A"
        };

    if (count <= 5)
        return {
            title: "Moderate",
            subtitle: "Keep learning.",
            badge: "Keep Learning",
            badgeBg: "#DBEAFE",
            badgeText: "#2563EB"
        };

    if (count <= 8)
        return {
            title: "Significant",
            subtitle: "Focus.",
            badge: "High Priority",
            badgeBg: "#FEF3C7",
            badgeText: "#D97706"
        };

    return {
        title: "Critical",
        subtitle: "Start immediately.",
        badge: "Start Now",
        badgeBg: "#FEE2E2",
        badgeText: "#DC2626"
    };

};



function SkillGapPage() {

  const [data, setData] =
    useState(null);

  
    const navigate = useNavigate();

  const [loading, setLoading] =
    useState(true);

  useEffect(() => {
    loadSkillGap();
  }, []);

  const loadSkillGap = async () => {

    try {

      const email =
        localStorage.getItem("email");

      const response =
        await axios.get(
          `http://localhost:8080/api/career/skill-gap/${email}`
        );

      setData(response.data);

    } catch (error) {

      console.log(error);

    } finally {

      setLoading(false);
    }
  };

const InsightCard = ({
    title,
    icon,
    items
}) => (

    <div
        className="
            bg-white
            border
            border-[#ECEAF5]
            rounded-[24px]
            p-6
            h-full
        "
    >

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
                    text-[#6B5CF6]
                "
            >
                {icon}
            </div>

            <h3
                className="
                    text-[17px]
                    font-semibold
                    text-[#1E2340]
                "
            >
                {title}
            </h3>

        </div>

        <div className="mt-6 space-y-3">

            {items?.map((item,index)=>(

                <div
                    key={index}
                    className="
                        flex
                        items-start
                        gap-3
                    "
                >

                   <Dot
    size={20}
    strokeWidth={2.8}
    className="text-[#6B5CF6] shrink-0 mt-1"
/>

                    <p
                        className="
                            text-[14px]
                            leading-7
                            text-slate-600
                        "
                    >
                        {item}
                    </p>

                </div>

            ))}

        </div>

    </div>

);

const skillLevel =
    data?.skillMatchPercentage || 0;

const currentLevel =
    getCurrentLevel(skillLevel);

const currentLearningStep = 1;    

const learningPace =
    getLearningPace(
        skillLevel,
        data?.timeToJobReady
    );

const totalMissingSkills =
    (data?.foundationSkills?.length || 0) +
    (data?.advancedSkills?.length || 0);  
    
const hasSkills =
    data?.currentSkills?.length > 0;

const missingSkillStatus =
    getMissingSkillStatus(totalMissingSkills);    


const StatCard = ({
    icon,
    iconBg,
    iconColor,
    title,
    value,
    subtitle,
    footer,
footerType = "progress",
progress = 50,
footerBg,
footerColor
}) => (
    <div
        className="
            bg-white
            border
            border-[#ECEAF5]
            rounded-[22px]
            pt-3
            pb-5
            px-5
            h-[176px]
            flex
            flex-col
        "
    >
       <div className="flex items-center gap-2">

    <div
        className="
            w-8
            h-8
            rounded-xl
            flex
            items-center
            justify-center
            shrink-0
        "
        style={{ background: iconBg }}
    >
        <div style={{ color: iconColor }}>
            {icon}
        </div>
    </div>

    <div>

        <p
            className="
                text-[15px]
                font-semibold
                text-[#344054]
            "
        >
            {title}
        </p>

    </div>

</div>
        <h2
            className="
                mt-1
                text-[20px]
                font-bold
                text-[#1E2340]
            "
        >
            {value}
        </h2>

        <p
           className="
                        mt-3
                        text-[13px]
                        font-medium
                        text-slate-400
                    "
        >
            {subtitle}
        </p>

        <div className="mt-1 pt-4">

            {footerType === "progress" ? (

                <div
                    className="
                        h-[9px]
                        rounded-full
                        bg-slate-100
                        overflow-hidden
                    "
                >
                    <div
                        className="h-full rounded-full"
                        style={{
                            width: `${Math.max(progress || 0,2)}%`,
                            background: iconColor
                        }}
                    />
                </div>

            ) : (

                <span
                    className="
                        inline-flex
                        items-center
                        gap-1
                        px-3
                        py-1
                        rounded-full
                        text-[11px]
                        font-medium
                    "
                  style={{
    background: footerBg || iconBg,
    color: footerColor || iconColor
}}
                >
                    {footer}
                </span>

            )}

        </div>

    </div>
);

const SkillColumn = ({
    title,
    skills = [],
    color
}) => {

    const [expanded, setExpanded] = useState(false);

    const colors = {

        emerald:
            "bg-emerald-50 text-emerald-700 border-emerald-100",

        violet:
            "bg-violet-50 text-violet-700 border-violet-100",

        sky:
            "bg-sky-50 text-sky-700 border-sky-100",

        indigo:
            "bg-indigo-50 text-indigo-700 border-indigo-100"

    };

  const safeSkills = skills ?? [];

const visibleSkills = expanded
    ? safeSkills
    : safeSkills.slice(0, 6);


    return (

        <div
            className="
                h-full
                rounded-[24px]
                border
                border-[#ECEAF5]
                bg-[#FCFCFE]
                p-6
            "
        >

            <h3
                className="
                    text-[16px]
                    font-semibold
                    text-[#1E2340]
                "
            >
                {title}
            </h3>

            <div
                className="
                    mt-4
                    flex
                    flex-wrap
                    gap-3
                    content-start
                    min-h-[100px]
                "
            >

                {visibleSkills.map((skill, index) => (

                    <span
                        key={index}
                        className={`
                            px-2
                            py-1
                            rounded-full
                            text-[14px]
                            font-medium
                            border
                            ${colors[color]}
                        `}
                    >
                        {skill}
                    </span>

                ))}

               {safeSkills.length > 6 && (

                  <button
    onClick={() => setExpanded(!expanded)}
    className="
        inline-flex
        items-center
        gap-2
        px-4
        py-2
        rounded-full
        border
        border-[#E5E7EB]
        bg-white
        text-[14px]
        font-semibold
        text-[#6B5CF6]
        hover:bg-violet-50
        hover:border-violet-200
        transition-all
        duration-200
        cursor-pointer
    "
>
    {expanded ? (
        <>
            ← Show Less
        </>
    ) : (
        <>
            View {safeSkills.length - 6} More →
        </>
    )}
</button>

                )}

            </div>

        </div>

    );

};



const ResourceCard = ({
    title,
    icon,
    items
}) => (

    <div
        className="
            bg-white
            border
            border-[#ECEAF5]
            rounded-[26px]
            p-7
        "
    >

        <div className="flex items-center gap-3">

            <div
                className="
                    w-12
                    h-12
                    rounded-2xl
                    bg-violet-50
                    flex
                    items-center
                    justify-center
                    text-[#6B5CF6]
                "
            >
                {icon}
            </div>

            <h3
                className="
                    text-[19px]
                    font-semibold
                    text-[#1E2340]
                "
            >
                {title}
            </h3>

        </div>

        <div className="mt-7 space-y-3">

            {items?.map((item,index)=>(

                <div
                    key={index}
                    className="
                        flex
                        items-start
                        gap-3
                    "
                >

                    <CircleCheck
                        size={16}
                        className="
                            text-[#6B5CF6]
                            mt-1
                            shrink-0
                        "
                    />

                    <span
                        className="
                            text-[15px]
                            text-slate-600
                            leading-7
                        "
                    >
                        {item}
                    </span>

                </div>

            ))}

        </div>

    </div>

);

const skillInsight =
    getSkillLevelMessage(
        data?.skillMatchPercentage || 0
    );

  if (loading) {

    return (
      <h2 className="text-center mt-10">
        Loading Skill Gap Analysis...
      </h2>
    );
  }

  return (
  <>
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

<div className="mb-8">

    <h1
        className="
            text-[34px]
            leading-none
            font-bold
            tracking-[-0.03em]
            text-[#1E2340]
        "
    >
        Skill Gap Analysis
    </h1>

    <p
        className="
            mt-5
            text-[16px]
            text-[#667085]
            leading-7
            max-w-4xl
        "
    >
        Here's your personalized skill gap summary for becoming a{" "}
        <span className="font-semibold text-[#6B5CF6]">
            {data?.targetRole}
        </span>
    </p>

</div>

{/* ================= QUICK STATS ================= */}

<div
    className="
        grid
        xl:grid-cols-4
        gap-5
        mb-5
    "
>
<StatCard
    icon={<Target size={18} />}
    iconBg="#FDECEC"
    iconColor="#E5484D"
    title="Skill Level"
    value={`${skillLevel}%`}
    subtitle={skillInsight.subtitle}
    progress={skillLevel}
/>

    <StatCard
        icon={<GraduationCap size={18} />}
        iconBg="#EAFBF2"
        iconColor="#22C55E"
        title="Current Level"
        value={currentLevel.title}
        subtitle={currentLevel.subtitle}
        footer={currentLevel.badge}
footerBg={currentLevel.badgeBg}
footerColor={currentLevel.badgeText}
        footerType="badge"
    />

    <StatCard
        icon={<Clock3 size={18} />}
        iconBg="#EEF5FF"
        iconColor="#3B82F6"
        title="Learning Pace"
        value={learningPace.title}
        subtitle={learningPace.subtitle}
        footer={learningPace.badge}         
        footerBg={learningPace.badgeBg}
        footerColor={learningPace.badgeText}
        footerType="badge"
    />

   <StatCard
    icon={<Puzzle size={18} />}
    iconBg="#FEF2F2"
    iconColor="#90cf2b"
    title="Missing Skills"
    value={`${totalMissingSkills} Skills`}
    subtitle={missingSkillStatus.subtitle}
    footer={missingSkillStatus.badge}
    footerType="badge"
    footerBg={missingSkillStatus.badgeBg}
    footerColor={missingSkillStatus.badgeText}
/>

</div>



{/* ================= SKILL BREAKDOWN ================= */}

<div
    className="
        mt-4
        bg-white
        border
        border-[#ECEAF5]
        rounded-[30px]
        p-8
    "
>

    <div
        className="
            flex
            items-center
            justify-between
        "
    >

      

            <h2
                className="
                    text-[24px]
                    font-bold
                    text-[#1E2340]
                "
            >
                Skill Breakdown
            </h2>

           
          <button
        onClick={() => navigate("/profile/edit")}
        className="
            group
            inline-flex
            items-center
            gap-2
            px-5
            py-3
            rounded-2xl
            border
            border-[#D9D6FE]
            bg-[#F8F7FF]
            text-[#6B5CF6]
            font-semibold
            text-[14px]
            transition-all
            duration-300
            hover:bg-[#6B5CF6]
            hover:text-white
            hover:border-[#6B5CF6]
            hover:shadow-lg
            hover:shadow-violet-100
        "
    >
        <Plus size={17}  className="
                transition-transform
                duration-300
                group-hover:translate-x-1
            "
        />

        Add Skills

       

    </button>
 

    </div>

   <div className="mt-8 space-y-5">

    {/* ================= YOUR SKILLS ================= */}

<SkillSection
    title="Your Skills"
    color="emerald"
    skills={
       hasSkills
    ? data.currentSkills
    : ["No skills added yet"]
    }
/>



    {/* ================= MISSING SKILLS ================= */}

    <div
        className="
            bg-[#FCFCFE]
            border
            border-[#ECEAF5]
            rounded-[22px]
            p-4
        "
    >

        <h3
            className="
                text-[18px]
                font-semibold
                text-[#1E2340]
            "
        >
            Missing Skills
        </h3>

        <p
            className="
                mt-1
                text-[15px]
                text-slate-500
            "
        >
            Focus on these skills walking towards {" "}
            <span className="font-medium">
                {data?.targetRole}
            </span>
        </p>

        <div
            className="
                mt-4
                grid
                md:grid-cols-2
                gap-4
            "
        >

<SkillSection
    title="Foundation Skills"
    color="violet"
    skills={data?.foundationSkills}
/>

          <SkillSection
    title="Advanced Skills"
    color="blue"
    skills={data?.advancedSkills}
/>

        </div>

    </div>

</div>


</div>

{/* ================= LEARNING ORDER ================= */}

<div
    className="
        mt-6
        bg-white
        border
        border-[#ECEAF5]
        rounded-[24px]
        p-7
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
            <Map
                size={20}
                className="text-[#6B5CF6]"
            />
        </div>

        <div>

            <h2
                className="
                    text-[22px]
                    font-bold
                    text-[#1E2340]
                "
            >
                Learning Order
            </h2>

            <p
                className="
                    mt-1
                    text-[14px]
                    text-slate-500
                "
            >
                AI recommends learning these skills in this order for the fastest path to your dream role.
            </p>

        </div>

    </div>


{/* Roadmap */}

<div
    className="
        mt-10
        flex
        items-start
        overflow-x-auto
        pb-3
    "
>

    {data?.learningOrder?.map((step, index) => (

        <div
            key={index}
            className="
                flex
                items-center
                min-w-[185px]
            "
        >

            {/* Step */}

            <div
                className="
                    flex
                    flex-col
                    items-center
                    w-full
                "
            >

                {/* Number */}

              <div
    className="
        w-[52px]
        h-[52px]
        rounded-2xl
        bg-violet-50
        border
        border-violet-100
        flex
        items-center
        justify-center
        shadow-sm
    "
>

    <span
        className="
            text-[16px]
            font-bold
            text-[#6B5CF6]
        "
    >
        {String(step.step).padStart(2, "0")}
    </span>

</div>
                {/* Skill */}

                <h3
                    className="
                        mt-4
                        text-[14px]
                        font-semibold
                        leading-5
                        text-center
                        text-[#1E2340]
                        px-2
                        min-h-[42px]
                    "
                >
                    {step.skill}
                </h3>

                {/* Estimated Time */}

                <div
                    className="
                        mt-3
                        flex
                        items-center
                        gap-1.5
                        px-3
                        py-1.5
                        rounded-full
                        bg-slate-100
                        border
                        border-slate-200
                        text-slate-600
                        text-[11px]
                        font-medium
                    "
                >

                    <Clock3
                        size={12}
                        strokeWidth={2}
                    />

                    {step.estimatedDuration}

                </div>

            </div>

            {/* Connector */}

            {index !== data.learningOrder.length - 1 && (

              <div
    className="
        flex-1
        h-[4px]
        rounded-full
        bg-gradient-to-r
        from-violet-100
        to-slate-200
        -mt-[66px]
    "
/>
            )}

        </div>

    ))}

</div>
  
     
</div>
{/* ================= AI CAREER GUIDANCE ================= */}

<div
    className="
        mt-6
        bg-white
        border
        border-[#ECEAF5]
        rounded-[24px]
        p-7
    "
>

    <div className="flex items-start gap-4">

        <div
            className="
                w-12
                h-12
                rounded-2xl
                bg-violet-50
                flex
                items-center
                justify-center
                shrink-0
            "
        >
            <Brain
                size={24}
                className="text-[#6B5CF6]"
            />
        </div>

        <div className="flex-1">

            <h2
                className="
                    text-[22px]
                    font-bold
                    text-[#1E2340]
                "
            >
                AI Career Guidance
            </h2>

            <p
                className="
                    mt-1
                    text-[14px]
                    text-slate-500
                "
            >
                Personalized recommendations based on your current skills and career goal.
            </p>

        </div>

    </div>

<div
    className="
        mt-8
        grid
        lg:grid-cols-4
        gap-5
    "
>

    <InsightCard
        title="Top Insights"
        icon={<Lightbulb size={18} />}
         items={data?.topInsights ?? []}
    />

    <InsightCard
        title="Employer Expectations"
        icon={<BadgeCheck size={18} />}
        items={data?.employerExpectations ?? []}
    />

     <InsightCard
        title="Industry Advice"
        icon={<TrendingUp size={18} />}
        items={data?.industryAdvice ?? []}
    />

    <InsightCard
        title="Avoid These Mistakes"
        icon={<TriangleAlert size={18} />}
        items={data?.commonMistakes ?? []}
    />

</div>
  
</div>




{/* ================= LEARNING RESOURCES ================= */}

<div
    className="
        mt-8
        grid
        xl:grid-cols-3
        gap-6
    "
>

    <ResourceCard
        title="Recommended Projects"
        icon={<FolderGit2 size={20} />}
        items={data?.recommendedProjects?.slice(0,6)}
    />

    <ResourceCard
        title="Recommended Certifications"
        icon={<Award size={20} />}
        items={data?.recommendedCertifications?.slice(0,6)}
    />

    <ResourceCard
        title="Learning Resources"
        icon={<BookOpen size={20} />}
        items={data?.recommendedResources?.slice(0,6)}
    />

</div>

{/* ================= ROADMAP CTA ================= */}
{/* ================= NEXT STEP ================= */}

<div
    className="
        mt-8
        bg-[#F8F7FF]
        border
        border-violet-200
        rounded-[30px]
        p-8
    "
>

    <div
        className="
            flex
            flex-col
            lg:flex-row
            lg:items-center
            lg:justify-between
            gap-8
        "
    >

        {/* Left */}

        <div className="max-w-3xl">

            <span
                className="
                    inline-flex
                    items-center
                    px-3
                    py-1
                    rounded-full
                    bg-violet-100
                    text-[#6B5CF6]
                    text-[12px]
                    font-semibold
                    uppercase
                    tracking-wider
                "
            >
                NEXT STEP
            </span>

            <h2
                className="
                    mt-4
                    text-[30px]
                    font-bold
                    text-[#1E2340]
                "
            >
                Build Your Personalized Career Roadmap
            </h2>

            <p
                className="
                    mt-4
                    text-[16px]
                    leading-8
                    text-slate-600
                "
            >
                Based on your current skills and career goal, you're approximately
                <span className="font-semibold text-[#6B5CF6]">
                    {" "}{data?.timeToJobReady}
                </span>
                {" "}away from becoming job-ready.

                Generate your AI-powered roadmap to receive
                weekly milestones, recommended learning resources,
                projects and progress tracking designed specifically
                for your journey.
            </p>

        </div>

        {/* Right */}

        <div
            className="
                flex
                flex-col
                items-center
                gap-5
                min-w-[240px]
            "
        >
<button
    onClick={() => navigate("/roadmap")}
    className="
        group
        relative
        inline-flex
        items-center
        gap-3
        overflow-hidden
        rounded-2xl
        bg-gradient-to-r
        from-[#6B5CF6]
        to-[#7C6CFF]
        px-8
        py-4
        text-[15px]
        font-semibold
        text-white
        shadow-[0_10px_30px_rgba(107,92,246,0.28)]
        transition-all
        duration-300
        hover:-translate-y-0.5
        hover:shadow-[0_16px_40px_rgba(107,92,246,0.38)]
        active:translate-y-0
        focus:outline-none
        focus:ring-4
        focus:ring-violet-200
    "
>
    <span className="relative z-10">
        Build Career Roadmap
    </span>

    <ArrowRight
        size={18}
        className="
            relative
            z-10
            transition-transform
            duration-300
            group-hover:translate-x-1
        "
    />

    <span
        className="
            absolute
            inset-0
            bg-white/10
            opacity-0
            transition-opacity
            duration-300
            group-hover:opacity-100
        "
    />
</button>
        

        </div>

    </div>

</div>



</div>

</main>

</div>

</>

);

}

function StatCard({

    title,

    value,

    subtitle

}) {

    return (

        <div
            className="
                rounded-[20px]
                border
                border-[#ECEAF5]
                bg-[#FCFCFE]
                px-6
                py-6
                transition
                hover:shadow-lg
hover:-translate-y-1
duration-300
                hover:border-violet-200
            "
        >

            <p
                className="
                    text-[13px]
                    uppercase
                    tracking-[0.12em]
                    text-slate-400
                    font-semibold
                "
            >
                {title}
            </p>

            <h3
                className="
                    mt-3
                    text-[22px]
                    font-bold
                    tracking-[-0.03em]
                    text-[#1E2340]
                "
            >
                {value}
            </h3>

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

    );

}

function SkillSection({

    title,

    skills,

    color

}) {

    const colors = {

        emerald:
            "bg-emerald-50 text-emerald-700",

        violet:
            "bg-violet-50 text-violet-700",

        blue:
            "bg-blue-50 text-blue-700",

        red:
            "bg-red-50 text-red-700"

    };

    return (

        <div
    className="
        bg-white
        border
        border-[#ECEAF5]
        rounded-[18px]
        p-4
    "
>

            <h4
                className="
                    text-[20px]
                    font-semibold
                    text-[#1E2340]
                    mb-2
                "
            >
                {title}
            </h4>

            <div className="flex flex-wrap gap-1.5">

                {skills?.map((skill,index)=>(

                    <span

                        key={index}

                        className={`
                           px-3
py-1.5
rounded-full
border
border-white
transition

                            text-[15px]
                            font-medium
                            ${colors[color]}
                        `}
                    >

                        {skill}

                    </span>

                ))}

            </div>

        </div>

    );

}




function InsightRow({

    title,

    value

}){

    return(

        <div
            className="
                flex
                items-center
                justify-between
                border-b
                border-[#F1F2F6]
                pb-4
            "
        >

            <span
                className="
                    text-[14px]
                    text-slate-500
                "
            >
                {title}
            </span>

            <span
                className="
                    text-[14px]
                    font-semibold
                    text-[#1E2340]
                "
            >
                {value}
            </span>

        </div>

    );

}

function FeatureItem({

    text

}){

    return(

        <div
            className="
                flex
                items-start
                gap-3
                rounded-[18px]
                bg-[#FBFBFD]
border
border-[#ECEAF5]
                px-4
                py-3
            "
        >

            <div
                className="
                    w-7
                    h-7
                    rounded-full
                    bg-violet-100
                    flex
                    items-center
                    justify-center
                    shrink-0
                "
            >

                <Check
                    size={14}
                    className="text-[#6B5CF6]"
                />

            </div>

            <p
                className="
                    text-[14px]
                    leading-6
                    text-[#1E2340]
                "
            >
                {text}
            </p>

        </div>

    );

}

function AnalysisBadge({

    title

}){

    return(

        <div
            className="
                px-4
                py-2
                rounded-full
                bg-white
                border
                border-[#ECEAF5]
                text-[13px]
                font-medium
                text-slate-600
            "
        >

            ✓ {title}

        </div>

    );

}

function OverviewCard({

    title,

    value,

    icon

}){

    return(

        <div
            className="
                bg-white
                border
                border-[#ECEAF5]
                rounded-[22px]
                px-6
                py-5
                flex
                items-center
                gap-4
            "
        >

            <div
                className="
                    w-11
                    h-11
                    rounded-2xl
                    bg-violet-50
                    flex
                    items-center
                    justify-center
                    text-[#6B5CF6]
                "
            >

                {icon}

            </div>

            <div>

                <p
                    className="
                        text-[13px]
                        text-slate-400
                        uppercase
                        tracking-[0.12em]
                    "
                >
                    {title}
                </p>

                <h4
                    className="
                        mt-1
                        text-[17px]
                        font-semibold
                        text-[#1E2340]
                    "
                >
                    {value}
                </h4>

            </div>

        </div>

    );

}

export default SkillGapPage;
