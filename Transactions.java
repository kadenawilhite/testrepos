import java.util.Date;
import java.lang.Math;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class Transactions
{
    public enum City
    {
        HUNTINGTON, MORGANTOWN, VIENNA, DUNBAR, UNKNOWN;
    }
    private static ArrayList<Transactions> valid_transactions = new ArrayList<Transactions>();

    private String consumer_name;
    private String merchant_name;
    private int cost;
    private Date purchase_date_time;
    private City location;

    private static void validate(Transactions t)
    {
        boolean flag = false;
        Iterator it = valid_transactions.iterator();
        if(t.cost > 1000)
        {
            return;
        }

        for(Transactions tr : valid_transactions)
        {
            if(t.consumer_name.equals(tr.consumer_name) || (t.purchase_date_time.getTime() - tr.purchase_date_time.getTime() < 1500000) )
            {
                return;
            }
        }

        while(it.hasNext())
        {
            Transactions cur = (Transactions)it.next();
            if(t.location != cur.location)
            {
                return;
            }
        }
        if(flag)
        {
            valid_transactions.add(t);
        }
    }

    public Transactions(String cName, String mName, int cst, Date d, City loc)
    {
        this.consumer_name = cName;
        this.merchant_name = mName;
        this.cost = cst;
        this.purchase_date_time = d;
        this.location = loc;
        validate(this);
    }
}
