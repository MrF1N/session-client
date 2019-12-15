package by.mrf1n.session_client;

import by.mrf1n.model.request.BodyContract;
import by.mrf1n.session_client.connection.MonoClient;
import by.mrf1n.session_client.service.InputTools;
import by.mrf1n.session_client.service.MenuTools;

import java.io.IOException;

import static by.mrf1n.model.CommandType.BAN_IP;
import static by.mrf1n.model.CommandType.KICK;
import static by.mrf1n.model.CommandType.QUIT;
import static by.mrf1n.model.CommandType.STATUS;

/**
 * Главный класс
 */

public class Application {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MonoClient monoClient = new MonoClient();
        monoClient.start();
        BodyContract transaction = monoClient.transaction(STATUS, "");
        if (!InputTools.anyMatchCommandType(transaction.getCommandType(), QUIT, BAN_IP, KICK)) {
            MenuTools menuTools = new MenuTools(monoClient);
            menuTools.mainMenu();
        } else {
            System.out.println(transaction.getBody());
        }
//        monoClient.stop();
    }
}
