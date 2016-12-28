# Microblog Continued

![screenshot](screenshot.png)

## Description

Use Spring Data to store the microblog messages in PostgreSQL. Add editing functionality as well.

## Requirements

* In PostgreSQL, create a database for your project called `microblog`
* Add the necessary lines to `application.properties`
* Modify the `Message` class to mark it as a Spring Data entity and make it store an `int id` with the proper annotations so it is treated as an id and is automatically generated
* Create an interface that extends `JpaRepository<Message, Integer>`
* In your controller, add the repository with `@Autowired` and use it instead of the `ArrayList<Message>`
* Add a way to edit messages and use the repository to update the object

## Hard Mode
* On the main page make it display all messages in reverse chronological order
* Add a User model and make it an entity in the database (remember to set the table name to `users` or you will have strangeness).
* Add a UserRepository with any methods you might need
* Make the messages be a ManyToOne with user
* Add a page that takes a user's id and displays all messages by that user only
	* Create the needed method in the repository
	* Page should take the user id in the query parameters
* On the main page add the user each message belongs to with a link to the page created in the previous step


## Resources
* [Github Repo](https://github.com/tiy-lv-java-2016-11/spring-microblog-continued)
* [Spring Guide for JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Spring JPA Query Guide](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)
