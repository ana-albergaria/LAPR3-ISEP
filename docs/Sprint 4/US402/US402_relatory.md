# RELATORY

# US 402 - Know the shortest path between two locals (city and/or port)

## 1. Requirements Engineering

### 1.1. User Story Description

As a Traffic manager I wish to know the shortest path between two locals (city and/or port).

### 1.2. Acceptance Criteria

* Land path (only includes land routes, may start/end in port/city).
* Maritime path (only includes ports).
* Land or sea path (may include cities and ports).
* Obligatorily passing through n indicated places.

### 1.3. Found out Dependencies

This US has dependencies with USs.....................

### 1.4 Input and Output Data

**Input Data:**

* Typed data:
    * local 1 (city or port)'s lat and lon
    * local 2 (city or port)'s lat and lon
    * desired path type (land path, maritime path, or land or sea path)

* Selected data:
    * n/a


**Output Data:**

* the shortest path between the two desired locals
* the path's total distance


### 1.5. System Sequence Diagram (SSD)

![US_402_SSD](./docs/Sprint 4/US402/US_402_SSD.svg)


### 1.6 Other Relevant Remarks

The present US is held many times during the business. As the Traffic Manager manages the traffic, he will need to know the shortest path for the trips, in order to make them more efficient, in terms of economy and speed.


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US_402_DM](./docs/Sprint 4/US402/US_402_DM.svg)


### 2.2. Other Remarks

n/a



## 3. Design - User Story Realization

## 3.1. Sequence Diagram (SD)

![US_402_SD](./docs/Sprint 4/US402/US_402_SD.svg)


## 3.2. Class Diagram (CD)

![US_402_CD](./docs/Sprint 4/US402/US_402_CD.svg)


# 4. Tests

### ShortestPathController class test:


**Test 1:** Test to check that ...

**Test 2:** Test to check that ...

**Test 3:** Test to check that ...