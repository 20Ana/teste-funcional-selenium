import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class uteis {
    public ArrayList<String> data (){
        Calendar calendar = new GregorianCalendar();
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        ArrayList<String> aux = new ArrayList<String>();

        aux.add(new DateFormatSymbols().getWeekdays()[calendar.get(Calendar.DAY_OF_WEEK)].toLowerCase());
        aux.add(meses[calendar.get(calendar.MONTH)].toLowerCase());
        aux.add(Integer.toString(calendar.get(calendar.YEAR)));

        return aux;
    }
}
