import { ArrowRight, Calendar, Code2, FolderGit2, Globe, MoreVertical, Star } from "lucide-react";
import { useNavigate } from "react-router-dom";
export default function ProjectCard({ project }) {
  const navigate = useNavigate();
  const score = project.projectScore ?? 0;
  const tech = project.techStack ?? [];
  const quality = score>=90?"Resume Ready":score>=80?"Senior Engineer Quality":score>=70?"Needs Documentation":"Needs Improvement";
  const stars = Math.round(score/20);
  return (
    <div className="group rounded-2xl border border-slate-200 bg-white hover:border-violet-300 hover:shadow-lg transition-all">
      <div className="grid grid-cols-[72px_1fr_210px_40px] gap-2 items-center px-1 py-2">
        <div className="flex h-16 w-16 items-center justify-center rounded-2xl bg-violet-100">
          <Code2 className="h-8 w-8 text-violet-600"/>
        </div>
        <div className="min-w-0">
          <div className="flex items-center gap-4 flex-wrap">
            <h2 className="text-xl font-bold truncate">{project.projectName}</h2>
            <span className="rounded-full bg-violet-100 px-3 py-1 text-xs font-semibold text-violet-700">{quality}</span>
          </div>
          <p className="mt-2 text-sm text-slate-500 line-clamp-2">{project.description}</p>
          <div className="mt-3 flex flex-wrap gap-2">
            {tech.map(t=><span key={t} className="rounded-full bg-slate-100 px-2.5 py-1 text-xs">{t}</span>)}
          </div>
          <div className="mt-4 flex items-center gap-2 text-xs text-slate-500">
            <Calendar className="h-3.5 w-3.5"/>
            <span>{project.analyzedAt?new Date(project.analyzedAt).toLocaleDateString():"--"}</span>
            <span>•</span>
            <span>{project.analysisStatus??"Completed"}</span>
          </div>
        </div>
        <div className="border-l border-slate-200 pl-6">
          <p className="text-xs uppercase tracking-wider text-slate-500">Engineering Score</p>
          <div className="mt-2 flex items-end gap-1">
            <span className="text-5xl font-bold">{score}</span>
            <span className="pb-1 text-slate-400">/100</span>
          </div>
          <div className="mt-2 flex gap-1">
            {Array.from({length:5}).map((_,i)=><Star key={i} className={i<stars?"h-4 w-4 fill-amber-400 text-amber-400":"h-4 w-4 text-slate-300"}/>)}
          </div>
          <button onClick={()=>navigate(`/project-intelligence/${project.id}`)} className="mt-5 inline-flex items-center gap-2 rounded-xl border border-violet-200 px-4 py-2 text-sm text-violet-700">
            View Report <ArrowRight className="h-4 w-4"/>
          </button>
          <div className="mt-3 flex gap-2">
            {project.githubUrl&&<a href={project.githubUrl} target="_blank" rel="noreferrer" className="rounded-lg border p-2"><FolderGit2 className="h-4 w-4"/></a>}
            {project.liveUrl&&<a href={project.liveUrl} target="_blank" rel="noreferrer" className="rounded-lg border p-2"><Globe className="h-4 w-4"/></a>}
          </div>
         </div>
        <button className="self-start rounded-lg p-2 hover:bg-slate-100">
        <MoreVertical className="h-5 w-5 text-slate-500"/>
      </button>
    </div>
  </div>
 );
}
