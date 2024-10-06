package com.temporal.membership.services;

import com.temporal.membership.model.MembershipDto;

public interface MembershipService {

    void register(MembershipDto inputTransaction);

    void verify(String registrationNo);

    void createAccount(String registrationNo);

    void complete(String registrationNo);
}
