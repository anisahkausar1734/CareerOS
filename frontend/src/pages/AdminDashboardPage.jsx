import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function AdminDashboardPage() {

  const [data, setData] = useState(null);

  useEffect(() => {
    loadDashboard();
  }, []);

  const loadDashboard = async () => {

    try {

      const response =
        await axios.get(
          "http://localhost:8080/api/admin/dashboard"
        );

      setData(response.data);

    } catch (error) {

      console.log(error);
    }
  };

  if (!data) {

    return (
      <h2 className="text-center mt-10">
        Loading...
      </h2>
    );
  }

  return (
    <>
      <Navbar />

      <div className="p-8 bg-gray-100 min-h-screen">

        <div className="max-w-6xl mx-auto">

          <h1 className="text-3xl font-bold mb-6">
            Admin Dashboard
          </h1>

          <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-4">

            <div className="bg-blue-100 p-6 rounded-xl">
              <h2>Total Users</h2>
              <p className="text-3xl font-bold">
                {data.totalUsers}
              </p>
            </div>

            <div className="bg-green-100 p-6 rounded-xl">
              <h2>Backend Developers</h2>
              <p className="text-3xl font-bold">
                {data.backendDevelopers}
              </p>
            </div>

            <div className="bg-purple-100 p-6 rounded-xl">
              <h2>Frontend Developers</h2>
              <p className="text-3xl font-bold">
                {data.frontendDevelopers}
              </p>
            </div>

            <div className="bg-yellow-100 p-6 rounded-xl">
              <h2>Average ATS Score</h2>
              <p className="text-3xl font-bold">
                {Math.round(
                  data.averageATSScore
                )}
              </p>
            </div>

          </div>

        </div>

      </div>
    </>
  );
}

export default AdminDashboardPage;