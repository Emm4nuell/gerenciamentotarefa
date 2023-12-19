package br.com.gerenciamentotarefa.jwt;

import br.com.gerenciamentotarefa.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${spring.jwt.chave}")
    private String chave;

    @Value("${spring.jwt.expiration}")
    private Integer expiration;

    public String gerarToken(Usuario usuario){
        LocalDateTime datetime = LocalDateTime.now().plusMinutes(2);
        Instant instant = datetime.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chave)
                .compact();
    }

    private Claims obterToken(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chave)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token){
        try {
            Claims claim = obterToken(token);
            Date data = claim.getExpiration();
            LocalDateTime localDateTime = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);

        }catch (Exception e){
            return false;
        }
    }

    public String obterLoginUsuario(String token){
        return (String) obterToken(token).getSubject();
    }
}
