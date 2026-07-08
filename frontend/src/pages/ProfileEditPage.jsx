import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { toast } from "react-hot-toast";
import ResumeCard from "../components/resume/ResumeCard";

function ProfileEditPage() { 

  const navigate = useNavigate();

  const email =
    localStorage.getItem("email");

  const [formData, setFormData] =
    useState({

      fullName:
        localStorage.getItem("fullName") || "",

      phoneNumber: "",

      collegeName: "",

      degree: "",

      branch: "",

      currentYear: "",

      graduationYear: "",

      dreamRole: "",

      skills: ""

    });

    const [loading, setLoading] =
    useState(true);

  const handleChange = (e) => {

    setFormData({
      ...formData,
      [e.target.name]:
        e.target.value
    });

  };

useEffect(() => {

    fetchProfile();

}, []);

const fetchProfile = async () => {

    try {

        const email =
                localStorage.getItem(
                        "email"
                );

        const response =
                await axios.get(
                        `http://localhost:8080/api/student-profile/${email}`
                );

        setFormData({

            fullName:
                response.data.fullName || "",

            phoneNumber:
                response.data.phoneNumber || "",

            collegeName:
                response.data.collegeName || "",

            degree:
                response.data.degree || "",

            branch:
                response.data.branch || "",

            currentYear:
                response.data.currentYear || "",

            graduationYear:
                response.data.graduationYear || "",

            dreamRole:
                response.data.dreamRole || "",

            skills:
                response.data.skills?.join(", ") || ""

        });

    } catch(error) {

        console.error(error);

    }

    finally {

        setLoading(false);
    }

};

  const handleSubmit = async (e) => {

    e.preventDefault();

    try {

      await axios.put(
  `http://localhost:8080/api/student-profile/${email}`,
  {
    email,

    fullName:
      formData.fullName,

    phoneNumber:
      formData.phoneNumber,

    collegeName:
      formData.collegeName,

    degree:
      formData.degree,

    branch:
      formData.branch,

    currentYear:
      formData.currentYear,

    graduationYear:
      Number(
        formData.graduationYear
      ),

    dreamRole:
      formData.dreamRole,

    skills:
  formData.skills
    .split(/[\n,]+/)
    .map(skill => skill.trim())
    .filter(skill => skill)
  }
);

toast.success(
    "Profile updated successfully!"
);

navigate("/profile");

    } catch (error) {

      console.log(error);

      toast.error(
    "Unable to update profile."
);

    }

  };

if(loading) {

    return (

        <div className="
            min-h-screen
            flex
            items-center
            justify-center
        ">

            <div className="
                text-2xl
                font-semibold
                text-indigo-600
            ">
                Loading Profile...
            </div>

        </div>

    );
}
  
  return (

   <div
className="
min-h-screen
bg-[#F5F7FB]
py-8
px-6
"
>

<div
className="
max-w-[920px]
mx-auto
"
>

      <div
        className="
          bg-white/90
backdrop-blur-md
w-full
max-w-4xl
rounded-[26px]
shadow-2xl
p-12
border
border-indigo-100
        "
      >

       <div className="flex items-center gap-3 mb-6">

    <div className="
        w-12
        h-12
        rounded-2xl
        bg-gradient-to-r
        from-indigo-600
        to-purple-600
        flex
        items-center
        justify-center
        text-white
        text-2xl
    ">
        ✏️
    </div>

    <div>

        <h1 className="
            text-3xl
            font-bold
            text-slate-800
        ">
            Edit Profile
        </h1>

       <p
className="
    mt-2
    max-w-[520px]
    text-[15px]
    leading-6
    text-slate-500
">
           Keep your profile updated to receive
more accurate career guidance and recommendations.
        </p>

    </div>

</div>
       

       <form
  onSubmit={handleSubmit}
  className="
    grid
    grid-cols-[180px_1fr]
    gap-y-6
    gap-x-6
    mt-8
  "
>


  <label
className="
    self-center
    uppercase
    tracking-wide
    text-[11px]
    font-semibold
    text-slate-400
"
>
        Full Name
    </label>
          <input
            type="text"
            name="fullName"
            placeholder="Full Name"
            value={formData.fullName}
            onChange={handleChange}
className="
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
          />

<label
className="
    self-center
    uppercase
    tracking-wide
    text-[11px]
    font-semibold
    text-slate-400
"
>
        Phone Number
    </label>
          <input
            type="text"
            name="phoneNumber"
            placeholder="Phone Number"
            value={formData.phoneNumber}
            onChange={handleChange}
className="
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
"            required
          />


<label
className="
    self-center
    uppercase
    tracking-wide
    text-[11px]
    font-semibold
    text-slate-400
"
>
        College Name
    </label>
          <input
            type="text"
            name="collegeName"
            placeholder="College Name"
            value={formData.collegeName}
            onChange={handleChange}
className="
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
"            required
          />
<label
className="
    self-center
    uppercase
    tracking-wide
    text-[11px]
    font-semibold
    text-slate-400
"
>
        Degree
    </label>
          <input
            type="text"
            name="degree"
            placeholder="Degree"
            value={formData.degree}
            onChange={handleChange}
className="
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
"            required
          />


<label
className="
    self-center
    uppercase
    tracking-wide
    text-[11px]
    font-semibold
    text-slate-400
"
>
        Branch
    </label>
          <input
            type="text"
            name="branch"
            placeholder="Branch"
            value={formData.branch}
            onChange={handleChange}
className="
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
"            required
          />
 <label
className="
    self-center
    uppercase
    tracking-wide
    text-[11px]
    font-semibold
    text-slate-400
"
>
        Current Year
    </label>
          <select
            name="currentYear"
            value={formData.currentYear}
            onChange={handleChange}
className="
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
"            required
          >
            <option value="">
              Select Year
            </option>

            <option>
              1st Year
            </option>

            <option>
              2nd Year
            </option>

            <option>
              3rd Year
            </option>

            <option>
              4th Year
            </option>

          </select>

 <label
className="
    self-center
    uppercase
    tracking-wide
    text-[11px]
    font-semibold
    text-slate-400
"
>
        Graduation Year
    </label>
          <input
            type="number"
            name="graduationYear"
            placeholder="Graduation Year"
            value={formData.graduationYear}
            onChange={handleChange}
className="
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
"            required
          />
<label
className="
    self-center
    uppercase
    tracking-wide
    text-[11px]
    font-semibold
    text-slate-400
"
>
        Dream Role
    </label>
          <input
            type="text"
            name="dreamRole"
            placeholder="Dream Role"
            value={formData.dreamRole}
            onChange={handleChange}
className="
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
"            required
          />

          <div className="md:col-span-2">

   <label
className="
    self-center
    uppercase
    tracking-wide
    text-[11px]
    font-semibold
    text-slate-400
"
>
        Skills
    </label>

    <textarea
        rows="4"
        name="skills"
        placeholder="Python, Java, React, Spring Boot..."
        value={formData.skills}
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
        
    />

    <p className="text-sm text-slate-500 mt-2">
        Leave empty if you're just getting started. Separate multiple skills using commas.
    </p>

</div>


<div className="md:col-span-2 mt-2">

   <ResumeCard
    email={email}
    compact
    onUploadSuccess={() => {

        // Optional:
        // Refresh profile if required

    }}
/>

</div>

<div className="
    md:col-span-2
    flex
    gap-3
">

    <button
        type="button"
        onClick={() =>
            navigate("/profile")
        }
        className="
            flex-1
            border
            border-slate-300
            py-4
            rounded-2xl
            font-semibold
            hover:bg-slate-100
        "
    >
        Cancel
    </button>

    <button
        type="submit"
        className="
            flex-1
            bg-gradient-to-r
            from-indigo-600
            to-purple-600
            hover:from-indigo-700
            hover:to-purple-700
            text-white
            py-4
            rounded-2xl
            font-semibold
            text-base
            shadow-lg
        "
    >
        Save Changes
    </button>

</div>

        </form>

      </div>

    </div>

    </div>

  );

}

export default ProfileEditPage;