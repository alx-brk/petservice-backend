package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailEquals(String email);

    User findByIdEqualsOrEmailEquals(Long id, String email);

    List<User> findAllByActivePetsitterIsTrueOrderByRatingDesc();

    @Query(value = "select user.* from user " +
            "inner join city on user.city_id = city.id " +
            "where user.active_petsitter is true " +
            "and (:city is null or city.name = :city) " +
            "and (:rating is null or user.rating >= :rating) " +
            "order by user.rating desc ",
            nativeQuery = true)
    List<User> findByFilterOptions(@Param(value = "city") String city,
                                   @Param(value = "rating") Double rating);

    @Query(value = "select user.* from user " +
            "inner join city on user.city_id = city.id " +
            "inner join user_animal ua on ua.user_id = user.id " +
            "where user.active_petsitter is true " +
            "and (:city is null or city.name = :city) " +
            "and (:rating is null or user.rating >= :rating) " +
            "and ua.animal_id in (:animals) " +
            "order by user.rating desc ",
            nativeQuery = true)
    List<User> findByFilterOptionsWithAnimals(@Param(value = "city") String city,
                                   @Param(value = "rating") Double rating,
                                   @Param(value = "animals") String animals);

    @Query(value = "select user.* from user " +
            "inner join city on user.city_id = city.id " +
            "inner join catalog on user.id = catalog.petsitter_id " +
            "where user.active_petsitter is true " +
            "and (:city is null or city.name = :city) " +
            "and (:rating is null or user.rating >= :rating) " +
            "and catalog.pet_service_id in (:services) " +
            "order by user.rating desc ",
            nativeQuery = true)
    List<User> findByFilterOptionsWithServices(@Param(value = "city") String city,
                                   @Param(value = "rating") Double rating,
                                   @Param(value = "services") String service);

    @Query(value = "select user.* from user " +
            "inner join city on user.city_id = city.id " +
            "inner join user_animal ua on ua.user_id = user.id " +
            "inner join catalog on user.id = catalog.petsitter_id " +
            "where user.active_petsitter is true " +
            "and (:city is null or city.name = :city) " +
            "and (:rating is null or user.rating >= :rating) " +
            "and ua.animal_id in (:animals) " +
            "and catalog.pet_service_id in (:services) " +
            "order by user.rating desc ",
            nativeQuery = true)
    List<User> findByFilterOptions(@Param(value = "city") String city,
                                   @Param(value = "rating") Double rating,
                                   @Param(value = "animals") String animals,
                                   @Param(value = "services") String service);


}
