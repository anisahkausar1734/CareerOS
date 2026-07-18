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

                Career Impact

            </h2>

            <p className="mt-2 text-slate-500">

                AI evaluation of how this project improves your
                software engineering career.

            </p>

            <div className="mt-8 space-y-7">

                {metrics.map((metric) => {

                    const Icon = metric.icon;

                    const value = project[metric.key] ?? 0;

                    return (

                        <div key={metric.key}>

                            <div className="flex justify-between">

                                <div className="flex items-center gap-3">

                                    <div className="rounded-xl bg-violet-100 p-3">

                                        <Icon className="h-5 w-5 text-violet-700" />

                                    </div>

                                    <span className="font-medium">

                                        {metric.title}

                                    </span>

                                </div>

                                <span className="font-bold">

                                    {value}/100

                                </span>

                            </div>

                            <div className="mt-3 h-3 rounded-full bg-slate-200">

                                <div
                                    className="
                                        h-3
                                        rounded-full
                                        bg-gradient-to-r
                                        from-emerald-500
                                        to-green-600
                                    "
                                    style={{
                                        width: `${value}%`,
                                    }}
                                />

                            </div>

                        </div>

                    );

                })}

            </div>

            <div
                className="
                    mt-10
                    rounded-2xl
                    bg-violet-50
                    p-6
                "
            >

                <h3 className="text-xl font-bold">

                    Overall Career Verdict

                </h3>

                <p
                    className="
                        mt-4
                        leading-8
                        text-slate-700
                    "
                >

                    {project.overallCareerVerdict}

                </p>

            </div>

        </section>

    );

}