# Spring Boot Rest Demo

CRUD operations on 'Notes' object containing id, creation date, description
* Requires a mysql database running on localhost

Test with


```
$ curl localhost:8080/notes | python -mjson.tool
[
    {
        "creationDate": "2020-09-06T00:00:00",
        "description": "First test note",
        "id": 1
    },
    {
        "creationDate": "2020-09-06T00:00:00",
        "description": "Next test note",
        "id": 2
    }
]
```

```
curl -X POST localhost:8080/notes -d '{"description":"Note"}' -H "Content-type: application/json" | python -mjson.tool
{
    "creationDate": "2020-09-06T12:32:53.945",
    "description": "Note",
    "id": 3
}
```

etc
