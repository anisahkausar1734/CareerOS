function ScoreCard({ title, score }) {
  return (
    <div className="bg-white rounded-xl shadow-md p-6 hover:shadow-xl transition duration-300">

      <h3 className="text-gray-500 text-sm font-medium">
        {title}
      </h3>

      <h1 className="text-4xl font-bold mt-3">
        {score}
      </h1>

    </div>
  );
}

export default ScoreCard;