package com.zipcodewilmington.streams.anthropoid;

import com.zipcodewilmington.streams.tools.ReflectionUtils;
import com.zipcodewilmington.streams.tools.logging.LoggerHandler;
import com.zipcodewilmington.streams.tools.logging.LoggerWarehouse;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 5/29/17.
 * The warehouse is responsible for storing, retrieving, and filtering personSequence
 *
 * @ATTENTION_TO_STUDENTS You are FORBIDDEN from using loops of any sort within the definition of this class.
 */
public final class PersonWarehouse implements Iterable<Person> {
    private final LoggerHandler loggerHandler = LoggerWarehouse.getLogger(PersonWarehouse.class);
    private final List<Person> people = new ArrayList<>();

    /**
     * @param person the Person object to add to the warehouse
     * @ATTENTION_TO_STUDENTS You are FORBIDDEN from modifying this method
     */
    public void addPerson(Person person) {
        loggerHandler.disbalePrinting();
        loggerHandler.info("Registering a new person object to the person warehouse...");
        loggerHandler.info(ReflectionUtils.getFieldMap(person).toString());
        people.add(person);
    }

    /**
     * @return list of names of Person objects
     */ // TODO
    public List<String> getNames() {
        List<String> myPeoplesNames =
                people.stream()
                        .map(Person::getName)
                        .collect(Collectors.toList());

        return myPeoplesNames;
    }


    /**
     * @return list of uniquely named Person objects
     */ //TODO
    public Stream<Person> getUniquelyNamedPeople() {
        List<String> names = getNames().stream().distinct().collect(Collectors.toList());
        List<Person> unique = new ArrayList<>();
        for (Person person: people) {
            if(names.contains(person.getName())){
                unique.add(person);
                names.remove(person.getName());
            }

        }
        Stream<Person> uniquePeeps = unique.stream();
//        Predicate<Person> matchNames = val -> names.contains(val.getName());;
//
//        Stream<Person> uniquePeeps = people
//                .stream()
//                .sorted(Person::compareTo);
//                //.filter(val -> names.contains(val.getName()));

        return uniquePeeps;
    }


    /**
     * @param character starting character of Person objects' name
     * @return a Stream of respective
     */ //TODO
    public Stream<Person> getUniquelyNamedPeopleStartingWith(Character character) {
        Stream<Person> sameLetter = getUniquelyNamedPeople()
                .filter(person -> person.getName().charAt(0) == character);


        return sameLetter;
    }

    /**
     * @param n first `n` Person objects
     * @return a Stream of respective
     */ //TODO
    public Stream<Person> getFirstNUniquelyNamedPeople(int n) {
        Stream<Person> first = getUniquelyNamedPeople().limit(n);
        return first;
    }

    /**
     * @return a mapping of Person Id to the respective Person name
     */ // TODO
    public Map<Long, String> getIdToNameMap() {
        Map <Long, String> nameMap = people
                .stream()
                .collect(Collectors.toMap(Person::getPersonalId, Person::getName));

        return nameMap;
    }




    /**
     * @return Stream of Stream of Aliases
     */ // TODO
    public Stream<Stream<String>> getNestedAliases() {
        Stream<Stream<String>> nested =
                people.stream()
                .map(val ->Arrays.stream(val.getAliases()));

        return nested;
    }


    /**
     * @return Stream of all Aliases
     */ // TODO
    public Stream<String> getAllAliases() {
        Stream<String> aliases = people
                .stream()
                .flatMap(val-> Arrays.stream(val.getAliases()));
        return  aliases;
    }

    // DO NOT MODIFY
    public Boolean contains(Person p) {
        return people.contains(p);
    }

    // DO NOT MODIFY
    public void clear() {
        people.clear();
    }

    // DO NOT MODIFY
    public int size() {
        return people.size();
    }

    @Override // DO NOT MODIFY
    public Iterator<Person> iterator() {
        return people.iterator();
    }
}
