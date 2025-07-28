package dg.microservicos.pedido.controller;

import dg.microservicos.pedido.dto.PedidoDTO;
import dg.microservicos.pedido.mapper.PedidoMapper;
import dg.microservicos.pedido.model.Pedido;
import dg.microservicos.pedido.service.PedidoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

    private final RabbitTemplate rabbitTemplate;
    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    public PedidoController(RabbitTemplate rabbitTemplate, PedidoService pedidoService, PedidoMapper pedidoMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
    }

    @Value("${broker.queue.processamento.name}")
    private String routingKey;

    @PostMapping
    public String criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);
        Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);
        rabbitTemplate.convertAndSend("", routingKey, pedidoSalvo.getDescricao());
        return "Pedido salvo e enviado para processamento: " + pedidoSalvo.getDescricao();
    }

    @GetMapping
    public List<Pedido> listarPedido() {
        return pedidoService.listarPedidos();
    }
}
