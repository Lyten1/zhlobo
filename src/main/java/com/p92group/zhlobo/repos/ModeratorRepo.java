package com.p92group.zhlobo.repos;

import com.p92group.zhlobo.models.Customer;
import com.p92group.zhlobo.models.Moderator;
import com.p92group.zhlobo.models.UserInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepo extends JpaRepository<Moderator, Long> {
    Moderator findByEmail(String email);
}
