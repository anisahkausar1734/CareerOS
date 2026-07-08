import {
  Bell,
  Search,
  User
} from "lucide-react";

import {
  useState
} from "react";

import {
  useNavigate
} from "react-router-dom";

function Topbar() {

  const navigate = useNavigate();

  const fullName =
    localStorage.getItem("fullName");

  const [search, setSearch] =
    useState("");

  const [showResults,
    setShowResults] =
    useState(false);

  const [showNotifications,
    setShowNotifications] =
    useState(false);

  const [showMenu,
    setShowMenu] =
    useState(false);

  const pages = [

    {
      name: "Dashboard",
      path: "/dashboard"
    },

    {
      name: "Profile",
      path: "/profile"
    },

    {
      name: "Resume Center",
      path: "/resume-center"
    },

    {
      name: "Resume Refinement",
      path: "/resume-refinement"
    },

    {
      name: "Skill Gap Analysis",
      path: "/skill-gap"
    },

    {
      name: "Career Roadmap",
      path: "/roadmap"
    },

    {
      name: "Learning Resources",
      path: "/resources"
    },

    {
      name: "Mock Interview",
      path: "/interview"
    },

    {
      name: "Job Recommendations",
      path: "/jobs"
    },

    {
      name: "Career Intelligence",
      path: "/career-intelligence"
    }

  ];

  const filteredPages =
    pages.filter(page =>
      page.name
        .toLowerCase()
        .includes(
          search.toLowerCase()
        )
    );

  const notifications = [

    "Resume uploaded successfully",

    "Roadmap generated successfully",

    "Career readiness increased",

    "New jobs available"

  ];

  const logout = () => {

    localStorage.clear();

    navigate("/");

  };

  return (

    <div
      className="
        bg-white
        h-10
        px-8
        flex
        items-center
        justify-between
        shadow-sm
        border-b
      "
    >

      {/* SEARCH */}

      <div className="relative">

        <div
          className="
            flex
            items-center
            gap-3
            bg-gray-100
            px-3
            py-1
            rounded-xl
            w-[450px]
          "
        >

          <Search size={18} />

          <input
            type="text"
            value={search}
            onChange={(e) => {

              setSearch(
                e.target.value
              );

              setShowResults(true);

            }}
            placeholder="Search CareerOS..."
            className="
              bg-transparent
              outline-none
              w-full
            "
          />

        </div>

        {

          showResults &&
          search && (

            <div
              className="
                absolute
                top-16
                w-full
                bg-white
                rounded-2xl
                shadow-xl
                border
                z-50
              "
            >

              {

                filteredPages.length > 0

                  ?

                  filteredPages.map(page => (

                    <button
                      key={page.path}
                      onClick={() => {

                        navigate(
                          page.path
                        );

                        setSearch("");

                        setShowResults(
                          false
                        );

                      }}
                      className="
                        block
                        w-full
                        text-left
                        px-5
                        py-3
                        hover:bg-slate-100
                      "
                    >
                      {page.name}
                    </button>

                  ))

                  :

                  <p className="p-4 text-gray-500">
                    No results found
                  </p>

              }

            </div>

          )

        }

      </div>

      {/* RIGHT SECTION */}

      <div
        className="
          flex
          items-center
          gap-6
        "
      >

        {/* NOTIFICATIONS */}

        <div className="relative">

          <Bell
            size={22}
            className="
              cursor-pointer
            "
            onClick={() =>
              setShowNotifications(
                !showNotifications
              )
            }
          />

          {

            showNotifications && (

              <div
                className="
                  absolute
                  right-0
                  top-10
                  w-80
                  bg-white
                  rounded-2xl
                  shadow-xl
                  border
                  p-4
                  z-50
                "
              >

                <h3
                  className="
                    font-bold
                    mb-3
                  "
                >
                  Notifications
                </h3>

                {

                  notifications.map(
                    (note, index) => (

                      <div
                        key={index}
                        className="
                          p-3
                          rounded-xl
                          hover:bg-gray-100
                        "
                      >
                        {note}
                      </div>

                    )
                  )

                }

              </div>

            )

          }

        </div>

        {/* USER MENU */}

        <div
          className="
            relative
          "
        >

          <div
            onClick={() =>
              setShowMenu(
                !showMenu
              )
            }
            className="
              flex
              items-center
              gap-3
              cursor-pointer
            "
          >

            <div
              className="
                w-10
                h-10
                rounded-full
                bg-indigo-500
                text-white
                flex
                items-center
                justify-center
                font-bold
              "
            >

              <User size={18} />

            </div>

            <div>

              <p
                className="
                  font-semibold
                  text-gray-800
                "
              >
                {fullName}
              </p>

              <p
                className="
                  text-sm
                  text-gray-500
                "
              >
                CareerOS User
              </p>

            </div>

          </div>

          {

            showMenu && (

              <div
                className="
                  absolute
                  right-0
                  top-14
                  w-56
                  bg-white
                  rounded-2xl
                  shadow-xl
                  border
                  p-3
                  z-50
                "
              >

                <button
                  onClick={() =>
                    navigate("/profile")
                  }
                  className="
                    w-full
                    text-left
                    p-3
                    rounded-xl
                    hover:bg-gray-100
                  "
                >
                  👤 My Profile
                </button>

                <button
                  onClick={() =>
                    navigate(
                      "/career-intelligence"
                    )
                  }
                  className="
                    w-full
                    text-left
                    p-3
                    rounded-xl
                    hover:bg-gray-100
                  "
                >
                  🧠 Career Intelligence
                </button>

                <button
                  onClick={logout}
                  className="
                    w-full
                    text-left
                    p-3
                    rounded-xl
                    text-red-600
                    hover:bg-red-50
                  "
                >
                  🚪 Logout
                </button>

              </div>

            )

          }

        </div>

      </div>

    </div>

  );

}

export default Topbar;