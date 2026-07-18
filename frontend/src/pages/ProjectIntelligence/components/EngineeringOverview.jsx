import {
    Blocks,
    Code2,
    FileText,
    ShieldCheck,
    TestTube2,
    Rocket,
    Gauge,
} from "lucide-react";

const metrics = [
    {
        label: "Architecture",
        key: "architectureScore",
        icon: Blocks,
    },
    {
        label: "Code Quality",
        key: "codeQuality",
        icon: Code2,
    },
    {
        label: "Documentation",
        key: "documentationQuality",
        icon: FileText,
    },
    {
        label: "Security",
        key: "securityScore",
        icon: ShieldCheck,
    },
    {
        label: "Testing",
        key: "testingQuality",
        icon: TestTube2,
    },
    {
        label: "Deployment",
        key: "deploymentReadiness",
        icon: Rocket,
    },
    {
        label: "Scalability",
        key: "scalability",
        icon: Gauge,
    },
];

export default function EngineeringOverview({ project }) {

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

            <h2
                className="
                    text-2xl
                    font-bold
                    text-slate-900
                "
            >
                Engineering Overview
            </h2>

            <p
                className="
                    mt-2
                    text-slate-500
                "
            >
                AI evaluation of the repository's engineering quality.
            </p>

            <div className="mt-8 space-y-7">

                {metrics.map((metric) => {

                    const Icon = metric.icon;

                    const value = project[metric.key] ?? 0;

                    return (

                        <div key={metric.key}>

                            <div className="flex items-center justify-between">

                                <div className="flex items-center gap-3">

                                    <div
                                        className="
                                            rounded-xl
                                            bg-violet-100
                                            p-3
                                        "
                                    >
                                        <Icon
                                            className="
                                                h-5
                                                w-5
                                                text-violet-700
                                            "
                                        />
                                    </div>

                                    <div>

                                        <h4
                                            className="
                                                font-semibold
                                                text-slate-900
                                            "
                                        >
                                            {metric.label}
                                        </h4>

                                    </div>

                                </div>

                                <span
                                    className="
                                        text-lg
                                        font-bold
                                        text-slate-900
                                    "
                                >
                                    {value}/100
                                </span>

                            </div>

                            <div
                                className="
                                    mt-3
                                    h-3
                                    w-full
                                    overflow-hidden
                                    rounded-full
                                    bg-slate-200
                                "
                            >

                                <div
                                    className="
                                        h-full
                                        rounded-full
                                        bg-gradient-to-r
                                        from-violet-500
                                        to-indigo-600
                                        transition-all
                                        duration-700
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

        </section>

    );

}