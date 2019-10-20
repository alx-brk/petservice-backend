package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends CrudRepository<T, Long> {
}
