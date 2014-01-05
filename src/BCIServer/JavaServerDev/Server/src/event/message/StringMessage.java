package event.message;

import java.util.LinkedList;
import java.util.ListIterator;

import dataPackages.EEGData;
import dataPackages.RawData;
import osc.OSCMessage;

public class StringMessage implements Message {
    private final String address;
    private final String[] data;
    private final String unsplitData;

    public StringMessage(String add, String d) {
        address = add;
        unsplitData = d;
        data = d.split(",");
    }

    @Override
    public LinkedList<OSCMessage> toMessage(EEGData e) {
        LinkedList<OSCMessage> l = new LinkedList<OSCMessage>();
        LinkedList<Object> o = new LinkedList<Object>();
        for (int i = 0; i < data.length; i++) {
            switch (data[i].toLowerCase()) {
            case ":rawdata":
                ListIterator<RawData> li = e.rawData.listIterator();
                while (li.hasNext()) {
                    l.add(new OSCMessage(address, li.next().toArray()));
                }
                break;
            case "emostate":
                Object[] oa = e.emoState.toArray();
                for (int j = 0; j < oa.length; j++) {
                    o.add(oa[j]);
                }
                break;
            case "emostate.meditation":
                o.add(e.emoState.meditation);
                break;
            case "emostate.frustration":
                o.add(e.emoState.frustration);
                break;
            case "emostate.excitement":
                o.add(e.emoState.excitement);
                break;
            case "emostate.engagement":
                o.add(e.emoState.engagement);
                break;
            case "=>rawdata":
                ListIterator<RawData> lif = e.rawData.listIterator();
                while (lif.hasNext()) {
                    Object[] oaf = lif.next().toArray();
                    for (int j = 0; j < oaf.length; j++) {
                        o.add(oaf[j]);
                    }
                }
                break;
            default:
                if (data[i].startsWith("=>")) {
                    o.add(data[i]);
                } else {
                    l.add(new OSCMessage(address, new Object[]{data[i]}));
                }
                break;
            }
        }
        if (!o.isEmpty()) {
            l.add(new OSCMessage(address, o.toArray()));
        }
        return l;
    }

}
