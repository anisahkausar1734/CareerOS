import { FolderGit2, Rocket } from "lucide-react";

export default function EmptyProjects() {
    return (
        <div
            className="
                flex
                flex-col
                items-center
                justify-center
                rounded-3xl
                border-2
                border-dashed
                border-violet-200
                bg-gradient-to-br
                from-violet-50
                via-white
                to-slate-50
                px-10
                py-20
                text-center
            "
        >
            {/* Illustration */}

            <div className="relative">

                <div
                    className="
                        flex
                        h-28
                        w-28
                        items-center
                        justify-center
                        rounded-full
                        bg-violet-100
                    "
                >
                    <FolderGit2
                        className="
                            h-14
                            w-14
                            text-violet-700
                        "
                    />
                </div>

                <div
                    className="
                        absolute
                        -right-2
                        -top-2
                        rounded-full
                        bg-violet-600
                        p-2
                        shadow-lg
                    "
                >
                    <FolderGit2
                        className="
                            h-5
                            w-5
                            text-white
                        "
                    />
                </div>

                <div
                    className="
                        absolute
                        -left-3
                        bottom-0
                        rounded-full
                        bg-orange-500
                        p-2
                        shadow-lg
                    "
                >
                    <Rocket
                        className="
                            h-5
                            w-5
                            text-white
                        "
                    />
                </div>

            </div>

            {/* Text */}

            <h2
                className="
                    mt-8
                    text-3xl
                    font-bold
                    text-slate-900
                "
            >
                No Projects Yet
            </h2>

            <p
                className="
                    mt-4
                    max-w-xl
                    text-lg
                    leading-8
                    text-slate-500
                "
            >
                Analyze your first GitHub repository to unlock engineering
                intelligence, resume readiness, portfolio insights,
                and personalized improvement recommendations.
            </p>

            {/* Button */}

            <button
                className="
                    mt-10
                    inline-flex
                    items-center
                    gap-3
                    rounded-2xl
                    bg-violet-600
                    px-7
                    py-4
                    font-semibold
                    text-white
                    transition
                    hover:bg-violet-700
                "
            >
                <FolderGit2 className="h-5 w-5" />

                Analyze Your First Project
            </button>
        </div>
    );
}