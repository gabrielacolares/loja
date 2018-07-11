package com.teste.loja.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.teste.loja.api.model.Departamento;
import com.teste.loja.api.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	private DepartamentoRepository departamentoRepository;

	public Departamento editar(Long id, Departamento departamento) {
		Departamento departamentoAtual = departamentoRepository.findOne(id);
		if(null == departamentoAtual) {
			throw new EmptyResultDataAccessException(1);
		} 
		BeanUtils.copyProperties(departamento, departamentoAtual,"id");
		return departamentoRepository.save(departamentoAtual);
	}
}
