function JobCard({
  job,
  matchData,
  onAnalyze,
  analyzing,
  passFullJob = false
}) {

  const getMatchColor = (score) => {

    if (score >= 90)
      return "bg-green-100 text-green-700";

    if (score >= 70)
      return "bg-blue-100 text-blue-700";

    if (score >= 50)
      return "bg-yellow-100 text-yellow-700";

    return "bg-red-100 text-red-700";
  };

  const getMatchText = (score) => {

    if (score >= 90)
      return "Excellent Match";

    if (score >= 70)
      return "Strong Match";

    if (score >= 50)
      return "Moderate Match";

    return "Low Match";
  };

  return (

    <div
      className="
        bg-white
        rounded-2xl
        shadow
        hover:shadow-lg
        transition
        p-6
      "
    >

      {/* Header */}

      <h2 className="text-xl font-bold">
        {job.title}
      </h2>

      <p className="text-gray-600 mt-2">
        🏢 {job.company}
      </p>

      <p className="text-gray-600">
        📍 {job.location}
      </p>

      {/* Match Section */}

      <div className="mt-4">

        {matchData ? (

          <div
            className={`
              inline-block
              px-3
              py-2
              rounded-full
              font-semibold
              text-sm
              ${getMatchColor(
                matchData.matchScore
              )}
            `}
          >

           🔥{matchData.matchScore}% •{" "}
            {getMatchText(
              matchData.matchScore
            )}

          </div>

        ) : (

          <div
            className="
              inline-block
              px-3
              py-2
              rounded-full
              bg-gray-100
              text-gray-600
              text-sm
            "
          >
            Not Analyzed Yet
          </div>

        )}

      </div>

      {/* Analysis */}

      {matchData && (

  <div
    className="
      mt-4
      bg-gray-50
      border
      rounded-xl
      p-4
    "
  >

    <h3
      className="
        font-semibold
        text-lg
        mb-3
      "
    >
      CareerOS Analysis
    </h3>

    {/* Strengths */}

    {matchData.strengths?.length > 0 && (

      <div className="mb-4">

        <h4
          className="
            font-semibold
            text-green-700
            mb-2
          "
        >
          Strengths
        </h4>

        <div className="space-y-1">

          {matchData.strengths.map(
            (item, index) => (

              <div
                key={index}
                className="
                  text-sm
                  text-green-600
                "
              >
                ✓ {item}
              </div>

            )
          )}

        </div>

      </div>

    )}

    {/* Missing Skills */}

    {matchData.missingSkills?.length > 0 && (

      <div className="mb-4">

        <h4
          className="
            font-semibold
            text-red-700
            mb-2
          "
        >
          Missing Skills
        </h4>

        <div className="space-y-1">

          {matchData.missingSkills.map(
            (item, index) => (

              <div
                key={index}
                className="
                  text-sm
                  text-red-600
                "
              >
                • {item}
              </div>

            )
          )}

        </div>

      </div>

    )}

    {/* Recommendations */}

    {matchData.recommendations?.length > 0 && (

      <div className="mb-4">

        <h4
          className="
            font-semibold
            text-blue-700
            mb-2
          "
        >
          Recommendations
        </h4>

        <div className="space-y-1">

          {matchData.recommendations.map(
            (item, index) => (

              <div
                key={index}
                className="
                  text-sm
                  text-blue-600
                "
              >
                → {item}
              </div>

            )
          )}

        </div>

      </div>

    )}

    {/* Summary */}

    <div
      className="
        mt-4
        pt-4
        border-t
      "
    >

      <h4
        className="
          font-semibold
          mb-2
        "
      >
        Summary
      </h4>

      <p
        className="
          text-sm
          text-gray-700
          whitespace-pre-line
        "
      >
        {matchData.explanation}
      </p>

    </div>

  </div>

)}

      {/* Actions */}

      <div className="flex gap-3 mt-5">

        <button
  onClick={() =>
  onAnalyze(
    passFullJob
      ? job
      : job.title
  )
}
  disabled={analyzing}
  className="
    flex-1
    bg-purple-600
    hover:bg-purple-700
    text-white
    py-2
    rounded-lg
    font-medium
    disabled:opacity-50
  "
>

  {analyzing
    ? "Analyzing..."
    : "Analyze Match"}

</button>

        <a
          href={job.applyLink}
          target="_blank"
          rel="noreferrer"
          className="
            flex-1
            text-center
            bg-blue-600
            hover:bg-blue-700
            text-white
            py-2
            rounded-lg
            font-medium
          "
        >
          Apply Now
        </a>

      </div>

    </div>

  );
}

export default JobCard;