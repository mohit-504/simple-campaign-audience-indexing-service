# Campaign Audience Indexing Service

An in-memory audience indexing engine built in Java to simulate how marketing and advertising platforms segment users for campaigns.

The project demonstrates efficient audience retrieval using Java Collections Framework, concurrent ingestion using ConcurrentHashMap, analytics generation, sorted audience discovery, and query-based audience filtering.

---

## Features

### Audience Indexing

Store users in multiple audience segments:

- Premium Users
- City-based Users
- Device-based Users

Supported lookups:

- Get all premium users
- Get users by city
- Get users by device type
- Get premium users by city
- Get users by city and device
- Get premium users by city and device

---

### Audience Analytics

Generate analytics while users are being indexed:

- Total user count
- Premium user count
- Premium user percentage
- City-wise user distribution
- Device-wise user distribution

---

### Concurrent User Ingestion

Supports multi-threaded indexing using:

- ConcurrentHashMap
- ConcurrentHashMap.newKeySet()

Benchmark comparison:

- Single-threaded ingestion
- Multi-threaded ingestion

---

### Sorted Audience Discovery

Supports ordered audience exploration using TreeSet:

- Get first user
- Get last user
- Get users within a range
- Get first N users

---

### Query Engine

Supports simple audience queries:

Examples:

```text
premium

city=Mumbai

device=ANDROID

premium AND city=Mumbai

premium AND city=Mumbai AND device=ANDROID
```

Query building uses:

- StringBuilder
- String manipulation
- Set intersection

---


## Collections Used

| Collection | Usage |
|------------|--------|
| HashMap | User storage and audience indexes |
| HashSet | Audience membership tracking |
| TreeMap | Analytics reporting by city |
| TreeSet | Sorted audience discovery |
| ConcurrentHashMap | Thread-safe indexing |
| ArrayList | Worker thread management |
| EnumMap | Device analytics |

---

## Time Complexity

### Indexing

| Operation | Complexity |
|------------|------------|
| Add User | O(1) average |
| User Lookup | O(1) average |
| City Lookup | O(1) average |
| Device Lookup | O(1) average |

### Segment Intersections

| Operation | Complexity |
|------------|------------|
| Premium AND City | O(min(n,m)) |
| City AND Device | O(min(n,m)) |
| Premium AND City AND Device | O(min(n,m,k)) |

### Sorted Discovery

| Operation | Complexity |
|------------|------------|
| Add User | O(log n) |
| First User | O(log n) |
| Last User | O(log n) |
| Range Query | O(log n + k) |

---

## Scalability Considerations

### O(1) Lookups

Audience retrieval is optimized using HashMap and HashSet indexes instead of scanning all users.

Without indexing:

```text
O(n)
```

With indexing:

```text
O(1)
```

average lookup time.

---

## Large Dataset Simulation

The application simulates:

```text
100,000 users
```

with:

- Random cities
- Random devices
- Random premium status

to benchmark indexing performance.

---

## Sample Query

```java
AudienceQueryService queryService =
        new AudienceQueryService(index);

Set<Long> users =
        queryService.execute(
            "premium AND city=Mumbai AND device=ANDROID"
        );
```

---

## Learning Objectives

This project demonstrates:

- Java Collections Framework
- In-memory indexing
- Audience segmentation
- Set intersections
- ConcurrentHashMap
- TreeMap and TreeSet
- StringBuilder
- Query parsing
- Multi-threading basics
- Benchmarking and scalability considerations

---

## Resume Impact

Designed and implemented an in-memory audience indexing engine supporting efficient user segmentation across 100k+ users using HashMap, HashSet, TreeMap, TreeSet, ConcurrentHashMap, and query-based audience filtering. Added concurrent ingestion, analytics reporting, and sorted audience discovery to optimize audience retrieval and campaign targeting workflows.