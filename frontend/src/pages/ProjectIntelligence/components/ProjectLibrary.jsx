import {
    FolderGit2,
    Plus,
} from "lucide-react";

import EmptyProjects from "./EmptyProjects";
import ProjectCard from "./ProjectCard";

export default function ProjectLibrary({

    projects,

    loading,

    onReAnalyze


}) {

if (loading) {

    return (

        <section className="rounded-3xl border bg-white p-10">

            Loading Projects...

        </section>

    );

}
    return (

        <section
            className="
                rounded-3xl
                border
                border-slate-200
                bg-white
                shadow-sm
            "
        >

            {/* Header */}

            <div
                className="
                    flex
                    items-center
                    justify-between
                    border-b
                    border-slate-100
                    p-8
                "
            >

                <div>

                    <h2
                        className="
                            text-2xl
                            font-bold
                            text-slate-900
                        "
                    >
                        My Projects
                    </h2>

                    <p
                        className="
                            mt-1
                            text-slate-500
                        "
                    >
                        Your engineering portfolio.
                    </p>

                </div>

                <button
                    className="
                        inline-flex
                        items-center
                        gap-2
                        rounded-2xl
                        bg-violet-600
                        px-5
                        py-3
                        font-medium
                        text-white
                        transition
                        hover:bg-violet-700
                    "
                >

                    <Plus className="h-5 w-5" />

                    Analyze Project

                </button>

            </div>

            {/* Body */}

           <div
    className="
        grid
        grid-cols-1
        gap-8
    "
>
    {projects.map((project) => (
        <ProjectCard
            key={project.id}
            project={project}
            onReAnalyze={onReAnalyze}
        />
    ))}
</div>

        </section>

    );

}