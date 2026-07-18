import {
    ShieldCheck,
    Briefcase,
    Rocket,
    Code2,
} from "lucide-react";

const cards = [
    {
        title: "Engineering",
        key:  "engineeringQuality",
        icon: ShieldCheck,
        color: "bg-violet-100 text-violet-700",
    },
    {
        title: "Career",
        key: "resumeImpact",
        icon: Briefcase,
        color: "bg-emerald-100 text-emerald-700",
    },
    {
        title: "Production",
        key: "productionReadiness",
        icon: Rocket,
        color: "bg-orange-100 text-orange-700",
    },
    {
        title: "Code Quality",
        key: "codeQuality",
        icon: Code2,
        color: "bg-sky-100 text-sky-700",
    },
];

export default function ScoreOverview({ project }) {

    return (

        <section
            className="
                mt-8
                grid
                gap-6
                md:grid-cols-2
                xl:grid-cols-4
            "
        >

            {cards.map((card) => {

                const Icon = card.icon;

                return (

                    <div
                        key={card.title}
                        className="
                            rounded-3xl
                            border
                            border-slate-200
                            bg-white
                            p-6
                            shadow-sm
                        "
                    >

                        <div className="flex items-center justify-between">

                            <div>

                                <p
                                    className="
                                        text-sm
                                        font-medium
                                        text-slate-500
                                    "
                                >
                                    {card.title}
                                </p>

                                <h2
                                    className="
                                        mt-3
                                        text-xl
                                        font-bold
                                        text-slate-900
                                    "
                                >
                                    {project[card.key] ?? "--"}
                                </h2>

                            </div>

                            <div
                                className={`
                                    rounded-2xl
                                    p-4
                                    ${card.color}
                                `}
                            >
                                <Icon className="h-7 w-7" />
                            </div>

                        </div>

                    </div>

                );

            })}

        </section>

    );

}