import {
    Brain,
    Blocks,
    BookOpen,
    ShieldCheck,
    TestTube2,
    Rocket,
} from "lucide-react";

const sections = [
    {
        title: "Architecture Review",
        key: "architectureReview",
        icon: Blocks,
    },
    {
        title: "Documentation Review",
        key: "documentationReview",
        icon: BookOpen,
    },
    {
        title: "Security Review",
        key: "securityReview",
        icon: ShieldCheck,
    },
    {
        title: "Testing Review",
        key: "testingReview",
        icon: TestTube2,
    },
    {
        title: "Deployment Review",
        key: "deploymentReview",
        icon: Rocket,
    },
];

export default function EngineeringReview({ project }) {

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

            <div className="flex items-center gap-4">

                <div className="rounded-2xl bg-violet-100 p-4">

                    <Brain className="h-7 w-7 text-violet-700" />

                </div>

                <div>

                    <h2 className="text-2xl font-bold">

                        AI Engineering Review

                    </h2>

                    <p className="text-slate-500">

                        AI-generated engineering assessment of the repository.

                    </p>

                </div>

            </div>

            <div
                className="
                    mt-8
                    rounded-2xl
                    bg-violet-50
                    p-7
                    leading-8
                    text-slate-700
                "
            >
                {project.engineeringReview}
            </div>

            <div className="mt-10 space-y-8">

                {sections.map((section) => {

                    const Icon = section.icon;

                    return (

                        <div
                            key={section.key}
                            className="
                                rounded-2xl
                                border
                                border-slate-100
                                p-6
                            "
                        >

                            <div className="flex items-center gap-3">

                                <Icon
                                    className="
                                        h-5
                                        w-5
                                        text-violet-600
                                    "
                                />

                                <h3
                                    className="
                                        text-lg
                                        font-semibold
                                    "
                                >
                                    {section.title}
                                </h3>

                            </div>

                            <p
                                className="
                                    mt-4
                                    leading-8
                                    text-slate-600
                                "
                            >
                                {project[section.key]}
                            </p>

                        </div>

                    );

                })}

            </div>

        </section>

    );

}