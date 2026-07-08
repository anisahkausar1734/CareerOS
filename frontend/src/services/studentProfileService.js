import axios from "axios";

const API_URL = "http://localhost:8080/api/student-profile";

export const saveStudentProfile = async (profile) => {

    const response = await axios.post(

        API_URL,

        profile

    );

    return response.data;

};

export const getStudentProfile = async (email) => {

    const response = await axios.get(

        `http://localhost:8080/api/student-profile/${email}`

    );

    return response.data;

};