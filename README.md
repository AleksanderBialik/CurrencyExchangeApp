This is an app for creating foreign exchange account(USD and PLN)
App uses official NBP API (http://api.nbp.pl/) for exchange calculations
App uses in-memory H2 Database

####ENDPOINTS#####

POST /api/account - creates an account
Example Body:
{
   "firstName":"Aleksander",
   "lastName":"Bialik",
   "plnAccount": 100
}

GET /api/account/{id} - returns an account with given id

PUT /api/account/{id}/pln-to-usd - exchanges the PLN to USD
Example Body:
{
  "amount": 30.1234
}

PUT /api/account/{id}/usd-to-pln - exchanges the USD to PLN
Example Body:
{
  "amount": 30.1234
}
