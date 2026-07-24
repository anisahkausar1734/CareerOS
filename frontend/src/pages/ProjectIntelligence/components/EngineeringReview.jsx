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

                        Engineering Review

                    </h2>

                    <p className="text-slate-500">

                        Detailed AI review of each engineering pillar based on your repository.

                    </p>

                </div>

            </div>

           <div
    className="
        mt-8
        rounded-2xl
        border
        border-violet-200
        bg-violet-50
        p-7
    "
>

    <h3 className="text-lg font-semibold text-slate-900">
    Executive Summary
</h3>

<p className="mt-2 text-sm text-slate-500">
    Overall engineering assessment generated from repository analysis.
</p>

                {project.engineeringReview}
            </div>


            <div className="mt-10 mb-6">
    <h3 className="text-xl font-semibold text-slate-900">
        Detailed Engineering Reviews
    </h3>

    <p className="mt-1 text-sm text-slate-500">
        Individual assessments for each engineering category.
    </p>
</div>

            <div className="mt-10 grid gap-6 md:grid-cols-2">

                {sections.map((section) => {

                    const Icon = section.icon;

                    return (

                        <div
                            key={section.key}
                           className="
    rounded-2xl
    border
    border-slate-200
    bg-white
    p-6
    transition
    hover:border-violet-300
    hover:shadow-md
"
>

                           <div className="flex items-center justify-between">

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

                                <span
    className="
        ml-auto
        rounded-full
        bg-violet-100
        px-3
        py-1
        text-xs
        font-semibold
        text-violet-700
    "
>
    AI Reviewed
</span>

                           

                           <p
    className="
        mt-5
        text-sm
        leading-7
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