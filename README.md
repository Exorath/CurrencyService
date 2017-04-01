# CurrencyService
A mongo backed service that provides our virtual currencies.

##Endpoints

###/currencies/{currency}/players/{uuid} [GET]:
####Gets currency information of this player

**Arguments**:
- currency (string): The currency which you wish to retrieve from the player
- uuid (string): The player to retrieve currency information of

**Response**: 
```json
{"balance": 123}
```

###/currencies/{currency}/players/{uuid}/inc?amount=10 [POST]:
####Increments the currency, for now this is an unsafe increment (no transaction ID to ensure that it didn't occur twice)

**Arguments**:
- currency (string): The currency which you wish to retrieve from the player
- uuid (string): The player to retrieve currency information of
- amount (int): The amount of currency to increment
- min (int) [OPTIONAL]: The minimum currnecy this player should have (can be negative)

**Response**: 

When false an error and code will be provided, the code indicates the error:
-1 = unknown error
1 = insufficient funds

```json
{"success": false, "error": "insufficient funds", "code": 1}
```

```json
{"success": true, "newBalance": 110}
```
