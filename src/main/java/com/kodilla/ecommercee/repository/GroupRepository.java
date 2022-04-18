package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    @Override
    Optional<Group> findById(Long id);

    @Override
    List<Group> findAll();
}
