package gorkemkara.com.tr.hesapmakinesi;

/**
 * Created by GÖRKEM KARA on 05-04-2019.
 */

public class Addition extends Operation {

    public Addition() {

    }

    @Override
    public Number doCalculation(Number first, Number second){
        Double result = first.doubleValue() + second.doubleValue();

        return format(result);
    }
}
