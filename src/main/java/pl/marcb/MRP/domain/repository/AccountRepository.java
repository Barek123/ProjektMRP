package pl.marcb.MRP.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcb.MRP.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByLogin(String login);
}