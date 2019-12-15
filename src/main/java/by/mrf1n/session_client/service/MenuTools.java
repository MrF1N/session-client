package by.mrf1n.session_client.service;

import by.mrf1n.model.CommandType;
import by.mrf1n.model.request.BodyContract;
import by.mrf1n.session_client.connection.MonoClient;
import by.mrf1n.session_client.model.MenuItem;

import java.io.IOException;
import java.util.Arrays;

import static by.mrf1n.model.CommandType.BAN_IP;
import static by.mrf1n.model.CommandType.CANT;
import static by.mrf1n.model.CommandType.KICK;
import static by.mrf1n.model.CommandType.QUIT;
import static by.mrf1n.model.CommandType.RESPONSE_ANSWER;
import static by.mrf1n.model.CommandType.RESPONSE_JOB;
import static by.mrf1n.model.CommandType.RUN_ANSWER;
import static by.mrf1n.model.CommandType.RUN_JOB;
import static by.mrf1n.model.CommandType.STATUS;
import static by.mrf1n.session_client.service.InputTools.anyMatchCommandType;

/**
 * Класс меню приложения, содержит клиентскую логику ввода/вывода на экран и общения с сервером
 */

public class MenuTools {
    private MonoClient monoClient;

    public MenuTools(MonoClient monoClient) {
        this.monoClient = monoClient;
    }

    private MenuItem mainMenuItem = new MenuItem(
            "Главное меню",
            MenuTools::mainMenu
    );
    private MenuItem checkStatusMenuItem = new MenuItem(
            "Проверить статус",
            menuTools -> {
                try {
                    menuTools.checkStatusMenu();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
    );
    private MenuItem runJobMenuItem = new MenuItem(
            "Решить задачу",
            menuTools -> {
                try {
                    menuTools.runJobMenu();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
    );
    private MenuItem exitMenuItem = new MenuItem(
            "Выйти",
            menuTools -> {
                try {
                    menuTools.exitMenu();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
    );

    public void mainMenu() {
        mainMenuItem.setRelatedItems(Arrays.asList(checkStatusMenuItem, runJobMenuItem, exitMenuItem));
        mainMenuItem.printMenuItems();
        int left = 1;
        int right = mainMenuItem.getCountRelatedItems();
        Integer menuNumber = InputTools.getInteger(left, right);
        mainMenuItem.runNextMenuItem(menuNumber, monoClient);
        if (menuNumber != right) {
            mainMenu();
        }
    }

    private void checkStatusMenu() throws IOException, ClassNotFoundException {
        if (monoClient.isActive()) {
            BodyContract transaction = monoClient.transaction(STATUS, "");
            CommandType commandType = transaction.getCommandType();
            Object body = transaction.getBody();
            System.out.println(body);
            if (anyMatchCommandType(commandType, QUIT, BAN_IP, KICK)) {
                monoClient.stop();
                System.out.println("You are disconnected!");
                System.exit(1);
            }
        } else {
            monoClient.stop();
            System.out.println("You are disconnected!");
            System.exit(1);
        }
    }

    private void runJobMenu() throws IOException, ClassNotFoundException {
        if (monoClient.isActive()) {
            BodyContract transaction = monoClient.transaction(RUN_JOB, "");
            CommandType commandType = transaction.getCommandType();
            Object body = transaction.getBody();
            System.out.println(body);
            if (anyMatchCommandType(commandType, QUIT, BAN_IP, KICK)) {
                monoClient.stop();
                System.out.println("You are disconnected for it!");
                System.exit(1);
            }
            if (anyMatchCommandType(commandType, RESPONSE_JOB)) {
                String string = InputTools.getString();
                BodyContract contract = monoClient.transaction(RUN_ANSWER, string);
                commandType = contract.getCommandType();
                if (anyMatchCommandType(commandType, QUIT, BAN_IP, KICK)) {
                    monoClient.stop();
                    System.out.println("You are disconnected for it!");
                    System.exit(1);
                }
                if (anyMatchCommandType(commandType, RESPONSE_ANSWER)){
                    System.out.println(contract.getBody());
                }
            }
        } else {
            monoClient.stop();
            System.out.println("You are disconnected!");
            System.exit(1);
        }
    }

    private void exitMenu() throws IOException, ClassNotFoundException {
        monoClient.stop();
    }
}
