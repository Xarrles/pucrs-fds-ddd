package br.pucrs.nomeusuario.exemplo.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.pucrs.nomeusuario.exemplo.application.dtos.ProdutoDTO;
import br.pucrs.nomeusuario.exemplo.application.usecases.ProdutosDisponiveisUC;
import br.pucrs.nomeusuario.exemplo.application.usecases.QuantidadeDisponivelProdutoUC;
import br.pucrs.nomeusuario.exemplo.application.usecases.VendaProdutoDisponiveisUC;

@RestController
public class ACMEController {
    private ProdutosDisponiveisUC produtosDisponiveis;
    private QuantidadeDisponivelProdutoUC quantidadeProduto; 
    private VendaProdutoDisponiveisUC vendaProduto;

    @Autowired
    public ACMEController(ProdutosDisponiveisUC produtosDisponiveis,
    QuantidadeDisponivelProdutoUC quantidadeProduto,
    VendaProdutoDisponiveisUC vendaProduto){
        this.produtosDisponiveis = produtosDisponiveis;
        this.quantidadeProduto = quantidadeProduto;
        this.vendaProduto = vendaProduto;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String mensagem(){
        return("Bem vindo a loja ACME!");
    }

    @GetMapping("produtos")
    @CrossOrigin(origins = "*")
    public List<ProdutoDTO> produtosDisponiveis(){
        return produtosDisponiveis.run();
    } 

    @GetMapping("quantidade/{id}")
    @CrossOrigin(origins = "*")
    public int quantidadeProduto(@PathVariable(value="id") long idProduto){
        return quantidadeProduto.run(idProduto);
    } 

    @GetMapping("venda/{id}/{qtd}")
    @CrossOrigin(origins = "*")
    public double vendaProduto(
        @PathVariable(value="id") long idProduto,
        @PathVariable(value="qtd") int quantidadeProduto){
            return vendaProduto.run(idProduto, quantidadeProduto);
    }
}
