package ma.enset.tp3;

import ma.enset.tp3.entities.Patient;
import ma.enset.tp3.repository.PatientRepository;
import ma.enset.tp3.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class Tp3Application implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);}
    @Override
    public void run(String... args) throws Exception
    {
        /*
        patientRepository.save(new Patient(null,"Chaymae",new Date(),false,34));
        patientRepository.save(new Patient(null,"Houda",new Date(),false,4321));
        patientRepository.save(new Patient(null,"Fatima",new Date(),true,34));
        */
    }
    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder)
    {
        return args -> {
            if (!jdbcUserDetailsManager.userExists("user11")) {
                jdbcUserDetailsManager.createUser(User.withUsername("user11")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER").build());
            }

            if (!jdbcUserDetailsManager.userExists("user22")) {
                jdbcUserDetailsManager.createUser(User.withUsername("user22")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER").build());
            }

            if (!jdbcUserDetailsManager.userExists("admin2")) {
                jdbcUserDetailsManager.createUser(User.withUsername("admin2")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER", "ADMIN").build());
            }
        };
    }

    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService)
    {
        return args->{
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");

            accountService.addNewUser("user10", "1234", "user1@gmail.com", "1234");
            accountService.addNewUser("user20", "1234", "user2@gmail.com", "1234");
            accountService.addNewUser("admin10", "1234", "admin@gmail.com", "1234");

            accountService.addRoleToUser("user10", "USER");
            accountService.addRoleToUser("user20", "USER");
            accountService.addRoleToUser("admin10", "USER");
            accountService.addRoleToUser("admin10", "ADMIN");
        };
    }
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
