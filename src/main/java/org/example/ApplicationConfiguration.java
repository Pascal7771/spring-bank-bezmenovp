package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import java.util.Scanner;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @Bean
    @Scope("singleton")
    public Scanner scanner() {
        return new Scanner(System.in);
    }

//    @Bean
//    @Scope("singleton")
//    public OperationConsoleListener operationConsoleListener(
//            Scanner scanner,
//            List<OperationCommandProcessor> operationCommandProcessors
//    ) {
//        Map<ConsoleOperationType, OperationCommandProcessor> processorMap = operationCommandProcessors.stream().collect(
//                Collectors.toMap(
//                        OperationCommandProcessor::getOperationType,
//                        processor -> processor
//                )
//        );
//        return new OperationConsoleListener(scanner, processorMap);
//    }

//    @Bean
//    @Scope("singleton")
//    public AccountService accountService(
//            @Value(value = "${account.default-amount}") int defaultAccountAmount,
//            @Value(value = "${account.transfer-commission}") double transferCommission
//    ) {
//        return new AccountService(defaultAccountAmount, transferCommission);
//    }
//
//    @Bean
//    @Scope("singleton")
//    public UserService userService(AccountService accountService) {
//        return new UserService(accountService);
//    }

}