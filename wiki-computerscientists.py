#pip install sparqlwrapper
#https://rdflib.github.io/sparqlwrapper/

from SPARQLWrapper import SPARQLWrapper, JSON
sparql = SPARQLWrapper("https://query.wikidata.org/sparql")
sparql.setQuery("""#Female artists
#added before 2016-10

SELECT DISTINCT ?women ?womenLabel ?image ?birthDate ?deathDate  ?citizenshipLabel ?coords ?countryOfBirth 
WHERE
{
       ?women wdt:P31 wd:Q5 .
       ?women wdt:P21 wd:Q6581072 .
       ?women wdt:P106/wdt:P279* wd:Q82594 . # computer scientist
  
     OPTIONAL{
        ?women wdt:P18 ?image .
        ?women wdt:P569 ?birthDate.
        ?women wdt:P570 ?deathDate.
        ?women wdt:P27   ?citizenship .
        ?women wdt:P19 ?birthPlace .
        ?birthPlace wdt:P625 ?coords .
         ?birthPlace wdt:P17 ?countryOfBirth . 
      
     }

       SERVICE wikibase:label {bd:serviceParam wikibase:language "en" }
}

# LIMIT 500""")
sparql.setReturnFormat(JSON)
results = sparql.query().convert()

for result in results["results"]["bindings"]:
    print(result)
