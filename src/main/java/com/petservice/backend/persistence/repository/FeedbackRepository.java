package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.Feedback;
import com.petservice.backend.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

    List<Feedback> findAllByPetsitterOrderByCreationDateDesc(User petsitter);
}
