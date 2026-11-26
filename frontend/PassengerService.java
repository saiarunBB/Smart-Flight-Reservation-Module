package com.flight.reservation.module1;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    private final PassengerRepository repo;
    private final AuthService authService;

    public PassengerService(PassengerRepository repo, AuthService authService) {
        this.repo        = repo;
        this.authService = authService;
    }

    public Passenger register(String fullName, String email, String phone, String password) {
        if (repo.existsByEmailId(email))
            throw new IllegalArgumentException("Email already registered: " + email);
        authService.register(email, password);
        return repo.save(new Passenger(fullName, email, phone));
    }

    public Optional<Passenger> findByEmail(String email) {
        return repo.findByEmailId(email);
    }

    public Passenger findById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Passenger not found: " + id));
    }

    public List<Passenger> listAll() {
        return repo.findAll();
    }

    public Passenger updateTier(String email, FrequentFlyerTier tier) {
        Passenger p = repo.findByEmailId(email)
                .orElseThrow(() -> new IllegalArgumentException("Passenger not found"));
        p.setTier(tier);
        return repo.save(p);
    }

    public boolean authenticate(String email, String password) {
        return authService.login(email, password);
    }
}