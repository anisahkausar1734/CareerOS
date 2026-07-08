function JobSkeleton() {

  return (

    <div
      className="
        bg-white
        rounded-xl
        shadow
        p-6
        animate-pulse
      "
    >

      <div className="h-6 bg-gray-200 rounded w-3/4 mb-4" />

      <div className="h-4 bg-gray-200 rounded w-1/2 mb-3" />

      <div className="h-4 bg-gray-200 rounded w-2/3 mb-5" />

      <div className="h-10 bg-gray-200 rounded mb-3" />

      <div className="flex gap-3">

        <div className="h-10 bg-gray-200 rounded flex-1" />

        <div className="h-10 bg-gray-200 rounded flex-1" />

      </div>

    </div>

  );
}

export default JobSkeleton;