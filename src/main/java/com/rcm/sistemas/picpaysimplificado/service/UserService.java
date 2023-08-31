package com.rcm.sistemas.picpaysimplificado.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcm.sistemas.picpaysimplificado.domain.user.User;
import com.rcm.sistemas.picpaysimplificado.domain.user.UserType;
import com.rcm.sistemas.picpaysimplificado.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public void validateTransation(User sender, BigDecimal amount) throws Exception {

		if (sender.getUserType() == UserType.MERCHANT) {
			throw new Exception("Usuário do tipo Logista, não está autorizado realizar transação.");
		}

		if (sender.getBalance().compareTo(amount) <= 0) {
			throw new Exception("Usuário não possui saldo suficiente para a transação.");
		}
	}

	public User findUserById(Long id) throws Exception {
		return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado."));
	}

	public void saveUser(User user) {
		this.repository.save(user);
	}

}
