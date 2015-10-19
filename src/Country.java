/**
 * Created by jessicahuffstutler on 10/15/15.
 */
public class Country { //method
    String name;
    String abbrev;

    public Country() {

    }

    public Country(String name, String abbrev) { //constructor
        this.name = name;
        this.abbrev = abbrev;
    }

    public String getName() { //getters
        return name;
    }

    public String getAbbrev() { //getters
        return abbrev;
    }
}
