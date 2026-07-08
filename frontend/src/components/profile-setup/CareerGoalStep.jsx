import { BriefcaseBusiness, Info } from "lucide-react";

function CareerGoalStep({

    formData,

    setFormData

}) {

    return (

        <div>

            <div className="mb-10">

                <h2
                    className="
                        text-[30px]
                        font-semibold
                        text-slate-900
                    "
                >
                    Career Goal
                </h2>

                <p
                    className="
                        mt-2
                        text-[15px]
                        leading-7
                        text-slate-500
                        max-w-2xl
                    "
                >
                    Tell us what you're working towards. CareerOS will use
                    this to personalize your roadmap, AI insights,
                    recommendations and career guidance.
                </p>

            </div>

            <div
                className="
                    max-w-2xl
                "
            >

                <label
                    className="
                        mb-2
                        flex
                        items-center
                        gap-2
                        text-sm
                        font-medium
                        text-slate-700
                    "
                >

                    <BriefcaseBusiness size={16} />

                    Dream Role

                </label>

                <input

                    type="text"

                    value={formData.dreamRole}

                    onChange={(e) =>
                        setFormData({

                            ...formData,

                            dreamRole: e.target.value

                        })
                    }

                    placeholder="e.g. AI Engineer, Backend Developer, Product Manager"

                    className="
                        w-full
                        h-12
                        px-4
                        rounded-xl
                        border
                        border-[#E5E7EB]
                        outline-none
                        transition-all
                        duration-200
                        focus:border-[#7367F0]
                        focus:ring-4
                        focus:ring-[#7367F0]/10
                    "

                />

            </div>

            <div
                className="
                    mt-8
                    max-w-2xl
                    rounded-2xl
                    border
                    border-[#ECEAF5]
                    bg-[#F8F9FC]
                    p-5
                "
            >

                <div
                    className="
                        flex
                        items-start
                        gap-3
                    "
                >

                    <div
                        className="
                            mt-0.5
                            text-[#7367F0]
                        "
                    >

                        <Info size={18} />

                    </div>

                    <div>

                        <h3
                            className="
                                text-sm
                                font-semibold
                                text-slate-900
                            "
                        >
                            CareerOS Tip
                        </h3>

                        <p
                            className="
                                mt-1
                                text-sm
                                leading-6
                                text-slate-500
                            "
                        >
                            Don't worry if you're still exploring.
                            Your dream role can always be updated later
                            from your profile.
                        </p>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default CareerGoalStep;