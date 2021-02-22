package hu.progmasters.invite_me.repository;

import hu.progmasters.invite_me.domain.InviteEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<InviteEvent, String> {
}
