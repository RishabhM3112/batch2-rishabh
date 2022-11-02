# Spring Boot Application Task 1 (```User - Book - Category```)

This is the Task-1 project Spring Boot (version 2.7.5) application using Java (version 1.8)/ Maven / PostgreSQL.

## About

This application is based on the entities ```User - Book - Category``` . 

## About the Entity Relation
 
* User to Book ```many-to-many```
* Category to Book ```one-to-many```
* Book to category ```many-to-one```

Here are some endpoints you can call:

### Create a User
```
POST http://localhost:8080/app/user/create
Content-Type: raw/json
{
    "name": "Rishabh"
}
```
### Get a User
```
GET http://localhost:8080/app/user/1
```
### Get all Users
```
GET http://localhost:8080/app/user/all
```


### Create a Book
```
POST http://localhost:8080/app/book/create
Content-Type: raw/json
{
            "name": "Jurrasic Park",
            "category" : {
                "name" : "Wild"
            }
}
```
### Get a Book
```
GET http://localhost:8080/app/book/1
```
### Get all Books
```
GET http://localhost:8080/app/book/all
```
### Delete a Book
```
DELETE http://localhost:8080/app/book/5
```
### Update a Book

```
PUT http://localhost:8080/app/book/1
Content-Type: raw/json
{
    "name": "Wolf of Wall Street",
    "category": {
        "name": "Finance"
    }
}
```


### Add a Book new to User
```
POST http://localhost:8080/app/user/1/book
Content-Type: raw/json
{
            "name": "Jurrasic Park",
            "category" : {
                "name" : "Wild"
            }
}
```
### Add a existing Book to User
```
GET http://localhost:8080/app/user/1/book/1
```
### Delete a Book from User
```
DELETE http://localhost:8080/app/user/1/book/1
```

### Get all the categories
```
GET http://localhost:8080/app/category/all
```
### Get all the categories which have books
```
GET http://localhost:8080/app/category/all/present
```
### Get all the categories which does not have books
```
GET http://localhost:8080/app/category/all/notp
```
### Delete all the categories with no books
```
DELETE http://localhost:8080/app/category/delete/allnp
```


