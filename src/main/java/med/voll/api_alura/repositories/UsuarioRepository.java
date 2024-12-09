package med.voll.api_alura.repositories;

import med.voll.api_alura.models.usuario.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    UserDetails findByLogin(String login);
}
