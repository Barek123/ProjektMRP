package pl.marcb.MRP.security;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.marcb.MRP.domain.Account;
import pl.marcb.MRP.domain.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountRepository.findByLogin(login);
        return buildUserForAuthentication(account);
    }

    private UserDetails buildUserForAuthentication(Account account) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(grantedAuthority);      //roles not required
        return new User(account.getLogin(), account.getPass(), true, true, true, true, authorities);
    }


}
