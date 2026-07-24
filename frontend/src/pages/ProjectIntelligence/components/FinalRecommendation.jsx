import {
    Award,
    BadgeCheck,
    Briefcase,
    CheckCircle2,
    Sparkles,
} from "lucide-react";

export default function FinalRecommendation({ project }) {

    return (

       <section
    className="
        mt-8
        rounded-3xl
        border
        border-violet-300
        bg-gradient-to-br
        from-violet-50
        via-purple-50
        to-indigo-50
        p-8
        shadow-sm
    "

        >
            

            <div className="flex items-center gap-4">

              <div className="rounded-2xl bg-violet-100 p-4">

                    <Sparkles className="h-8 w-8 text-violet-700" />

                </div>

                <div>

                    <h2 className="text-3xl font-bold text-slate-900">

                        Executive Recommendation

                    </h2>

                    <p className="mt-2 text-slate-500">

                        Final engineering and career recommendation based on your complete project analysis.

                    </p>

                </div>

            </div>

            <div
                className="
                    mt-10
                    grid
                    gap-6
                    lg:grid-cols-2
                "
            >

                <div className="space-y-6">

                    <div
    className="
        mt-10
        flex
        items-center
        justify-between
        rounded-2xl
        border
        border-white/20
        bg-white/10
        p-5
        transition
hover:shadow-md
hover:border-violet-300
    "
>

    <div>

        <h3 className="text-lg font-semibold text-slate-900">

            Project Analysis Complete

        </h3>

        <p className="mt-1 text-sm text-slate-500">

            CareerOS has finished evaluating your repository.

        </p>

    </div>

    <CheckCircle2 className="h-8 w-8 text-emerald-300" />

</div>

                    <div className="
rounded-2xl
border
border-violet-200
bg-white
p-6
shadow-sm
transition
hover:shadow-md
transition
hover:shadow-md
hover:border-violet-300
"
>

                        <div className="flex items-center gap-3">

                            <Award className="h-6 w-6 text-violet-600" />

                            <h3 className="text-lg font-semibold text-slate-900">

                                Engineering Maturity

                            </h3>

                        </div>

                        <p className="mt-4 text-base
leading-7 text-slate-600">

                            {project.maturityStage}

                        </p>

                       <div className="mt-5">

    <span
        className="
            rounded-xl
            bg-white
            px-3
            py-2
            text-lg
            font-bold
            text-violet-700
        "
    >
        {project.maturityScore ?? 0}/100
    </span>

</div>

                    </div>

                    <div className="
rounded-2xl
border
border-violet-200
bg-white
p-6
shadow-sm
transition
hover:shadow-md
transition
hover:shadow-md
hover:border-violet-300
" >

                        <div className="flex items-center gap-3">

                            <BadgeCheck className="h-6 w-6 text-violet-600" />

                            <h3 className="text-lg font-semibold text-slate-900">

                                Repository Confidence

                            </h3>

                        </div>

                        <p className="mt-4 text-base
leading-7 text-slate-600">

                            {project.repositoryConfidence}

                        </p>

                    </div>

                </div>

                <div className="space-y-6">

                    <div className="
rounded-2xl
border
border-violet-200
bg-white
p-6
shadow-sm
transition
hover:shadow-md
transition
hover:shadow-md
hover:border-violet-300
"
>

                        <div className="flex items-center gap-3">

                           <Briefcase className="h-6 w-6 text-violet-600" />

                            <h3 className="text-lg font-semibold text-slate-900">

                                Hiring Recommendation

                            </h3>

                        </div>

                        <p className="mt-4 text-base
leading-7 text-slate-600">

                            {project.hiringRecommendation}

                        </p>

                    </div>

                    <div className="
rounded-2xl
border
border-violet-200
bg-white
p-6
shadow-sm
transition
hover:shadow-md
transition
hover:shadow-md
hover:border-violet-300
">

                        <div className="flex items-center gap-3">

                           <CheckCircle2 className="h-6 w-6 text-violet-600" />

                            <h3 className="text-lg font-semibold text-slate-900">

                                Portfolio Recommendation

                            </h3>

                        </div>

                        <p className="mt-4 text-base
leading-7 text-slate-600">

                            {project.portfolioRecommendation}

                        </p>

                    </div>

                </div>

            </div>

            <div
                className="
                    mt-10
                    rounded-2xl
                    bg-white
                    p-8
                    text-slate-900
                "
            >

              <h3 className="text-2xl font-bold text-slate-900">
    Executive Summary
</h3>

<p className="mt-2 text-sm text-slate-500">
    This recommendation combines engineering quality, career intelligence, and repository analysis to summarize the overall value of your project.
</p>

<p className="mt-5 break-words text-base leading-7 text-slate-600">
    {project.finalEngineeringReview}
</p>

            </div>

        </section>

    );

}