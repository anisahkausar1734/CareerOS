import React from "react";


function ProgressStepper({

    steps,

    currentStep

}) {

    return (

        <div className="w-full">

            <div className="flex items-start">

                {

                    steps.map((step, index) => {

                        const Icon = step.icon;

                        const active =
                            currentStep >= step.number;

                        return (

                            <React.Fragment
                                key={step.number}
                            >

                                <div
                                    className="
                                        flex
                                        flex-col
                                        items-center
                                        min-w-[110px]
                                    "
                                >

                                    <div

                                        className={`
                                            w-11
                                            h-11
                                            rounded-full
                                            flex
                                            items-center
                                            justify-center
                                            transition-all
                                            duration-300
                                            border

                                            ${

                                                active

                                                    ?

                                                    "bg-[#7367F0] border-[#7367F0] text-white shadow-sm"

                                                    :

                                                    "bg-white border-[#E5E7EB] text-slate-400"

                                            }

                                        `}
                                    >

                                        <Icon size={18} />

                                    </div>

                                    <p

                                        className={`
                                            mt-3
                                            text-[13px]
                                            font-medium
                                            text-center

                                            ${

                                                active

                                                    ?

                                                    "text-slate-900"

                                                    :

                                                    "text-slate-400"

                                            }

                                        `}
                                    >

                                        {step.title}

                                    </p>

                                </div>

                                {

                                    index !==
                                    steps.length - 1 && (

                                        <div
                                            className="
                                                flex-1
                                                h-[2px]
                                                mt-5
                                                mx-3
                                                rounded-full
                                                bg-[#ECEAF5]
                                                overflow-hidden
                                            "
                                        >

                                            <div

                                                className={`
                                                    h-full
                                                    bg-[#7367F0]
                                                    transition-all
                                                    duration-500

                                                    ${

                                                        currentStep >

                                                        step.number

                                                            ?

                                                            "w-full"

                                                            :

                                                            "w-0"

                                                    }

                                                `}
                                            />

                                        </div>

                                    )

                                }

                            </React.Fragment>

                        );

                    })

                }

            </div>

        </div>

    );

}

export default ProgressStepper;