# flightAssessment
my task is to implement application with two functionalities : 
1. For requested Flight Number and date will respond with following :
 a. Cargo Weight for requested Flight
 b. Baggage Weight for requested Flight
 c. Total Weight for requested Flight
   
  For requested IATA Airport Code and date will respond with following :
   a. Number of flights departing from this airport,
   b. Number of flights arriving to this airport,
   c. Total number (pieces) of baggage arriving to this airport, d. Total number (pieces) of baggage departing from this airport. 
I created two GetMapping request for geting response and two PostMapping for saving generated test data to database.
I used Java version 11 ,Spring boot framework version 2.7.5 and h2 database . I also used Mockito and Junit 4 for unit testing .
for generating test data I used  https://www.json-generator.com/ 
