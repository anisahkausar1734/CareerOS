import { useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function ProjectAnalysisPage() {
  const [project, setProject] = useState({
    projectName: "",
    hasFrontend: false,
    hasBackend: false,
    hasDatabase: false,
    hasAuthentication: false,
    deployed: false,
    usesAI: false,

    complexityLevel: "INTERMEDIATE",
    estimatedUsers: 100,
    teamSize: 1,

    usesMicroservices: false,
    usesCloud: false,
    hasCICD: false,
    openSource: false,
    researchBased: false,

    revenueGenerated: 0,
    architectureType: "MONOLITH"
  });

  const [result, setResult] = useState(null);

  const handleChange = (e) => {
    const { name, value, checked, type } = e.target;

    setProject({
      ...project,
      [name]:
        type === "checkbox"
          ? checked
          : value
    });
  };

  const analyzeProject = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/projects/analyze",
        project
      );

      setResult(response.data);
    } catch (error) {
      console.error(error);
      alert("Project Analysis Failed");
    }
  };

  return (
    <>
      <Navbar />

      <div className="p-6 max-w-5xl mx-auto">

        <h1 className="text-3xl font-bold mb-6">
          Project Analysis
        </h1>

        <input
          type="text"
          name="projectName"
          placeholder="Project Name"
          value={project.projectName}
          onChange={handleChange}
        />

        <br /><br />

        <h3>Technical Features</h3>

        <label>
          <input
            type="checkbox"
            name="hasFrontend"
            checked={project.hasFrontend}
            onChange={handleChange}
          />
          Frontend
        </label>

        <br />

        <label>
          <input
            type="checkbox"
            name="hasBackend"
            checked={project.hasBackend}
            onChange={handleChange}
          />
          Backend
        </label>

        <br />

        <label>
          <input
            type="checkbox"
            name="hasDatabase"
            checked={project.hasDatabase}
            onChange={handleChange}
          />
          Database
        </label>

        <br />

        <label>
          <input
            type="checkbox"
            name="hasAuthentication"
            checked={project.hasAuthentication}
            onChange={handleChange}
          />
          Authentication
        </label>

        <br />

        <label>
          <input
            type="checkbox"
            name="deployed"
            checked={project.deployed}
            onChange={handleChange}
          />
          Deployed
        </label>

        <br />

        <label>
          <input
            type="checkbox"
            name="hasCICD"
            checked={project.hasCICD}
            onChange={handleChange}
          />
          CI/CD
        </label>

        <br />

        <label>
          <input
            type="checkbox"
            name="usesAI"
            checked={project.usesAI}
            onChange={handleChange}
          />
          Uses AI
        </label>

        <br />

        <label>
          <input
            type="checkbox"
            name="openSource"
            checked={project.openSource}
            onChange={handleChange}
          />
          Open Source
        </label>

        <br />

        <label>
          <input
            type="checkbox"
            name="researchBased"
            checked={project.researchBased}
            onChange={handleChange}
          />
          Research Based
        </label>

        <br /><br />

        <h3>Complexity Level</h3>

        <select
          name="complexityLevel"
          value={project.complexityLevel}
          onChange={handleChange}
        >
          <option value="BEGINNER">
            Beginner
          </option>

          <option value="INTERMEDIATE">
            Intermediate
          </option>

          <option value="ADVANCED">
            Advanced
          </option>
        </select>

        <br /><br />

        <h3>Architecture Type</h3>

        <select
          name="architectureType"
          value={project.architectureType}
          onChange={handleChange}
        >
          <option value="MONOLITH">
            Monolith
          </option>

          <option value="LAYERED">
            Layered
          </option>

          <option value="MICROSERVICES">
            Microservices
          </option>

          <option value="EVENT_DRIVEN">
            Event Driven
          </option>
        </select>

        <br /><br />

        <h3>Estimated Users</h3>

        <input
          type="number"
          name="estimatedUsers"
          value={project.estimatedUsers}
          onChange={handleChange}
        />

        <br /><br />

        <h3>Team Size</h3>

        <input
          type="number"
          name="teamSize"
          value={project.teamSize}
          onChange={handleChange}
        />

        <br /><br />

        <h3>Revenue Generated</h3>

        <input
          type="number"
          name="revenueGenerated"
          value={project.revenueGenerated}
          onChange={handleChange}
        />

        <br /><br />

        <button onClick={analyzeProject}>
          Analyze Project
        </button>

        {result && (
          <div className="mt-8">

            <h2>
              Final Score: {result.finalScore}
            </h2>

            <p>
              {result.feedback}
            </p>

            <hr />

            <p>
              Complexity Score:
              {result.complexityScore}
            </p>

            <p>
              Technical Score:
              {result.technicalScore}
            </p>

            <p>
              Architecture Score:
              {result.architectureScore}
            </p>

            <p>
              Deployment Score:
              {result.deploymentScore}
            </p>

            <p>
              Impact Score:
              {result.impactScore}
            </p>

            <p>
              Innovation Score:
              {result.innovationScore}
            </p>

            <p>
              Collaboration Score:
              {result.collaborationScore}
            </p>

          </div>
        )}

      </div>
    </>
  );
}

export default ProjectAnalysisPage;