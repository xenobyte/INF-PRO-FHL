package com.osc;

import java.net.SocketException;
import java.util.Date;
import java.util.LinkedList;

import controll.Controller;

import event.AlwaysSendEvent;
import event.OneTimeEvent;
import event.condition.Condition;
import event.condition.FloatCondition;
import event.condition.IntegerCondition;
import event.condition.StringCondition;
import event.message.FloatMessage;
import event.message.IntegerMessage;
import event.message.Message;
import event.message.StringMessage;

import osc.OSCListener;
import osc.OSCMessage;
import osc.OSCPortIn;

public class OSCInput {
    private static OSCPortIn portIn;
    private static Controller controller;

    private static OSCListener createThread = new OSCListener() {
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());
            Object[] o = message.getArguments();
            if (o.length == 4 && o[0] instanceof String && o[1] instanceof String && o[2] instanceof Integer && o[3] instanceof Float) {
                controller.createNewMessageThread((String) o[0], (String) o[1], (int) o[2], (float) o[3]);
            } else {
                System.err.println("Invalid Message");
            }
        }
    };

    private static OSCListener blockThread = new OSCListener() {
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());
            Object[] o = message.getArguments();
            if (o.length == 1 && o[0] instanceof String) {
                controller.blockThread((String) o[0]);
            } else {
                System.err.println("Invalid Message");
            }
        }
    };

    private static OSCListener unblockThread = new OSCListener() {
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());
            Object[] o = message.getArguments();
            if (o.length == 1 && o[0] instanceof String) {
                controller.unblockThread((String) o[0]);
            } else {
                System.err.println("Invalid Message");
            }
        }
    };

    private static OSCListener startThread = new OSCListener() {
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());
            Object[] o = message.getArguments();
            if (o.length == 1 && o[0] instanceof String) {
                controller.startThread((String) o[0]);
            } else {
                System.err.println("Invalid Message");
            }
        }
    };

    private static OSCListener killThread = new OSCListener() {
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());
            Object[] o = message.getArguments();
            if (o.length == 1 && o[0] instanceof String) {
                controller.killThread((String) o[0]);
            } else {
                System.err.println("Invalid Message");
            }
        }
    };

    private static OSCListener addPackages = new OSCListener() {
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());
            Object[] o = message.getArguments();
            LinkedList<Message> m = new LinkedList<Message>();
            if (o.length % 2 == 1 && o[0] instanceof String) {
                for (int i = 1; i < o.length; i += 2) {
                    if (o[i] instanceof String) {
                        if (o[i + 1] instanceof String) {
                            m.add(new StringMessage((String) o[i], (String) o[i + 1]));
                        } else if (o[i + 1] instanceof Integer) {
                            m.add(new IntegerMessage((String) o[i], (Integer) o[i + 1]));
                        } else if (o[i + 1] instanceof Float) {
                            m.add(new FloatMessage((String) o[i], (Float) o[i + 1]));
                        } else {
                            System.err.println("Invalid Message");
                            return;
                        }
                    } else {
                        System.err.println("Invalid Message");
                        return;
                    }
                }
                if (m.isEmpty()) {
                    System.err.println("Invalid Message");
                } else {
                    controller.addEvent((String) o[0], new AlwaysSendEvent(new LinkedList<Condition>(), m));
                }
            } else {
                System.err.println("Invalid Message");
            }
        }
    };

    private static OSCListener addAlwaysSendEvent = new OSCListener() {
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());
            Object[] o = message.getArguments();
            LinkedList<Message> m = new LinkedList<Message>();
            LinkedList<Condition> c = new LinkedList<Condition>();
            if (o[0] instanceof String) {
                for (int i = 1; i < o.length;) {
                    if (o[i] instanceof String && isVariable((String) o[i]) && o.length > i + 2) {
                        if (o[i + 1] instanceof String) {
                            if (o[i + 2] instanceof String) {
                                c.add(new StringCondition((String) o[i], (String) o[i + 1], (String) o[i + 2]));
                            } else if (o[i + 2] instanceof Integer) {
                                c.add(new IntegerCondition((String) o[i], (String) o[i + 1], (Integer) o[i + 2]));
                            } else if (o[i + 2] instanceof Float) {
                                c.add(new FloatCondition((String) o[i], (String) o[i + 1], (Float) o[i + 2]));
                            } else {
                                System.err.println("Invalid Message");
                                return;
                            }
                            i += 3;
                        } else {
                            System.err.println("Invalid Message");
                            return;
                        }
                    } else if (o[i] instanceof String && o.length > i + 1) {
                        if (o[i + 1] instanceof String) {
                            m.add(new StringMessage((String) o[i], (String) o[i + 1]));
                        } else if (o[i + 1] instanceof Integer) {
                            m.add(new IntegerMessage((String) o[i], (Integer) o[i + 1]));
                        } else if (o[i + 1] instanceof Float) {
                            m.add(new FloatMessage((String) o[i], (Float) o[i + 1]));
                        } else {
                            System.err.println("Invalid Message");
                            return;
                        }
                        i += 2;
                    }
                }
                if (m.isEmpty()) {
                    System.err.println("Invalid Message");
                } else {
                    controller.addEvent((String) o[0], new AlwaysSendEvent(c, m));
                }
            } else {
                System.err.println("Invalid Message");
            }
        }
    };

    private static OSCListener addOneTimeEvent = new OSCListener() {
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());
            Object[] o = message.getArguments();
            LinkedList<Message> m = new LinkedList<Message>();
            LinkedList<Condition> c = new LinkedList<Condition>();
            if (o[0] instanceof String) {
                for (int i = 1; i < o.length;) {
                    if (o[i] instanceof String && isVariable((String) o[i]) && o.length > i + 2) {
                        if (o[i + 1] instanceof String) {
                            if (o[i + 2] instanceof String) {
                                c.add(new StringCondition((String) o[i], (String) o[i + 1], (String) o[i + 2]));
                            } else if (o[i + 2] instanceof Integer) {
                                c.add(new IntegerCondition((String) o[i], (String) o[i + 1], (Integer) o[i + 2]));
                            } else if (o[i + 2] instanceof Float) {
                                c.add(new FloatCondition((String) o[i], (String) o[i + 1], (Float) o[i + 2]));
                            } else {
                                System.err.println("Invalid Message");
                                return;
                            }
                            i += 3;
                        } else {
                            System.err.println("Invalid Message");
                            return;
                        }
                    } else if (o[i] instanceof String && o.length > i + 1) {
                        if (o[i + 1] instanceof String) {
                            m.add(new StringMessage((String) o[i], (String) o[i + 1]));
                        } else if (o[i + 1] instanceof Integer) {
                            m.add(new IntegerMessage((String) o[i], (Integer) o[i + 1]));
                        } else if (o[i + 1] instanceof Float) {
                            m.add(new FloatMessage((String) o[i], (Float) o[i + 1]));
                        } else {
                            System.err.println("Invalid Message");
                            return;
                        }
                        i += 2;
                    }
                }
                if (m.isEmpty()) {
                    System.err.println("Invalid Message");
                } else {
                    controller.addEvent((String) o[0], new OneTimeEvent(c, m));
                }
            } else {
                System.err.println("Invalid Message");
            }
        }
    };
    
    public OSCInput(int port, Controller c) {
        controller = c;
        try {
            portIn = new OSCPortIn(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        portIn.addListener("/Server/createThread", createThread);
        portIn.addListener("/Server/blockThread", blockThread);
        portIn.addListener("/Server/unblockThread", unblockThread);
        portIn.addListener("/Server/startThread", startThread);
        portIn.addListener("/Server/killThread", killThread);
        portIn.addListener("/Server/addPackages", addPackages);
        portIn.addListener("/Server/addEvent/AlwaysSend", addAlwaysSendEvent);
        portIn.addListener("/Server/addEvent/OneTime", addOneTimeEvent);
        portIn.startListening();
    }

    private static boolean isVariable(String s) {
        return !s.startsWith("/");
    }
}
