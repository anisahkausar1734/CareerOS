import {
  ArrowRight, Calendar, Code2, FolderGit2, Globe,
  RotateCw, Star, CheckCircle2
} from "lucide-react";
import { useNavigate } from "react-router-dom";

export default function ProjectCard({ project, onReAnalyze }) {
  const navigate = useNavigate();
  const score = project.projectScore ?? 0;
  const quality =
    score >= 95 ? "Principal Engineer Quality" :
    score >= 85 ? "Senior Engineer Quality" :
    score >= 75 ? "Production Ready" :
    score >= 65 ? "Needs Documentation" :
    "Needs Improvement";
  const stars = Math.max(1, Math.round(score / 20));
  const tech = project.techStack ?? ["Java","Spring Boot","React","MongoDB"];

  return (
    <div className="group rounded-3xl border border-slate-200 bg-white hover:border-violet-200 hover:shadow-xl transition-all">
      <div className="grid lg:grid-cols-[1fr_320px] gap-10 p-10">
        <div>
          <div className="flex gap-6">
            <div className="flex h-20 w-20 items-center justify-center rounded-3xl bg-violet-100">
              <Code2 className="h-10 w-10 text-violet-700"/>
            </div>
            <div className="flex-1 min-w-0">
              <h2 className="text-[24px] font-bold">{project.projectName}</h2>
              <p className="mt-3 text-slate-500 leading-8">{project.description}</p>
              <div className="mt-6 flex flex-wrap gap-2">
                {tech.map(t=>(
                  <span key={t} className="rounded-full border bg-slate-50 px-4 py-2 text-sm">{t}</span>
                ))}
              </div>
            </div>
          </div>
        </div>
        <div className="border-l border-slate-200 pl-10 flex flex-col justify-center">
          <p className="text-sm uppercase tracking-wide text-slate-500">Engineering Score</p>
          <div className="mt-3 flex items-end gap-2">
            <span className="text-5xl font-extrabold">{score}</span>
            <span className="mb-2 text-slate-400">/100</span>
          </div>
          <div className="mt-4 flex gap-1">
            {Array.from({length:5}).map((_,i)=>(
              <Star key={i} className={i<stars?"fill-amber-400 text-amber-400":"text-slate-300"}/>
            ))}
          </div>
          <div className="mt-5 inline-flex rounded-full bg-emerald-100 px-4 py-2 text-sm font-semibold text-emerald-700">{quality}</div>
        </div>
      </div>
      <div className="border-t border-slate-200 px-10 py-5 flex flex-wrap items-center justify-between gap-4">
        <div className="flex items-center gap-6 text-sm text-slate-500">
          <span className="flex items-center gap-2"><Calendar className="h-4 w-4"/>{project.analyzedAt?new Date(project.analyzedAt).toLocaleDateString():"--"}</span>
          <span className="flex items-center gap-2"><CheckCircle2 className="h-4 w-4 text-emerald-600"/>{project.analysisStatus}</span>
        </div>
        <div className="flex items-center gap-3">
          {project.githubUrl && <a href={project.githubUrl} target="_blank" rel="noreferrer" className="rounded-xl border p-3"><FolderGit2 className="h-5 w-5"/></a>}
          {project.liveUrl && <a href={project.liveUrl} target="_blank" rel="noreferrer" className="rounded-xl border p-3"><Globe className="h-5 w-5"/></a>}
          <button onClick={()=>onReAnalyze(project.id)} className="rounded-xl border px-5 py-3 flex gap-2 items-center"><RotateCw className="h-4 w-4"/>Re-analyze</button>
          <button onClick={()=>navigate(`/project-intelligence/${project.id}`)} className="rounded-xl bg-violet-600 text-white px-6 py-3 flex gap-2 items-center">View Report<ArrowRight className="h-4 w-4"/></button>
        </div>
      </div>
    </div>
  );
}
