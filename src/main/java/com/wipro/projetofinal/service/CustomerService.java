package com.wipro.projetofinal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.Moviment;
import com.wipro.projetofinal.entities.SpecialAccount;
import com.wipro.projetofinal.entities.enums.MovimentDescription;
import com.wipro.projetofinal.repository.CheckingAccountRepository;
import com.wipro.projetofinal.repository.SpecialAccountRepository;
import com.wipro.projetofinal.service.exeption.InvalidMoneyValue;
import com.wipro.projetofinal.service.exeption.ResourceNotFoundExcception;

@Service
public class CustomerService {

	@Autowired
	private CheckingAccountRepository checkingAccountRepository;

	@Autowired
	private SpecialAccountRepository specialAccountRepository;

	public Account getAccount(String numberAccount) {
		Account account = checkingAccountRepository.findByAccountNumber(numberAccount);
		if (account != null) {
			return account;
		} else {
			account = specialAccountRepository.findByAccountNumber(numberAccount);
			Optional<Account> special = Optional.ofNullable(account);
			return special.orElseThrow(() -> new ResourceNotFoundExcception(numberAccount));
		}
	}

	public Account deposit(String numberAccount, Double value) throws Exception {
		Account account = getAccount(numberAccount);
		
		if(value > 0) {
						
			// Guardando a movimentação de deposito na conta
			Moviment moviment = new Moviment(value, MovimentDescription.DEPOSIT);
			account.addMoviment(moviment);
			
			// Mandando o repository salvar
			account = saveAccount(account);
			
			return account;
		} 
			throw new InvalidMoneyValue(value);
	}

	// Metodo para Sacar
	public Account withdraw(String numberAccount, Double value) {
		Account account = getAccount(numberAccount);
		if (value > 0) {

			if (account.getClass().getName().equals(CheckingAccount.class.getName())) {
				if (account.getBalance() >= value) {
					account.withdraw(value);
					
					// Guardando a movimentação de conta corrente
					Moviment moviment = new Moviment(value, MovimentDescription.WITHDRAW);
					account.addMoviment(moviment);
					
					// Mandando o repository salvar
					account = saveAccount(account);
					
					return account;
				} else {
					throw new InvalidMoneyValue("Saldo Insuficiente!");
				}
			} else {
				SpecialAccount specialAccount = (SpecialAccount) account;
				if ((specialAccount.getBalance() + specialAccount.getSpecialLimit()) >= value) {
					specialAccount.withdraw(value);
					
									
					// Guardando a movimentação de conta Especial
					Moviment moviment = new Moviment(value, MovimentDescription.WITHDRAW);
					account.addMoviment(moviment);
					
					// Mandando o repository salvar
					account = saveAccount(account);
					
					return account;
				} else {
					throw new InvalidMoneyValue("Saldo Insuficiente para Saque!");
				}
			}

		} else {
			throw new InvalidMoneyValue("Valor " + value + " inválido");
		}

	}

	public Account transfer(String originAccount, String destinationAccount, Double value) {

		Account originAcc = getAccount(originAccount);
		Account destinationAcc = getAccount(destinationAccount);

		if (value > 0) {

			if (originAcc.getBalance() >= value) {
				originAcc.withdraw(value);
				destinationAcc.deposit(value);
				
				
				// Guardando a movimentação na conta origem
				Moviment movimentOrigin = new Moviment(value, originAcc.getAccountNumber(), destinationAccount, MovimentDescription.TRANSFER_SENT);
				originAcc.addMoviment(movimentOrigin);
				
				// Mandando o repository salvar as atualizaçoes da conta origem
				originAcc = saveAccount(originAcc);

				// Guardando a movimentação  na conta Destino
				Moviment movimentDestination = new Moviment(value, originAcc.getAccountNumber(), destinationAccount, MovimentDescription.TRANSFER_RECEIVED);
				destinationAcc.addMoviment(movimentDestination);
				
				// Mandando o repository salvar as atualizaçoes da conta destino
				destinationAcc = saveAccount(destinationAcc);
			
				return originAcc;
			} else {
				throw new InvalidMoneyValue("Saldo Insuficiente para transferencia!");
			}

		} else {
			throw new InvalidMoneyValue("Valor " + value + " inválido");
		}

	}

	private Account saveAccount(Account account) {
		if (account.getClass().getName().equals(CheckingAccount.class.getName())) {
			return checkingAccountRepository.save((CheckingAccount) account);
		} else {
			return specialAccountRepository.save((SpecialAccount) account);
		}
	}
}
