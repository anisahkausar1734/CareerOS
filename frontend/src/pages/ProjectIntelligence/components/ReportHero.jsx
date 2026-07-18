import {
    FolderGit2,
    Globe,
    Star,
    ShieldCheck,
    Sparkles,
} from "lucide-react";

export default function ReportHero({ project }) {

    return (

        <section
            className="
                rounded-3xl
                border
                border-slate-200
                bg-white
                p-10
                shadow-sm
            "
        >

            <div className="flex justify-between gap-10">

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
                                    text-slate-900
                                "
                            >
                                {project.projectName}
                            </h1>

                        </div>

                    </div>

                    <p
                        className="
                            mt-6
                            max-w-3xl
                            text-sm
                            leading-8
                            text-slate-600
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
                                    px-4
                                    py-2
                                    text-sm
                                    font-medium
                                    text-slate-700
                                "
                            >
                                {tech}
                            </span>

                        ))}

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
                        bg-gradient-to-br
                        from-violet-600
                        to-indigo-600
                        p-8
                        text-white
                    "
                >

                    <ShieldCheck className="h-10 w-10" />

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
                            {project.verdict}
                        </span>

                    </div>

                </div>

            </div>

        </section>

    );

}