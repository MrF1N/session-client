package by.mrf1n.session_client.connection;

import by.mrf1n.model.CommandType;
import by.mrf1n.model.request.BodyContract;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MonoClient {
    protected Socket serverDialog = null;
    protected ObjectOutputStream objectOutputStream;
    protected ObjectInputStream objectInputStream;

    public void start() throws IOException {
        serverDialog = new Socket("192.168.43.196", 3345);
        objectOutputStream = new ObjectOutputStream(serverDialog.getOutputStream());
        objectInputStream = new ObjectInputStream(serverDialog.getInputStream());

    }

    public void stop() throws IOException {
        if (!serverDialog.isOutputShutdown()) {
            BodyContract quit = new BodyContract();
            quit.setCommandType(CommandType.QUIT);
            quit.setBody("Disconnect.");
            objectOutputStream.writeObject(quit);
        }
        serverDialog.close();
        objectInputStream.close();
        objectOutputStream.close();
    }

    public boolean isActive() {
        return serverDialog.isConnected();
    }

    public BodyContract transaction(CommandType commandType, Object object) throws IOException, ClassNotFoundException {
        BodyContract bodyContract = new BodyContract();
        bodyContract.setCommandType(commandType);
        bodyContract.setBody(object);
        objectOutputStream.writeObject(bodyContract);
        return (BodyContract) objectInputStream.readObject();
    }
}
