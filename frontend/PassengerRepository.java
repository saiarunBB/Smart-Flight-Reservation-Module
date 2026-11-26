package com.flight.reservation.module1;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Optional<Passenger> findByEmailId(String emailId);
    boolean existsByEmailId(String emailId);
}