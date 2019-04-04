package gorkemkara.com.tr.hesapmakinesi;

/**
 * Created by GÖRKEM KARA on 05-04-2019.
 */

public class Division extends Operation {

    public Division() {

    }

    @Override
    public Number doCalculation(Number first, Number second) throws Exception{

        if (second.doubleValue() == 0) {
            throw new Exception("Error - Division by Zero");
        } else {
            Double result = first.doubleValue() / second.doubleValue();

            return format(result);
        }
    }

}
