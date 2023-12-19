import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation

class MongoSimulation extends Simulation {
  val scn = scenario("MongoDB Test")
    .exec(session => {
      MongoTest.testMongo() // This is the Java method you need to implement
      session
    })

  setUp(scn.inject(atOnceUsers(1)))
}