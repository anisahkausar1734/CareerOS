import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useEffect, useState } from "react";
import Sidebar from "../components/Sidebar";
import { Upload } from "lucide-react";

function InterviewPage() {

    const [company, setCompany] = useState("");
    const [role, setRole] = useState("");
    const [interviewType, setInterviewType] = useState("");
    const [resume, setResume] = useState("");
    const [customPrompt, setCustomPrompt] = useState("");

    const [sessionId, setSessionId] = useState(null);

    const [messages, setMessages] = useState([]);

    const [answer, setAnswer] = useState("");

    const [loading, setLoading] = useState(false);

    const [started, setStarted] = useState(false);

const fetchHistory = async () => {

    try {

        const response =
            await axios.get(
                "http://localhost:8080/api/interview/history"
            );

        setHistory(
            response.data
        );

    } catch (error) {

        console.error(error);
    }
};


    const startInterview = async () => {

        try {

            setLoading(true);

            const response =
                axios.post(
    "http://localhost:8080/api/interview/start",
    {
        company,
        role,
        interviewType,
        resume,
        customPrompt,
        email: localStorage.getItem("email")
    }
);


            setSessionId(
                response.data.sessionId
            );

            setMessages([
                {
                    sender: "AI",
                    text: response.data.firstQuestion
                }
            ]);

            setStarted(true);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);
        }
    };

const [resumeFile, setResumeFile] =
    useState(null);

const [history, setHistory] =
    useState([]);    

const navigate = useNavigate();    

const endInterview = async () => {

    try {

        const response =
            await axios.post(
                "http://localhost:8080/api/interview/end",
                {
                    sessionId
                }
            );

        navigate(
            "/interview-report",
            {
                state: response.data
            }
        );

    } catch (error) {

        console.error(error);

        alert(
            "Please complete at least 5 questions before ending the interview."
        );
    }
};

    const submitAnswer = async () => {

        if (!answer.trim()) return;

        const userMessage = {
            sender: "YOU",
            text: answer
        };

        setMessages(prev => [
            ...prev,
            userMessage
        ]);

        const currentAnswer = answer;

        setAnswer("");

        try {

            setLoading(true);

            const response =
                await axios.post(
                    "http://localhost:8080/api/interview/answer",
                    {
                        sessionId,
                        answer: currentAnswer
                    }
                );

            setMessages(prev => [
                ...prev,
                {
                    sender: "AI",
                    text: response.data.nextQuestion
                }
            ]);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);
        }
    };

    useEffect(() => {

    fetchHistory();

}, []);

    return (
        <>

<div className="flex bg-slate-100 min-h-screen">

    <Sidebar />

    <main className="ml-72 flex-1 p-10">

        <div className="max-w-6xl mx-auto">

       <div
  className="
    bg-gradient-to-r
    from-[#7367F0]
    to-[#9D8DFF]
    text-white
    rounded-3xl
    p-8
    mb-10
  "
>

  <p
    className="
      uppercase
      tracking-wider
      text-sm
      mb-2
      text-white/80
      font-medium
    "
  >
    Interview Intelligence
  </p>

  <h1
    className="
      text-4xl
      font-bold
      mb-3
    "
  >
    🎤 AI Mock Interview
  </h1>

  <p
    className="
      text-white/90
      max-w-3xl
    "
  >
    Practice realistic AI-powered interviews
    tailored to your dream role, improve your
    communication skills and receive detailed
    performance feedback.
  </p>

  <div
    className="
      flex
      flex-wrap
      gap-3
      mt-5
    "
  >

    <span
      className="
        bg-white/20
        px-4
        py-2
        rounded-xl
        text-sm
      "
    >
      🎯 Role-Based Questions
    </span>

    <span
      className="
        bg-white/20
        px-4
        py-2
        rounded-xl
        text-sm
      "
    >
      🧠 AI Evaluation
    </span>

    <span
      className="
        bg-white/20
        px-4
        py-2
        rounded-xl
        text-sm
      "
    >
      📈 Performance Analytics
    </span>

    <span
      className="
        bg-white/20
        px-4
        py-2
        rounded-xl
        text-sm
      "
    >
      🚀 Interview Readiness
    </span>

  </div>

</div>
                {!started && (

<div className="max-w-5xl mx-auto">
    <div className="lg:col-span-2">

        <div
            className="
                bg-white/90
                backdrop-blur-md
                rounded-[32px]
                shadow-xl
                border
                border-slate-200
                p-10
                space-y-8
            "
        >

            {/* Company */}

            <div>

                <label
                    className="
                        block
                        mb-3
                        font-semibold
                        text-slate-800
                        text-lg
                    "
                >
                    Company
                </label>

                <input
                    type="text"
                    placeholder="Google, Microsoft, Amazon..."
                    value={company}
                    onChange={(e) =>
                        setCompany(e.target.value)
                    }
                    className="
                        w-full
                        p-4
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

            </div>

            {/* Role */}

            <div>

                <label
                    className="
                        block
                        mb-3
                        font-semibold
                        text-slate-800
                        text-lg
                    "
                >
                    Role
                </label>

                <input
                    type="text"
                    placeholder="AI Engineer"
                    value={role}
                    onChange={(e) =>
                        setRole(e.target.value)
                    }
                    className="
                        w-full
                        p-4
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

            </div>

            {/* Interview Type */}

            <div>

                <label
                    className="
                        block
                        mb-4
                        font-semibold
                        text-slate-800
                        text-lg
                    "
                >
                    Interview Type
                </label>

                <div
                    className="
                        grid
                        grid-cols-2
                        gap-4
                    "
                >

                    {[
                        "Technical",
                        "Behavioral",
                        "HR",
                        "System Design"
                    ].map((type) => (

                        <button
                            key={type}
                            type="button"
                            onClick={() =>
                                setInterviewType(type)
                            }
                            className={`
                                p-6
                                rounded-3xl
                                border
                                font-semibold
                                text-lg
                                transition-all
                                duration-300

                                ${
                                    interviewType === type
                                        ? `
                                            bg-gradient-to-r
                                            from-indigo-600
                                            to-purple-600
                                            text-white
                                            border-transparent
                                            shadow-xl
                                            scale-[1.02]
                                          `
                                        : `
                                            bg-slate-50
                                            border-slate-200
                                            text-slate-700
                                            hover:bg-white
                                            hover:border-indigo-300
                                            hover:shadow-md
                                          `
                                }
                            `}
                        >

                            {type}

                        </button>

                    ))}

                </div>

            </div>

          <div className="
    border-2
    border-dashed
    border-indigo-200
    rounded-3xl
    p-10
    text-center
    bg-gradient-to-br
    from-indigo-50
    to-purple-50
">

    <input
        id="resumeUpload"
        type="file"
        accept=".pdf"
        onChange={(e) =>
            setResumeFile(
                e.target.files[0]
            )
        }
        className="hidden"
    />

    <label
        htmlFor="resumeUpload"
        className="
            inline-flex
            items-center
            gap-3
            px-8
            py-4
            rounded-2xl
            bg-gradient-to-r
            from-indigo-600
            to-purple-600
            text-white
            font-semibold
            cursor-pointer
            shadow-lg
            hover:scale-105
            transition
        "
    >
        <Upload size={20} />
        
        📄  Upload Resume
    </label>

    <p className="mt-5 text-slate-600">

        {
            resumeFile
                ? resumeFile.name
                : "No file selected"
        }

    </p>

    <p className="mt-2 text-sm text-slate-400">
        PDF files only • Max size 5MB
    </p>

</div>
            <textarea
                placeholder="Custom Instructions"
                rows={4}
                value={customPrompt}
                onChange={(e) =>
                    setCustomPrompt(
                        e.target.value
                    )
                }
               className="
    w-full
    p-4
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

            <button
                onClick={startInterview}
                disabled={loading}
                className="
                    w-full
                    bg-gradient-to-r
from-indigo-600
to-purple-600
hover:from-indigo-700
hover:to-purple-700
shadow-lg
hover:scale-[1.01]
transition-all
                    text-white
                    py-4
                    rounded-xl
                    font-semibold
                "
            >
                {
                    loading
                        ? "Generating Interview..."
                        : "Start AI Interview"
                }
            </button>



            <div className="mt-12">

    <h2 className="text-2xl font-bold mb-6">
        Previous Interviews
    </h2>

    <div className="grid md:grid-cols-3 gap-4">

        <div className="bg-gradient-to-br
from-white
to-slate-50
rounded-3xl
shadow-md
border
border-slate-200
p-6
hover:shadow-xl
hover:-translate-y-1
transition-all
duration-300">

            <h3 className="font-semibold">
                AI Engineer
            </h3>

            <p className="text-sm text-gray-500">
                Mock Interview
            </p>

            <div className="mt-4">

                <span className="
                    bg-green-100
                    text-green-700
                    px-3
                    py-1
                    rounded-full
                    text-sm
                ">
                    Score: 82%
                </span>

            </div>

        </div>

        <div className="bg-gradient-to-br
from-white
to-slate-50
rounded-3xl
shadow-md
border
border-slate-200
p-6
hover:shadow-xl
hover:-translate-y-1
transition-all
duration-300">

            <h3 className="font-semibold">
                Backend Engineer
            </h3>

            <p className="text-sm text-gray-500">
                Technical Interview
            </p>

            <div className="mt-4">

                <span className="
                    bg-blue-100
                    text-blue-700
                    px-3
                    py-1
                    rounded-full
                    text-sm
                ">
                    Score: 76%
                </span>

            </div>

        </div>

    </div>

</div>

        </div>

    </div>

   
</div>

)}
                {started && (

                    <div>

                        <div
                            className="
                                bg-white
rounded-3xl
shadow-lg
p-6
border
border-slate-200
                                h-[500px]
                                overflow-y-auto
                                mb-4
                            "
                        >

                            {messages.map(
                                (message, index) => (

                                    <div
                                        key={index}
                                        className={
                                            message.sender === "YOU"
                                                ? "text-right mb-4"
                                                : "text-left mb-4"
                                        }
                                    >

                                        <div
                                            className="
                                                inline-block
                                               bg-gradient-to-r
from-indigo-600
to-purple-600
text-white
                                                max-w-[80%]
                                            "
                                        >
                                            <strong>
                                                {message.sender}
                                            </strong>

                                            <p>
                                                {message.text}
                                            </p>
                                        </div>

                                    </div>
                                )
                            )}

                        </div>

                        <textarea
                            rows={5}
                            value={answer}
                            onChange={(e) =>
                                setAnswer(
                                    e.target.value
                                )
                            }
                            placeholder="Type your answer..."
                            className="
                                w-full
                                bg-slate-100
text-slate-800
                                mb-3
                            "
                        />

                        <button
                            onClick={submitAnswer}
                            className="
                                bg-green-600
                                text-white
                                px-6
                                py-3
                                rounded
                            "
                        >
                            Send Answer
                        </button>

                         <button
        onClick={endInterview}
        className="
            bg-red-600
            text-white
            px-6
            py-3
            rounded
        "
    >
        End Interview
    </button>



                        {loading && (
                            <p className="mt-3">
                                AI is thinking...
                            </p>
                        )}

                    </div>
                )}

            </div>

             </main>

                        </div>

            



        </>
    );
}

export default InterviewPage;