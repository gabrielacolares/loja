package com.teste.loja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.loja.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
