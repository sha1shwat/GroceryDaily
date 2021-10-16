# GroceryDaily

## Description
Design & Build APIs that provide the capability to check and reserve orders that
we can serve on particular delivery date. An order can be served only if all items
(with requested quantities) can be served. In order to do this, there are a bunch
of criteria that we want to consider :</br>
- We want to have a check based on available items in the Warehouse : A</br>
customer order is served from a Warehouse (a place used to pack items to be</br>
sent to customer). Warehouse has items which are available for different</br>
delivery dates. If item(s) are not available, we cannot take the order. Items</br>
start to become unavailable as and when we reserve orders.</br>
Eg. Warehouse has Item i1 :</br>
100 units for 15 Oct 20</br>
200 unit for 16 Oct 20</br>
If we have reserved 99 units of i1 for orders on 15 Oct 20, we can take only</br>
order for 1 quantity more for i1.</br>
- We also want to have a check based on item category : Every Item belongs to</br>
a Category. Eg. All fruits, vegetable items belong to a category called "F_N_V".</br>
We want to be able to throttle maximum items that can be shipped for a</br>
given category on a given delivery date. This is irrespective of the</br>
Warehouse that serves the customer.</br>
Eg.</br>
Category "F_N_V":</br>
150 units on 15 Oct 20</br>
200 units on 16 Oct 20</br>
Combined orders for a date should not be more than 150 units</br>
We can fulfil the order only if all the criteria are met.</br>

**Note: For implementing each criterion, please assume all relevant data/configs to be
present and define relevant data structures for them. The data can
be assumed to be static**

### - API 1: Check If We can Fulfil An Order

Input:
```
 {
    "customer_id": 10001,
    "delivery_date": "2020-10-13",
    "warehouse_id": 100,
    "items": [
        {
            "item_id": 1,
            "item_name": "Washington Apple (1 pc)",
            "category": "F_N_V",
            "quantity": 3
        },
        {
            "item_id": 2,
            "item_name": "Banana (0.5kg)",
            "category": "F_N_V",
            "quantity": 1
        },
        {
            "item_id": 3,
            "item_name": "Parle-G Biscuit (200g)",
            "category": "Grocery",
            "quantity": 1
        }
    ]
}
```
Output:
```
{
    "can_fulfil": true
}
```

### - API 2: Reserve An Order

Input:
```
 {
    "customer_id": 10001,
    "delivery_date": "2020-10-13",
    "warehouse_id": 100,
    "items": [
        {
            "item_id": 1,
            "item_name": "Washington Apple (1 pc)",
            "category": "F_N_V",
            "quantity": 3
        },
        {
            "item_id": 2,
            "item_name": "Banana (0.5kg)",
            "category": "F_N_V",
            "quantity": 1
        },
        {
            "item_id": 3,
            "item_name": "Parle-G Biscuit (200g)",
            "category": "Grocery",
            "quantity": 1
        }
    ]
}
```

Output:
```
{
    "code": "Success",
    "data": {
        "reserved": true,
        "message": "Success"
    }
}
```
In case of any error:
```
{
    "code": "Success",
    "data": {
        "reserved": false,
        "message": "Insufficient quantities!"
    }
}
```
