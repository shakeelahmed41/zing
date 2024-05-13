package com.zing.app.repository;


import com.zing.app.model.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDetailsRepository extends JpaRepository<AccountDetails,Long> {
}
