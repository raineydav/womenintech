//https://github.com/BorderCloud/SPARQL-JAVA
import com.bordercloud.sparql.Endpoint;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        try {
            Endpoint sp = new Endpoint("https://query.wikidata.org/sparql", false);

            String querySelect = "#Female artists\n" +
                "#added before 2016-10\n" +
                "\n" +
                "SELECT DISTINCT ?women ?womenLabel ?image ?birthDate ?deathDate  ?citizenshipLabel ?coords ?countryOfBirth \n" +
                "WHERE\n" +
                "{\n" +
                "       ?women wdt:P31 wd:Q5 .\n" +
                "       ?women wdt:P21 wd:Q6581072 .\n" +
                "       ?women wdt:P106/wdt:P279* wd:Q82594 . # computer scientist\n" +
                "  \n" +
                "     OPTIONAL{\n" +
                "        ?women wdt:P18 ?image .\n" +
                "        ?women wdt:P569 ?birthDate.\n" +
                "        ?women wdt:P570 ?deathDate.\n" +
                "        ?women wdt:P27   ?citizenship .\n" +
                "        ?women wdt:P19 ?birthPlace .\n" +
                "        ?birthPlace wdt:P625 ?coords .\n" +
                "         ?birthPlace wdt:P17 ?countryOfBirth . \n" +
                "      \n" +
                "     }\n" +
                "\n" +
                "       SERVICE wikibase:label {bd:serviceParam wikibase:language \"en\" }\n" +
                "}\n" +
                "\n" +
                "# LIMIT 500";

            HashMap rs = sp.query(querySelect);
            printResult(rs,30);

        }catch(EndpointException eex) {
            System.out.println(eex);
            eex.printStackTrace();
        }
    }

    public static void printResult(HashMap rs , int size) {

      for (String variable : (ArrayList) rs.get("result").get("variables")) {
        System.out.print(String.format("%-"+size+"."+size+"s", variable ) + " | ");
      }
      System.out.print("\n");
      for (HashMap value : (ArrayList>) rs.get("result").get("rows")) {
        //System.out.print(value);
        /* for (String key : value.keySet()) {
         System.out.println(value.get(key));
         }*/
        for (String variable : (ArrayList) rs.get("result").get("variables")) {
          //System.out.println(value.get(variable));
          System.out.print(String.format("%-"+size+"."+size+"s", value.get(variable)) + " | ");
        }
        System.out.print("\n");
      }
    }
}
