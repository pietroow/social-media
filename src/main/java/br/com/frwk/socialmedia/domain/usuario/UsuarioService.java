package br.com.frwk.socialmedia.domain.usuario;

import br.com.frwk.socialmedia.domain.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario createUser(UsuarioFormDTO userFormDTO) {
        Usuario user = new Usuario(userFormDTO);
        return usuarioRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmailIgnoreCase(email);
        return usuarioOptional.orElseThrow(() -> new UsernameNotFoundException(email));
    }

}
