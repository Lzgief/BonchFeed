package ss.kupp.bonchfeed.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ss.kupp.bonchfeed.Model.AsyncFeed;
import ss.kupp.bonchfeed.Presenter.FeedAdapter;
import ss.kupp.bonchfeed.R;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Создает на экране скролл-лист
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        FeedAdapter adapter = new FeedAdapter(AsyncFeed.getFeed( "https://www.sut.ru/news/public/rss/feed"), getBaseContext());
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Создает кнопку рефреша
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // При нажатии на кнопку рефрешит
        if(item.getItemId() == R.id.menu_refresh) {
            FeedAdapter adapter = new FeedAdapter(AsyncFeed.getFeed( "https://habr.com/ru/rss/all/all/"), getBaseContext());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        return true;
    }
}
