package med.voll.api_alura.controllers;

import jakarta.validation.Valid;
import med.voll.api_alura.dtos.usuario.DadosAutenticacao;
import med.voll.api_alura.infra.sercurity.DadosTokenJW;
import med.voll.api_alura.infra.sercurity.TokenService;
import med.voll.api_alura.models.usuario.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
         var authentication = manager.authenticate(authenticationToken);

         var tokenJWT = tokenService.gerarToken((UsuarioModel) authentication.getPrincipal());

         return ResponseEntity.ok( new DadosTokenJW(tokenJWT));

    }


}
