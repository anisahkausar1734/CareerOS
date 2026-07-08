import { useEffect, useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";

import {
    Pencil,
    Mail,
    Phone,
    Building2,
    GraduationCap,
    BookOpen,
    Target,
    Brain,
    Route,
    FileText,
    BriefcaseBusiness,
    Mic,
    CircleCheckBig,
    Clock3,
    Info,
    UserCircle2,
    TrendingUp
} from "lucide-react";

function ProfilePage() {

    const [profile, setProfile] = useState(null);

    const email =
        localStorage.getItem("email");

    useEffect(() => {

        fetchProfile();

    }, []);

    const fetchProfile = async () => {

        try {

            const response =
                await axios.get(
                    `http://localhost:8080/api/student-profile/${email}`
                );

            setProfile(
                response.data
            );

        }

        catch (error) {

            console.error(error);

        }

    };

    if (!profile) {

        return (

            <>

                <Sidebar />

                <div
                    className="
                        ml-[240px]
                        
                        bg-[#F6F7FB]
                        flex
                        items-center
                        justify-center
                        text-lg
                        text-slate-500
                    "
                >

                    Loading Profile...

                </div>

            </>

        );

    }

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

                    {/* ================= HEADER ================= */}

                    <div
                        className="
                            flex
                            justify-between
                            items-start
                            mb-10
                        "
                    >

                        <div>

                            <h1
                                className="
                                    text-[42px]
                                    font-bold
                                    tracking-tight
                                    text-[#1E2340]
                                "
                            >

                                My Profile

                            </h1>

                            <p
                                className="
                                    mt-4
                                    text-[17px]
                                    leading-8
                                    max-w-2xl
                                    text-slate-500
                                "
                            >

                                Manage your personal information,
                                academic details and career preferences
                                that power your personalized CareerOS
                                journey.

                            </p>

                        </div>

                        <button

                            onClick={() =>
                                window.location.href =
                                "/profile/edit"
                            }

                            className="
                                flex
                                items-center
                                gap-2
                                px-6
                                py-3
                                rounded-2xl
                                bg-[#7367F0]
                                hover:bg-[#6559E9]
                                transition
                                text-white
                                font-semibold
                                shadow-md
                            "

                        >

                            <Pencil size={18} />

                            Edit Profile

                        </button>

                    </div>

                    {/* ================= HERO ================= */}

                    <div
    className="
        bg-white
        rounded-[24px]
        border
        border-[#ECEAF5]
        shadow-[0_10px_30px_rgba(15,23,42,0.05)]
        p-8
        mb-8
    "
>

    <div
        className="
            grid
            grid-cols-[1.5fr_1fr_1fr]
            items-center
        "
    >

        {/* ================= LEFT ================= */}

        <div
            className="
                flex
                items-center
                gap-6
                pr-8
            "
        >

            <div
                className="
                    w-20
                    h-20
                    rounded-full
                    bg-gradient-to-br
                    from-[#7367F0]
                    to-[#8E82FF]
                    flex
                    items-center
                    justify-center
                    text-white
                    text-3xl
                    font-bold
                    shrink-0
                "
            >

                {profile.fullName?.charAt(0)}

            </div>

            <div>

                <h2
                    className="
                        text-[34px]
                        font-bold
                        text-[#1E2340]
                    "
                >

                    {profile.fullName}

                </h2>

                <p
                    className="
                        mt-2
                        text-lg
                        font-semibold
                        text-[#7367F0]
                    "
                >

                    {profile.dreamRole}

                </p>

                <div className="mt-6 space-y-3">

                    <div className="flex items-center gap-3">

                        <Mail
                            size={16}
                            className="text-slate-400"
                        />

                        <span className="text-sm text-slate-500">

                            {profile.email}

                        </span>

                    </div>

                    <div className="flex items-center gap-3">

                        <Phone
                            size={16}
                            className="text-slate-400"
                        />

                        <span className="text-sm text-slate-500">

                            {profile.phoneNumber}

                        </span>

                    </div>

                </div>

            </div>

        </div>

        {/* ================= CAREER READINESS ================= */}

        <div
            className="
                border-l
                border-r
                border-[#ECEAF5]
                px-8
                flex
                flex-col
                justify-center
            "
        >

            <div className="flex items-center gap-2">

                <span
                    className="
                        text-sm
                        text-slate-500
                    "
                >

                    Career Readiness

                </span>

                <Info
                    size={14}
                    className="text-slate-400"
                />

            </div>

            <h2
                className="
                    mt-4
                    text-[42px]
                    font-bold
                    text-[#7367F0]
                "
            >

                {profile.careerReadiness ?? 0}%

            </h2>

            <div
                className="
                    mt-5
                    h-[8px]
                    rounded-full
                    bg-[#ECEAF5]
                    overflow-hidden
                "
            >

                <div

                    className="
                        
                        rounded-full
                        bg-gradient-to-r
                        from-[#7367F0]
                        to-[#8E82FF]
                    "

                    style={{
                        width:
                            `${profile.careerReadiness ?? 0}%`
                    }}

                />

            </div>

            <p
                className="
                    mt-4
                    text-xs
                    text-slate-500
                "
            >

                Keep improving every day 🚀

            </p>

        </div>

        {/* ================= CURRENT STAGE ================= */}

        <div
            className="
                pl-8
                
                flex
                flex-col
                justify-center
            "
        >

            <div className="flex items-center gap-2">

                <Route
                    size={18}
                    className="text-[#7367F0]"
                />

                <span
                    className="
                        text-sm
                        text-slate-500
                    "
                >

                    Current Stage

                </span>

            </div>

            <h3
                className="
                    mt-4
                    text-[28px]
                    font-bold
                    leading-9
                    text-[#1E2340]
                "
            >

                {
                    profile.currentStage
                        ?.replaceAll("_"," ")
                        ?.toLowerCase()
                        ?.replace(/\b\w/g, c => c.toUpperCase())
                }

            </h3>

            <div
                className="
                    mt-5
                    inline-flex
                    w-fit
                    rounded-full
                    bg-[#F4F2FF]
                    px-4
                    py-2
                    text-xs
                    font-semibold
                    text-[#7367F0]
                "
            >

                You're on the right track

            </div>

        </div>

    </div>

</div>

{/* ================= INFORMATION CARDS ================= */}

<div
    className="
        grid
        grid-cols-3
        gap-6
        mb-8
        items-start
    "
>

    {/* ================= PERSONAL INFORMATION ================= */}

    <div
        className="
            bg-white
            rounded-[24px]
            border
            border-[#ECEAF5]
            shadow-[0_10px_30px_rgba(15,23,42,0.05)]
            p-5
        "
    >

        <div className="flex items-center gap-3 mb-5">

            <div
                className="
                    w-10
                    h-10
                    rounded-xl
                    bg-[#F4F2FF]
                    flex
                    items-center
                    justify-center
                "
            >

                <UserCircle2
                    size={20}
                    className="text-[#7367F0]"
                />

            </div>

            <div>

                <h3
                    className="
                        text-[17px]
                        font-bold
                        text-[#1E2340]
                    "
                >

                    Personal Information

                </h3>

                <p
                    className="
                        text-[13px]
                        text-slate-500
                        mt-1
                    "
                >

                    Basic personal details

                </p>

            </div>

        </div>

        <InfoRow

            icon={UserCircle2}

            label="Full Name"

            value={profile.fullName}

        />

        <InfoRow

            icon={Mail}

            label="Email Address"

            value={profile.email}

        />

        <InfoRow

            icon={Phone}

            label="Phone Number"

            value={profile.phoneNumber}

        />

    </div>

    {/* ================= ACADEMIC CARD ================= */}

    <div
    className="
        bg-white
        rounded-[24px]
        border
        border-[#ECEAF5]
        shadow-[0_10px_30px_rgba(15,23,42,0.05)]
        p-5
    "
>

    <div className="flex items-center gap-3 mb-5">

        <div
            className="
                w-10
                h-10
                rounded-xl
                bg-[#F4F2FF]
                flex
                items-center
                justify-center
            "
        >

            <GraduationCap
                size={20}
                className="text-[#7367F0]"
            />

        </div>

        <div>

            <h3
                className="
                    text-[17px]
                    font-bold
                    text-[#1E2340]
                "
            >

                Academic Information

            </h3>

            <p
                className="
                    text-[13px]
                    text-slate-500
                    mt-1
                "
            >

                Education details

            </p>

        </div>

    </div>

    <InfoRow
        icon={Building2}
        label="College"
        value={profile.collegeName}
    />

    <InfoRow
        icon={GraduationCap}
        label="Degree"
        value={profile.degree}
    />

    <InfoRow
        icon={BookOpen}
        label="Branch"
        value={profile.branch}
    />

    <InfoRow
        icon={TrendingUp}
        label="Current Year"
        value={profile.currentYear}
    />

    <InfoRow
        icon={GraduationCap}
        label="Graduation Year"
        value={profile.graduationYear}
    />

</div>

{/* ================= CAREER PROFILE CARD ================= */}


<div
    className="
        bg-white
        rounded-[24px]
        border
        border-[#ECEAF5]
        shadow-[0_10px_30px_rgba(15,23,42,0.05)]
        p-5
    "
>

    <div className="flex items-center gap-3 mb-5">

        <div
            className="
                w-10
                h-10
                rounded-xl
                bg-[#F4F2FF]
                flex
                items-center
                justify-center
            "
        >

            <Target
                size={20}
                className="text-[#7367F0]"
            />

        </div>

        <div>

            <h3
                className="
                    text-[17px]
                    font-bold
                    text-[#1E2340]
                "
            >

                Career Profile

            </h3>

            <p
                className="
                    text-[13px]
                    text-slate-500
                    mt-1
                "
            >

                Your career aspirations

            </p>

        </div>

    </div>

    <InfoRow
        icon={Target}
        label="Dream Role"
        value={profile.dreamRole}
    />

    <div className="mt-6">

        <p
            className="
                text-[13px]
                font-medium
                text-slate-500
                mb-4
            "
        >

            Skills

        </p>

        <div className="flex flex-wrap gap-2">

            {

                profile.skills?.length > 0

                ?

                profile.skills.map((skill,index)=>(

                    <span

                        key={index}

                        className="
                            px-3
                            py-2
                            rounded-full
                            bg-[#F4F2FF]
                            text-[#7367F0]
                            text-xs
                            font-semibold
                        "

                    >

                        {skill}

                    </span>

                ))

                :

                <span
                    className="
                        text-sm
                        text-slate-400
                    "
                >

                    No Skills Added

                </span>

            }

        </div>

    </div>

    <div className="mt-6">

        <InfoRow
            icon={FileText}
            label="Resume"
            value={
                profile.resumeCompleted
                    ? "Uploaded"
                    : "Not Uploaded"
            }
        />

    </div>

</div>

</div>

{/* ================= CAREER JOURNEY ================= */}

<div
    className="
        bg-white
        rounded-[24px]
        border
        border-[#ECEAF5]
        shadow-[0_10px_30px_rgba(15,23,42,0.05)]
        p-8
        mb-8
    "
>

    {/* HEADER */}

    <div
        className="
            flex
            items-center
            justify-between
            mb-10
        "
    >

        <div>

            <h2
                className="
                    text-[22px]
                    font-bold
                    text-[#1E2340]
                "
            >

                Career Journey

            </h2>

            <p
                className="
                    mt-2
                    text-[14px]
                    text-slate-500
                "
            >

                Track your progress through every CareerOS milestone.

            </p>

        </div>

        <button

            className="
                text-[#7367F0]
                font-semibold
                text-sm
                hover:underline
            "

        >

            View Journey →

        </button>

    </div>

    {/* TIMELINE */}

    <div
        className="
            flex
            items-center
            justify-between
            gap-5
        "
    >

        <JourneyStep

            icon={Brain}

            title="Skill Gap"

            completed={profile.skillGapCompleted}

        />

        <JourneyStep

            icon={Route}

            title="Roadmap"

            completed={profile.roadmapCompleted}

        />

        <JourneyStep

            icon={FileText}

            title="Resume"

            completed={profile.resumeCompleted}

        />

        <JourneyStep

            icon={Mic}

            title="Interview"

            completed={profile.interviewCompleted}

        />

        <JourneyStep

            icon={BriefcaseBusiness}

            title="Jobs"

            completed={profile.jobsCompleted}

            isLast

        />

    </div>

</div>

{/* ================= PROFILE COMPLETION ================= */}

<div
    className="
        bg-white
        rounded-[24px]
        border
        border-[#ECEAF5]
        shadow-[0_10px_30px_rgba(15,23,42,0.05)]
        p-8
        mb-10
    "
>

    <div
        className="
            flex
            justify-between
            items-center
            gap-10
        "
    >

        {/* LEFT */}

        <div className="flex items-center gap-6">

            <div
                className="
                    w-24
                    h-24
                    rounded-full
                    border-[6px]
                    border-[#7367F0]
                    flex
                    items-center
                    justify-center
                    text-[26px]
                    font-bold
                    text-[#1E2340]
                "
            >

                {profile.careerReadiness ?? 0}%

            </div>

            <div>

                <h2
                    className="
                        text-[22px]
                        font-bold
                        text-[#1E2340]
                    "
                >
                    Profile Completion
                </h2>

                <p
                    className="
                        mt-2
                        text-sm
                        leading-6
                        text-slate-500
                        max-w-sm
                    "
                >

                    Complete every section to unlock
                    the complete AI-powered CareerOS
                    experience.

                </p>

            </div>

        </div>

        {/* CENTER */}

        <div
            className="
                grid
                grid-cols-5
                gap-6
            "
        >

            <CompletionCard
                title="Personal"
                completed
            />

            <CompletionCard
                title="Academic"
                completed
            />

            <CompletionCard
                title="Career"
                completed
            />

            <CompletionCard
                title="Skills"
                completed={
                    profile.skills?.length > 0
                }
            />

            <CompletionCard
                title="Resume"
                completed={
                    profile.resumeCompleted
                }
            />

        </div>

        {/* RIGHT */}

        <button

            onClick={()=>

                window.location.href="/resume-center"

            }

            className="
                bg-[#7367F0]
                hover:bg-[#6559E9]
                transition
                text-white
                rounded-2xl
                px-7
                py-3.5
                font-semibold
                shadow-md
                whitespace-nowrap
            "

        >

            Upload Resume

        </button>

    </div>

</div>

                </div>

            </div>

        </>

    );

}

function InfoRow({

    icon: Icon,

    label,

    value

}) {

    return (

        <div
            className="
                flex
                items-start
                gap-4
                py-2.5
                border-b
                border-[#F2F3F7]
                last:border-none
            "
        >

            <div
                className="
                    w-8
                    h-8
                    rounded-xl
                    bg-[#F4F2FF]
                    flex
                    items-center
                    justify-center
                    shrink-0
                "
            >

                <Icon
                    size={16}
                    className="text-[#7367F0]"
                />

            </div>

            <div className="flex-1">

                <p
                    className="
                        text-xs
                        text-slate-500
                    "
                >
                    {label}
                </p>

                <p
                    className="
                       mt-0.5
text-[14px]
font-medium
                        text-[#1E2340]
                    "
                >
                    {value || "Not Added"}
                </p>

            </div>

        </div>

    );

}

function JourneyStep({

    icon: Icon,

    title,

    completed,

    isLast

}) {

    return (

        <div
            className="
                relative
                flex-1
            "
        >

            <div
                className="
                    bg-[#FAFAFD]
                    border
                    border-[#ECEAF5]
                    rounded-2xl
                
                    px-4
                    py-6
                    flex
                    flex-col
                    items-center
                    justify-center
                    text-center
                    relative
                    z-10
                "
            >

                <div
                    className={`
                        w-14
                        h-14
                        rounded-full
                        flex
                        items-center
                        justify-center
                        ${
                            completed
                                ? "bg-[#7367F0] text-white"
                                : "bg-[#F4F2FF] text-[#7367F0]"
                        }
                    `}
                >

                    <Icon size={24} />

                </div>

                <h4
                    className="
                        mt-5
                        font-semibold
                        text-sm
                        text-[#1E2340]
                    "
                >

                    {title}

                </h4>

                <span
                    className={`
                        mt-3
                        px-3
                        py-1
                        rounded-full
                        text-xs
                        font-medium
                        ${
                            completed
                                ? "bg-green-100 text-green-700"
                                : "bg-slate-100 text-slate-500"
                        }
                    `}
                >

                    {
                        completed
                            ? "Completed"
                            : "Pending"
                    }

                </span>

            </div>

            {

                !isLast && (

                    <div
                        className="
                            absolute
                            top-8
                            left-full
                            w-6
                            
                            bg-[#E6E7EF]
                        "
                    />

                )

            }

        </div>

    );

}

function CompletionCard({

    title,

    completed

}) {

    return (

        <div
            className="
                flex
                flex-col
                items-center
                justify-center
                text-center
                gap-3
            "
        >

            {

                completed

                ?

                <CircleCheckBig
                    size={24}
                    className="text-green-500"
                />

                :

                <Clock3
                    size={24}
                    className="text-slate-400"
                />

            }

            <p
                className="
                    text-sm
                    font-semibold
                    text-[#1E2340]
                "
            >

                {title}

            </p>

        </div>

    );

}

export default ProfilePage;