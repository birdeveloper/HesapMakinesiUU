package gorkemkara.com.tr.hesapmakinesi;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by GÖRKEM KARA on 05-04-2019.
 */

public class Calculator extends Observable implements Observer {

    //Properties
    private boolean hasUpdated;
    private boolean hasDecimal;
    private boolean hasBracket;
    public boolean isForumalaModeOn;
    private Display display;
    private Display resultDisplay;
    private Number operand;
    private Operation operation;
    private String myMessage;


    // hesap makinesini başlat
    public Calculator() {
        display = new Display();
        resultDisplay = new Display();
        setOperand(null);
        setOperation(null);
        setMessage(null);

        hasUpdated = false;
        hasDecimal = false;
        hasBracket = false;
        isForumalaModeOn = false;

        display.addObserver(this);
        resultDisplay.addObserver(this);
    }


    // HAFIZADA HESAPLAYIP SONUCU DÖNDER
    public void calculate() {
        Number result;

        if(getOperation() == null) {
            return;
        }

        if (getOperand() == null) {
            setOperand(getDisplay());

        }

        if (hasUpdated) {
            try {
                Number first = getOperand();
                setOperand(getDisplay());
                result = getOperation().doCalculation(first, getOperand());
                setOperation(null);
                setOperand(null);
                appendDisplay(result, true);
//                appendResultDisplay(result, true);
                hasDecimal = false;
                hasUpdated = false;
                hasBracket = false;
                isForumalaModeOn = false;
            } catch (Exception except) {
                setMessage(except.getMessage());
                setChanged();
                notifyObservers();
            }
        } else if ((hasUpdated) && (isForumalaModeOn)) {
            try {
                Number first = getOperand();
                setOperand(getDisplay());
                result = getOperation().doCalculation(first, getOperand());
                setOperation(null);
                setOperand(null);
                appendDisplay(result,false);
                hasDecimal = false;
                hasUpdated = true;
                hasBracket = false;
                isForumalaModeOn = true;
            } catch (Exception except) {
                setMessage(except.getMessage());
                setChanged();
                notifyObservers();
            }
        }
    }

    //GÖRÜNECEK YAZI
    public String getDisplay() {
        return display.getMyMessage();
    }

    //mETİNİ EKRANA EKLE
    public void appendDisplay(String myMessage) {
        appendDisplay(myMessage, false);
    }

    //METNİ DEĞİŞTİR
    public void appendDisplay(String myMessage, boolean isReplaced) {
        if (isReplaced) {
            display.setMyMessage(myMessage);
            display.setAppendableOff();
        } else {
            display.appendMessage(myMessage);
        }

        setChanged();
        notifyObservers();
    }


    public void appendResultDisplay(String myMessage, boolean isReplaced) {
        if(isReplaced) {
            resultDisplay.setMyMessage(myMessage);
            resultDisplay.setAppendableOff();
        } else {
            resultDisplay.appendMessage(myMessage);
        }

        setChanged();
        notifyObservers();
    }


    public void appendDisplay(Number number, boolean isReplaced) {
        String myMessage;

        if (number.doubleValue() > 99999999) {
            myMessage = formatNumber(number);
        } else {
            myMessage = number.toString();
        }

        appendDisplay(myMessage, isReplaced);
    }


    public String formatNumber(Number number) {
        NumberFormat myFormat = new DecimalFormat("0.####E0");
        return myFormat.format(number.doubleValue());
    }

    public void addition() {
        setOperation(new Addition());
    }

    public void multiplication(){
        setOperation(new Multiplication());
    }

    public void subtraction(){
        setOperation(new Subtraction());
    }

    public void division(){
        setOperation(new Division());
    }

    public void clear() {
        setOperand(null);
        setOperation(null);
        appendDisplay("0", true);
        hasDecimal = false;
        hasBracket = false;
    }

       public void appendDecimal() {
        if (!hasDecimal) {
            appendDisplay(".");
            hasDecimal = true;
        }
    }

    public  void appendOpenBracket() {
        if(!hasBracket) {
            appendDisplay("(");
            hasBracket = true;
        }
    }

    public void appendCloseBracket() {
        if(hasBracket) {
            appendDisplay(")");
            hasBracket = false;
        }
    }

    private static boolean isInteger(Double number) {
        return number % 1 == 0;
    }

    private void setOperand(String operand) {
        if (operand == null){
            this.operand = null;
            return;
        }

        Double number = Double.valueOf(operand);

        if (isInteger(number)) {
            this.operand = number.intValue();
        } else {
            this.operand = number;
        }
    }

    public Number getOperand() {
        return operand;
    }


    private void setOperation(Operation operation) {
        if (operation == null) {
            this.operation = null;
            return;
        }

        calculate();
        setOperand(getDisplay());
        this.operation = operation;
        display.setAppendableOff();

    }

    private Operation getOperation() {
        return operation;
    }


    public String getMessage() {
        return myMessage;
    }
    public void setMessage(String message) {
        if(message == null) {
            myMessage = null;
        } else {
            myMessage = message;
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        hasUpdated = true;
    }
}
