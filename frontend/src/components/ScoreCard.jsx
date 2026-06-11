function ScoreCard({ title, score }) {
  return (
    <div
      style={{
        border: "1px solid black",
        padding: "20px",
        margin: "10px",
        width: "200px"
      }}
    >
      <h3>{title}</h3>
      <h1>{score}</h1>
    </div>
  );
}

export default ScoreCard;