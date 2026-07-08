import { useLocation, useNavigate } from "react-router-dom";

function InterviewReportPage() {

    const { state } = useLocation();

    const navigate = useNavigate();

    if (!state) {

        return (
            <div className="p-10">
                No report found.
            </div>
        );
    }

    return (
        <div className="min-h-screen bg-slate-50 p-8">

            <div className="max-w-6xl mx-auto">

                <div className="flex justify-between items-center mb-8">

                    <h1 className="text-4xl font-bold">
                        Interview Report
                    </h1>

                    <button
                        onClick={() =>
                            navigate("/interview")
                        }
                        className="
                            bg-blue-600
                            text-white
                            px-5
                            py-2
                            rounded-lg
                        "
                    >
                        New Interview
                    </button>

                </div>

                <div className="grid md:grid-cols-5 gap-4 mb-8">

                    <div className="bg-white rounded-xl shadow p-5">
                        <h3 className="text-gray-500">
                            Overall
                        </h3>

                        <p className="text-3xl font-bold text-blue-600">
                            {Math.round(state.overallScore)}%
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow p-5">
                        <h3 className="text-gray-500">
                            Technical
                        </h3>

                        <p className="text-2xl font-bold">
                            {state.technicalScore.toFixed(1)}
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow p-5">
                        <h3 className="text-gray-500">
                            Communication
                        </h3>

                        <p className="text-2xl font-bold">
                            {state.communicationScore.toFixed(1)}
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow p-5">
                        <h3 className="text-gray-500">
                            Problem Solving
                        </h3>

                        <p className="text-2xl font-bold">
                            {state.problemSolvingScore.toFixed(1)}
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow p-5">
                        <h3 className="text-gray-500">
                            Confidence
                        </h3>

                        <p className="text-2xl font-bold">
                            {state.confidenceScore.toFixed(1)}
                        </p>
                    </div>

                </div>

                <div className="grid md:grid-cols-2 gap-6">

                    <div className="bg-white rounded-xl shadow p-6">

                        <h2 className="text-xl font-semibold mb-4">
                            Strengths
                        </h2>

                        <pre className="whitespace-pre-wrap">
                            {state.strengths}
                        </pre>

                    </div>

                    <div className="bg-white rounded-xl shadow p-6">

                        <h2 className="text-xl font-semibold mb-4">
                            Areas To Improve
                        </h2>

                        <pre className="whitespace-pre-wrap">
                            {state.improvements}
                        </pre>

                    </div>

                </div>

                <div className="bg-white rounded-xl shadow p-6 mt-6">

                    <h2 className="text-xl font-semibold mb-4">
                        AI Recommendation
                    </h2>

                    <p>
                        {state.recommendation}
                    </p>

                </div>

            </div>

        </div>
    );
}

export default InterviewReportPage;