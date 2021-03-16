package register.system.knowledgeregistersystem.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import register.system.knowledgeregistersystem.Models.Admin;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long>
{
    Optional<Admin> findByUsername(String username);
    Admin save(Admin admin);
}
