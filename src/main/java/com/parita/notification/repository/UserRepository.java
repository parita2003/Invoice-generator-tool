package com.parita.notification.repository;

import com.parita.notification.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT * FROM public.users WHERE id = :id",
            nativeQuery = true
    )
    Optional<User> findUserById(Long id);

    @Query(
            value = "SELECT COUNT(*) FROM public.users",
            nativeQuery = true
    )
    Long countUsers();

    @Query(
            value = "SELECT id FROM public.users ORDER BY id",
            nativeQuery = true
    )
    List<Long> getUserIds();

    @Query(
            value = "SELECT current_database() || ' | ' || current_user || ' | ' || current_schema()",
            nativeQuery = true
    )
    String getDatabaseInfo();
}