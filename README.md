# Wortal

`Wortal` is a project of a web application accessible via a web browser that provides the platform for organizing sport meetings or events such as sparring games, football matches or training sessions. The main purpose of the application is to facilitate the process of arranging a meeting, allowing users to effectively select a group of players and an optimal place and time for the game. The website is available for amateur football teams searching for opponents and for individuals seeking to join a game for recreation. The key element of the application is creating a sport meeting in a way that is optimal for all participants. 
<br><br>

Welcome screen:
---

![welcome](screens/welcome_screen.png)
<br><br>

# Running the App

- download the source code from `Github` repository,

- In the root folder of the backend application -> `/Wortal/service`

run:

```bash
$ mvn package -DskipTests
```

- In order to create and run docker containers containing `database`, `Spring Boot` backend app and `Vue.js` frontend app, run following command in the root directory of the project (`/Wortal`):

```bash
$ docker compose up
```
Note: `maven`, `docker` and `docker compose` must be installed and the `docker daemon` must be running.
<br><br>

`docker-compose` and `docker containers` workflow chart:

![flowchart](wortal_containers_flowchart.png)
<br><br>

# ER Diagrams

## Conceptual ER diagram:

![ERD conceptual](wortal_conceptual_ERD.png)

## Physical ER diagram:

![ERD physical](wortal_physical_ERD.png)
<br><br>

# Interface & Features

## sign in & sign up

Authentication & Authorization was implemented with a use of `Spring Security` and `JWT`.

`JWT` authentication and authorization flow chart:

![jwt](jwt_authentication_flow.png)

sign in screen:
---
![signin](screens/signin_screen.png)

sign up screen:
---
![singup](screens/signup_screen.png)
<br><br>

# Events - main views & features

Events Overview screen:
---
![events_overview](screens/events_overview.png)

Event Details screen:
---
![event_details](screens/event_details.png)

Create Event screen:
---
![create_event](screens/create_event.png)

Create Event screen - adding participants:
---
![add_participants](screens/create_event_add_participants.png)
<br><br>
# Teams - main views & features

Create Team screen:
---
![create_team](screens/create_team.png)

Teams Overview screen:
---
![teams_overview](screens/teams_overview.png)

Team Details screen:
---
![team_details](screens/team_details.png)
<br><br>

## User Profile

![user_profile](screens/user_profile_screen.png)
<br><br>

## Administrator panel

![administrator_panel](screens/administrator_panel.png)
