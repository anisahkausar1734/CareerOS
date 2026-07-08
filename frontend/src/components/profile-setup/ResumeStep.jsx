import {

    FileText,

    Upload,

    ArrowRight

} from "lucide-react";

import ResumeCard from "../resume/ResumeCard";

function ResumeStep({

    formData,

    setFormData,

    handleCompleteSetup

})


{

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
                    Resume
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
                    If you already have a resume, CareerOS can analyze it
                    and personalize your recommendations even further.
                </p>

            </div>

            <div className="space-y-6">

                {/* YES */}

            <ResumeCard
    email={formData.email}
    onUploadSuccess={() => {

        setFormData({

            ...formData,

            hasResume: true

        });

    }}
/>
                {/* NO */}

              <div
    className="
       bg-slate-50
rounded-2xl
border
border-slate-200
p-6
text-center
        transition-all
        duration-200
    "
>

                    <div
                        className="
                            w-12
                            h-12
                            rounded-2xl
                            bg-violet-50
                            flex
                            items-center
                            justify-center
                        "
                    >

                        <FileText
                            size={22}
                            className="text-[#7367F0]"
                        />

                    </div>

                    <h3
                        className="
                            mt-6
                            text-xl
                            font-semibold
                            text-slate-900
                        "
                    >

                      Skip for now

                    </h3>

                    <p
                        className="
                            mt-3
                            text-sm
                            leading-7
                            text-slate-500
                        "
                    >

                        You can upload your resume anytime from Resume Center or your profile settings. CareerOS will continue helping you build your career.

                    </p>

                    <button
    type="button"
  onClick={() => {

   const updatedProfile = {

    ...formData,

    hasResume: false

};

setFormData(updatedProfile);

handleCompleteSetup(updatedProfile);

}}
    className="
        mt-8
        inline-flex
        items-center
        justify-center
        rounded-xl
        px-6
        py-3
        text-sm
        font-semibold
        border
border-[#7367F0]
text-[#7367F0]
bg-white
hover:bg-violet-50
        hover:bg-[#6558EA]
        transition-all
    "
>
    Continue Without Resume
</button>

                   

                </div>

            </div>

        </div>

    );

}

export default ResumeStep;