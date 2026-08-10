# Parking Lot API

## How to run
App can be run using two ways:
- Maven wrapper:
```shell
./mvnw spring-boot:run
```
_Note_: JVM 25 is required.

- using Docker:
```shell
docker compose up
```
_Note_: Worth mentioning that current Dockerfile is not production ready.

## Assumptions
### Additional "5 minute" cost
It's not clear if additional 5 minute cost should be applied on every 5 minute passed or every 5 minute started, eg.:
- 5 minute passed : parking ends at 6 minute - additional cost is £1,
- 5 minute started: parking ends at 6 minute - additional cost is £2.
Design decision: Additional cost was calculated on every passed 5 minute parking time.

### 1 minute charge logic
It's not clear if 1 minute charge should be counted from the start of the minute or at the end of it, eg.:
- 1 minute started: parking ends at 59 second for small vehicle - cost is £0.1,
- 1 minute passed : parking ends at 1 minute for small vehicle - cost is £0.1.
Design decision: Cost calculation is using "1 minute passed" way.

### 5 minute additional charge and 1 minute regular charge
It's not clear if the "5 minute" charge should exclude "1 minute" charge at the same minute.
Design decision: Both are charged at the same minute.

### One country/timezone datetime
It's not clear if the service going to be used in one timezone.
Design decision: Used LocalDateTime as timeIn/timeOut as it doesn't care about time zone.

### No clear REST API contract definition
REST API contract definition shows only request response structure, but it doesn't contain:
- which fields are mandatory
- HTTP status codes for exceptions
- error response structure
Design decision: All of the above where added pragmatically.

### BillId type
It's not clear what is the structure of `BillId`.
Design decision: As it's defined as `String` in requirements, I assumed that it should be an UUID.

### Registration plate nationality
It's not clear what validation should be used for license plates.
Design decision: Only `@NotBlank` validation added for now, without regex validation (`@Pattern`).

### Number of parking spots
Requirements doesn't show how many parking spots should be used.
Design decision: Number of parking spots is configurable.

### Endpoints prefix
Requirements shows that endpoints should look like:
- `/parking`
- `/parking/bill`
Design decision: Based on best practices, `/api/v1` was added to endpoints as a prefix.

### Vehicles not deleted from database
It's not clear if after vehicle leaved parking, it should be deleted from database.
Design decision: Vehicle stays in database after leaving the parking lot.

### Spots are not persisted in database
In my implementation I didn't implement parking spots database persistence.
It will just take implementation time and won't show any new skills.
Similar implementations were already done in `InMemoryBillRepository` and `InMemoryVehicleRepository`.

## Questions
- How should the business logic in "5 minute" charge look like?
- How should the business logic in "1 minute" charge look like?
- Should the additional "5 minute" cost exclude the regular "1 minute" charge for that minute?
- Will the service be running in other timezones? Will there be a use of multi-region AWS architecture?
- Should the history of parked vehicles be persisted?
- What HTTP status should be returned in case of exceptions (`NoSpotsLeftException`, `NoSuchVehicleParkedException`, `UnknownVehicleTypeException`, `VehicleAlreadyParkedException`)?
- Which REST API request fields are mandatory?
- How to validate a license plate? What pattern/regex should be used?
- How does the REST APIs error response should look like? 
- How should the `BillId` looks like?
- How many parking spots? Should it be configurable?
- Can the endpoints have `/api/v1` prefix added?

## Trade-offs
- More tests to add
- Add logging
- Add production ready lock in `ParkingSpotsCoordinatorService`