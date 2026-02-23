package in.learn.employeeregistration.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImp implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
//        Logic for spring security context,authentication,principle,username
        return Optional.of("Aadesh Jain");
    }
}
