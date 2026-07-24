import {
    FileText,
    Briefcase,
    Building2,
    Target,
    TrendingUp,
    UserCheck,
} from "lucide-react";

const metrics = [
    {
        title: "Resume Impact",
        key: "resumeImpact",
        icon: FileText,
    },
    {
        title: "Internship Readiness",
        key: "internshipImpact",
        icon: Briefcase,
    },
    {
        title: "Job Readiness",
        key: "jobImpact",
        icon: Building2,
    },
    {
        title: "Role Alignment",
        key: "roleAlignment",
        icon: Target,
    },
    {
        title: "Industry Demand",
        key: "industryDemand",
        icon: TrendingUp,
    },
    {
        title: "Hiring Signal",
        key: "hiringSignal",
        icon: UserCheck,
    },
];

export default function CareerImpactSection({ project }) {

    return (

        <section
            className="
                mt-8
                rounded-3xl
                border
                border-slate-200
                bg-white
                p-8
                shadow-sm
            "
        >

            <h2 className="text-2xl font-bold">

                Career Intelligence

            </h2>

            <p className="mt-2 text-slate-500">

                Understand how this project strengthens your resume, interview readiness, and career opportunities.

            </p>

           <div className="mt-8 grid gap-5 md:grid-cols-2 xl:grid-cols-3">

                {metrics.map((metric) => {

                    const Icon = metric.icon;

                    const value = project[metric.key] ?? 0;

                   return (

    <div
        key={metric.key}
        className="
            rounded-2xl
            border
            border-slate-200
            bg-slate-50
            p-5
            transition
            hover:border-violet-300
            hover:shadow-md
        "
    >

        <div className="flex items-center justify-between">

            <div className="flex items-center gap-3">

                <div className="rounded-xl bg-violet-100 p-3">

                    <Icon className="h-5 w-5 text-violet-700" />

                </div>

                <div>

                    <h4 className="font-semibold text-slate-900">

                        {metric.title}

                    </h4>

                    <p className="mt-1 text-sm text-slate-500">

                        {value >= 90
                            ? "Excellent"
                            : value >= 80
                            ? "Very Good"
                            : value >= 70
                            ? "Good"
                            : value >= 60
                            ? "Average"
                            : "Needs Improvement"}

                    </p>

                </div>

            </div>

            <span
                className="
                    rounded-xl
                    bg-violet-100
                    px-3
                    py-1
                    text-sm
                    font-bold
                    text-violet-700
                "
            >
                {value}/100
            </span>

        </div>

    </div>

);

                })}

            </div>

           <div
    className="
        mt-10
        rounded-2xl
        border
        border-violet-200
        bg-violet-50
        p-6
    "
>

               <h3 className="text-xl font-bold text-slate-900">

    Career Intelligence Summary

</h3>

                <p
                    className="
                        mt-4
                        leading-8
                        text-slate-700
                    "
                >
                    <p className="mt-2 text-sm text-slate-500">
    AI-generated summary of this project's impact on your software engineering career.
</p>

                    {project.overallCareerVerdict}

                </p>

            </div>

        </section>

    );

}