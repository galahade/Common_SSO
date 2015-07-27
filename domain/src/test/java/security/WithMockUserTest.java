package security;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yang.young.common.sso.Application;
import com.yang.young.common.sso.persistance.jpa.entity.CustomerEntity;
import com.yang.young.common.sso.persistance.jpa.entity.GroupEntity;
import com.yang.young.common.sso.persistance.jpa.entity.RoleEntity;
import com.yang.young.common.sso.service.TestAuthorityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class WithMockUserTest {
	
	//@Before
	public void setup() {
		//save customer
				RoleEntity role1 = new RoleEntity("Seller");
				RoleEntity role2 = new RoleEntity("Customer");
	
				GroupEntity group = new GroupEntity("customer_group");
				group.getRoles().add(role1);
				group.getRoles().add(role2);
		
				CustomerEntity user = new CustomerEntity("yyang","password","yyang@salmon.com", -1, "USD");
				user.getGroups().add(group);
				

		List<GrantedAuthority> authorities = AuthorityUtils
				.createAuthorityList("ROLE_USER");
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				user, "notused", authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	//@After
	public void cleanup() {
		SecurityContextHolder.clearContext();
	}
	
	@Autowired
	TestAuthorityService service ;
	
	@Test(expected=AuthenticationCredentialsNotFoundException.class)
	public void get() {
		service.getMessage();
	}

}
