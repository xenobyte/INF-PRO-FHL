package user_Interface;

import threads.Controller;
import threads.Event;

public class StringParser {
    Controller ctrl;

    public StringParser(Controller c) {
        ctrl = c;
    }

    public String doMagic(String s) {
        String returnString = null;
        String temp;
        String[] commands = s.split(";");
        for (int i = 0; i < commands.length; i++) {
            String[] command = commands[i].split("[()]");
            temp = command[0].toLowerCase();
            if (temp.equals("createthread")) {
                ctrl.createNewMessageThread(command[1]);
            } else {
                String[] splitCommand = command[0].split("\\.");
                temp = splitCommand[1].toLowerCase();
                String name = splitCommand[0];
                switch (temp) {
                case "block":
                    ctrl.blockThread(name);
                    break;
                case "unblock":
                    ctrl.unblockThread(name);
                    break;
                case "addevent":
                    String[] addCommand = command[1].split("\\|");
                    if (addCommand.length == 1) {
                        ctrl.addEvent(name, new Event(addCommand[0]));
                    } else {                    
                    ctrl.addEvent(name, new Event(addCommand[0], addCommand[1]));
                    }
                    break;
                case "kill":
                    ctrl.killThread(name);
                    break;
                case "start":
                    ctrl.startThread(name);
                    break;
                case "show":
                    returnString += ctrl.getString(name);
                }
            }
        }
        return returnString;
    }
}
