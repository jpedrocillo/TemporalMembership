package com.temporal.membership.model;

public class MembershipDto {
    private String registrationNo;
    private String name;
    private String membershipType;
    private Double paymentAmount;
    private String email;

    public MembershipDto() {
    }

    public MembershipDto(String registrationNo, String name, String membershipType, Double paymentAmount, String email) {
        this.registrationNo = registrationNo;
        this.name = name;
        this.membershipType = membershipType;
        this.paymentAmount = paymentAmount;
        this.email = email;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
