# TicTacToeUltimate

A java application which uses following backend and frontend web frameworks:
  * Spring Boot
  * Hibernate
  * AngularJS 2
  * Spring Security
  
Database used to store users' and games' information is MySQL, but there are also configurations 
in mMaven's pom.xml file for PostgreSQL and HSQLDB systems.

The application uses REST to communicate between backend and frontend layers and basic authentication to secure requests.

The users can register and their information is stored in a MySQL Database. 

There are two roles:
* Admin
* User

on of which can be applied to a user. The default role is `User`, which can be only changed by a user with `Admin` role.

Registered users can start and join games. 
As for this version the game users can play is a custom implementation of Tic tac toe game, 
but the plan is to add more playable games in the future. 

The user can view it's game history and moves registered by the application as well as the account's information by
clicking at it's nickname in upper right corner of the page.

Users with `Admin` role can delete and  ~~update~~ other users via Admin tab in the application.
