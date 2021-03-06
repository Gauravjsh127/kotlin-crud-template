# Rest APIs

Build a Restful CRUD API using Kotlin, Spring Boot, JPA and Hibernate.
Reference from tutorial : https://www.callicoder.com/kotlin-spring-boot-mysql-jpa-hibernate-rest-api-tutorial

Database : Postgres

## Requirements

1. Java - 11.x +

2. Maven - 3.x.x +

3. Docker and docker-compose

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/Gauravjsh127/kotlin-crud-template.git
```

**2. Create database: docker way**
```
docker-compose up

```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your database installation(docker compose file)

**4. Running the App**

Type the following command in your terminal to run the app -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/articles
    
    POST /api/articles
    
    GET /api/articles/{id}
    
    PUT /api/articles/{id}
    
    DELETE /api/articles/{id}

You can test them using postman or any other rest client.
