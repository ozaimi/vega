package vega.security;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class VegaAuthenticationProvider implements AuthenticationProvider {

    // This would be a JPA repository to snag your user entities
    //private final UserRepository userRepository;

   // @Autowired
   // public DemoAuthenticationProvider(UserRepository userRepository) {
   //     this.userRepository = userRepository;
    //}

    private String token;

    public VegaAuthenticationProvider(String token){
        this.token = token;
    }

    public VegaAuthenticationProvider(){

    }



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        PreAuthenticatedAuthenticationToken demoAuthentication = (PreAuthenticatedAuthenticationToken) authentication;

        String token = (String)demoAuthentication.getPrincipal();
        if(token==null || !token.equals("sami")){
            throw new SessionAuthenticationException("Could not find user with ID: " + demoAuthentication.getPrincipal());
        }

        /*User user = userRepository.find(demoAuthentication.getId());

        if(user == null){
            throw new UnknownUserException("Could not find user with ID: " + demoAuthentication.getId());
        }*/

        String sami = "sami";

        //if(true) return null;

        return demoAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}