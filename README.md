# CC
It is a standard spring boot project created in java. Can be run by 
```
mvn clean spring-boot:run
```   
## Usage

Sample requests: 
* Persist charging stations (Create/Update)
```
curl -v -XPUT --header "Content-Type: application/json" -d '{ "lat":52.5219, "lon":13.4132,"postCode":"10178"}' http://localhost:8080/chargingStations/1
```
* Retrieve charging stations by id
```
curl -v -X GET  http://localhost:8080/chargingStations/1
```
* Find charging stations by postcode
```
curl -v -X GET  http://localhost:8080/chargingStations?postCode=10178
```

## Architecture 

### Disclaimer 
The architecture described below is definitely an overkill for such a trivial task. Nevertheless i decided to implement it 
just this way to show how i imagine building software. The solutions applied in this project allows me to:
* keep business logic independent of any framework 
* employ TDD (BDD more precisely)   
* have highly testable solution
 

## Overview
This project is an example of simplified version of hexagonal architecture (ports-and-adapters). The simplification is mostly 
expressed by a lack of a dedicated layer for input/primary ports.

The application layer (according to hexagonal architecture) is represented in packages _usecases_ and _ports_. Its classes are fully independent of 
any framework (like spring boot) or delivery mechanisms (DB, WEB Controllers) - only a pure java.

Every use case is represented as a separate class within _usecases_ package. 

For each use-case there is a dedicated test being more of BDD test than a regular unit test. These tests (not fully completed) were used to drive and verify an implementation 
of each use-case as a black-box. 
This kind of testing allows one to fully verify business logic of the use-case with an extra fast feedback loop in a fully reliable environment.  

Every time a _use case_ class needs to interact with an external world, the interaction is performed via _output(secondary) port_.
_Output ports_ layer is represented by interfaces and DTOs (for now we have only a single interface ) within _ports_ package.
These interfaces would be implemented by _output port adapters_ responsible for integration with
external dependencies like DBs, external systems, message queues and so on.
For this project there is a single Dummy Implementation (which stores charging station in memory) which acts as an adapter.     
Each _output port adapter_ shall have its own test being an integration test. In this case i created (as an example) a single 
integration test which validates my Dummy Implementation. 

### Flow

Each http request is handled by a dedicated spring rest controller hosted within _web_ package.
HTTP request entities (HTTP request body) are captured by Request classes.
_Controller_ only job is to build _input bean_ (from HTTP Body, Headers, Path or Query parameters) and delegate to a _use case_ class 
which drives the whole use-case logic. 
Each _use case_ class returns an instance of _ServiceResponse_ that encapsulate delivery-mechanism agnostic output of a use case to be finally delivered as HTTP response.
Mapping of a _ServiceResponse_ instance to org.springframework.http.ResponseEntity (a standard spring rest controller output) is done via a dedicated mapper 
which translates application-layer response to spring rest controller response. This mapping can be much easier done via pattern-matching  
but i decided to go old-school to not complicate the overall solution.


## Tests
For this project (albeit being an overkill) i decided to go with following tests:
* Unit (BDD) tests for each _use case_ class - allows me to drive development of each use case
* Integration test for _output port_ adapter - verification of interaction with external system (being _ConcurrentHashMap_ in this case)
* End2End test - validate the whole flow  
  

## Known issue
* Most of the things missing is already captured as TODO in tests
* Missing Swagger (OpenApi3) documentation     
