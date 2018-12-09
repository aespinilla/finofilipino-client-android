package es.aespinilla.software.android.finofilipinoclient.api;

import android.support.annotation.Nullable;
import android.util.Log;
import com.google.gson.Gson;
import es.aespinilla.software.android.finofilipinoclient.listeners.FinoCallback;
import es.aespinilla.software.android.finofilipinoclient.models.post.Post;
import es.aespinilla.software.android.finofilipinoclient.models.error.FinoError;
import es.aespinilla.software.android.finofilipinoclient.models.tag.Tag;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class FinoClient implements InterfaceService{

    private static final String TAG = "FINOCLIENT";

    private static FinoClient instance = null;

    private FinoRESTClient finoRESTClient;

    private Gson gson;

    public static FinoClient getInstance(){
        if (instance == null){
            instance = new FinoClient();
        }
        return instance;
    }

    private FinoClient(){
        this.gson = new Gson();
        this.finoRESTClient = ServiceGenerator.createService(FinoRESTClient.class);
    }

    @Override
    public void fetchPosts(@Nullable Integer page, final FinoCallback<List<Post>> listCallback) {
        GenericResponse<List<Post>> genericResponse = new GenericResponse<>(listCallback);
        this.finoRESTClient.fetchPosts(null, page, null).enqueue(genericResponse);
    }

    @Override
    public void fetchPostById(Integer postId, final FinoCallback<Post> postFinoCallback) {
        GenericResponse<Post> genericResponse = new GenericResponse<>(postFinoCallback);
        this.finoRESTClient.fetchPostById(postId).enqueue(genericResponse);
    }

    @Override
    public void fetchPopularPosts(@Nullable Integer limit, FinoCallback<List<Post>> listFinoCallback) {
        GenericResponse<List<Post>> genericResponse = new GenericResponse<>(listFinoCallback);
        this.finoRESTClient.fetchPopularPost(limit).enqueue(genericResponse);
    }

    @Override
    public void searchPosts(String query, @Nullable Integer page, FinoCallback<List<Post>> listFinoCallback) {
        GenericResponse<List<Post>> genericResponse = new GenericResponse<>(listFinoCallback);
        this.finoRESTClient.fetchPosts(query, page, null).enqueue(genericResponse);
    }

    @Override
    public void searchPostsByTag(Integer tagId, @Nullable Integer page, FinoCallback<List<Post>> listFinoCallback) {
        GenericResponse<List<Post>> genericResponse = new GenericResponse<>(listFinoCallback);
        this.finoRESTClient.fetchPosts(null, page, tagId).enqueue(genericResponse);
    }

    @Override
    public void fetchTags(@Nullable Integer page, FinoCallback<List<Tag>> listFinoCallback) {
        GenericResponse<List<Tag>> genericResponse = new GenericResponse<>(listFinoCallback);
        this.finoRESTClient.fetchTags(page).enqueue(genericResponse);
    }


    /**
     * Parse error response
     * @param response
     * @return FinoError parsed
     */
    private FinoError parseError(Response response){
        FinoError finoError = new FinoError();
        try {
            String raw = response.errorBody().string();
            finoError = this.gson.fromJson(raw, FinoError.class);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }
        return finoError;
    }

    /**
     * Parametrized class for generic reponse
     * @param <T>
     */
    private class GenericResponse<T> implements Callback<T> {

        private FinoCallback<T> finoCallback;

        public GenericResponse(FinoCallback<T> finoCallback) {
            this.finoCallback = finoCallback;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.code() == 200){
                Log.i(TAG, "Data: "+response.body());
                this.finoCallback.onSucessResponse(response.body());
            }else{
                FinoError finoError = parseError(response);
                Log.e(TAG, finoError.toString());
                this.finoCallback.onErrorResponse(finoError);
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            if (t != null && t.getLocalizedMessage() != null){
                Log.e(TAG, t.getLocalizedMessage());
            }else{
                Log.e(TAG, "Failure on connect!");
            }
            this.finoCallback.onFailureError(t);
        }
    }
}
