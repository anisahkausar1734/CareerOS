import { useState } from "react";
import {
    ArrowRight,
    BarChart3,
    Briefcase,
    CheckCircle2,
    FolderGit2,
    Rocket,
} from "lucide-react";

export default function AnalyzeProjectCard({
    analyzing,
    onAnalyze,
}) {

    const [githubUrl, setGithubUrl] = useState("");
    const [liveUrl, setLiveUrl] = useState("");

    return (

        <section
            className="
                rounded-3xl
                border
                border-slate-200
                bg-white
                shadow-sm
                overflow-hidden
            "
        >
            <div className="grid lg:grid-cols-3">

                {/* LEFT */}

                <div className="lg:col-span-2 p-8">

                    <div className="flex items-center gap-3">

                        <div className="rounded-xl bg-violet-100 p-3">

                            <Rocket className="h-6 w-6 text-violet-700" />

                        </div>

                        <div>

                            <h2 className="text-2xl font-bold text-slate-900">
                                Analyze New Project
                            </h2>

                            <p className="mt-1 text-slate-500">
                                Enter your GitHub repository and let CareerOS
                                generate a complete engineering intelligence
                                report.
                            </p>

                        </div>

                    </div>

                    {/* FORM */}

                    <div className="mt-8 space-y-6">

                        {/* GitHub */}

                        <div>

                            <label className="mb-2 block font-medium text-slate-700">
                                GitHub Repository URL
                            </label>

                            <div className="relative">

                                <FolderGit2
                                    className="
                                        absolute
                                        left-4
                                        top-1/2
                                        h-5
                                        w-5
                                        -translate-y-1/2
                                        text-slate-400
                                    "
                                />

<input
    type="text"
    value={githubUrl}
    onChange={(e) =>
        setGithubUrl(e.target.value)
    }
    placeholder="https://github.com/username/project"
    className="
        w-full
        rounded-2xl
        border
        border-slate-200
        py-4
        pl-12
        pr-4
        outline-none
        transition
        focus:border-violet-500
        focus:ring-4
        focus:ring-violet-100
    "
/>

                            </div>

                        </div>

                        {/* LIVE */}

                        <div>

                            <label className="mb-2 block font-medium text-slate-700">
                                Live Project URL
                                <span className="ml-2 text-slate-400">
                                    (Optional)
                                </span>
                            </label>

                          <input

    value={liveUrl}

    onChange={(e)=>

        setLiveUrl(
            e.target.value
        )

    }

    />

                        </div>

                        {/* BUTTON */}

                     <button

    disabled={
        analyzing ||
        githubUrl.trim() === ""
    }

   onClick={() => {

    console.log("Analyze clicked");

    console.log({
        githubUrl,
        liveUrl,
    });

    onAnalyze({
        githubUrl,
        liveUrl,
    });

}}

    className="
        inline-flex
        items-center
        gap-2
        rounded-2xl
        bg-violet-600
        px-7
        py-4
        font-semibold
        text-white
        transition
        hover:bg-violet-700
        disabled:cursor-not-allowed
        disabled:opacity-60
    "
>

    {

        analyzing

            ?

            "Analyzing..."

            :

            "Analyze Project"

    }

</button>

                    </div>

                </div>

                {/* RIGHT */}

                <div
                    className="
                        border-l
                        border-slate-100
                        bg-gradient-to-b
                        from-violet-50
                        to-white
                        p-8
                    "
                >

                    <h3
                        className="
                            text-lg
                            font-bold
                            text-slate-900
                        "
                    >
                        What You'll Discover
                    </h3>

                    <p className="mt-2 text-sm leading-6 text-slate-500">
                        CareerOS performs a complete engineering review
                        of your project and transforms it into actionable
                        career insights.
                    </p>

                    <div className="mt-8 space-y-5">

                        <Insight
                            icon={<BarChart3 />}
                            title="Engineering Quality"
                        />

                        <Insight
                            icon={<CheckCircle2 />}
                            title="Resume Readiness"
                        />

                        <Insight
                            icon={<Briefcase />}
                            title="Career Impact"
                        />

                        <Insight
                            icon={<Rocket />}
                            title="Improvement Roadmap"
                        />

                    </div>

                </div>

            </div>

        </section>
    );
}

function Insight({ icon, title }) {
    return (
        <div className="flex items-center gap-4">

            <div
                className="
                    rounded-xl
                    bg-white
                    p-3
                    shadow-sm
                    text-violet-700
                "
            >
                {icon}
            </div>

            <span
                className="
                    font-medium
                    text-slate-700
                "
            >
                {title}
            </span>

        </div>
    );
}