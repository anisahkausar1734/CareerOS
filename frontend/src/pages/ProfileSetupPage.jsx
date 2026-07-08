import { useState } from "react";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import ProgressStepper from "../components/profile-setup/ProgressStepper";
import AboutYouStep from "../components/profile-setup/AboutYouStep";
import CareerGoalStep from "../components/profile-setup/CareerGoalStep";
import SkillsStep from "../components/profile-setup/SkillsStep";
import ResumeStep from "../components/profile-setup/ResumeStep";
import { saveStudentProfile } from "../services/studentProfileService";
import ResumeCard from "../components/resume/ResumeCard";

import {
    User,
    Briefcase,
    Code2,
    FileText
} from "lucide-react";

function ProfileSetupPage() {

    const [step, setStep] = useState(1);

    const navigate = useNavigate();

    const [showSkills, setShowSkills] = useState(false);

    const [formData, setFormData] = useState({

    fullName: "",

    phoneNumber: "",

    collegeName: "",

    degree: "",

    branch: "",

    currentYear: "",

    graduationYear: "",

    dreamRole: "",

    skills: [],

    hasResume: null

});

    const steps = [

        {
            number: 1,
            title: "About You",
            icon: User
        },

        {
            number: 2,
            title: "Career Goal",
            icon: Briefcase
        },

        {
            number: 3,
            title: "Current Skills",
            icon: Code2
        },

        {
            number: 4,
            title: "Resume",
            icon: FileText
        }

    ];


    const handleCompleteSetup = async (profileData = formData) => {

    try {

       await saveStudentProfile({

    email: localStorage.getItem("email"),

    ...profileData

});

       toast.success(
    "Profile setup completed! You can edit it anytime.",
    {
        duration: 2500,
    }
);

       setTimeout(() => {

    navigate("/dashboard");

}, 1500);

    }

    catch (error) {

        console.error(error);

        alert("Unable to save profile.");

    }

};

    const handleContinue = () => {

    if (step === 1) {

        if (

            !formData.fullName ||

            !formData.phoneNumber ||

            !formData.collegeName ||

            !formData.degree ||

            !formData.branch ||

            !formData.currentYear ||

            !formData.graduationYear

        ) {

            alert("Please complete all required fields.");

            return;

        }

    }

    if (step === 2) {

        if (!formData.dreamRole.trim()) {

            alert("Please enter your dream role.");

            return;

        }

    }

    if(step===3){

    setStep(4);

    return;

}

if (step < 4) {

    setStep(step + 1);

    return;

}

handleCompleteSetup();

navigate("/dashboard");

};

    return (

       <div
className="
min-h-screen
bg-[#F5F7FB]
p-6
"
>

           <div
className="
w-full
max-w-7xl
mx-auto
bg-white
rounded-[32px]
shadow-xl
grid
grid-cols-12
min-h-[92vh]
"
>

    {/* LEFT PANEL */}

<div
className="
col-span-4
bg-[#6D5EF7]
text-white
p-12
flex
flex-col
justify-between
"
>

<div>

<h2
className="
text-3xl
font-bold
"
>

CareerOS

</h2>

<p
className="
mt-5
text-white/80
leading-8
"
>

Your AI-powered career growth platform.

Complete your profile once and let CareerOS personalize your entire journey.

</p>

</div>

<div
className="
space-y-5
"
>

<div>

✔ Personalized Roadmaps

</div>

<div>

✔ Career Intelligence

</div>

<div>

✔ Resume Analysis

</div>

<div>

✔ Internship Recommendations

</div>

<div>

✔ Mock Interviews

</div>

</div>

<div>

<p className="text-white/70">

Start your journey today.

</p>

</div>

</div>

{/* RIGHT PANEL */}

<div
    className="
        col-span-8
        h-[92vh]
        overflow-y-auto
        bg-white
    "
>
               {/* Header */}

<div
    className="
        px-12
        pt-12
    "
>

    <p
        className="
            text-sm
            font-medium
            text-[#7367F0]
        "
    >
        Step {step} of 4
    </p>

    <h1
        className="
            mt-2
            text-[38px]
            font-bold
            leading-tight
            text-slate-900
        "
    >
        Let's set up your profile
    </h1>

    <p
        className="
            mt-3
            text-[16px]
            leading-7
            text-slate-500
            max-w-xl
        "
    >
        Complete your profile to unlock personalized career guidance,
        AI recommendations, roadmaps and internship opportunities.
    </p>

</div>


<div
    className="
        px-12
        mt-10
    "
>
                {/* Progress */}

               <ProgressStepper

    steps={steps}

    currentStep={step}

/>

</div>
                {/* Content */}


<div
    className="
        px-12
        py-8
    "
>

    <div
        className="
            bg-white
            border
            border-[#ECEAF5]
            rounded-3xl
            p-8
        "
    >

        {step === 1 && (
            <AboutYouStep
                formData={formData}
                setFormData={setFormData}
            />
        )}

        {step === 2 && (
            <CareerGoalStep
                formData={formData}
                setFormData={setFormData}
            />
        )}

        {step === 3 && (
          <SkillsStep
    formData={formData}
    setFormData={setFormData}
    setStep={setStep}
    showSkills={showSkills}
    setShowSkills={setShowSkills}
/>
        )}

        {step === 4 && (
           <ResumeStep
    formData={formData}
    setFormData={setFormData}
    handleCompleteSetup={handleCompleteSetup}
/>
        )}

    </div>

</div>
                {/* Footer */}

                <div
                    className="
                        border-t
                        border-[#ECEAF5]
                        px-12
                        py-6
                        flex
                        items-center
                        justify-between
                    "
                >

                 <div>

    {

        step > 1 ?

        (

            <button
                onClick={() => setStep(step - 1)}
                className="
                    px-6
                    py-3
                    rounded-xl
                    border
                    border-[#E5E7EB]
                    text-slate-600
                    hover:bg-slate-50
                "
            >
                Back
            </button>

        )

        :

        <div className="w-24" />

    }

</div>
                   <button

   onClick={handleContinue}

    className="
        px-6
        py-3
        rounded-xl
        bg-[#7367F0]
        text-white
        font-medium
        hover:bg-[#6658EA]
        transition-all
        duration-200
    "

>

    {

        step === 4

            ?

            "Complete Setup"

            :

            "Continue"

    }

</button>

                </div>

            </div>

        </div>

        </div>   

    );

}

export default ProfileSetupPage;