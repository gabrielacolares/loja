package com.teste.loja.api.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.teste.loja.api.model.Produto;
@Service
public class ProductDAO {
	@Autowired
	EntityManager em;
	public List<Produto> getByFilter(String nome){
		System.out.println(nome);
		CriteriaBuilder  criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> produto = criteriaQuery.from(Produto.class);
		Predicate namePredicate = criteriaBuilder.equal(produto.get("nome"), nome);
		criteriaQuery.where(namePredicate);
		TypedQuery<Produto> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}
}
