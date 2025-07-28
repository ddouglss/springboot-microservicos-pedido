package dg.microservicos.pedido.service;

import dg.microservicos.pedido.model.ItemPedido;
import dg.microservicos.pedido.model.Pedido;
import dg.microservicos.pedido.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.lang.constant.PackageDesc;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido salvarPedido (Pedido pedido) {

        if (pedido.getItens() != null){
           for (ItemPedido item : pedido.getItens()){
               item.setPedido(pedido);
           }
        }
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}
