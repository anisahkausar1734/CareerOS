function CareerSnapshot({ profile }) {

  if (!profile) return null;

  return (

    <div className="bg-white rounded-2xl shadow p-6 mb-6">

      <h2 className="text-xl font-bold mb-4">
        Career Snapshot
      </h2>

      <div className="grid md:grid-cols-4 gap-4">

        <div className="bg-blue-50 p-4 rounded-xl">
          <p className="text-sm text-gray-600">
            Dream Role
          </p>

          <p className="font-bold text-lg">
            {profile.dreamRole}
          </p>
        </div>

        <div className="bg-green-50 p-4 rounded-xl">
          <p className="text-sm text-gray-600">
            Career Readiness
          </p>

          <p className="font-bold text-lg">
            {profile.careerReadiness}%
          </p>
        </div>

        <div className="bg-purple-50 p-4 rounded-xl">
          <p className="text-sm text-gray-600">
            Skills
          </p>

          <p className="font-bold text-lg">
            {profile.skills?.length || 0}
          </p>
        </div>

        <div className="bg-yellow-50 p-4 rounded-xl">
          <p className="text-sm text-gray-600">
            Current Stage
          </p>

          <p className="font-bold text-sm">
            {profile.currentStage}
          </p>
        </div>

      </div>

    </div>

  );
}

export default CareerSnapshot;