import { useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";

function AICareerMentor() {

  const [messages, setMessages] =
    useState([]);

  const [prompt, setPrompt] =
    useState("");

  const [loading, setLoading] =
    useState(false);

  const sendMessage = async () => {

    if (!prompt.trim()) {
      return;
    }

    const userMessage = {
      sender: "user",
      text: prompt
    };

    setMessages(prev => [
      ...prev,
      userMessage
    ]);

    const currentPrompt = prompt;

    setPrompt("");

    setLoading(true);

    try {

      const response =
        await axios.post(
          "http://localhost:8080/api/gemini/ask",
          {
            prompt: currentPrompt
          }
        );

      const aiMessage = {
        sender: "ai",
        text: response.data.response
      };

      setMessages(prev => [
        ...prev,
        aiMessage
      ]);

    } catch (error) {

      console.log(error);

      setMessages(prev => [
        ...prev,
        {
          sender: "ai",
          text: "Something went wrong."
        }
      ]);

    } finally {

      setLoading(false);
    }
  };

  return (
    <>

      <Sidebar />

<div
  className="
    ml-72
    min-h-screen
    bg-[#F5F3F8]
  "
>

  <div className="p-8">

    <div className="max-w-6xl mx-auto">

         <div
  className="
    bg-gradient-to-r
    from-[#7367F0]
    to-[#9D8DFF]
    text-white
    rounded-3xl
    p-8
    mb-8
  "
>

  <p
    className="
      uppercase
      tracking-wider
      text-sm
      text-white/80
      mb-2
    "
  >
    Career Intelligence
  </p>

  <h1
    className="
      text-4xl
      font-bold
      mb-3
    "
  >
    🤖 AI Career Mentor
  </h1>

  <p
    className="
      text-white/90
      max-w-3xl
    "
  >
    Your personal AI career coach for
    resumes, internships, jobs, projects,
    interview preparation and career growth.
  </p>

  <div
    className="
      flex
      flex-wrap
      gap-3
      mt-5
    "
  >

    <span className="bg-white/20 px-4 py-2 rounded-xl">
      📄 Resume Advice
    </span>

    <span className="bg-white/20 px-4 py-2 rounded-xl">
      🎤 Interview Prep
    </span>

    <span className="bg-white/20 px-4 py-2 rounded-xl">
      💼 Job Guidance
    </span>

    <span className="bg-white/20 px-4 py-2 rounded-xl">
      🚀 Career Planning
    </span>

  </div>

</div>

<div
  className="
    grid
    md:grid-cols-3
    gap-4
    mb-8
  "
>

  {[
    "🎯 How to improve ATS Score",
    "📄 Review My Resume",
    "💼 Internship Tips",
    "🎤 Speaking Skills",
    "🚀 Projects to build",
    "🧠 Skill Gap Analysis"
  ].map((item) => (

    <button
      key={item}
      onClick={() => setPrompt(item)}
      className="
        bg-white
        rounded-2xl
        border
        border-[#E8E6EF]
        p-5
        text-left
        hover:border-[#7367F0]
        hover:shadow-md
        transition
      "
    >
      {item}
    </button>

  ))}

</div>



<div
  className="
    bg-white
    rounded-3xl
    border
    border-[#E8E6EF]
    p-6
h-[calc(150vh-420px)]
    overflow-y-auto
    shadow-sm
  "
>
            {messages.length === 0 && (

              <div className="text-gray-400 text-center mt-20">

<div
  className="
    flex
    flex-col
    items-center
    justify-center
    h-full
    text-center
  "
>

  <div className="text-6xl mb-4">
    🤖
  </div>

  <h2
    className="
      text-2xl
      font-bold
      mb-3
    "
  >
    Start a Career Conversation
  </h2>

  <p
    className="
      text-gray-500
      max-w-md
    "
  >
    Ask anything about internships,
    resumes, projects, interviews
    or career growth.
  </p>

</div>
                <div className="mt-4 space-y-2">

                  <p>
                    How can I improve my ATS score?
                  </p>

                  <p>
                    Suggest Spring Boot projects.
                  </p>

                  <p>
                    How do I become a Java Developer?
                  </p>

                </div>

              </div>

            )}

            {messages.map((message, index) => (

              <div
                key={index}
                className={`mb-4 flex ${
                  message.sender === "user"
                    ? "justify-end"
                    : "justify-start"
                }`}
              >

                <div
  className={`max-w-[75%] p-4 rounded-2xl ${
    message.sender === "user"
      ? "bg-[#7367F0] text-white"
      : "bg-[#F5F3F8] border border-[#E8E6EF]"
  }`}
>

<div
  className="
    whitespace-pre-line
    leading-7
    text-[15px]
  "
>
  {message.text}
</div>
                </div>

              </div>

            ))}

            {loading && (

              <div className="flex justify-start">

                <div className="bg-gray-200 p-3 rounded-xl">

                  Thinking...

                </div>

              </div>

            )}

          </div>

<div
  className="
    mt-6
    bg-white
    rounded-2xl
    border
    border-[#E8E6EF]
    p-3
    flex
    gap-3
  "
>
            <input
              type="text"
              value={prompt}
              onChange={(e) =>
                setPrompt(e.target.value)
              }
              placeholder="Ask CareerOS AI Mentor..."
              className="flex-1 border rounded-lg px-4 py-3"
              onKeyDown={(e) => {
                if (e.key === "Enter") {
                  sendMessage();
                }
              }}
            />

            <button
              onClick={sendMessage}
              className="bg-[#7367F0]
hover:opacity-90 text-white px-6 rounded-lg"
            >
              Send
            </button>

          </div>

        </div>

      </div>

      </div>
    </>
  );
}

export default AICareerMentor;
