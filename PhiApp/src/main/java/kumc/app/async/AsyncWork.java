package kumc.app.async;

import java.util.List;

/**
 * Created by septboy on 2014. 11. 11..
 */
public abstract class AsyncWork<T> {
    protected Object onPreExecute() {
        return null;
    };

    abstract protected List<T> doInBackground(Object param);

    protected void onPostExecute( List<T> params) {

    };
    public void execute(){}
}
