import { useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

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
      <Navbar />

      <div className="bg-gray-100 min-h-screen p-6">

        <div className="max-w-5xl mx-auto">

          <h1 className="text-3xl font-bold mb-2">
            AI Career Mentor
          </h1>

          <p className="text-gray-600 mb-6">
            Ask career, resume, internship,
            project or interview questions.
          </p>

          <div className="bg-white rounded-xl shadow p-4 h-[500px] overflow-y-auto">

            {messages.length === 0 && (

              <div className="text-gray-400 text-center mt-20">

                Try asking:

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
                  className={`max-w-[75%] p-3 rounded-xl ${
                    message.sender === "user"
                      ? "bg-blue-500 text-white"
                      : "bg-gray-200"
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

          <div className="mt-4 flex gap-3">

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
              className="bg-blue-600 text-white px-6 rounded-lg"
            >
              Send
            </button>

          </div>

        </div>

      </div>
    </>
  );
}

export default AICareerMentor;