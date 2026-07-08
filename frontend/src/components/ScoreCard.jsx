function ScoreCard({
  title,
  score
}) {

  return (

    <div
      className="
        bg-white
        rounded-2xl
        border
        border-[#E8E6EF]
        p-6
        shadow-sm
      "
    >

      <p
        className="
          text-[#8A88A1]
          text-sm
          font-medium
          mb-3
        "
      >
        {title}
      </p>

      <h1
        className="
          text-5xl
          font-semibold
          text-[#4A4A4A]
        "
      >
        {score}
      </h1>

      <div
        className="
          mt-4
          h-1
          w-full
          bg-[#F1EFF7]
          rounded-full
          overflow-hidden
        "
      >

        <div
          className="
            h-full
            bg-[#7367F0]
            rounded-full
          "
          style={{
            width: `${Math.min(score, 100)}%`
          }}
        />

      </div>

    </div>

  );

}

export default ScoreCard;