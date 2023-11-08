package br.com.gerenciamentotarefa;

import br.com.gerenciamentotarefa.dto.UsuarioDto;
import br.com.gerenciamentotarefa.model.Usuario;
import br.com.gerenciamentotarefa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class GerenciamentotarefaApplication {

	public static void main(String[] args) {

		SpringApplication.run(GerenciamentotarefaApplication.class, args);
	}
}
