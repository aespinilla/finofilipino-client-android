package es.aespinilla.software.android.finofilipinoclient.api;

import android.support.annotation.Nullable;
import es.aespinilla.software.android.finofilipinoclient.listeners.FinoCallback;
import es.aespinilla.software.android.finofilipinoclient.models.post.Post;
import es.aespinilla.software.android.finofilipinoclient.models.tag.Tag;

import java.util.List;

public interface InterfaceService {

    void fetchPosts(@Nullable Integer page, FinoCallback<List<Post>> listCallback);
    void fetchPostById(Integer postId, FinoCallback<Post> postFinoCallback);
    void fetchPopularPosts(@Nullable Integer limit, FinoCallback<List<Post>> listFinoCallback);
    void searchPosts(String query, @Nullable Integer page, FinoCallback<List<Post>> listFinoCallback);
    void searchPostsByTag(Integer tagId, @Nullable Integer page, FinoCallback<List<Post>> listFinoCallback);

    void fetchTags(@Nullable Integer page, FinoCallback<List<Tag>> listFinoCallback);
}
