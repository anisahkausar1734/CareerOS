import {
    FolderGit2,
    BadgeCheck,
    TrendingUp,
    TriangleAlert,
} from "lucide-react";

export default function ProjectStats({

    projects

}) {
    
  const totalProjects = projects?.length ?? 0;

const resumeReadyProjects = projects?.filter(
    (project) => (project.engineeringScore ?? project.projectScore ?? 0) >= 90
).length ?? 0;

const averageScore =
    totalProjects > 0
        ? Math.round(
              projects.reduce(
                  (sum, project) => sum + (project.engineeringScore ?? project.projectScore ?? 0),
                  0
              ) / totalProjects
          )
        : 0;

const needsImprovementProjects = projects?.filter(
    (project) => (project.engineeringScore ?? project.projectScore ?? 0) < 70
).length ?? 0;


    const stats = [
        {
            title: "Total Projects",
            value: totalProjects,
            subtitle: "Analyzed",
            icon: FolderGit2,
            bg: "bg-violet-100",
            color: "text-violet-700",
        },
        {
            title: "Resume Ready",
            value: resumeReadyProjects,
            subtitle: "Projects",
            icon: BadgeCheck,
            bg: "bg-emerald-100",
            color: "text-emerald-700",
        },
        {
            title: "Average Score",
            value: averageScore,
            subtitle: "Out of 100",
            icon: TrendingUp,
            bg: "bg-blue-100",
            color: "text-blue-700",
        },
        {
            title: "Needs Improvement",
            value: needsImprovementProjects,
            subtitle: "Projects",
            icon: TriangleAlert,
            bg: "bg-orange-100",
            color: "text-orange-700",
        },
    ];

    return (
        <section className="grid gap-6 sm:grid-cols-2 xl:grid-cols-4">

            {stats.map((stat) => {

                const Icon = stat.icon;

                return (

                    <div
                        key={stat.title}
                        className="
                            group
                            rounded-3xl
                            border
                            border-slate-200
                            bg-white
                            p-6
                            shadow-sm
                            transition-all
                            duration-300
                            hover:-translate-y-1
                            hover:shadow-lg
                        "
                    >

                        <div className="flex items-center justify-between">

                            <div
                                className={`
                                    flex
                                    h-14
                                    w-14
                                    items-center
                                    justify-center
                                    rounded-2xl
                                    ${stat.bg}
                                `}
                            >

                                <Icon
                                    className={`
                                        h-7
                                        w-7
                                        ${stat.color}
                                    `}
                                />

                            </div>

                            <span
                                className="
                                    rounded-full
                                    bg-slate-100
                                    px-3
                                    py-1
                                    text-xs
                                    font-medium
                                    text-slate-500
                                "
                            >
                                Live
                            </span>

                        </div>

                        <div className="mt-8">

                            <p className="text-sm text-slate-500">
                                {stat.title}
                            </p>

                            <h2
                                className="
                                    mt-2
                                    text-4xl
                                    font-bold
                                    text-slate-900
                                "
                            >
                                {stat.value}
                            </h2>

                            <p
                                className="
                                    mt-2
                                    text-sm
                                    text-slate-500
                                "
                            >
                                {stat.subtitle}
                            </p>

                        </div>

                    </div>

                );

            })}

        </section>
    );
}