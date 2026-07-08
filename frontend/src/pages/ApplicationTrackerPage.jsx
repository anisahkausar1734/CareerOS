import { useEffect, useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";
import {
    PieChart,
    Pie,
    Cell,
    Tooltip,
    Legend,
    ResponsiveContainer,
    BarChart,
    Bar,
    XAxis,
    YAxis,
    CartesianGrid
} from "recharts";

function ApplicationTrackerPage() {

    const [applications, setApplications] = useState([]);
    const totalApplications = applications.length;

    const [insights, setInsights] = useState("");
const [loadingInsights, setLoadingInsights] = useState(false);

const analyzeJobSearch = async () => {

    try {

        setLoadingInsights(true);

        const response = await axios.post(
            `http://localhost:8080/api/application-insights/${email}`
        );

        setInsights(response.data.analysis);

    } catch (error) {

        console.error(error);

    } finally {

        setLoadingInsights(false);
    }
};

const interviews = applications.filter(
    app => app.status === "INTERVIEW"
).length;

const offers = applications.filter(
    app => app.status === "OFFER"
).length;

const rejected = applications.filter(
    app => app.status === "REJECTED"
).length;

const applied = applications.filter(
    app => app.status === "APPLIED"
).length;

const successRate =
    totalApplications === 0
        ? 0
        : (
            (offers / totalApplications) * 100
        ).toFixed(1);

    const [company, setCompany] = useState("");
    const [role, setRole] = useState("");
    const [status, setStatus] = useState("APPLIED");
    const [notes, setNotes] = useState("");

    const email = localStorage.getItem("email");

    const fetchApplications = async () => {
        try {

            const response = await axios.get(
                `http://localhost:8080/api/applications/${email}`
            );

            setApplications(response.data);

        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchApplications();
    }, []);

    const addApplication = async () => {

        try {

            await axios.post(
                "http://localhost:8080/api/applications",
                {
                    email,
                    company,
                    role,
                    status,
                    notes
                }
            );

            setCompany("");
            setRole("");
            setStatus("APPLIED");
            setNotes("");

            fetchApplications();

        } catch (error) {
            console.error(error);
        }
    };

    const updateStatus = async (id, newStatus) => {

        try {

            await axios.put(
                `http://localhost:8080/api/applications/${id}?status=${newStatus}`
            );

            fetchApplications();

        } catch (error) {
            console.error(error);
        }
    };

    const deleteApplication = async (id) => {

        try {

            await axios.delete(
                `http://localhost:8080/api/applications/${id}`
            );

            fetchApplications();

        } catch (error) {
            console.error(error);
        }
    };
const statusData = [
    {
        name: "Applied",
        value: applied
    },
    {
        name: "Interview",
        value: interviews
    },
    {
        name: "Rejected",
        value: rejected
    },
    {
        name: "Offer",
        value: offers
    }
];
    return (
        <>

           <div className="flex bg-gradient-to-br from-slate-50 via-white to-indigo-50 min-h-screen">

    <Sidebar />

    <div className="ml-72 flex-1 p-8">

        <div className="max-w-7xl mx-auto">

            

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
      mb-2
      text-white/80
      font-medium
    "
  >
    Application Intelligence
  </p>

  <h1
    className="
      text-4xl
      font-bold
      mb-3
    "
  >
    📋 Application Tracker
  </h1>

  <p
    className="
      text-white/90
      max-w-3xl
    "
  >
    Track your job and internship applications,
    monitor interview progress, manage offers
    and stay organized throughout your career journey.
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
      📋 Application Tracking
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
      🎤 Interview Pipeline
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
      💼 Offer Management
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
      🚀 Career Progress
    </span>

  </div>

</div>

                <div className="grid md:grid-cols-5 gap-4 mb-8">

    <div className="
    bg-white/90
    backdrop-blur-md
    rounded-3xl
    border
    border-slate-200
    p-6
    shadow-lg
    hover:shadow-xl
    transition
">
        <h3 className="text-gray-500">
            Applications
        </h3>

        <p className="text-3xl font-bold">
            {totalApplications}
        </p>
    </div>

    <div className="
    bg-white/90
    backdrop-blur-md
    rounded-3xl
    border
    border-slate-200
    p-6
    shadow-lg
    hover:shadow-xl
    transition
">
        <h3 className="text-gray-500">
            Interviews
        </h3>

        <p className="text-3xl font-bold">
            {interviews}
        </p>
    </div>

    <div className="
    bg-white/90
    backdrop-blur-md
    rounded-3xl
    border
    border-slate-200
    p-6
    shadow-lg
    hover:shadow-xl
    transition
">
        <h3 className="text-gray-500">
            Offers
        </h3>

        <p className="text-3xl font-bold text-green-600">
            {offers}
        </p>
    </div>

    <div className="
    bg-white/90
    backdrop-blur-md
    rounded-3xl
    border
    border-slate-200
    p-6
    shadow-lg
    hover:shadow-xl
    transition
">
        <h3 className="text-gray-500">
            Rejected
        </h3>

        <p className="text-3xl font-bold text-red-600">
            {rejected}
        </p>
    </div>

    <div className="
    bg-white/90
    backdrop-blur-md
    rounded-3xl
    border
    border-slate-200
    p-6
    shadow-lg
    hover:shadow-xl
    transition
">
        <h3 className="text-gray-500">
            Success Rate
        </h3>

        <p className="text-3xl font-bold text-blue-600">
            {successRate}%
        </p>
    </div>

</div>

<button
    onClick={analyzeJobSearch}
className="
    mb-8
    bg-gradient-to-r
    from-indigo-600
    to-purple-600
    hover:from-indigo-700
    hover:to-purple-700
    text-white
    px-8
    py-4
    rounded-2xl
    shadow-lg
    font-semibold
    transition
">
    Analyze My Job Search
</button>

{loadingInsights && (

    <div className="
    bg-gradient-to-r
    from-indigo-50
    to-purple-50
    border
    border-indigo-100
    p-8
    rounded-3xl
    shadow-lg
    mb-8
">
        Analyzing your job search...
    </div>

)}

{insights && (

    <div className="
    bg-gradient-to-r
    from-indigo-50
    to-purple-50
    border
    border-indigo-100
    p-8
    rounded-3xl
    shadow-lg
    mb-8
">

        <h2 className="text-xl font-bold mb-4">
            AI Job Search Insights
        </h2>

        <p className="whitespace-pre-line">
            {insights}
        </p>

    </div>

)}

<div className="
    bg-white/90
    backdrop-blur-md
    rounded-[32px]
    shadow-xl
    border
    border-slate-200
    p-8
    mb-8
">

    <div  className="
    bg-white
    rounded-[32px]
    shadow-xl
    border
    border-slate-200
    p-8
">

        <h2 className="text-xl font-semibold mb-4">
            Application Status Distribution
        </h2>

        <ResponsiveContainer
            width="100%"
            height={300}
        >

            <PieChart>

                <Pie
                    data={statusData}
                    dataKey="value"
                    nameKey="name"
                    outerRadius={100}
                    label
                >
                    <Cell fill="#3B82F6" />
                    <Cell fill="#FACC15" />
                    <Cell fill="#EF4444" />
                    <Cell fill="#22C55E" />
                </Pie>

                <Tooltip />

                <Legend />

            </PieChart>

        </ResponsiveContainer>

    </div>

</div>
                {/* Add Application Form */}

              <div className="
    bg-white/90
    backdrop-blur-md
    rounded-[32px]
    shadow-xl
    border
    border-slate-200
    p-8
    mb-8
">

                    <h2 className="text-xl font-semibold mb-4">
                        Add Application
                    </h2>

                    <div className="grid md:grid-cols-2 gap-4">

                        <input
                            type="text"
                            placeholder="Company"
                            value={company}
                            onChange={(e) =>
                                setCompany(e.target.value)
                            }
                           className="
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

                        <input
                            type="text"
                            placeholder="Role"
                            value={role}
                            onChange={(e) =>
                                setRole(e.target.value)
                            }
                           className="
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

                        <select
                            value={status}
                            onChange={(e) =>
                                setStatus(e.target.value)
                            }
                            className="border p-2 rounded"
                        >
                            <option>APPLIED</option>
                            <option>INTERVIEW</option>
                            <option>REJECTED</option>
                            <option>OFFER</option>
                        </select>

                        <input
                            type="text"
                            placeholder="Notes"
                            value={notes}
                            onChange={(e) =>
                                setNotes(e.target.value)
                            }
                           className="
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

                    <button
                        onClick={addApplication}
                       className="
    mt-6
    w-full
    bg-gradient-to-r
    from-indigo-600
    to-purple-600
    hover:from-indigo-700
    hover:to-purple-700
    text-white
    py-4
    rounded-2xl
    font-semibold
    shadow-lg
"
                    >
                        Add Application
                    </button>

                </div>

                {/* Applications Table */}

                <div className="bg-white shadow rounded-lg p-6">

                    <h2 className="text-xl font-semibold mb-4">
                        My Applications
                    </h2>

                    <div className="overflow-x-auto">

                       <div className="grid gap-4">

    {applications.map((app) => (

        <div
            key={app.id}
            className="
                bg-slate-50
                rounded-3xl
                border
                border-slate-200
                p-6
                flex
                justify-between
                items-center
            "
        >

            <div>

                <h3 className="text-xl font-bold">
                    {app.company}
                </h3>

                <p className="text-slate-600">
                    {app.role}
                </p>

                <p className="text-sm text-slate-500 mt-2">
                    Applied on {app.applicationDate}
                </p>

            </div>

            <div className="flex items-center gap-4">

                <select
                    value={app.status}
                    onChange={(e) =>
                        updateStatus(
                            app.id,
                            e.target.value
                        )
                    }
                    className="
                        p-3
                        rounded-xl
                        border
                        border-slate-200
                    "
                >
                    <option>APPLIED</option>
                    <option>INTERVIEW</option>
                    <option>REJECTED</option>
                    <option>OFFER</option>
                </select>

                <button
                    onClick={() =>
                        deleteApplication(app.id)
                    }
                    className="
                        bg-red-500
                        hover:bg-red-600
                        text-white
                        px-4
                        py-3
                        rounded-xl
                    "
                >
                    Delete
                </button>

            </div>

        </div>

    ))}

</div>

                    </div>
</div>

</div>
                </div>

                 </div>

        </>
    );
}

export default ApplicationTrackerPage;