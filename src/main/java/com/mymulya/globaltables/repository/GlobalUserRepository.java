package com.mymulya.globaltables.repository;

import com.mymulya.globaltables.model.GlobalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalUserRepository extends JpaRepository<GlobalUser, Long> {
    GlobalUser findByDomain(String domain);
}
