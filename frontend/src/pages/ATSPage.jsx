import { useEffect, useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";
import {
  RefreshCw,
  Search,
  FileText,
  AlertTriangle,
  CheckCircle2,
  Sparkles,
  Target
} from "lucide-react";


function ATSPage() {

    const [data, setData] = useState(null);

    useEffect(() => {

        fetchATS();

    }, []);

const reanalyzeResume = async () => {

  try {

    const email =
      localStorage.getItem("email");

    const response =
      await axios.post(
        `http://localhost:8080/api/resume-analysis/reanalyze/${email}`
      );

    setData(response.data);

  } catch (error) {

    console.log(error);

  }

};

    const fetchATS = async () => {

        try {

            const email =
                localStorage.getItem(
                    "email"
                );

          const response =
  await axios.get(
    `http://localhost:8080/api/resume-analysis/cached/${email}`
  );  

            setData(
                response.data
            );

        } catch(error) {

            console.log(error);

        }
    };

   if(!data) {

    return (

        <div
            className="
                min-h-screen
                flex
                items-center
                justify-center
            "
        >

            <div
                className="
                    bg-white
                    p-8
                    rounded-3xl
                    shadow-lg
                    text-center
                "
            >

                <h2
                    className="
                        text-2xl
                        font-bold
                        mb-3
                    "
                >
                    ATS Analysis Not Available
                </h2>

                <p className="text-gray-500">
                    Please analyze your resume first.
                </p>

            </div>

        </div>

    );
}
console.log(data);
    return (

        <div className="
            flex
            bg-slate-100
            min-h-screen
        ">

            <Sidebar />

            <div className="
                ml-72
                flex-1
                p-8
            ">

                <div className="
                    max-w-7xl
                    mx-auto
                ">

                    {/* HEADER */}

                   <div
  className="
    bg-gradient-to-r
    from-[#7367F0]
    to-[#9D8DFF]
    rounded-3xl
    p-10
    text-white
    mb-8
  "
>

  <p className="uppercase text-sm opacity-80">
    Resume Optimization
  </p>

  <h1
    className="
      text-5xl
      font-bold
      mt-3
    "
  >
<div className="flex items-center gap-3">
  <h1>ATS Resume Analysis</h1>
</div>  </h1>

  <p
    className="
      mt-4
      text-lg
      opacity-90
    "
  >
    Improve recruiter visibility and
    maximize interview opportunities.
  </p>

  <button
  onClick={reanalyzeResume}
  className="
    bg-white
    text-[#7367F0]
    px-6
    py-3
    rounded-xl
    font-semibold
  "
>
  Reanalyze Resume
</button>

</div>


                    {/* ATS SCORE */}

                <div
  className="
    bg-white
    rounded-3xl
    border
    border-[#E8E6EF]
    p-10
    mb-8
    text-center
  "
>

  <p
    className="
      text-gray-500
      mb-5
      text-lg
    "
  >
    Overall ATS Score
  </p>

  {/* ATS Score Circle */}

  <div
    className="
      w-44
      h-44
      mx-auto
      rounded-full
      border-[12px]
      border-[#7367F0]
      flex
      items-center
      justify-center
    "
  >
    <span
      className="
        text-5xl
        font-bold
        text-[#7367F0]
      "
    >
      {data.atsScore}%
    </span>
  </div>

  {/* Progress Bar */}

  <div
    className="
      w-80
      mx-auto
      mt-6
    "
  >

    <div
      className="
        h-3
        bg-gray-200
        rounded-full
      "
    >

      <div
        className="
          h-3
          bg-[#7367F0]
          rounded-full
          transition-all
          duration-700
        "
        style={{
          width: `${data.atsScore}%`
        }}
      />

    </div>

  </div>

  {/* Verdict Badge */}

  <div className="mt-6">

    {
      data.atsScore >= 80 && (
        <span
          className="
            inline-flex
            px-5
            py-2
            rounded-full
            bg-green-100
            text-green-700
            font-semibold
          "
        >
          🟢 Excellent ATS Match
        </span>
      )
    }

    {
      data.atsScore >= 60 &&
      data.atsScore < 80 && (
        <span
          className="
            inline-flex
            px-5
            py-2
            rounded-full
            bg-yellow-100
            text-yellow-700
            font-semibold
          "
        >
          🟡 Needs Optimization
        </span>
      )
    }

    {
      data.atsScore < 60 && (
        <span
          className="
            inline-flex
            px-5
            py-2
            rounded-full
            bg-red-100
            text-red-700
            font-semibold
          "
        >
          🔴 Poor ATS Visibility
        </span>
      )
    }

  </div>

  {/* ATS Summary */}

  <p
    className="
      mt-8
      text-gray-600
      max-w-4xl
      mx-auto
      leading-8
      text-lg
    "
  >
    {data.verdict}
  </p>

</div>

<div
  className="
    mt-6
    text-sm
    text-gray-500
  "
>
  Target ATS Score:
  <span className="font-semibold text-[#7367F0]">
    {" "}85%+
  </span>
</div>


                    {/* SCORE BREAKDOWN */}

                    <div className="
                        grid
                        md:grid-cols-4
                        gap-6
                        mb-8
                    ">

                        <ScoreCard
                            title="Keywords"
                            score={data.keywordScore}
                        />

                        <ScoreCard
                            title="Formatting"
                            score={data.formattingScore}
                        />

                        <ScoreCard
                            title="Sections"
                            score={data.sectionScore}
                        />

                        <ScoreCard
                            title="Readability"
                            score={data.readabilityScore}
                        />

                    </div>

                    <div
  className="
    bg-yellow-50
    border
    border-yellow-200
    rounded-2xl
    p-6
    mb-8
  "
>

  <h3
    className="
      font-bold
      text-lg
      mb-2
    "
  >
<div
  className="
    flex
    items-center
    gap-2
    mb-3
  "
>
  <Target size={22}/>
  <h3>Recommended Next Action</h3>
</div>  </h3>

🚀 ATS Improvement Potential

Current ATS Score: 65%
Target ATS Score: 85%

Estimated Improvement:
+20%

  <p>
    Add AI, Machine Learning,
    TensorFlow and PyTorch
    related projects to improve
    ATS visibility.
  </p>

</div>


                    {/* MISSING KEYWORDS */}

                    <div className="
                        bg-white
rounded-3xl
border
border-[#E8E6EF]
                        p-8
                        mb-8
                    ">

                        <h2 className="
                            text-2xl
                            font-bold
                            mb-6
                        ">

<div className="flex items-center gap-2 mb-6"><Search size={24}/> <h2>
    Missing Keywords
  </h2>

</div>                        </h2>

                        <div className="
                            flex
                            flex-wrap
                            gap-3
                        ">

                            {
                                data.missingKeywords?.map(
                                    (
                                        keyword,
                                        index
                                    ) => (

                                        <span
                                            key={index}
                                           className="
  px-4
  py-2
  rounded-xl
  bg-[#FFF1F1]
  text-red-600
  font-medium
"
                                        >
                                            {keyword}
                                        </span>

                                    )
                                )
                            }

                        </div>

                    </div>

                    {/* STRENGTHS + IMPROVEMENTS */}

                    <div className="
                        grid
                        md:grid-cols-2
                        gap-6
                    ">

                        <div className="
                            bg-white
rounded-3xl
border
border-[#E8E6EF]
                            p-8
                        ">

                            <h2 className="
                                text-2xl
                                font-bold
                                mb-6
                                text-green-600
                            ">
                                Strengths
                            </h2>

                            <div className="space-y-4">

                                {
                                    data.strengths?.map(
                                        (
                                            item,
                                            index
                                        ) => (

                                            <div
                                                key={index}
                                                className="
                                                    p-4
                                                    rounded-2xl
                                                    bg-green-50
                                                "
                                            >

<div className="flex gap-3">
 <CheckCircle2
  size={20}
  className="text-green-600"
/>
  <span>{item}</span>
</div>                                            </div>

                                        )
                                    )
                                }

                            </div>

                        </div>

                        <div className="
                            bg-white
rounded-3xl
border
border-[#E8E6EF]
                            p-8
                        ">

                            <h2 className="
                                text-2xl
                                font-bold
                                mb-6
                                text-orange-600
                            ">
                                Improvements
                            </h2>

                            <div className="space-y-4">

                                {
  data?.improvements?.map(
    (item, index) => (

      <div
        key={index}
        className="
          bg-orange-50
          p-5
          rounded-2xl
          mb-4
        "
      >
        <div className="flex gap-3">

          <AlertTriangle
            size={20}
            className="text-orange-500 mt-1"
          />

          <span>{item}</span>

        </div>

      </div>

    )
  )
}

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );
}

function ScoreCard({
  title,
  score
}) {

  return (

    <div
      className="
        bg-white
        rounded-2xl
        border
        border-[#E8E6EF]
        p-6
      "
    >

      <h3
        className="
          text-gray-500
          text-sm
        "
      >
        {title}
      </h3>

      <h2
        className="
          text-4xl
          font-bold
          mt-2
          text-[#7367F0]
        "
      >
        {score}%
      </h2>

      <div
        className="
          mt-4
          h-2
          bg-gray-200
          rounded-full
        "
      >

        <div
          className="
            h-2
            bg-[#7367F0]
            rounded-full
          "
          style={{
            width: `${score}%`
          }}
        />

      </div>

    </div>

  );

}
export default ATSPage;
