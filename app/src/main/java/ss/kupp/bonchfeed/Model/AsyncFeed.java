package ss.kupp.bonchfeed.Model;

import android.os.AsyncTask;

import com.rometools.rome.feed.synd.SyndFeed;

import java.util.concurrent.ExecutionException;

public class AsyncFeed extends AsyncTask<String,String,SyndFeed> {

        @Override
        protected SyndFeed doInBackground (String...params){
            SyndFeed feed;
            feed = FeedLoader.getFeed(params[0]);
            return feed;
        }

        @Override
        protected void onPostExecute (SyndFeed f){
            super.onPostExecute(f);
        }

        public static SyndFeed getFeed(String s) {
            AsyncFeed af = new AsyncFeed();
            try {
                return af.execute(s).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }


}

