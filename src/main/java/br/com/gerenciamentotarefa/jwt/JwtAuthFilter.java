package br.com.gerenciamentotarefa.jwt;

import br.com.gerenciamentotarefa.service.UserDetailsServiceImp;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserDetailsServiceImp details;

    public JwtAuthFilter(JwtService jwtService, UserDetailsServiceImp details) {
        this.jwtService = jwtService;
        this.details = details;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer")){
            String token = header.split(" ")[1];

            boolean isvalid = jwtService.tokenValido(token);
            if(isvalid){
                String loginUsuario = jwtService.obterLoginUsuario(token);
                UserDetails userDetails = details.loadUserByUsername(loginUsuario);

                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }
        filterChain.doFilter(request, response);
    }
}
