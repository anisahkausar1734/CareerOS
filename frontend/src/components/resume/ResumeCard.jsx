import { useEffect, useRef, useState } from "react";
import axios from "axios";
import { toast } from "react-hot-toast";
import {
    Upload,
    FileText,
    Trash2,
    ExternalLink
} from "lucide-react";

function ResumeCard({

    email,

    compact = false,

    onUploadSuccess

}) {

const [resume, setResume] = useState(null);

const [file, setFile] = useState(null);

const [uploading, setUploading] = useState(false);

const [dragActive, setDragActive] = useState(false);

const [loading, setLoading] = useState(true);

const fileInputRef = useRef(null);

const loadResume = async () => {

    try {

        setLoading(true);

      const response = await axios.get(
    `http://localhost:8080/api/resumes/${email}`
);

console.log("Resume Response:", response.data);

if (
    response.data &&
    response.data.resumeFileName
) {

    console.log("Resume Found");

    setResume(response.data);

} else {

    console.log("No Resume Found");

    setResume(null);

}
    }

   catch (error) {

    console.log("Resume Error:", error);

    setResume(null);

}
    finally {

        setLoading(false);

    }

};

const handleSelectedFile = (selectedFile) => {

    if (!selectedFile) return;

    const allowed = [

        "application/pdf",

        "application/msword",

        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"

    ];

    if (!allowed.includes(selectedFile.type)) {

        toast.error("Please upload a PDF or DOC/DOCX file.");

        return;

    }

    if (selectedFile.size > 5 * 1024 * 1024) {

        toast.error("Maximum file size is 5 MB.");

        return;

    }

    setFile(selectedFile);

};

const uploadResume = async () => {

    if (!file) {

        toast.error("Select a resume first.");

        return;

    }

    try {

        setUploading(true);

        const formData = new FormData();

        formData.append(
            "email",
            email
        );

        formData.append(
            "file",
            file
        );

        await axios.post(

            "http://localhost:8080/api/resumes/upload",

            formData,

            {

                headers: {

                    "Content-Type":
                        "multipart/form-data"

                }

            }

        );

        toast.success(
            "Resume uploaded successfully."
        );

        setFile(null);

        await loadResume();

        onUploadSuccess?.();

    }

    catch {

        toast.error(
            "Upload failed."
        );

    }

    finally {

        setUploading(false);

    }

};

useEffect(() => {

    loadResume();

}, []);

console.log("Current Resume State:", resume);
console.log("Loading:", loading);

return (

<div
className={`
bg-white
rounded-[22px]
border
border-[#ECEAF5]
${compact ? "px-7 py-6" : "px-8 py-7"}
`}

>
<div className="flex items-start gap-4 mb-6">

    <div
        className="
           w-14
h-14
rounded-[18px]
            bg-violet-100
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

    <div>

        <h2
            className="
                text-[18px]
                font-semibold
                text-slate-900
            "
        >
            Resume
        </h2>

        {
resume && (

<span
className="
inline-flex
items-center
gap-2
mt-1.5
px-3
py-1
rounded-full
bg-emerald-100
text-emerald-700
text-xs
font-medium
"
>

● Resume Uploaded

</span>

)
}

{
!resume && !loading && (

<span
className="
inline-flex
items-center
gap-2
mt-2
px-3
py-1
rounded-full
bg-amber-100
text-amber-700
text-xs
font-medium
"
>

● Resume not uploaded

</span>

)
}


        <p
            className="
                text-sm
                text-slate-500
                mt-1
            "
        >
            Upload your latest resume for AI analysis.
        </p>

    </div>

</div>

{

loading

?

<p>Loading...</p>

:

resume?.resumeFileName ?

<div
className="
border
border-violet-100
bg-violet-50/50
rounded-[22px]
px-7
py-6
flex
justify-between
items-center
"
>

<div className="flex items-center gap-4">

    <div
        className="
        w-14
        h-14
        rounded-[22px]
        bg-white
        shadow-sm
        flex
        items-center
        justify-center
        "
    >

        <FileText
            size={26}
            className="text-[#7367F0]"
        />

    </div>

    <div>

        <h3
            className="
            font-semibold
            text-slate-800
            "
        >
            {resume.resumeFileName}
        </h3>

        <p
            className="
            text-sm
            text-emerald-600
            mt-1
            "
        >
            ✓ Resume uploaded successfully
        </p>

        <p
            className="
            text-xs
            text-slate-500
            mt-1
            "
        >
            AI analysis is now available.
        </p>

    </div>

</div>

<div className="flex items-center gap-3">

    <a

        href={resume.resumeUrl}

        target="_blank"

        rel="noreferrer"

        className="
        w-11
        h-11
        rounded-xl
        bg-white
        border
        flex
        items-center
        justify-center
        hover:bg-violet-50
        transition
        "
    >

        <ExternalLink
            size={18}
            className="text-[#7367F0]"
        />

    </a>

    <button

        onClick={() =>
            fileInputRef.current.click()
        }

        className="
        px-5
        py-2.5
        rounded-xl
        bg-[#7367F0]
        hover:bg-[#6658EA]
        text-white
        font-medium
        transition
        "
    >

        Replace

    </button>

</div>

<input

    ref={fileInputRef}

    type="file"

    accept=".pdf,.doc,.docx"

    className="hidden"

   onChange={async (e)=>{

    const selected = e.target.files[0];

    if(!selected) return;

    setFile(selected);

    const formData = new FormData();

    formData.append("email", email);

    formData.append("file", selected);

    try{

        setUploading(true);

        await axios.post(

            "http://localhost:8080/api/resumes/upload",

            formData,

            {

                headers:{

                    "Content-Type":"multipart/form-data"

                }

            }

        );

        toast.success("Resume replaced successfully.");

        await loadResume();

        onUploadSuccess?.();

    }

    finally{

        setUploading(false);

    }

}}

/>

</div>
:

<>

<div

    onDragEnter={(e) => {

        e.preventDefault();
        setDragActive(true);

    }}

    onDragOver={(e) => {

        e.preventDefault();
        setDragActive(true);

    }}

    onDragLeave={(e) => {

        e.preventDefault();

        if (e.currentTarget.contains(e.relatedTarget)) return;

        setDragActive(false);

    }}

    onDrop={(e) => {

        e.preventDefault();

        setDragActive(false);

        const dropped = e.dataTransfer.files[0];

        if (dropped) {

            handleSelectedFile(dropped);

        }

    }}

    className={`
        border-2
        border-dashed
        rounded-[22px]
        py-5
        px-6
        max-w-[500px]
        mx-auto
        text-center
        transition-all
        duration-300
        cursor-pointer

        ${
            dragActive

            ? "border-[#7367F0] bg-violet-50"

            : "border-[#D7CCFF] bg-[#FBFAFF] hover:border-[#7367F0] hover:bg-violet-50/40"

        }
    `}

>

    <input
        ref={fileInputRef}
        type="file"
        accept=".pdf,.doc,.docx"
        className="hidden"
       onChange={(e) =>
    handleSelectedFile(e.target.files[0])
        }
    />

   <Upload

    size={34}

    strokeWidth={1.8}

    className={`
        mx-auto
        mb-5
        text-[#7367F0]
        transition-transform
        duration-300

        ${dragActive ? "scale-110" : ""}

    `}
/>

   <div className="space-y-2">

    <h3
        className="
            text-[20px]
            font-semibold
            tracking-[-0.02em]
            text-[#1E2340]
        "
    >
        Upload Resume
    </h3>

    {dragActive

    ? "Drop your resume here"

    : "Drag & drop your resume here or browse your files."

}
    <p
        className="
            text-[13px]
            text-slate-800
            font-semibold
            leading-6
        "
    >
        PDF or DOCX • Maximum 5 MB
    </p>

</div>

   

    <button

        onClick={() =>
            fileInputRef.current.click()
        }

       className="
mt-8
inline-flex
items-center
justify-center
px-7
h-11
rounded-xl
bg-[#7367F0]
hover:bg-[#6558EA]
text-white
text-[15px]
font-medium
shadow-sm
transition-all
duration-200
hover:shadow-md
"
    >

        Choose Resume

    </button>

</div>

{
file && (

<div
className="
mt-3
rounded-[22px]
border
border-slate-200
bg-white
px-5
py-4
flex
items-center
justify-between
"
>

<div className="flex items-center gap-3">

<FileText
size={20}
className="text-[#7367F0]"
/>

<div>

<p className="font-medium">

{file.name}

</p>

<p
className="
text-xs
text-slate-500
"
>

Ready to upload

</p>

</div>

</div>

<button

onClick={uploadResume}

disabled={uploading}

className="
px-5
py-2.5
rounded-xl
bg-[#7367F0]
hover:bg-[#6658EA]
text-white
font-medium
transition
"

>

{

uploading

?

"Uploading..."

:

"Upload"

}

</button>

</div>

)
}

</>

}

</div>

);




}

export default ResumeCard;