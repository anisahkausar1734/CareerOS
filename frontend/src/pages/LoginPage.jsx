import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { loginUser } from "../services/authService";
import backgroundImage from "../assets/login-bg1.jpg";
import { getStudentProfile } from "../services/studentProfileService";

function LoginPage() {

const navigate = useNavigate();

const [formData, setFormData] = useState({
email: "",
password: ""
});

const handleChange = (e) => {


setFormData({
  ...formData,
  [e.target.name]: e.target.value
});


};

const handleSubmit = async (e) => {


e.preventDefault();

try {

  const response =
    await loginUser(formData);

  localStorage.setItem(
    "email",
    response.email
  );

  localStorage.setItem(
    "role",
    response.role
  );

  localStorage.setItem(
    "fullName",
    response.fullName
  );

  try {

    await getStudentProfile(response.email);

    navigate("/dashboard");

}
catch {

    navigate("/profile-setup");

}

} catch (error) {

  alert(
    error.response?.data?.message ||
    error.message
  );

}


};

return (
<div
  className="
    min-h-screen
    bg-cover
    bg-center
    flex
    items-center
    justify-center
    relative
  "
  style={{
    backgroundImage:
      "url(" + backgroundImage + ")"
  }}
>

  <div
  className="
    absolute
    inset-0
    bg-black/60
    pointer-events-none
  "
/>

  <div
    className="
      relative
      z-10
      w-full
      max-w-lg
      mx-4
      backdrop-blur-xl
      bg-white/10
      border
      border-white/20
      rounded-3xl
      shadow-2xl
      p-10
      text-white
    "
  >

    <h1
      className="
        text-6xl
        font-bold
        text-center
        mb-4
      "
    >
      CareerOS
    </h1>

    <p
  className="
    text-center
    text-lg
    text-white/90
    mb-8
  "
>
  AI-Powered Career Growth Platform
</p>

    <form
      onSubmit={handleSubmit}
      className="space-y-5"
    >

      <div>

  <label
    className="
      block
      mb-2
      text-white
      font-medium
    "
  >
    Email Address
  </label>

  <input
    type="email"
    name="email"
    placeholder="Enter your email"
    value={formData.email}
    onChange={handleChange}
    required
    className="
      w-full
      p-4
      rounded-xl
      bg-white/20
      border
      border-white/20
      text-white
      placeholder-gray-300
      outline-none
    "
  />

</div>

      <div>

  <label
    className="
      block
      mb-2
      text-white
      font-medium
    "
  >
    Password
  </label>

  <input
    type="password"
    name="password"
    placeholder="Enter your password"
    value={formData.password}
    onChange={handleChange}
    required
    className="
      w-full
      p-4
      rounded-xl
      bg-white/20
      border
      border-white/20
      text-white
      placeholder-gray-300
      outline-none
    "
  />

</div>

      <button
        type="submit"
        className="
          w-full
          py-4
          rounded-xl
          bg-white
          text-black
          font-semibold
          hover:scale-105
          transition
          duration-300
        "
      >
        Login
      </button>

    </form>

    <p
      className="
        text-center
        mt-6
        text-white/80
      "
    >
      Don't have an account?

      <Link
        to="/register"
        className="
          ml-2
          font-semibold
          text-white
          hover:underline
        "
      >
        Register
      </Link>

    </p>

  </div>

</div>

);

}

export default LoginPage;
