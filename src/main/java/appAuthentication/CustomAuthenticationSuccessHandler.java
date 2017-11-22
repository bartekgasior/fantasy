package appAuthentication;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        // Get the role of logged in user
    	System.out.println("Hit the AuthSuccessHandler");
    	
        Collection<? extends GrantedAuthority> auths = authResult.getAuthorities();
                
        for (GrantedAuthority grantedAuthority : auths) {
        	if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
            	response.sendRedirect("/admin");
            	System.out.println("admin");
        	} else if(grantedAuthority.getAuthority().equals("ROLE_USER")) {
        		response.sendRedirect("/user");
        		System.out.println("user");
        	} else 
        		System.out.println("b��d");
        }
    }
}