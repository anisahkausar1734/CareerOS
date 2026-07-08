import { useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";

function CopilotPage() {

    const [question, setQuestion] = useState("");

    const [messages, setMessages] = useState([]);

    const [loading, setLoading] = useState(false);

    const email =
        localStorage.getItem("email");

    const suggestions = [
        "Why am I not getting interviews?",
        "What skills should I learn next?",
        "How can I reach my dream company?",
        "How strong is my profile?",
        "What projects should I build?"
    ];

    const history = messages.map(
    msg =>
        `${msg.sender}: ${msg.text}`
);

    const askCopilot = async () => {

        if (!question.trim()) return;

        const currentQuestion = question;

        setMessages(prev => [
            ...prev,
            {
                sender: "user",
                text: currentQuestion
            }
        ]);

        setQuestion("");

        try {

            setLoading(true);

            const response =
                
    await axios.post(
        "http://localhost:8080/api/copilot",
        {
            email,
            question: currentQuestion,
            history
        }
    );

            setMessages(prev => [
                ...prev,
                {
                    sender: "copilot",
                    text: response.data.response
                }
            ]);

        } catch (error) {

            console.error(error);

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

        {/* Hero */}

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
            🤖 CareerOS Copilot
          </h1>

          <p
            className="
              text-white/90
              max-w-3xl
            "
          >
            Your personal AI career strategist.
            Get personalized guidance based on
            your resume, roadmap, applications,
            projects and career goals.
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
              🎯 Profile Analysis
            </span>

            <span className="bg-white/20 px-4 py-2 rounded-xl">
              🚀 Career Planning
            </span>

            <span className="bg-white/20 px-4 py-2 rounded-xl">
              💼 Job Strategy
            </span>

            <span className="bg-white/20 px-4 py-2 rounded-xl">
              📄 Resume Intelligence
            </span>

          </div>

        </div>

        {/* Suggestions */}

        <div
          className="
            grid
            md:grid-cols-3
            gap-4
            mb-8
          "
        >

          {suggestions.map((item) => (

            <button
              key={item}
              onClick={() => setQuestion(item)}
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

        {/* Input */}

        <div
          className="
            bg-white
            rounded-3xl
            border
            border-[#E8E6EF]
            p-6
            mb-8
          "
        >

          <textarea
            rows="5"
            value={question}
            onChange={(e) =>
              setQuestion(e.target.value)
            }
            placeholder="Ask CareerOS Copilot..."
            className="
              w-full
              border
              border-[#E8E6EF]
              rounded-xl
              p-4
              resize-none
              focus:outline-none
              focus:border-[#7367F0]
            "
          />

          <button
            onClick={askCopilot}
            className="
              mt-4
              bg-[#7367F0]
              text-white
              px-6
              py-3
              rounded-xl
              hover:opacity-90
            "
          >
            Ask Copilot
          </button>

        </div>

        {/* Conversation */}

        <div
          className="
            bg-white
            rounded-3xl
            border
            border-[#E8E6EF]
            p-6
          "
        >

          <h2
            className="
              text-2xl
              font-semibold
              mb-6
            "
          >
            Conversation
          </h2>

          <div className="space-y-4">

            {messages.length === 0 && (

              <div
                className="
                  text-center
                  py-16
                  text-gray-500
                "
              >
                <div className="text-6xl mb-4">
                  🤖
                </div>

                <h3 className="text-xl font-semibold mb-2">
                  Start a Conversation
                </h3>

                <p>
                  Ask anything about resumes,
                  internships, jobs, projects,
                  interviews or career growth.
                </p>

              </div>

            )}

            {messages.map((msg, index) => (

              <div
                key={index}
                className={
                  msg.sender === "user"
                    ? "bg-[#7367F0] text-white p-5 rounded-2xl"
                    : "bg-[#F5F3F8] border border-[#E8E6EF] p-5 rounded-2xl"
                }
              >

                <p
                  className="
                    font-semibold
                    mb-2
                  "
                >
                  {msg.sender === "user"
                    ? "👤 You"
                    : "🤖 CareerOS Copilot"}
                </p>

                <p className="whitespace-pre-line">
                  {msg.text}
                </p>

              </div>

            ))}

            {loading && (

              <div
                className="
                  bg-[#F5F3F8]
                  border
                  border-[#E8E6EF]
                  p-5
                  rounded-2xl
                "
              >
                🤖 CareerOS Copilot is thinking...
              </div>

            )}

          </div>

        </div>

      </div>

    </div>

  </div>

</>
);

}

export default CopilotPage;