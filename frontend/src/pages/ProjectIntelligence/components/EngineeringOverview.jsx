import {
    Blocks,
    Code2,
    FileText,
    ShieldCheck,
    TestTube2,
    Rocket,
    Gauge,
} from "lucide-react";

import { motion } from "framer-motion";

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

        <motion.section
    initial={{ opacity: 0, y: 20 }}
    whileInView={{ opacity: 1, y: 0 }}
    viewport={{ once: true }}
    transition={{ duration: 0.4 }}
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
                Engineering Analysis
            </h2>

            <p
                className="
                    mt-2
                    text-slate-500
                "
            >
                AI evaluation of the repository's engineering quality.
            </p>

<div className="mt-6 grid gap-5 md:grid-cols-2 xl:grid-cols-3">
    
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
                        {metric.label}
                    </h4>

                    <p className="mt-2 text-sm text-slate-500">
                        {value >= 90
                            ? "Excellent"
                            : value >= 80
                            ? "Very Good"
                            : value >= 70
                            ? "Good"
                            : value >= 60
                            ? "Needs Improvement"
                            : "Poor"}
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

        <p className="mt-4 text-sm leading-6 text-slate-500">
            {metric.label} assessment generated from repository analysis.
        </p>

    </div>
);

                })}

            </div>

        </motion.section>

    );

}