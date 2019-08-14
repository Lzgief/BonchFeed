package ss.kupp.bonchfeed.Presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rometools.rome.feed.synd.SyndFeed;

import ss.kupp.bonchfeed.R;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView txtTitle,txtPubDate;
    private ItemClickListener itemClickListener;

    FeedViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtPubDate = itemView.findViewById(R.id.txtPubDate);

        itemView.setOnClickListener(this);
    }

    void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>{

    private Context mContext;
    private LayoutInflater inflater;
    private SyndFeed feed;

    public FeedAdapter(SyndFeed feed, Context mContext) {
        this.feed = feed;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }


    // Создает ВьюХолдер, пассивный код

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row,parent, false);
        return new FeedViewHolder(itemView);
    }


    // Связывает Объект ВьюХолдера с объектом Фида, активный код

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder feedViewHolder, int position) {

        feedViewHolder.txtTitle.setText(feed.getEntries().get(position).getTitle());
        feedViewHolder.txtPubDate.setText(feed.getEntries().get(position).getPublishedDate().toString());

        feedViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(feed.getEntries().get(position).getLink()));
                    mContext.startActivity(browserIntent);
                    }
                }
            }
        );
    }

    @Override
    public int getItemCount() {
        return feed.getEntries().size();
    }
}



