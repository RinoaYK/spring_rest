package br.com.dio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dio.entity.Produto;
import br.com.dio.exception.ProductNullException;
import br.com.dio.exception.ProductPriceException;
import br.com.dio.exception.ProductStringException;
import br.com.dio.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto save(Produto produto) throws Exception {
        if(produto.getNome() == null || produto.getPreco() == null) 
            throw new ProductNullException();
        if (produto.getPreco() < 0)
            throw new ProductPriceException();
        if(produto.getNome().length() < 3 || produto.getNome().length() > 50) 
            throw new ProductStringException();
        return repository.save(produto);
    }

    public Produto findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }
}