package problems;

import java.io.*;
import java.util.*;

public class OptiverRegistry {


// scope: class Solution
// this seems to be the problematic part of the code

    class Person {
        private String Name = "Unknown";
        private Map<String, String> Traits = new TreeMap<>();

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Person{");
            sb.append("Name='").append(Name).append('\'');
            sb.append(", Traits=").append(Traits);
            sb.append('}');
            return sb.toString();
        }
    }

    ;

    static Person ConvertRawInputToPerson(String rawInput) {
        Person person = new OptiverRegistry().new Person();

        String[] traits = rawInput.split(" ");
        for (String trait : traits) {
            String[] keyValuePair = trait.split("=");

            if (keyValuePair.length > 2) {
                throw new RuntimeException("Invalid Input");
            }

            String key = keyValuePair[0];
            String value = keyValuePair[1];

            if (key.equals("Name")) {
                person.Name = value;
            } else {
                person.Traits.put(key, value);
            }
        }
        return person;
    }

    static boolean StringEquals(String left, String right) {
        return left.equals(right);
    }

    static boolean HasTrait(Map<String, String> traits, Map.Entry<String, String> soughtTrait) {
        for (Map.Entry<String, String> trait : traits.entrySet()) {
            if (StringEquals(trait.getKey(), soughtTrait.getKey())
                && StringEquals(trait.getValue(), soughtTrait.getValue())) {
                return true;
            }
        }
        return false;
    }

    class Registry {
        List<Person> mPersons = new ArrayList<Person>();

        public void add(Person person) {
            mPersons.add(person);
        }

        public Person findMatchingPersonByRawTraits(String rawTraits) {
            Person soughtPerson = OptiverRegistry.ConvertRawInputToPerson(rawTraits);

            for (Person person : mPersons) {
                boolean match = false;
                for (Map.Entry<String, String> soughtTrait : soughtPerson.Traits.entrySet()) {
                    match = HasTrait(person.Traits, soughtTrait);
                    if (!match)
                        break;
                }
                if (!OptiverRegistry.StringEquals(soughtPerson.Name, "Unknown")) {
                    match = OptiverRegistry.StringEquals(soughtPerson.Name, person.Name);
                }
                if (match)
                    return person;
            }

            Person unknownPerson = new Person();
            return unknownPerson;
        }
    }

    ;

    // Entry point
    static String GetNameFromRegistryByTraits(String[] rawRegistry, String soughtTraits) {
        Registry registry = new OptiverRegistry().new Registry();
        try {
            for (String rawInput : rawRegistry) {
                registry.add(ConvertRawInputToPerson(rawInput));
            }

            Person person = registry.findMatchingPersonByRawTraits(soughtTraits);
            return person.Name;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return "Error";
        }
    }

    public static void main(String[] args) throws IOException {

    }
}