import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function ProfilePage() {
  const [profile, setProfile] = useState(null);

  const email = localStorage.getItem("email");

  useEffect(() => {
    loadProfile();
  }, []);

  const loadProfile = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/users/profile/${email}`
      );

      setProfile(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const updateProfile = async () => {
    try {
      await axios.put(
        "http://localhost:8080/api/users/profile",
        profile
      );

      alert("Profile Updated Successfully");
    } catch (error) {
      console.log(error);
      alert("Update Failed");
    }
  };

  if (!profile) {
    return (
      <>
        <Navbar />
        <h2 className="p-6">Loading...</h2>
      </>
    );
  }

  return (
    <>
      <Navbar />

      <div className="p-6">

        <h1>Profile</h1>

        <p>Name:</p>

        <input
          type="text"
          value={profile.fullName || ""}
          readOnly
        />

        <br /><br />

        <p>College:</p>

        <input
          type="text"
          value={profile.college || ""}
          onChange={(e) =>
            setProfile({
              ...profile,
              college: e.target.value
            })
          }
        />

        <br /><br />

        <p>Branch:</p>

        <input
          type="text"
          value={profile.branch || ""}
          onChange={(e) =>
            setProfile({
              ...profile,
              branch: e.target.value
            })
          }
        />

        <br /><br />

        <p>Graduation Year:</p>

        <input
          type="number"
          value={profile.graduationYear || ""}
          onChange={(e) =>
            setProfile({
              ...profile,
              graduationYear:
                parseInt(e.target.value)
            })
          }
        />

        <br /><br />

        <p>Target Role:</p>

        <input
          type="text"
          value={profile.targetRole || ""}
          onChange={(e) =>
            setProfile({
              ...profile,
              targetRole: e.target.value
            })
          }
        />

        <br /><br />

        <button onClick={updateProfile}>
          Save Changes
        </button>

      </div>
    </>
  );
}

export default ProfilePage;