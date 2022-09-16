package io.github.julianomachadoo.domain.repository;

import io.github.julianomachadoo.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

//    @Query(value = " select c from Cliente c where c.nome like = :nome ")
//    List<Cliente> encontrarPorNome ( @Param("nome") String nome);
//
//    @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
//    List<Cliente> encontrarPorNome ( @Param("nome") String nome);

    @Query(" delete from Cliente c where c.nome = :nome ")
    @Modifying
    void deleteByNome(@Param("nome") String nome);

    boolean existsByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos p where c.id = :id ")
    Cliente findClienteFetchPedidos( Integer id );
}
