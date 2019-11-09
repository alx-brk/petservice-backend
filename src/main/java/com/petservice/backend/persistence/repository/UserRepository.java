package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.Animal;
import com.petservice.backend.persistence.entity.City;
import com.petservice.backend.persistence.entity.PetService;
import com.petservice.backend.persistence.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailEquals(String email);

    User findByIdEqualsOrEmailEquals(Long id, String email);

    @Query("select u from User u " +
            "inner join u.catalogSet cs " +
            "where u.city = :city " +
            "and u.rating >= :rating " +
            "and u.animals in :animals " +
            "and cs.petService in :services " +
            "order by u.rating desc ")
    List<User> findByFilterOptions(@Param(value = "city") City city,
                                   @Param(value = "rating")Double rating,
                                   @Param(value = "animals")Set<Animal> animals,
                                   @Param(value = "services")Set<PetService> services);
}
