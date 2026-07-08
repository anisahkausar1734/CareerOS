function AboutYouStep({

    formData,

    setFormData

}) {

    const handleChange = (e) => {

        setFormData({

            ...formData,

            [e.target.name]: e.target.value

        });

    };

    return (

        <div>

            {/* Card Header */}

            <div
                className="
                    flex
                    items-center
                    gap-4
                    mb-8
                "
            >

                <div
                    className="
                        w-12
                        h-12
                        rounded-2xl
                        bg-violet-100
                        flex
                        items-center
                        justify-center
                    "
                >

                    👤

                </div>

                <div>

                    <h2
                        className="
                            text-xl
                            font-semibold
                            text-slate-900
                        "
                    >

                        Personal Information

                    </h2>

                    <p
                        className="
                            text-sm
                            text-slate-500
                            mt-1
                        "
                    >

                        Basic details to personalize your CareerOS experience.

                    </p>

                </div>

            </div>

            {/* Form */}

            <div
                className="
                    grid
                    grid-cols-1
                    md:grid-cols-2
                    gap-x-8
                    gap-y-6
                "
            >

                <Input
                    label="Full Name"
                    name="fullName"
                    value={formData.fullName}
                    onChange={handleChange}
                />

                <Input
                    label="Phone Number"
                    name="phoneNumber"
                    value={formData.phoneNumber}
                    onChange={handleChange}
                />

                <Input
                    label="College"
                    name="collegeName"
                    value={formData.collegeName}
                    onChange={handleChange}
                />

                <Input
                    label="Degree"
                    name="degree"
                    value={formData.degree}
                    onChange={handleChange}
                />

                <Input
                    label="Branch"
                    name="branch"
                    value={formData.branch}
                    onChange={handleChange}
                />

               <div>

   <label
    className="
        block
        mb-2
        text-[12px]
        font-semibold
        tracking-[0.12em]
        uppercase
        text-slate-500
    "
>
    Current Year
</label>

    <select
        name="currentYear"
        value={formData.currentYear}
        onChange={handleChange}
        className="
            w-full
            px-5
            py-3
            rounded-2xl
            border
            border-slate-200
            bg-slate-50
            focus:bg-white
            focus:border-indigo-500
            focus:ring-4
            focus:ring-indigo-100
            outline-none
            transition
        "
        required
    >
        <option value="">
            Select Year
        </option>

        <option value="1st Year">1st Year</option>
        <option value="2nd Year">2nd Year</option>
        <option value="3rd Year">3rd Year</option>
        <option value="4th Year">4th Year</option>

    </select>

</div>

                <Input
                    label="Graduation Year"
                    name="graduationYear"
                    value={formData.graduationYear}
                    onChange={handleChange}
                />

            </div>

            {/* Bottom Info */}

            <div
                className="
                    mt-8
                    rounded-2xl
                    border
                    border-violet-100
                    bg-violet-50
                    px-5
                    py-4
                    flex
                    items-center
                    gap-3
                "
            >

                <div
                    className="
                        w-8
                        h-8
                        rounded-full
                        bg-white
                        flex
                        items-center
                        justify-center
                    "
                >

                    ℹ️

                </div>

                <p
                    className="
                        text-sm
                        text-slate-600
                    "
                >

                    This information helps CareerOS generate personalized roadmaps, career insights and recommendations.

                </p>

            </div>

        </div>

    );

}

function Input({

    label,

    ...props

}) {

    return (

        <div>

            <label
                className="
                    block
                    mb-2
                    text-[12px]
                    font-semibold
                    tracking-[0.12em]
                    uppercase
                    text-slate-500
                "
            >

                {label}

            </label>

            <input

                {...props}

                className="
                    w-full
                    h-12
                    rounded-2xl
                    border
                    border-[#D8DCE8]
                    bg-[#FCFCFD]
                    px-4
                    text-[15px]
                    text-slate-900
                    outline-none
                    transition-all
                    duration-200
                    focus:border-[#7367F0]
                    focus:ring-4
                    focus:ring-[#7367F0]/10
                "

            />

        </div>

    );

}

export default AboutYouStep;