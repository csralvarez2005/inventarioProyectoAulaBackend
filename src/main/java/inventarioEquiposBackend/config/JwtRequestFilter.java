package inventarioEquiposBackend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService; // Carga los detalles del usuario

    @Autowired
    private JwtTokenUtil jwtTokenUtil; // Utiliza JwtTokenUtil para obtener y validar el token

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization"); // 1. Obtener el token del encabezado

        String username = null;
        String jwtToken = null;

        // Verificar que el token comienza con "Bearer " y extraer el token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7); // Eliminar "Bearer " y quedarnos solo con el token

            try {
                // 2. Extraer el username del token
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (Exception e) {
                logger.warn("JWT Token ha expirado o es inválido");
            }
        } else {
            logger.warn("JWT Token no comienza con 'Bearer '");
        }

        // Si tenemos un username y no hay autenticación ya en curso (es decir, no está autenticado)
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 3. Cargar los detalles del usuario (esto debe ser una implementación personalizada de UserDetails)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // 4. Validar el token
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                // Si el token es válido, crear un objeto de autenticación
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // Establecer los detalles de la autenticación
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 5. Establecer el contexto de seguridad con la autenticación
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // Continuar con la cadena de filtros (pasar la petición al siguiente filtro)
        chain.doFilter(request, response);
    }
}
