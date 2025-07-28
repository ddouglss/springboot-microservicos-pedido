package dg.microservicos.pedido.dto;

import java.util.List;

public class PedidoDTO {
    private String descricao;
    private List<ItemPedidoDTO> itens;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
}
