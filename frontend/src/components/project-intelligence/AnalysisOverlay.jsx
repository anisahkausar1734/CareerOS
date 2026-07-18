import {
    BrainCircuit,
    CheckCircle2,
    Github,
    LoaderCircle,
    Rocket,
    Search,
    Sparkles,
} from "lucide-react";

const steps = [

    {
        title: "Connecting to GitHub",
        icon: Github,
    },

    {
        title: "Reading Repository",
        icon: Search,
    },

    {
        title: "Understanding Architecture",
        icon: BrainCircuit,
    },

    {
        title: "Detecting Technologies",
        icon: Rocket,
    },

    {
        title: "Generating Engineering Intelligence",
        icon: Sparkles,
    },

];

export default function AnalysisOverlay({

    open,

    currentStep = 0,

}) {

    if (!open) return null;

    return (

        <div
            className="
                fixed
                inset-0
                z-50
                flex
                items-center
                justify-center
                bg-black/60
                backdrop-blur-md
            "
        >

            <div
                className="
                    w-full
                    max-w-2xl
                    rounded-3xl
                    bg-white
                    p-10
                    shadow-2xl
                "
            >

                <h2
                    className="
                        text-3xl
                        font-bold
                        text-slate-900
                    "
                >
                    AI Engineering Analysis
                </h2>

                <p
                    className="
                        mt-3
                        text-slate-500
                    "
                >
                    CareerOS is performing a complete engineering review
                    of your repository.
                </p>

                <div className="mt-10 space-y-5">

                    {

                        steps.map((step, index) => {

                            const Icon = step.icon;

                            const completed =
                                index < currentStep;

                            const active =
                                index === currentStep;

                            return (

                                <div
                                    key={step.title}
                                    className="
                                        flex
                                        items-center
                                        gap-4
                                    "
                                >

                                    <div
                                        className="
                                            flex
                                            h-12
                                            w-12
                                            items-center
                                            justify-center
                                            rounded-2xl
                                            bg-violet-100
                                        "
                                    >

                                        {

                                            completed

                                                ?

                                                <CheckCircle2
                                                    className="
                                                        h-6
                                                        w-6
                                                        text-green-600
                                                    "
                                                />

                                                :

                                                active

                                                    ?

                                                    <LoaderCircle
                                                        className="
                                                            h-6
                                                            w-6
                                                            animate-spin
                                                            text-violet-700
                                                        "
                                                    />

                                                    :

                                                    <Icon
                                                        className="
                                                            h-6
                                                            w-6
                                                            text-violet-700
                                                        "
                                                    />

                                        }

                                    </div>

                                    <div>

                                        <h4
                                            className="
                                                font-semibold
                                                text-slate-900
                                            "
                                        >
                                            {step.title}
                                        </h4>

                                        <p
                                            className="
                                                text-sm
                                                text-slate-500
                                            "
                                        >
                                            {

                                                completed

                                                    ?

                                                    "Completed"

                                                    :

                                                    active

                                                        ?

                                                        "Processing..."

                                                        :

                                                        "Waiting"

                                            }

                                        </p>

                                    </div>

                                </div>

                            );

                        })

                    }

                </div>

            </div>

        </div>

    );

}