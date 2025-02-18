package ru.netology.Spring.Security.Сontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.Spring.Security.Model.Person;
import ru.netology.Spring.Security.Repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/by-city")
    public List<Person> findByCity(@RequestParam String city) {
        return personRepository.findPersonCityOfLiving(city);
    }
    @GetMapping("/by-age")
    public List<Person> findByAge(@RequestParam int age) {
        return personRepository.findByPersonID_AgeLessThanOrderByPersonID_AgeAsc(age);
    }
    @GetMapping("/by-name-and-surname")
    public Optional<Person> findByNameAndSurname (@RequestParam String name, @RequestParam String surname){
        return personRepository.findByPersonID_NameAndPersonID_Surname(name, surname);
    }
}