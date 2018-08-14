const endpointUrl = 'https://query.wikidata.org/sparql',
      sparqlQuery = `#Computer Scientists

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

# LIMIT 500`,
      fullUrl = endpointUrl + '?query=' + encodeURIComponent( sparqlQuery ),
      headers = { 'Accept': 'application/sparql-results+json' };

fetch( fullUrl, { headers } ).then( body => body.json() ).then( json => {
    const { head: { vars }, results } = json;
    for ( const result of results.bindings ) {
        for ( const variable of vars ) {
            console.log( '%s: %o', variable, result[variable] );
        }
        console.log( '---' );
    }
} );
