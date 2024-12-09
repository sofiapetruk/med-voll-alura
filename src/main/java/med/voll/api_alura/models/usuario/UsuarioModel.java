package med.voll.api_alura.models.usuario;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TB_LOGIN")
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_seq")
    @SequenceGenerator(name = "login_seq", sequenceName = "tb_sq_id_login", allocationSize = 1)

    @Column(name = "id_login")
    private Long idLogin;

    private String login;
    private String senha;

    //construtores ,Getters and Setters
    public UsuarioModel() {
    }

    public UsuarioModel(Long idLogin, String senha, String login) {
        this.idLogin = idLogin;
        this.senha = senha;
        this.login = login;
    }

    public Long getIdLogin() {
        return idLogin;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
