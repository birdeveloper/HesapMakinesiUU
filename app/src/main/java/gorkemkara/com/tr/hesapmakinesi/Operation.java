package gorkemkara.com.tr.hesapmakinesi;

/**
 * Created by GÖRKEM KARA on 05-04-2019.
 */

public abstract class Operation {




    public abstract Number doCalculation(Number first, Number second) throws Exception;

    protected static Number format(Double number){
        if (number % 1 == 0) {
            return number.longValue();
        } else {
            return  number;
        }
    }


}
