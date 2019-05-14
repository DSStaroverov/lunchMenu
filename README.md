[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d15c13e3ad064016887f199a0efc6c24)](https://www.codacy.com/app/DSStaroverov/lunchMenu?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DSStaroverov/lunchMenu&amp;utm_campaign=Badge_Grade)

## Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

#### The task is:

    Build a voting system for deciding where to have lunch.

    - 2 types of users: admin and regular users
    - Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
    - Menu changes each day (admins do the updates)
    - Users can vote on which restaurant they want to have lunch at
    - Only one vote counted per user
    - If user votes again the same day:
        If it is before 11:00 we asume that he changed his mind.
        If it is after 11:00 then it is too late, vote can't be changed
    - Each restaurant provides new menu each day.



## Example cURL commands:

### Votes:

##### Create vote for authorized user:
    curl --location --request POST "http://localhost:8080/lunchMenu/rest/vote/100008" \
    --header "Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=" \
    --header "Content-Type: application/json" \
    --data ""
  
##### Get all votes for day(without date get for today):
    curl --location --request GET "http://localhost:8080/lunchMenu/rest/vote/?date=2019-04-29" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu"


##### Get all authorized user votes:
    curl --location --request GET "http://localhost:8080/lunchMenu/rest/vote/user" \
    --header "Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ="

***

### Menus:

##### Create new menu(only admin):
    curl --location --request POST "http://localhost:8080/lunchMenu/rest/menu/" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu" \
    --header "Content-Type: application/json" \
    --data "{
        \"id\": null,
        \"restaurant\": 100005,
        \"createDate\": null,
        \"price\": 400,
        \"totalCalories\": 0
    }"

##### Update menu(only admin):
    curl --location --request PUT "http://localhost:8080/lunchMenu/rest/menu/100007" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu" \
    --header "Content-Type: application/json" \
    --data "{
        \"id\": 100007,
        \"restaurant\": 100005,
        \"createDate\": \"2019-04-29\",
        \"price\": 600,
        \"totalCalories\": 0
    }"

##### Delete menu(only admin):
    curl --location --request DELETE "http://localhost:8080/lunchMenu/rest/menu/100008" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu"

##### Get one menu:
    curl --location --request GET "http://localhost:8080/lunchMenu/rest/menu/100007" \
    --header "Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ="
  
##### Get all menu for day (without date get for today):
    curl --location --request GET "http://localhost:8080/lunchMenu/rest/menu/?date=2019-04-29" \
    --header "Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ="
  
##### Get list menu lunch items:
    curl --location --request GET "http://localhost:8080/lunchMenu/rest/menu/100007/items" \
    --header "Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ="
  
  
***  
### Restaurants(only admin):

##### Create new restaurant:
    curl --location --request POST "http://localhost:8080/lunchMenu/rest/restaurant" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu" \
    --header "Content-Type: application/json" \
    --data "{
        \"id\": null,
        \"name\": \"newRest\",
        \"address\": \"Pionerskay st. 33\"
    }"

##### Update restaurant:
    curl --location --request PUT "http://localhost:8080/lunchMenu/rest/restaurant/100005" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu" \
    --header "Content-Type: application/json" \
    --data "{
        \"id\": 100005,
        \"name\": \"VacabiNew\",
        \"address\": \"Pionerskay st. 25\"
    }"

##### Delete restaurant:
    curl --location --request DELETE "http://localhost:8080/lunchMenu/rest/restaurant/100006" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu"
  
##### Get one restaurant:
    curl --location --request GET "http://localhost:8080/lunchMenu/rest/restaurant/100005" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu"
  
##### Get restaurant menus:
    curl --location --request GET "http://localhost:8080/lunchMenu/rest/restaurant/100005/menus" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu"
  
##### Get all restaurant:
    curl --location --request GET "http://localhost:8080/lunchMenu/rest/restaurant" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu"
  
***  
### Lunch items(only admin):

##### Create new lunch item for menu:
    curl --location --request POST "http://localhost:8080/lunchMenu/rest/item/" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu" \
    --header "Content-Type: application/json" \
    --data "{
        \"id\": null,
        \"name\": \"new Item\",
        \"calories\": 330,
        \"menuId\": 100007
    }"

##### Update lunch item:
    curl --location --request PUT "http://localhost:8080/lunchMenu/rest/item/100009" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu" \
    --header "Content-Type: application/json" \
    --data "{
        \"id\": 100009,
        \"name\": \"Soap Vasabi Sunday Update\",
        \"calories\": 230,
        \"menuId\": 100007
    }"

##### Delete lunch item:
    curl --location --request DELETE "http://localhost:8080/lunchMenu/rest/item/100009" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu"
  
##### Get lunch item:
    curl --location --request GET "http://localhost:8080/lunchMenu/rest/item/100009" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu"
  
***
### Users:

##### Create new user(only anonymous):
    curl --location --request POST "http://localhost:8080/lunchMenu/rest/user/register" \
      --header "Content-Type: application/json" \
      --data "{
        \"name\": \"UserNew\",
        \"email\": \"usernew@yandex.ru\",
        \"password\": \"password\"
    }"

##### Update user:
    curl --location --request PUT "http://localhost:8080/lunchMenu/rest/user/100000" \
      --header "Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=" \
      --header "Content-Type: application/json" \
      --data "{
        \"id\": 100000,
        \"name\": \"UserUpdate\",
        \"email\": \"userupdate@yandex.ru\",
        \"password\": \"update\"
    }"

##### Delete user:
    curl --location --request DELETE "http://localhost:8080/lunchMenu/rest/user/100003" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu"
  
##### Get user:
    curl --location --request GET "http://localhost:8080/lunchMenu/rest/user/100000" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu"
  
##### Get all users:
    curl --location --request GET "http://localhost:8080/lunchMenu/rest/user/" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu"
  
##### Get user by e-mail:
    curl --location --request GET "http://localhost:8080/lunchMenu/rest/user/by?email=user@yandex.ru" \
    --header "Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu"
  
