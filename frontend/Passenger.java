package com.flight.reservation.module1;

import jakarta.persistence.*;

@Entity
@Table(name = "PASSENGERS")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer passengerId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Column(name = "phone_num", nullable = false)
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequent_flyer_tier")
    private FrequentFlyerTier tier = FrequentFlyerTier.STANDARD;

    public Passenger() {}

    public Passenger(String fullName, String emailId, String phoneNum) {
        this.fullName = fullName;
        this.emailId  = emailId;
        this.phoneNum = phoneNum;
    }

    public Integer getPassengerId() { return passengerId; }
    public String  getFullName()    { return fullName; }
    public String  getEmailId()     { return emailId; }
    public String  getPhoneNum()    { return phoneNum; }
    public FrequentFlyerTier getTier() { return tier; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }
    public void setTier(FrequentFlyerTier tier) { this.tier = tier; }
}