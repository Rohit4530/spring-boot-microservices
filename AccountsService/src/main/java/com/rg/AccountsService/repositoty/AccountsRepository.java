package com.rg.AccountsService.repositoty;

import com.rg.AccountsService.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}
