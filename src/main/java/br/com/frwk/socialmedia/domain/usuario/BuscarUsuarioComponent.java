package br.com.frwk.socialmedia.domain.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscarUsuarioComponent {

    private final UsuarioService usuarioService;

    public Usuario getUsuario(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        return (Usuario) usuarioService.loadUserByUsername(email);
    }

}
