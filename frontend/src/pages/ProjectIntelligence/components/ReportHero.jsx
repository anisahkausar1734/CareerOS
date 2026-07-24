import {
    ArrowLeft,
    FolderGit2,
    Globe,
    Star,
    ShieldCheck,
    Sparkles,
    RotateCw,
} from "lucide-react";

import { useNavigate } from "react-router-dom";

export default function ReportHero({ project }) {

    const navigate = useNavigate();

   return (

<>
    <button
        onClick={() => navigate(-1)}
        className="
            mb-5
            inline-flex
            items-center
            gap-2
            text-sm
            font-medium
            text-slate-600
            hover:text-violet-600
        "
    >
        <ArrowLeft className="h-4 w-4" />
        Back to Projects
    </button>

    <section

            className="
                rounded-3xl
                border
                border-slate-200
                bg-white
                p-8
                shadow-sm
            "
        >

<div
    className="
        grid
        gap-8
        lg:grid-cols-[1fr_320px]
        items-start
    "
>

                {/* Left */}

                <div className="flex-1">

                    <div className="flex items-center gap-3">

                        <div
                            className="
                                rounded-2xl
                                bg-violet-100
                                p-4
                            "
                        >
                            <Sparkles
                                className="
                                    h-7
                                    w-7
                                    text-violet-700
                                "
                            />
                        </div>

                        <div>

                            <p
                                className="
                                    text-sm
                                    font-medium
                                    uppercase
                                    tracking-widest
                                    text-violet-600
                                "
                            >
                                Project Intelligence Report
                            </p>

                            <h1
                                className="
                                    mt-1
                                    text-2xl
                                    font-bold
                                    tracking-tight
                                    text-slate-900
                                "
                            >
                                {project.projectName}
                            </h1>

                        </div>

                    </div>

                   <p
    className="
        mt-5
        max-w-3xl
        text-base
        leading-7
        text-slate-600
        line-clamp-3
    "
>
                      {project.description}
                    </p>

                    {/* Tech Stack */}

                    <div
                        className="
                            mt-8
                            flex
                            flex-wrap
                            gap-3
                        "
                    >

                        {(project.techStack ?? []).map((tech) => (

                            <span
                                key={tech}
                                className="
                                   rounded-full
bg-slate-100
px-3
py-1
text-xs
font-medium
                                    text-slate-700
                                "
                            >
                                {tech}
                            </span>

                        ))}

                    </div>

                    <div className="mt-6 flex flex-wrap items-center gap-4 text-sm text-slate-500">

    <span>
        Analyzed:
        {" "}
        {project.analyzedAt
            ? new Date(project.analyzedAt).toLocaleDateString()
            : "--"}
    </span>

    <span>•</span>

    <span>
        {project.analysisStatus ?? "Completed"}
    </span>

    <span>•</span>

    <span>
        Version {project.analysisVersion ?? 1}
    </span>

</div>

                    {/* Links */}

                    <div
                        className="
                            mt-8
                            flex
                            gap-4
                        "
                    >

                        {project.githubUrl && (

                            <a
                                href={project.githubUrl}
                                target="_blank"
                                rel="noreferrer"
                                className="
                                    inline-flex
                                    items-center
                                    gap-2
                                    rounded-xl
                                    border
                                    border-slate-200
                                    px-5
                                    py-3
                                    font-medium
                                    hover:bg-slate-50
                                "
                            >

                                <FolderGit2 className="h-5 w-5" />

                                GitHub

                            </a>

                        )}

                        {project.liveUrl && (

                            <a
                                href={project.liveUrl}
                                target="_blank"
                                rel="noreferrer"
                                className="
                                    inline-flex
                                    items-center
                                    gap-2
                                    rounded-xl
                                    border
                                    border-slate-200
                                    px-5
                                    py-3
                                    font-medium
                                    hover:bg-slate-50
                                "
                            >

                                <Globe className="h-5 w-5" />

                                Live Demo

                                <button
    className="
        inline-flex
        items-center
        gap-2
        rounded-xl
        bg-violet-600
        px-5
        py-3
        font-medium
        text-white
        hover:bg-violet-700
    "
>
    <RotateCw className="h-5 w-5" />
    Re-analyze
</button>

                            </a>

                        )}

                    </div>

                </div>

                {/* Right */}

                <div
                    className="
                        flex
                        w-72
                        flex-col
                        items-center
                        justify-center
                        rounded-3xl
                       border
border-violet-200
bg-violet-50
                        p-8
                        text-slate-900
                    "
                >

                    <div className="rounded-2xl bg-violet-600 p-3">
    <ShieldCheck className="h-7 w-7 text-white" />
</div>

                    <p className="mt-6 text-sm uppercase tracking-widest">

                        Engineering Score

                    </p>

                    <h2
                        className="
                            mt-2
                            text-5xl
                            font-extrabold
                        "
                    >
                        {project.projectScore ?? "--"}
                    </h2>

                    <div
                        className="
                            mt-6
                            flex
                            items-center
                            gap-2
                        "
                    >

                        <Star
                            className="
                                h-5
                                w-5
                                fill-yellow-300
                                text-yellow-300
                            "
                        />

                        <span
                            className="
                                text-sm
                                font-semibold
                            "
                        >
                            {project.verdict ?? "Engineering Quality Verified"}
                        </span>

                    </div>

                </div>

            </div>

      </section>

</>

);

}