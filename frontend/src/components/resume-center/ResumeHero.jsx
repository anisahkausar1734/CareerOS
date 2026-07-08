import {
    FileSearch,
    Search,
    FileText,
    CheckCircle2,
    Clock3
} from "lucide-react";

export default function ResumeHero({ data }) {

      const score =
        data?.resumeScore ?? 0;

    return (

        <div>
<div
    className="
        bg-white
        border
        border-[#ECECF6]
        rounded-[26px]
        shadow-sm
        px-4
        py-4
        mb-5
    "
>

    {/* ==========================================
                    TOP ROW
    ========================================== */}

    <div
        className="
            grid
            grid-cols-12
            gap-6
            items-start
        "
    >

        {/* Resume Icon */}

        <div
            className="
                col-span-2
                flex
                justify-center
            "
        >

            <div
                className="
                    h-[60px]
                    w-[60px]
                    rounded-full
                    border
                    border-[#ECECF6]
                    bg-gradient-to-br
                    from-white
                    to-[#F8F7FF]
                    shadow-sm
                    flex
                    items-center
                    justify-center
                "
            >

                <FileSearch
                    size={28}
                    strokeWidth={1.5}
                    className="text-[#6C63FF]"
                />

            </div>

        </div>

        {/* Resume Score */}

        <div
            className="
                col-span-3
            "
        >

            <p
    className="
        text-[15px]
        font-medium
        text-slate-500
    "
>
    Resume Score
</p>

<div
    className="
        mt-2
        flex
        items-end
        gap-2
    "
>

    <h2
        className="
            text-[18px]
            font-black
            tracking-[-0.03em]
            text-slate-900
        "
    >
        {score}
    </h2>

    <span
        className="
            text-[18px]
            text-slate-400
        "
    >
        /100
    </span>

</div>

<p
    className="
        mt-2
        text-[16px]
        font-semibold
        text-green-600
    "
>
    Good Foundation
</p>

<div
    className="
        mt-4
        w-[145px]
        h-[6px]
        rounded-full
        bg-slate-200
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
            width: `${score}%`
        }}
    />

</div>

</div>

{/* ==========================================
                Resume Confidence
========================================== */}

<div
    className="
        col-span-3
    "
>
    <p
    className="
        text-[15px]
        font-medium
        text-slate-500
    "
>
    Resume Confidence
</p>

<h3
    className="
        mt-1
        max-w-[285px]
        text-[15px]
        font-medium
        text-slate-800
    "
>
    {data?.resumeConfidence ?? "Medium"}
</h3>

<p
    className="
        mt-3
        text-[14px]
        text-slate-500
    "
>
    AI Confidence Level
</p>

<div
    className="
        mt-3
        flex
        items-center
        gap-2
    "
>

    <div className="w-3 h-3 rounded-full bg-[#F59E0B]" />

    <div className="w-3 h-3 rounded-full bg-[#F59E0B]" />

    <div className="w-3 h-3 rounded-full bg-[#F59E0B]" />

    <div className="w-3 h-3 rounded-full bg-slate-300" />

    <div className="w-3 h-3 rounded-full bg-slate-300" />

</div>

</div>

{/* ==========================================
            One Line Verdict
========================================== */}

<div
    className="
        col-span-4
    "
> <p
    className="
        text-[14px]
        font-medium
        text-slate-500
    "
>
    One Line Verdict
</p>

<p
    className="
        mt-1
        max-w-[285px]
        text-[15px]
        font-medium
        text-slate-800
    "
>

    {data?.oneLineResumeVerdict}

</p>

</div>

</div>

{/* ==========================================================
                    EXECUTIVE SUMMARY + ILLUSTRATION
========================================================== */}

<div
    className="
        mt-6
        grid
        grid-cols-12
        gap-6
        items-center
    "
>

{/* Left */}

<div
    className="
        col-span-8
    "
>

<h3
    className="
        text-[17px]
        font-semibold
        text-slate-900
    "
>
    Executive Summary
</h3>

<p
    className="
        mt-4
        text-[15px]
        leading-7
        text-slate-600
        pr-6
    "
>

    {data?.executiveSummary}

</p>

</div>

{/* Right */}

<div
    className="
        col-span-4
        flex
        justify-center
    "
>
    <div
    className="
        relative
        h-[250px]
        w-[250px]
        flex
        items-center
        justify-center
    "
>

    {/* Background Circle */}

    <div
        className="
            absolute
            h-[220px]
            w-[220px]
            rounded-full
            bg-gradient-to-br
            from-[#F3F0FF]
            via-[#ECE8FF]
            to-[#DDD6FE]
        "
    />

    {/* Resume Card */}

    <div
        className="
            absolute
            left-10
            top-6
            h-[140px]
            w-[100px]
            rounded-[22px]
            bg-white
            border
            border-[#ECECF6]
            shadow-xl
            flex
            items-center
            justify-center
        "
    >

        <FileText
            size={52}
            strokeWidth={1.6}
            className="text-[#6C63FF]"
        />

    </div>

    {/* Search Bubble */}

    <div
        className="
            absolute
            right-4
            bottom-5
            h-[84px]
            w-[84px]
            rounded-full
            bg-white
            border
            border-[#ECECF6]
            shadow-xl
            flex
            items-center
            justify-center
        "
    >

        <Search
            size={34}
            strokeWidth={2}
            className="text-[#6C63FF]"
        />

    </div>

    {/* Analytics Bars */}

    <div
        className="
            absolute
            left-4
            bottom-7
            flex
            items-end
            gap-[5px]
        "
    >

        <div className="w-[7px] h-7 rounded-full bg-[#C4B5FD]" />

        <div className="w-[7px] h-12 rounded-full bg-[#6C63FF]" />

        <div className="w-[7px] h-5 rounded-full bg-[#A78BFA]" />

    </div>

    {/* Floating Success */}

    <div
        className="
            absolute
            top-1
            right-7
            h-12
            w-12
            rounded-full
            bg-[#22C55E]
            shadow-lg
            flex
            items-center
            justify-center
        "
    >

        <CheckCircle2
            size={22}
            className="text-white"
        />

    </div>

</div>

</div>

</div>

{/* ==========================================================
                    HERO FOOTER
========================================================== */}

<div
    className="
        mt-8
        pt-5
        border-t
        border-[#ECECF6]
        flex
        items-center
        justify-between
    "
>
    {/* Left Status */}

<div
    className="
        flex
        items-center
        gap-3
    "
>

    <Clock3
        size={17}
        className="text-slate-400"
    />

    <p
        className="
            text-[14px]
            text-slate-500
        "
    >
        AI-powered resume intelligence personalized for your career profile.
    </p>

</div>

{/* Right Status */}

<div
    className="
        flex
        items-center
        gap-3
        rounded-full
        bg-green-50
        border
        border-green-100
        px-4
        py-2
    "
>

    <CheckCircle2
        size={16}
        className="text-green-600"
    />

    <span
        className="
            text-[14px]
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

    );

}