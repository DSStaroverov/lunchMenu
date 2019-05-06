package ru.dsstaroverov.lunchMenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsstaroverov.lunchMenu.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT DISTINCT user FROM User user JOIN FETCH user.roles WHERE user.email=:email")
    User getByEmail(@Param("email")String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM User WHERE id=:id")
    int delete(@Param("id") int id);
}
