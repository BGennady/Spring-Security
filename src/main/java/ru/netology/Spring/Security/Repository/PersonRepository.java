package ru.netology.Spring.Security.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.netology.Spring.Security.Model.Person;
import ru.netology.Spring.Security.Model.PersonID;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, PersonID> {

    @Query("SELECT p FROM Person p WHERE p.cityOfLiving  =:city")
    List<Person> findPersonCityOfLiving(@Param("city") String city);

    @Query("SELECT p FROM Person p WHERE p.personID.age <:age ORDER BY p.personID.age ASC")
    List<Person> findByPersonID_AgeLessThanOrderByPersonID_AgeAsc(@Param("age") int age);

    @Query("SELECT p FROM Person p WHERE p.personID.name =:name AND p.personID.surname =:surname")
    Optional<Person> findByPersonID_NameAndPersonID_Surname(@Param("name") String name, @Param("surname") String surname);
}
