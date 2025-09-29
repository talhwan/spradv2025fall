package com.thc.sprbasic2025summer.repository;

import com.thc.sprbasic2025summer.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);

    //List<User> findByPhone(String phone);
//    @EntityGraph(attributePaths = {"userRoleType.roleType"})
//    Optional<User> findEntityGraphRoleTypeById(Long id);
}
