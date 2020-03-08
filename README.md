Short description

Bankapp is a Maven based Java application for processing incoming bank transactions and creating corresponding reports.

To be in compliance with the requirements, the application uses just one dependency: com.fasterxml.jackson.core 
(if more dependencies were allowed, it could have been a Spring application).
JSON format was chosen to simulate incoming REST calls.
For demonstration purposes there are 20 transactions in the incoming JSON source (the app prints a report after each 10-th transaction).