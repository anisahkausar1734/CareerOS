import {
  Plus,
  Search,
  SlidersHorizontal,
} from "lucide-react";

import EmptyProjects from "./EmptyProjects";
import ProjectCard from "./ProjectCard";

export default function ProjectLibrary({
  projects = [],
  loading,
  onReAnalyze,
}) {

  if (loading) {
    return (
      <section className="rounded-3xl border border-slate-200 bg-white p-10">
        Loading Projects...
      </section>
    );
  }

  return (
    <section className="space-y-8">

      {/* Header */}
      <div className="flex flex-col gap-6 rounded-3xl border border-slate-200 bg-white p-8 shadow-sm">

        <div className="flex flex-col gap-5 lg:flex-row lg:items-center lg:justify-between">

          <div>
            <h2 className="text-3xl font-bold text-slate-900">
              My Projects
            </h2>

            <p className="mt-2 text-slate-500">
              Build an engineering portfolio that showcases your real-world work.
            </p>
          </div>

          <button
            className="inline-flex h-11 items-center gap-2 rounded-xl bg-violet-600 px-5 font-medium text-white transition hover:bg-violet-700 hover:shadow-lg">
            <Plus className="h-5 w-5" />
            Analyze Project
          </button>

        </div>

        {/* Toolbar */}
        <div className="flex flex-col gap-4 lg:flex-row lg:items-center">

          <div className="relative flex-1">

            <Search className="absolute left-4 top-1/2 h-4 w-4 -translate-y-1/2 text-slate-400"/>

            <input
              type="text"
              placeholder="Search projects..."
              className="h-11 w-full rounded-xl border border-slate-200 bg-slate-50 pl-11 pr-4 outline-none transition focus:border-violet-500 focus:bg-white"
            />

          </div>

          <button
            className="inline-flex h-11 items-center gap-2 rounded-xl border border-slate-200 bg-white px-4 font-medium text-slate-700 hover:bg-slate-50">
            <SlidersHorizontal className="h-4 w-4"/>
            Filter
          </button>

        </div>


    
      {/* Projects */}

        {projects.length === 0 ? (
          <div className="p-8">
            <EmptyProjects />
          </div>
        ) : (
          <div className="divide-y divide-slate-200">
            {projects.map((project) => (
              <div key={project.id} className="p-5">
                <ProjectCard
                  project={project}
                  onReAnalyze={onReAnalyze}
                />
              </div>
            ))}
          </div>
        )}

      </div>



      {/* Footer CTA */}
      <div className="rounded-3xl border border-dashed border-violet-300 bg-violet-50 p-8 text-center">
        <h3 className="text-xl font-bold text-slate-900">
          Keep Building 🚀
        </h3>

        <p className="mt-2 text-slate-600">
          Every project strengthens your engineering portfolio.
        </p>

        <button className="mt-5 inline-flex h-11 items-center rounded-xl bg-violet-600 px-5 font-medium text-white hover:bg-violet-700">
          Analyze Another Project
        </button>
      </div>

    </section>
  );
}
