package com.temporal.membership.services;

import com.temporal.membership.model.MembershipDto;


public interface AccountService {
    void activate(String registrationNo);
}
