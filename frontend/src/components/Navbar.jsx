import { Link, useNavigate } from "react-router-dom";

function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
  };

  return (
    <nav className="bg-gray-800 text-white p-4">
      <div className="flex justify-between items-center">

        <h1 className="text-xl font-bold">
          CareerOS
        </h1>

        <div className="flex gap-4">

          <Link to="/dashboard">
            Dashboard
          </Link>

          <Link to="/profile">
            Profile
          </Link>

          <Link to="/ats">
            ATS Analysis
          </Link>

          <Link to="/internship">
            Internship
          </Link>

          <Link to="/projects">
  Projects
</Link>

<Link to="/resume">
  Resume
</Link>

          <button
            onClick={handleLogout}
            className="bg-red-500 px-3 py-1 rounded"
          >
            Logout
          </button>

        </div>
      </div>
    </nav>
  );
}

export default Navbar;