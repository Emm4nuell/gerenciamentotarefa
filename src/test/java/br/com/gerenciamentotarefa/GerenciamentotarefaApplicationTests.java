package br.com.gerenciamentotarefa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("test")
class GerenciamentotarefaApplicationTests {

	@Test
	void main() {
		GerenciamentotarefaApplication.main(new String[]{});
	}

}
