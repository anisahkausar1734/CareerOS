import {
    CheckCircle2,
    AlertTriangle,
    Rocket,
} from "lucide-react";

const sections = [
    {
        title: "Key Strengths",
        key: "strengths",
        icon: CheckCircle2,
        iconColor: "text-emerald-600",
        bg: "bg-emerald-50",
        border: "border-emerald-100",
    },
    {
        title: "Areas for Improvement",
        key: "weaknesses",
        icon: AlertTriangle,
        iconColor: "text-orange-600",
        bg: "bg-orange-50",
        border: "border-orange-100",
    },
    {
        title: "Recommended Next Steps",
        key: "improvements",
        icon: Rocket,
        iconColor: "text-violet-600",
        bg: "bg-violet-50",
        border: "border-violet-100",
    },
];

export default function EngineeringInsights({ project }) {

    return (

        <section className="mt-8">

            <div className="mb-6">

                <h2 className="text-2xl font-bold">

                    Engineering Insights

                </h2>

                <p className="mt-2 text-slate-500">

                    A summary of your project's strengths, improvement opportunities, and engineering recommendations.

                </p>

            </div>

            <div className="grid gap-6 lg:grid-cols-3">

                {sections.map((section) => {

                    const Icon = section.icon;

                    return (

                        <div
                            key={section.key}
                            className={`
                                rounded-2xl
                                border
                                ${section.border}
                                ${section.bg}
                                p-6
                            `}
                        >

                            <div className="flex items-center gap-3">

                                <Icon
                                    className={`
                                        h-6
                                        w-6
                                        ${section.iconColor}
                                    `}
                                />

                                <h3
                                    className="
                                        text-xl
                                        font-semibold
                                    "
                                >
                                    {section.title}
                                </h3>

                            </div>

                          <div className="mt-6 space-y-4">

    {(project[section.key] ?? []).length > 0 ? (

        (project[section.key] ?? []).map((item) => (

            <div
                key={item}
                className="
                    rounded-xl
                    border
                    border-slate-200
                    bg-white
                    p-4
                    transition
                    hover:border-violet-300
                    hover:shadow-md
                "
            >
                <div className="flex items-start gap-3">

                    <div
                        className={`
                            mt-2
                            h-2
                            w-2
                            rounded-full
                            ${section.bg}
                        `}
                    />

                    <p className="flex-1 text-sm leading-7 text-slate-700">
                        {item}
                    </p>

                </div>
            </div>

        ))

    ) : (

        <div
            className="
                rounded-xl
                border
                border-dashed
                border-slate-300
                bg-white
                p-4
            "
        >
            <p className="text-sm text-slate-500">
                No engineering recommendations available.
            </p>
        </div>

    )}

</div>

                        </div>

                    );

                })}

            </div>

        </section>

    );

}