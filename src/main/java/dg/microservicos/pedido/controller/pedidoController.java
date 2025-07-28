package dg.microservicos.pedido.controller;

import dg.microservicos.pedido.model.Pedido;
import dg.microservicos.pedido.service.PedidoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
public class pedidoController {


    private final RabbitTemplate rabbitTemplate;
    private final PedidoService pedidoService;

    public pedidoController(RabbitTemplate rabbitTemplate, PedidoService pedidoService) {
        this.rabbitTemplate = rabbitTemplate;
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public String criarPedido(@RequestBody Pedido pedido) {
        Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);
        rabbitTemplate.convertAndSend(pedidoSalvo);
        return "Pedido salvo e enviando para processamento" + pedido.getDescricao();
    }

    @GetMapping
    public List<Pedido> listarPedido() {
        return pedidoService.listarPedidos();
    }
}
