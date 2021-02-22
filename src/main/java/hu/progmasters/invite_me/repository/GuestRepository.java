package hu.progmasters.invite_me.repository;

import hu.progmasters.invite_me.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, String> {
}
