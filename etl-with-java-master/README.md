# ETL with Java Spring Boot

Simple repository showcasing ETL jobs using Java Spring Boot 

## Goal

Read these two CSV files :

- `models.csv`

```
ProductCode;Name;UnitOfMeasure;Brand;DetailCategory
1;MaxiCookie;kg;A COOKIE COMPANY;Cakes|Snacks|Cookies
2;SodaCool;L;A SODA COMPANY;Soft drinks|Beverages
```

- `compositions.csv`

```
ProductCode;Name;IngredientCode;IngredientName;Quantity;UnitOfMeasure
1;MaxiCookie;34;Sugar;100;g/kg
1;MaxiCookie;3;Chocolate;33;chunks/unit
1;MaxiCookie;1;Butter;200;g/kg
2;SodaCool;34;Sucre;500;g/l
2;SodaCool;34;Water;1;%/l
```

Transform the incoming data and load this kind of object into Mongo :

```json
{
    "_id" : ObjectId("5d5fe20ec4bcd7724ceb140b"),
    "brand" : "A COOKIE COMPANY",
    "categories" : [ 
        "Cakes", 
        "Snacks", 
        "Cookies"
    ],
    "code" : 1,
    "compositions" : [ 
        {
            "measureUnit" : "g/kg",
            "name" : "Sugar",
            "quantity" : 100.0
        }, 
        {
            "measureUnit" : "chunks/unit",
            "name" : "Chocolate",
            "quantity" : 33.0
        }, 
        {
            "measureUnit" : "g/kg",
            "name" : "Butter",
            "quantity" : 200.0
        }
    ],
    "measureUnit" : "kg",
    "name" : "MaxiCookie"
}
```