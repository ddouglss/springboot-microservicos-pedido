package dg.microservicos.pedido.mapper;

import dg.microservicos.pedido.dto.ItemPedidoDTO;
import dg.microservicos.pedido.dto.PedidoDTO;
import dg.microservicos.pedido.model.ItemPedido;
import dg.microservicos.pedido.model.Pedido;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    public Pedido toEntity(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setDescricao(dto.getDescricao());

        List<ItemPedido> itens = dto.getItens().stream().map(itemDTO -> {
            ItemPedido item = new ItemPedido();
            item.setNome(itemDTO.getNome());
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPedido(pedido); // associação reversa
            return item;
        }).collect(Collectors.toList());

        pedido.setItens(itens);
        return pedido;
    }
}
