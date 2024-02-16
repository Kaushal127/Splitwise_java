package org.example.command;

import org.example.controllers.ExpenseController;
import org.example.controllers.GroupController;
import org.example.controllers.UserController;
import org.example.controllers.UserExpenseController;
import org.example.repositories.GroupRepository;
import org.example.repositories.UserRepository;
import org.example.services.ExpenseService;
import org.example.services.GroupService;
import org.example.services.UserExpenseService;
import org.example.services.UserService;
import org.example.repositories.ExpenseRepository;
import org.example.repositories.UserExpenseRepository;
import org.example.strategies.HeapSettleStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandMain {
    public static void main(String[] args) {
        CommandExecutor commandExecutor = getCommandExecutor();

        Scanner scan = new Scanner(System.in) ;

          while (true){
              System.out.println("Enter a Command or exit to end : ");
              String input = scan.nextLine() ;
              if (input.equalsIgnoreCase("exit")){
                  break ;
              }
              commandExecutor.execute(input);
          }
    }

    private static CommandExecutor getCommandExecutor() {
        GroupRepository groupRepository = new GroupRepository() ;
        ExpenseRepository expenseRepository = new ExpenseRepository() ;
        UserRepository userRepository = new UserRepository() ;
        UserExpenseRepository userExpenseRepository = new UserExpenseRepository() ;

        GroupService groupService = new GroupService(groupRepository ,userRepository);
        ExpenseService expenseService = new ExpenseService(expenseRepository,groupRepository) ;
        UserService userService = new UserService(userExpenseRepository,groupRepository,userRepository,new HeapSettleStrategy());
        UserExpenseService userExpenseService = new UserExpenseService(userExpenseRepository ,userRepository,expenseRepository);

        GroupController groupController = new GroupController(groupService) ;
        ExpenseController expenseController = new ExpenseController(expenseService);
        UserController userController = new UserController(userService);
        UserExpenseController userExpenseController = new UserExpenseController(userExpenseService);

        List<Command> commands = new ArrayList<>() ;
        AddGroupCommand addGroupCommand = new AddGroupCommand(groupController);
        AddMemberCommand addMemberCommand = new AddMemberCommand(groupController);
        ExpenseCommand expenseCommand = new ExpenseCommand(expenseController);
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(userController);
        SettleUpUserCommand settleUpUserCommand = new SettleUpUserCommand(userController);
        UserExpenseCommand userExpenseCommand = new UserExpenseCommand(userExpenseController);

        /*  commands.add(addGroupCommand);
          commands.add(addMemberCommand);
          commands.add(expenseCommand);
          commands.add(registerUserCommand);
          commands.add(settleUpUserCommand);
          commands.add(userExpenseCommand);
*/
        CommandExecutor commandExecutor = new CommandExecutor(commands) ;
        commandExecutor.addCommand(addGroupCommand);
        commandExecutor.addCommand(addMemberCommand);
        commandExecutor.addCommand(expenseCommand);
        commandExecutor.addCommand(registerUserCommand);
        commandExecutor.addCommand(settleUpUserCommand);
        commandExecutor.addCommand(userExpenseCommand);
        return commandExecutor;
    }
}
