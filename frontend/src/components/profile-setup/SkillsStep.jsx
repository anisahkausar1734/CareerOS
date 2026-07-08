import { useState } from "react";
import {
    Code2,
    ArrowRight,
    X
} from "lucide-react";

function SkillsStep({

formData,

setFormData,

setStep,

showSkills,

setShowSkills

}) {

    const [skillInput, setSkillInput] = useState("");

    return (

        <div>

            <div className="mb-10">

                <h2 className="text-[30px] font-semibold text-slate-900">

                    Current Skills

                </h2>

                <p className="mt-2 text-[15px] leading-7 text-slate-500 max-w-2xl">

                    We'll use your current skills to personalize your roadmap.
                    If you're just starting, don't worry — CareerOS will guide
                    you from the beginning.

                </p>

            </div>

            {

                !showSkills ?

                (

                    <div className="grid md:grid-cols-2 gap-6">

                        {/* CARD 1 */}

                        <div
                            className="
                                border
                                border-[#ECEAF5]
                                rounded-3xl
                                p-8
                                hover:border-[#7367F0]
                                transition-all
                            "
                        >

                            <div className="w-12 h-12 rounded-2xl bg-violet-50 flex items-center justify-center">

                                <Code2
                                    size={22}
                                    className="text-[#7367F0]"
                                />

                            </div>

                            <h3 className="mt-6 text-xl font-semibold text-slate-900">

                                Yes, I'd like to add my skills

                            </h3>

                            <p className="mt-3 text-sm leading-7 text-slate-500">

                                I'll add the technologies I already know so CareerOS
                                can personalize my recommendations.

                            </p>

                           <button
    type="button"
    onClick={() => setShowSkills(true)}
    className="
        mt-8
        inline-flex
        items-center
        justify-center
        rounded-xl
        bg-[#7367F0]
        px-6
        py-3
        text-sm
        font-semibold
        text-white
        transition-all
        duration-200
        hover:bg-[#6558EA]
        hover:shadow-lg
        active:scale-[0.98]
    "
>
    Add My Skills
</button>

                        </div>

                        {/* CARD 2 */}

                        <div
                            className="
                                border
                                border-[#ECEAF5]
                                rounded-3xl
                                p-8
                                hover:border-[#7367F0]
                                transition-all
                            "
                        >

                            <div className="w-12 h-12 rounded-2xl bg-violet-50 flex items-center justify-center">

                                <Code2
                                    size={22}
                                    className="text-[#7367F0]"
                                />

                            </div>

                            <h3 className="mt-6 text-xl font-semibold text-slate-900">

                                Start from the Basics

                            </h3>

                            <p className="mt-3 text-sm leading-7 text-slate-500">

                                That's completely okay. CareerOS will build a
                                beginner-friendly roadmap and guide you from
                                the basics.

                            </p>

                           <button
    type="button"
    onClick={() => {

        setFormData({
            ...formData,
            skills: []
        });

        setStep(4);

    }}
    className="
        mt-8
        inline-flex
        items-center
        justify-center
        rounded-xl
        bg-[#7367F0]
        px-6
        py-3
        text-sm
        font-semibold
        text-white
        hover:bg-[#6558EA]
        transition-all
    "
>
    Continue Without Skills
</button>

                           
                        </div>

                    </div>

                )

                :

                (

                   <div>

    <label
        className="
            text-sm
            font-medium
            text-slate-700
        "
    >
        Add Your Skills
    </label>

    <p
        className="
            mt-2
            text-sm
            text-slate-500
        "
    >
        Press Enter after each skill.
    </p>

    <input

        type="text"

        value={skillInput}

        onChange={(e) =>
            setSkillInput(e.target.value)
        }

        onKeyDown={(e) => {

            if (

                e.key === "Enter" &&

                skillInput.trim()

            ) {

                e.preventDefault();

                if (

                    !formData.skills.includes(

                        skillInput.trim()

                    )

                ) {

                    setFormData({

                        ...formData,

                        skills: [

                            ...formData.skills,

                            skillInput.trim()

                        ]

                    });

                }

                setSkillInput("");

            }

        }}

        placeholder="Type a skill and press Enter"

        className="
            mt-4
            w-full
            h-12
            rounded-xl
            border
            border-[#E5E7EB]
            px-4
            outline-none
            transition
            focus:border-[#7367F0]
            focus:ring-4
            focus:ring-[#7367F0]/10
        "

    />

    <div
        className="
            flex
            flex-wrap
            gap-3
            mt-6
        "
    >

        {

            formData.skills.map(

                (skill, index) => (

                    <div

                        key={index}

                        className="
                            bg-[#7367F0]
                            text-white
                            rounded-full
                            px-4
                            py-2
                            flex
                            items-center
                            gap-2
                            text-sm
                        "

                    >

                        {skill}

                       <button
    type="button"
    onClick={(e) => {

        e.preventDefault();
        e.stopPropagation();

        setFormData({

            ...formData,

            skills: formData.skills.filter(

                s => s !== skill

            )

        });

    }}
>
    <X size={14}/>
</button>
                    </div>

                )

            )

        }

    </div>

    <div className="mt-8">

    <button
        type="button"
        onClick={() => {

            setFormData({

                ...formData,

                skills: []

            });

            setStep(4);

        }}
        className="
            w-full
            rounded-xl
            bg-[#7367F0]
            py-3
            text-sm
            font-semibold
            text-white
            hover:bg-[#6558EA]
            transition-all
        "
    >
        Continue Without Skills
    </button>

</div>

</div>

                )

            }

        </div>

    );

}

export default SkillsStep;