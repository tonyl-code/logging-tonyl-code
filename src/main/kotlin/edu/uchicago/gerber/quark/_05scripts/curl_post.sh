curl -X POST localhost:8080/beers/ \
   -H 'Content-Type: application/json' \
   -d '{"name":"TestName", "hop":"TestHop", "malt":"TestMalt", "style":"TestStyle",
   "yeast":"TestYeast"}'