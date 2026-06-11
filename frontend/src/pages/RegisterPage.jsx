import { useState } from "react";
import { registerUser } from "../services/authService";

function RegisterPage() {

  const [formData, setFormData] = useState({
    fullName: "",
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
        await registerUser(formData);

      alert(response.message);

    } catch (error) {

      console.log("FULL ERROR:", error);

      console.log("RESPONSE:", error.response);

      alert(
        error.response?.data?.message ||
        error.message
      );
    }
  };

  return (
    <div>

      <h1>Register</h1>

      <form onSubmit={handleSubmit}>

        <input
          type="text"
          name="fullName"
          placeholder="Full Name"
          onChange={handleChange}
        />

        <br />

        <input
          type="email"
          name="email"
          placeholder="Email"
          onChange={handleChange}
        />

        <br />

        <input
          type="password"
          name="password"
          placeholder="Password"
          onChange={handleChange}
        />

        <br />

        <button type="submit">
          Register
        </button>

      </form>

    </div>
  );
}

export default RegisterPage;