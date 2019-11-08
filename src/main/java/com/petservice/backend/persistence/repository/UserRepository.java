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

    @Query("select us from User us " +
            "inner join Catalog ca " +
            "on us.catalogSet = ca " +
            "where us.city = :city and us.rating >= :rating and us.animals in :animals and ca.petService in :services " +
            "order by us.rating desc ")
    List<User> findByFilterOptions(@Param(value = "city") City city,
                                   @Param(value = "rating")Double rating,
                                   @Param(value = "animals")Set<Animal> animals,
                                   @Param(value = "services")Set<PetService> services);
}
