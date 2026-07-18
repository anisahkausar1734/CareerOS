import api from "./api";

export const createAndAnalyzeProject = async (payload) => {

    const response = await api.post(
        "/projects/create-and-analyze",
        payload
    );

    return response.data;
};

export const getProjectById = async (projectId) => {

    const response = await api.get(
        `/projects/report/${projectId}`
    );

    return response.data;

};

export const getProjects = async (email) => {

    const response = await api.get(
        `/projects/user/${email}`
    );

    return response.data;
};

export const reAnalyzeProject = async (projectId) => {

    const response = await api.post(
        `/projects/${projectId}/reanalyze`
    );

    return response.data;

};

export const getProjectIntelligence = async (email) => {

    const response = await api.get(
        `/projects/intelligence/${email}`
    );

    return response.data;
};

export const deleteProject = async (projectId) => {

    await api.delete(
        `/projects/${projectId}`
    );

};