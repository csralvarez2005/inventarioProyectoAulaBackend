package inventarioEquiposBackend.config;



import inventarioEquiposBackend.model.Funcionario;
import inventarioEquiposBackend.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;



@Service
public class FuncionarioDetailsService implements UserDetailsService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String identificacion) throws UsernameNotFoundException {
        return funcionarioRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new UsernameNotFoundException("Funcionario no encontrado con identificación: " + identificacion));
    }
}
