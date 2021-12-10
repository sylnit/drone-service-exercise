# Drone Service Coding Assignment

This application is a RESTful api service for a transportation company that uses drones to deliver drugs. This is expected to leapfrog the traditional transportation infrastructure.
The transport company has 10 drones under it's fleet and requires a service to provide the following:

## Serice Features

- Register a drone
- Load a drone with medication items
- Check loaded medication items for a drone
- Check available drones for loading
- Check drone battery for a given drone

## Functional requirements

- There is no need for UI;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.

## Non-functional requirements

- Input/output data must be in JSON format;
- Your project must be buildable and runnable;
- Your project must have a README file with build/run/test instructions (use DB that can be run locally, e.g. in-memory, via container);
- Required data must be preloaded in the database.
- JUnit tests are optional but advisable (if you have time);
- Advice: Show us how you work through your commit history.

## Prerequisites

- Java (Minimum 8)
- Jre

## Project Dependencies

- Spring data JPA
- H2 In-memory database
- Lombok

## Build the application

```
mvn package
```

## Run the application

```
mvn spring-boot:run
```

## Run the tests

```
mvn test
```

## Endpoints documentation

| Endpoint                                                   |             Description              |                                                                                                          Sample Payload |
| :--------------------------------------------------------- | :----------------------------------: | ----------------------------------------------------------------------------------------------------------------------: |
| `POST` http://localhost:8080/drones                        |          Registers a drone           |  `{"serialNumber": "SXOIDLKDLSKAD", "model": "Cruiserweight","weightLimit": 500,"batteryCapacity": 100,"state": "IDLE"} |
| `GET` http://localhost:8080/drones                         |     Gets the list of all drones      |                                                                                                                      NA |
| `POST` http://localhost:8080/drones/{droneId}/load/{medId} |    Loads a drone with medication     | {"serialNumber": "SXOIDLKDLSKAD","model": "Cruiserweight", "weightLimit": 500, "batteryCapacity": 100, "state": "IDLE"} |
| `GET` http://localhost:8080/drones/{droneId}/medications   |  Get loaded medications for a drone  |                                                                                                                      NA |
| `GET` http://localhost:8080/drones/available               |      Gets the available drones       |                                                                                                                      NA |
| `GET` http://localhost:8080/battery-level/{droneId}        | Gets battery level for a given drone |                                                                                                                      NA |

## Git Commit History

- 04b71f1 (HEAD -> master) Add README.md file
- aaffa5f Add dispatch controller test
- 2f38d47 Add smoke test
- e3b1649 Complete dispatch controller
- f862edf Add method findByDrone()
- 16272a4 Finish off services
- 2fd2a67 Add JsonManagedRef and JsonBackRef
- 8714c5f Add BatteryLevel response POJO
- 8bdc242 Create BatteryLog Repo
- e081de1 Create Medication Repo
- 740d6e9 Create DroneLoad Repo
- b13b1cc Create Drone Repository
- 57a432b Add relationship to Drone
- 81a16dc Add DroneLoad Entity
- 50a1235 Add BatteryLog Entity
- 3a60927 Add seed data for medications
- 38ab41a Create Drone and Medicine entities
- 63cbb3d Upload medicines images
- 144c6bb Defer datasource init for db
- 7da0ba7 Add jpa properties for h2 database
- 19feeb7 Initial commit
