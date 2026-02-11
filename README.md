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

## Time spent
I spent between 2 and 3 hours coding on this assignment. I first opened the assignment 2026-09-16:16 and was given a 48 hour deadline. Unfortunately, I had made commitments to staff an ambulance on the nights of the 9th and 10th, making uninterrupted time difficult to find. 
I began setup work that night. It took me about 2 hours from 0 install (Eclipse + Spring/no GitHub SSH key) to first commit on my repository, and I spent ~20 minutes stubbing the initial solution. There were some interruptions from calls + context switching.
I sat back down on 2026-02-10 at around 19:30 after the training program which we discussed in my interview. It is currently 2026-02-11 02:18. I was somewhat interrupted by calls as I worked.
I have not included time wrestling setup in my tally. I started from effectively zero dev tooling and encountered some teething pains (e.g. I lost multiple hours on two separate issues fighting Eclipse classpath).
I am learning some new frameworks here - Jackson, and last time I used Spring was with an older version, so the webserver setup and Application/bean scheme is new to me.
There is also some research time and design time (e.g. during my commute) that is not included in the tally.

## AI Usage
I used AI for aid in design (here's my problem, here's my environment, what tools are available?) to validate my assumptions. It was very helpful in onboarding to the portions of framework that I wasn't familiar with and is wholly responsible for SpEL's inclusion; I had intended to write my own DSL.
I also used AI in the generation of some unit test code (TestRule's cases were AI-generated initially, then refined by me). 