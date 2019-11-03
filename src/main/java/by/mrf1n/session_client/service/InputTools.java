package by.mrf1n.session_client.service;

import by.mrf1n.model.CommandType;

import java.util.Scanner;

public class InputTools {
    private static Scanner scanner = new Scanner(System.in);


    public static Integer getInteger(Integer left, Integer right) {
        if (scanner.hasNextBigInteger()) {
            Integer nextInt = scanner.nextInt();
            if (nextInt >= left && nextInt <= right) {
                return nextInt;
            } else {
                System.out.println("Пожалуйста введите число из диапазона {" + left + "," + right + "} .");
            }
        } else {
            scanner.nextLine();
            System.out.println("Пожалуйста введите число.");
        }
        return getInteger(left, right);
    }

    public static boolean anyMatchCommandType(CommandType commandType, CommandType ... types) {
        for (CommandType type : types) {
            if(type.equals(commandType)){
                return true;
            }
        }
        return false;
    }

    public static String getString() {
        scanner.nextLine();
        return scanner.nextLine();
    }

//    public static Message getMessage() {
//        scanner.nextLine();
//        System.out.println("Пожалуйста введите отправителя.");
//        String sender = scanner.nextLine();
//        System.out.println("Пожалуйста введите получателя.");
//        String recipient = scanner.nextLine();
//        System.out.println("Пожалуйста введите сообщение.");
//        String message = scanner.nextLine();
//        return new Message(sender, recipient, message);
//    }
//
//    public static Message getMessage(Message oldMessage) {
//        scanner.nextLine();
//        System.out.println("Пожалуйста введите отправителя. Старый - " + oldMessage.getSender());
//        String sender = scanner.nextLine();
//        System.out.println("Пожалуйста введите получателя. Старый - " + oldMessage.getRecipient());
//        String recipient = scanner.nextLine();
//        System.out.println("Пожалуйста введите сообщение. Старое - " + oldMessage.getMessage());
//        String message = scanner.nextLine();
//        return new Message(sender, recipient, message);
//    }

//    public static void saveToXmlFile(Message message) {
//            try {
//                File file = new File(xmlFilePath);
//                JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
//                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//                jaxbMarshaller.marshal(message, file);
//                jaxbMarshaller.marshal(message, System.out);
//            } catch (JAXBException e) {
//                e.printStackTrace();
//            }
//
//    }
}
