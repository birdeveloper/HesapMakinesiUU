package gorkemkara.com.tr.hesapmakinesi;

import java.util.Observable;

/**
 * Created by GÖRKEM KARA on 05-04-2019.
 */

public class Display extends Observable {

    private String myMessage;
    private boolean isAppendableOff;


    public Display() {
        setAppendableOff();
        setMyMessage("0");
    }

    public void setAppendableOn() {
        isAppendableOff = false;
    }
    public void setAppendableOff() {
        isAppendableOff = true;
    }

    public boolean isAppendableOff() {
        return isAppendableOff;
    }

    public void setMyMessage(String myMessage) {
        this.myMessage = myMessage;
    }
    public String getMyMessage() {
        return myMessage;
    }

    public void appendMessage(String myMessage) {
        if (myMessage == null) {
            return;
        }

        if (isAppendableOff()) {
            setMyMessage(myMessage);
            setAppendableOn();
        } else {
            setMyMessage(getMyMessage() + myMessage);
        }

        setChanged();
        notifyObservers();
    }



}
