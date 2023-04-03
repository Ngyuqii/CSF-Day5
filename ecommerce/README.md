### Client
- Create a online shopping cart that takes in buyer's order. Fields required:
    1. Name
    2. Email
    3. Shopping list of items and quantity
- Post the Json string to server (http://localhost:8080/order) upon order placed
- A response of "Order id {orderId} is placed." to be received upon successful HTTP POST.

### Server
- Receive Json string from client and save the document into Mongo.
```
{
    "name" : "Tim",
    "email" : "tim@gmail.com",
    "lineItems" : [
        {
            "item" : "Apple",
            "quantity" : NumberInt(5)
        },
        {
            "item" : "Orange",
            "quantity" : NumberInt(2)
        },
        {
            "item" : "Carrot",
            "quantity" : NumberInt(6)
        }
    ]
}
```
- Return orderId to client.
```
"orderId" : "58fcb361".
```