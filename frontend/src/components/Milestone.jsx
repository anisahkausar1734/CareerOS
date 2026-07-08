function Milestone({
  title,
  completed
}) {

  return (

    <div
      className="
        flex
        items-center
        gap-4
      "
    >

      <div
        className={`
          w-4
          h-4
          rounded-full

          ${
            completed
              ? "bg-green-500"
              : "bg-gray-300"
          }
        `}
      />

      <span
        className={
          completed
            ? "font-medium"
            : "text-gray-500"
        }
      >
        {title}
      </span>

    </div>

  );

}

export default Milestone;