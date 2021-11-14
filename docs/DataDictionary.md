# Data Dictionary

>**A Data Dictionary is a collection of names, definitions, and attributes about data elements that are being used or captured in a database, information system, or part of a research project.**

##Ship
| **_Field Name_**  | **_Data Type_** | **_Data Format_** | **_Field Size_** | **_Description_** | **_Example_** |
|:------------------|:----------------|:------------------|:-----------------|:------------------|:--------------|
| **mmsi**  | Int | NNNNNNNNN | 9 | Unique MMSI Code for all ships | 211331640 |
| **imo**  | Text | IMONNNNNNN | 10 | Unique IMO Code for all ships | IMO9193305 |
| **calSignID**  | Text |  |  | Unique Call Sign for all ships | DHBN |
| **shipName**  | Text |  |  | Name for Ship | SEOUL EXPRESS |
| **vesselTypeID**  | Int |  |  | ID for Ship's Vessel Type | 70 |
| **maxCapacity**  | Text |  |  | Ship's Cargo | 79 |
| **draft**  | Double |  |  | Ship's Draft | 13.6 |
| **length**  | Int |  |  | Ship's Length | 294 |
| **width**  | Int |  |  | Ship's Width | 32 |

###Restrictions

**mmsi:** Integer field as primary key of the table and it's not nullable.

**imo:**

**callSignID:**

**shipName:**

**vesselTypeID:** Integer field, cannot be null, is a primary key of the table.

**maxCapacity:** Numeric field with 2 decimal places and at max 6 digits(counting decimals).

**draft:**

**length:** Numeric field with 2 decimal places and at max 5 digits(counting decimals), not nullable.

**width:** Numeric field with 2 decimal places and at max 5 digits(counting decimals), not nullable.


##Position
| **_Field Name_**  | **_Data Type_** | **_Data Format_** | **_Field Size_** | **_Description_** | **_Example_** |
|:------------------|:----------------|:------------------|:-----------------|:------------------|:--------------|
| **baseDateTime**  | Date / Time | DD/MM/YYYY HH:MM | 16 | Date for Ship's Position | 31/12/2020 01:25 |
| **latitude**  | Double |  |  | Latitude for Ship's Position | 36.39094 |
| **longitude**  | Double |  |  | Longitude for Ship's Position | -122.71335 |
| **sog**  | Double |  |  | SOG for Ship's Position | 19.7 |
| **cog**  | Double |  |  | COG for Ship's Position | 145.5 |
| **heading**  | Int |  |  | Heading for Ship's Position | 147 |
| **transceiver**  | Text |  |  | Transceiver Class for Ship's Position | B |
| **country_name** | Text |  |  | Name for Country | Cyprus |
| **continent** | Text |  |  | Continent for Country | Europe |

###Restrictions

**baseDateTime:**

**latitude:** Is a numeric field with 4 decimal places in the range of [-90,91] or 91 (unavailable).

**longitude:** Is a numeric field with 4 decimal places in the range of [-180,180] or 181 (unavailable).

**sog:**

**cog:**

**heading:**

**transceiver:**


##Country
| **_Field Name_**  | **_Data Type_** | **_Data Format_** | **_Field Size_** | **_Description_** | **_Example_** |
|:------------------|:----------------|:------------------|:-----------------|:------------------|:--------------|
| **country_name** | Text |  |  | Name for Country | Cyprus |
| **continent** | Text |  |  | Continent for Country | Europe |

###Restrictions

**country:** Not nullable and it references to the country_name field in the country table.

**country_name:** Cannot be null, is primary key, has to be a string with maximum of 70 chars.

**continent:** Cannot be null and it's a string with 30 chars at maximum.