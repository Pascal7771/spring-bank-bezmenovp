//package org.example.operations;
//
//import org.example.account.AccountService;
//import org.example.operations.processors.*;
//import org.example.user.UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
//import java.util.Scanner;
//
//@Configuration
//public class OperationProcessorsConfiguration {
//
//    @Bean
//    @Scope("singleton")
//    public CreateUserProcessor createUserProcessor(Scanner scanner, UserService userService) {
//        return new CreateUserProcessor(scanner, userService);
//    }
//
//    @Bean
//    @Scope("singleton")
//    public CreateAccountProcessor createAccountProcessor(Scanner scanner, UserService userService, AccountService accountService) {
//        return new CreateAccountProcessor(scanner, userService, accountService);
//    }
//
//    @Bean
//    @Scope("singleton")
//    public ShowAllUserProcessor showAllUserProcessor(UserService userService) {
//        return new ShowAllUserProcessor(userService);
//    }
//
//    @Bean
//    @Scope("singleton")
//    public DepositAccountProcessor depositAccountProcessor(Scanner scanner, AccountService accountService) {
//        return new DepositAccountProcessor(scanner, accountService);
//    }
//
//    @Bean
//    @Scope("singleton")
//    public WithdrawAccountProcessor withdrawAccountProcessor(Scanner scanner, AccountService accountService) {
//        return new WithdrawAccountProcessor(scanner, accountService);
//    }
//
//    @Bean
//    @Scope("singleton")
//    public CloseAccountProcessor closeAccountProcessor(Scanner scanner, AccountService accountService, UserService userService) {
//        return new CloseAccountProcessor(scanner, accountService, userService);
//    }
//
//    @Bean
//    @Scope("singleton")
//    public TransferAccountProcessor transferAccountProcessor(Scanner scanner, AccountService accountService) {
//        return new TransferAccountProcessor(scanner, accountService);
//    }
//
//}