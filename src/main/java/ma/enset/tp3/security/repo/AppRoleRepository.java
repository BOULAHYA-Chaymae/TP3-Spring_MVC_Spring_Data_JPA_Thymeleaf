package ma.enset.tp3.security.repo;


import ma.enset.tp3.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String>
{

}
