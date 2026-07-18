import {
    FolderGit2,
    ShieldCheck,
    BarChart3,
    Code2,
} from "lucide-react";

export default function HeroSection() {
    return (
        <section
            className="
                relative
                overflow-hidden
                rounded-3xl
                border
                border-violet-100
                bg-white
                px-6
                py-6
                shadow-sm
            "
        >
            {/* Background Glow */}
            <div
                className="
                    absolute
                    right-0
                    top-0
                    h-80
                    w-80
                    rounded-full
                    bg-violet-500/10
                    blur-3xl
                "
            />

            <div className="relative grid gap-10 lg:grid-cols-2 lg:items-center">

                {/* Left */}
                <div>

                    <span
                        className="
                            inline-flex
                            items-center
                            gap-2
                            rounded-full
                            bg-violet-100
                            px-4
                            py-2
                            text-sm
                            font-medium
                            text-violet-700
                        "
                    >
                        <FolderGit2 className="h-4 w-4" />
                        Engineering Intelligence
                    </span>

                    <h1
                        className="
                            mt-5
                            text-4xl
                            font-bold
                            tracking-tight
                            text-slate-900
                        "
                    >
                        Project Intelligence
                    </h1>

                    <p
                        className="
                            mt-3
                            max-w-2xl
                            text-18px
                            leading-8
                            text-slate-600
                        "
                    >
                        Analyze your GitHub repositories like an engineering
                        manager. Discover engineering quality, portfolio value,
                        career impact, and resume readiness before adding
                        projects to your resume.
                    </p>

                    <div className="mt-10 grid gap-6 sm:grid-cols-3">

                        <Feature
                            icon={<Code2 />}
                            title="Deep Engineering"
                            subtitle="Architecture, code quality & engineering practices."
                        />

                        <Feature
                            icon={<ShieldCheck />}
                            title="Portfolio Insights"
                            subtitle="Career impact, hiring signals & strengths."
                        />

                        <Feature
                            icon={<BarChart3 />}
                            title="Resume Ready"
                            subtitle="Know exactly when a project belongs on your resume."
                        />

                    </div>

                </div>

                {/* Right */}
                <div className="relative hidden lg:flex justify-center">

                    <div
                        className="
                            relative
                            flex
                            h-[340px]
                            w-[430px]
                            items-center
                            justify-center
                            rounded-[32px]
                            border
                            border-violet-200
                            bg-gradient-to-br
                            from-violet-50
                            to-white
                            shadow-xl
                        "
                    >

                        <FolderGit2
                            className="
                                h-24
                                w-24
                                text-violet-600
                            "
                        />

                        <div
                            className="
                                absolute
                                -left-6
                                top-10
                                rounded-2xl
                                bg-white
                                p-4
                                shadow-lg
                            "
                        >
                            <Code2 className="h-7 w-7 text-violet-600" />
                        </div>

                        <div
                            className="
                                absolute
                                -right-5
                                bottom-8
                                rounded-2xl
                                bg-white
                                p-4
                                shadow-lg
                            "
                        >
                            <BarChart3 className="h-7 w-7 text-violet-600" />
                        </div>

                        <div
                            className="
                                absolute
                                left-16
                                bottom-20
                                rounded-2xl
                                bg-white
                                p-4
                                shadow-lg
                            "
                        >
                            <ShieldCheck className="h-7 w-7 text-green-600" />
                        </div>

                    </div>

                </div>

            </div>

        </section>
    );
}

function Feature({ icon, title, subtitle }) {
    return (
        <div className="flex gap-4">

            <div
                className="
                    flex
                    h-12
                    w-12
                    items-center
                    justify-center
                    rounded-2xl
                    bg-violet-100
                    text-violet-700
                "
            >
                {icon}
            </div>

            <div>

                <h4 className="font-semibold text-slate-900">
                    {title}
                </h4>

                <p className="mt-1 text-sm leading-6 text-slate-500">
                    {subtitle}
                </p>

            </div>

        </div>
    );
}