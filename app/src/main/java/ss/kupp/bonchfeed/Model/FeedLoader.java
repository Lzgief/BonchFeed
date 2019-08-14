package ss.kupp.bonchfeed.Model;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;

public class FeedLoader {

    public static SyndFeed getFeed(String s){

        try {
            URL feedUrl = new URL(s);

            SyndFeedInput input = new SyndFeedInput();

            return input.build(new XmlReader(feedUrl));
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
