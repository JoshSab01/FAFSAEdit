# FAFSAEdit Project Joshua Sabol

## Overview
### Tooling
I used Eclipse 2025-12 (4.38.0) with Spring Tools (5.0.1) and Gradle to complete this project. 
Eclipse was my hobbyist IDE of choice so I had an install already - though using it proved questionable as my latest version was **2019** and it presented a couple hiccups. I needed an update to 2025 and stuck with what I had.
Spring tools is part of the GDIT toolchain but also provides some very nice tools for spooling up webservers without having to worry about many implementation details. Plus fancy "just check a box and it's ready" (e.g. Lombok!)
Gradle was the out of the box default and I didn't see a compelling reason to change it.
### Functionality
The project works as a SpringBootApp which ingests a config file at startup which defines rules. These rules are all native SpEL powered, for the most part - one required rule is defined in-code.
The project then listens on port 8080 for POST requests to /application. It is expecting a JSON formatted like the exampleRequest.json file.
It runs each rule over the passed application, then returns a ApplicationResult with a `status` (SUCCESSFUL or UNSUCCESSFUL) and a list of all the errors found with the application.

### Technical Oddities
You will notice a lot of boilerplate that could be removed with a framwork like Lombok. This was attempted, but walked back. 
This is because Eclipse's test suite (apparently) hates Lombok. This is a known issue with several workarounds, none of which worked quickly. 

## Running and testing
I've included my entire Eclipse directory as a package - running Eclipse should let you import as an existing project no problem. From there, Run -> Run Configurations -> Spring Boot App -> New Configuration. Defaults are all fine.
The project will run on `http://localhost:8080/` There is one accessible endpoint: `http://localhost:8080/application`. I recommend Postman (using raw body to pass in applications) to interact with the web server.
To invoke outside of IDE, you can navigate to the root directory and run `./gradlew bootRun`. The /application API is hit like below:
```json
{
  "studentInfo": {
    "firstName": String,
    "lastName": String,
    "ssn": String,
    "dateOfBirth": String (ISO-8601 format)
  },
  "dependencyStatus": enum ["dependent", "independent"],
  "maritalStatus": enum ["single", "married"],
  "household": {
    "numberInHousehold": int,
    "numberInCollege": int
  },
  Optional<"spouseInfo">: {
    "firstName": String,
    "lastName": String,
    "ssn": String,
    "dateOfBirth": String (ISO-8601 format)
  },
  "income": {
    "studentIncome": int,
    "parentIncome": Optional<int>
  },
  "stateOfResidence": String (two-character state code)
}
```

## Modifying behavior
Rules can be adjusted by editing the config.json file. The program ingests the version of the file that is in bin/config (effectively a build directory). 
