package io.github.julianomachadoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendasApplication {

//    @Bean
//    public CommandLineRunner init(
//            @Autowired ClienteRespository clienteRespository,
//            @Autowired ProdutoRespository produtoRespository
//    ) {
//        return args -> {
//            System.out.println("Salvando clientes");
//            Cliente fulano = new Cliente("Fulano", "111.111.111-11");
//            clienteRespository.save(fulano);
//
//            Produto p = new Produto("Celular", 500.00);
//            produtoRespository.save(p);
//        };
//    }

    public static void main(String[] args) {

        SpringApplication.run(VendasApplication.class, args);
    }
}
