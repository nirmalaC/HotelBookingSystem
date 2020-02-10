# Performance Testing
## Load Testing :
Load testing tells us how many simultaneous users can the application handle and the scale of the application required in terms of hardware, network capacity etc., It helps to identify the maximum operating capacity of an application as well as any bottlenecks and determine which element is causing degradation.

### A real world user scenario:
1. User arrives at the homepage : [Hotel Bookings](http://hotel-test.equalexperts.io/)
2. Users post details to do hotel booking.
3. Users save the bookings.
4. Once the bookings are saved , the user refreshes the page.

### Steps :
Load testing is performed using Gatling tool : [Gatling](https://gatling.io/)

Installation and Recording can be done by following the link :
[Gatling setup](https://gatling.io/docs/current/quickstart)

### Below is Scala script :
```bash
package equalexperts

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class BookingsSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("http://hotel-test.equalexperts.io")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-GB,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_4 = Map(
		"Accept" -> "application/json",
		"Content-Type" -> "application/json; charset=utf-8",
		"Origin" -> "http://hotel-test.equalexperts.io",
		"X-Requested-With" -> "XMLHttpRequest")



	val scn = scenario("BookingsSimulation")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/script.js"),
            http("request_2")
			.get("/booking")
			.headers(headers_2),
            http("request_3")
			.get("/booking/13395")
			.headers(headers_2)))
		.pause(17)
		.exec(http("request_4")
			.post("/booking")
			.headers(headers_4)
			.body(RawFileBody("equalexperts/bookingssimulation/0004_request.json")))

 setUp(scn.inject(
    nothingFor(4 seconds),
    atOnceUsers(10),
    rampUsers(10) during (5 seconds)
  ).protocols(httpProtocol)
  )
}
```

 ```bash
rampUsers(10) during(5 seconds) : Injects a given number of users with a linear ramp over a given duration.
nothingFor(4 seconds) : Pause for a given duration.
atOnceUsers(10) : Injects a given number of users at once.
```
## Gatling Reports :



### Gatling detail report :
![alt text](https://user-images.githubusercontent.com/36641942/74136077-61e1bf80-4be5-11ea-91fe-24417854c035.PNG)


### Gatling number of request report :
![alt text](https://user-images.githubusercontent.com/36641942/74136227-935a8b00-4be5-11ea-8a27-3b8fc28b35a9.PNG)


### Gatling response time report :
![alt text](https://user-images.githubusercontent.com/36641942/74136391-da488080-4be5-11ea-8357-3ad6338db78c.PNG)


### Gatling statistics report :
![alt text](https://user-images.githubusercontent.com/36641942/74136478-0106b700-4be6-11ea-817e-09861596aa57.PNG)