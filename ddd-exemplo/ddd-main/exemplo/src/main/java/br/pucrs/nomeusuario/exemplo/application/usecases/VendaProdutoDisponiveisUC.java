package br.pucrs.nomeusuario.exemplo.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.pucrs.nomeusuario.exemplo.domain.models.ProdutoModel;
import br.pucrs.nomeusuario.exemplo.domain.services.ServicoDeEstoque;

@Component
public class VendaProdutoDisponiveisUC {
    private ServicoDeEstoque servicoEstoque;

    @Autowired
    public VendaProdutoDisponiveisUC(ServicoDeEstoque servicoEstoque){
        this.servicoEstoque = servicoEstoque;
    }

    public double run(long idProduto, int quantidade){
        if (quantidade <= 0){
            return -1;
        }

        int estoque = servicoEstoque.qtdadeEmEstoque(idProduto);
        if (estoque < quantidade){
            return -1;}

        double desconto = 1;

        if (quantidade > 10){
            desconto = 0.90;
        }

        servicoEstoque.baixaEstoque(idProduto, quantidade);

        return servicoEstoque.produtoPorCodigo(idProduto).getPrecoUnitario() * 1.05 * desconto;
    }
}
