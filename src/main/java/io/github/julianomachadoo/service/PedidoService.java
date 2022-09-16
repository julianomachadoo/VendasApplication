package io.github.julianomachadoo.service;

import io.github.julianomachadoo.domain.entity.Pedido;
import io.github.julianomachadoo.domain.entity.enums.StatusPedido;
import io.github.julianomachadoo.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
