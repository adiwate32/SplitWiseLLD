package com.lld.splitwise;

import com.lld.splitwise.commands.CommandExecutor;
import com.lld.splitwise.commands.SettleUpCommand;
import com.lld.splitwise.exceptions.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class SplitwiseApplication implements CommandLineRunner {

	@Autowired
	private SettleUpCommand settleUpCommand;

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws InvalidUserException {
		CommandExecutor commandExecutor = new CommandExecutor();
		commandExecutor.addCommand(settleUpCommand);

		Scanner scanner = new Scanner(System.in);

		while (true)
		{
			String input = scanner.nextLine();
			System.out.println(input);
			commandExecutor.execute(input);
		}
	}
}
