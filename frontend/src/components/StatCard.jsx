function StatCard({
  title,
  value
}) {

  const numericValue =
    parseInt(value);

  return (

    <div
      className="
        bg-white
        rounded-2xl
        border
        border-[#E8E6EF]
        p-6
      "
    >

      <h3
        className="
          text-gray-500
          text-sm
        "
      >
        {title}
      </h3>

      <h1
        className="
          text-3xl
          font-bold
          mt-2
        "
      >
        {value}
      </h1>

      <div
        className="
          mt-4
          h-2
          bg-gray-200
          rounded-full
        "
      >

        <div
          className="
            h-2
            bg-[#7367F0]
            rounded-full
          "
          style={{
            width: `${numericValue}%`
          }}
        />

      </div>

    </div>

  );

}

export default StatCard;