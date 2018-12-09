package es.aespinilla.software.android.finoexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import es.aespinilla.software.android.finofilipinoclient.listeners.FinoCallback;
import es.aespinilla.software.android.finofilipinoclient.api.FinoClient;
import es.aespinilla.software.android.finofilipinoclient.models.post.Post;
import es.aespinilla.software.android.finofilipinoclient.models.error.FinoError;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //fab.setVisibility(View.GONE);

        //fetchPosts();
        //fetchPostById();
        fetchPopularPost();
    }


    private void fetchPosts(){
        //FinoRestClient finoRestClient = FinoRestClient.getInstance();
        FinoClient.getInstance().fetchPosts(1, new FinoCallback<List<Post>>() {
            @Override
            public void onSucessResponse(List<Post> r) {
                Log.d("MAIN", r.toString());
            }

            @Override
            public void onErrorResponse(FinoError finoError) {

            }

            @Override
            public void onFailureError(Throwable t) {
                Log.e("MAIN", t.getLocalizedMessage());
            }
        });
    }

    private void fetchPostById(){
        //3635
        FinoClient.getInstance().fetchPostById(9999, new FinoCallback<Post>() {
            @Override
            public void onSucessResponse(Post r) {
                Log.d("MAIN", r.toString());
            }

            @Override
            public void onErrorResponse(FinoError finoError) {

            }

            @Override
            public void onFailureError(Throwable t) {
                Log.e("MAIN", t.getLocalizedMessage());
            }
        });
    }

    private void fetchPopularPost(){
        FinoClient.getInstance().fetchPopularPosts(2, new FinoCallback<List<Post>>() {
            @Override
            public void onSucessResponse(List<Post> r) {
                Log.d("MAIN", r.toString());
            }

            @Override
            public void onErrorResponse(FinoError finoError) {

            }

            @Override
            public void onFailureError(Throwable t) {
                Log.e("MAIN", t.getLocalizedMessage());
            }
        });
    }
}
