package com.student_crm.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student_crm.dao.UserDao;
import com.student_crm.entity.Role;
import com.student_crm.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional("securityTransactionManager")
	public User findUserByUserName(String name) {
		return userDao.findUserByUserName(name);
	}
	
	@Override
	@Transactional("securityTransactionManager")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findUserByUserName(username);
		System.out.println("user "+user);
		if(user == null) {
			throw new UsernameNotFoundException("Invalids usernames or passwords");
		}
        List<GrantedAuthority > authorities = AuthorityUtils.createAuthorityList();
        
        // if the user selected role other than employee, 
        // then add that one too (multiple roles)
        //Collection<Role> formRole = user.getRoles();
        List<Role> formRole = user.getRoles();

        for ( Role formRoles:formRole) {
        		authorities.add(new SimpleGrantedAuthority(formRoles.getName()));
        }
        System.out.println("formRole "+formRole);
        /*return new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), mapRolesToAuthorities(user.getRoles()));*/
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
		user.getPassword(),authorities);
		
		
	}
	
	private Collection<? extends GrantedAuthority>mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new
		SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
}
