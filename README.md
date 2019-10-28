# FakeReddit

Contributors: David Hernandez & Benjamin Karasik

FakeReddit functions as a backend for a reddit-like application called DC-IT that allows users to create, delete, update and see account information, posts, and comments. Utilizing Spring REST as the backbone Framework for this Java application, users could complete CRUD operations on various aspects for FakeReddit.

The application structure is layered in a Spring format with entity, controller, service, and dao layers.

The database is local per machine and utilizes Postgres for storing tables; hibernate completes the transactions happening from user to database and vice-versa

Postman was used to test endpoints and application accuracy.

Mockito was used for JUNIT testing.

# Approaches Taken:

## 1. Pair-Programming, Agile methodologies, and Extreme Programming Approaches
    - Constant, daily meetings between developers in person and over Slack
    - In person, pair programming achieved during 90% of development
    - Seen during user Story creation, monolith structure design, implementing app layers
    - Pair programming also achieved when developers had questions about partner code and explanation was required
    - Extreme Programming achieved with daily stand-ups, basic endpoint acceptance tests, pair programming, and pair                negotiation
    - Agile methodlogies achieved with over 95 commits on Github and development of multiple, small aspects of MVP daily
    - Test Driven Development
    
    
## 2. Development Approaches
    - Created a Github repository
    - Worked on Master and seperate branches 
    - Used Spring REST structure
    - Created seperate folder structure for entity, controller, service, and dao packages
    - Loose dependency and dependency injection used within Spring
    - Mix of in person pair programming where one person codes and the other guides, as well as independent programming
    - Each coder led development for their own pages while the other would provide notes, feedback, help if needed  
    - Both developers contribute to code evaluation and JUNIT testing, accounting for over 70% of test coverage
   


 # General Approach
                                                                                                                              
We held an initial, sprint planning meeting in which we discussed the needed deliverables for our application. We discussed best practices as well as our own expectations while pair programing.
With those deliverables in mind we went to Pivotal Tracker and developed epics and user stories. As we agreed that we would pair program (using one computer at a time – both contributing to every line of code) we were both owners (responsible) for the tasks assigned to each user story. We discussed and agreed on a general timeline of task completion and began to work. Starting first with our ERD diagram (linked below) and by setting up our Spring project.


# Technologies used

1. Git – local repository
2. Github – remote repository
3. Java
4. Spring REST
5. Maven
5. Hibernate
6. PostgresSQL
7. Mockito - JUnit
8. Draw.io - ERD diagram
[google drive] (https://drive.google.com/file/d/12HwpUj9DMQfros2TJW2yWE1vU4oTVTQv/view?usp=sharing)
9. Pivotal Tracker – user stories
[Pivotal Tracker] (https://www.pivotaltracker.com/n/projects/2407543)
10. Postman – test / verify API calls
11. Eclipse / IntelliJ



# Challenges / Areas of Opportunity 

1. Applying the new, backend api calls to already created fornt end DC-IT application
2. Using Mockito to unit test each layer (Entity, Controller, Service, Dao)
3. Code refactoring needs to be done
4. Could add exception handling


                     
# Successes / What Went Well 

1. Open and Honest Communication
2. Pair Programing
3. Well layered application without much dependancy unless required
4. Over 70% test coverage on entire application (which includes config package)


# Project Structure
https://docs.google.com/document/d/1w3EYUc0I9atXEO6oQ2ce6moj8QcO1YIqotwOsdai-_A/edit?usp=sharing

# Project Timeline

Monday: Create entities, file struture, github repository, ERD, user stories
Tuesday: Security, service, dao
Wednesday: Debugging, Response entity added, testing with Postman
Thursday: Connect application to working front end with api calls
Friday-Sunday: Testing, completing ReadMe

# Methodologies

Agile Development and Extreme Programming

- Pair Programming

- Fast Iterations

- Continuous Integration

- Test Driven Development

# Principles

- KISS (keep it simple, stupid)

- DRY (don't repeat yourself) 
