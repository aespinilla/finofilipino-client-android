package es.aespinilla.software.android.finofilipinoclient.api;

import android.support.annotation.Nullable;
import es.aespinilla.software.android.finofilipinoclient.models.post.Post;
import es.aespinilla.software.android.finofilipinoclient.models.tag.Tag;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface FinoRESTClient {

    @GET("wp/v2/posts")
    Call<List<Post>> fetchPosts(@Nullable @Query("search") String query,
                                @Nullable @Query("page") Integer page,
                                @Nullable @Query("tags") Integer tag);

    @GET("wp/v2/posts/{Id}")
    Call<Post> fetchPostById(@Path("Id") Integer postId);

    @GET("wordpress-popular-posts/v1/popular-posts")
    Call<List<Post>> fetchPopularPost(@Nullable @Query("limit") Integer limit);

    @GET("wp/v2/tags")
    Call<List<Tag>> fetchTags(@Nullable @Query("page") Integer page);
}
