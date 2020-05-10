***
Library
---

**The task target:**

Use Spring JDBC and spring-boot-starter-jdbc to connect to relational databases. 


**Task description:**

1. Use Spring JDBC and a relational database (Postgresql). Strongly recommended using NamedParametersJdbcTemplate
2. Create tables of authors, books and genres.
3. One-to-many relationships are assumed (the book has one author, but the author may have several books, the same applies to books and genres). An optional complication is a many-to-many relationship.
4. The interface is run on Spring Shell (CRUD for books are required, operations with authors and journals - as it will be convenient).
5. The script for creating tables and the script for filling data should be automatically launched using spring-boot-starter-jdbc.
6. Cover with tests as much as possible.

**Recommendations for the implementation of the work:**
1. DO NOT do AbstractDao.
2. DO NOT do inheritance in tests