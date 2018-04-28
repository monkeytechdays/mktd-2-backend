# API Rest
  
GET  /api/quizz
---

query param: userName

```json
{
    "id": "27549fb4-98a6-42e8-8f1a-c7a9eb4d17bc",
    "questions": [
        {
            "responses": [
                "Titi roux ",
                "Douc",
                "Atèle",
                "Tamarin aux mains rousses"
            ]
        },
        ...
    ],
    "userName": "Igor"
}
```


GET  /api/quizz/:quizzId/:index
---

curl -XGET 'http://localhost:8080/api/quizz/27549fb4-98a6-42e8-8f1a-c7a9eb4d17bc/0'

```json
{
    "attribution": "By Tony Hisgett (Flickr: Red Titi Monkey 1) [CC BY 2.0 (http://creativecommons.org/licenses/by/2.0)], via Wikimedia Commons",
    "url": "https://upload.wikimedia.org/wikipedia/commons/6/6d/Coppery_Titi_1.jpg"
}
```

POST /api/quizz/:quizzId
---

Request
```json
{
    "responses": ["Atèle",  "Orang Outan", ...]
}
```


Response
```json
{
    "userName" : "Igor",
    "score": 42,
    "duration": 213
}
```

GET  /api/leaderboard
---

```json
[
    { "userName" : "Igor", "score": 42, "duration": 213 },
    ...
]
```