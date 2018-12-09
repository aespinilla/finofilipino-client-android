# FinoFilipino Client
Android library for query Finofilipino website. This library can retreive popular post, posts by page, post by id, posts by tag and tags.

## Install

Add to project build.gradle

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add dependency as normal

```
dependencies {
	   implementation 'com.github.aespinilla:finofilipino-client-android:Tag'
}
```

## Usage

Contains this methods: 

```
void fetchPosts(@Nullable Integer page, FinoCallback<List<Post>> listCallback);
void fetchPostById(Integer postId, FinoCallback<Post> postFinoCallback);
void fetchPopularPosts(@Nullable Integer limit, FinoCallback<List<Post>> listFinoCallback);
void searchPosts(String query, @Nullable Integer page, FinoCallback<List<Post>> listFinoCallback);
void searchPostsByTag(Integer tagId, @Nullable Integer page, FinoCallback<List<Post>> listFinoCallback);
void fetchTags(@Nullable Integer page, FinoCallback<List<Tag>> listFinoCallback);
```

Implments callback like this

```
FinoClient.getInstance().fetchPosts(null, new FinoCallback<List<Post>>() {
            @Override
            public void onSucessResponse(List<Post> r) {
                //Contains post
            }

            @Override
            public void onErrorResponse(FinoError finoError) {
                //Error on web like not found post id
            }

            @Override
            public void onFailureError(Throwable t) {
                //Error by conectivity
            }
        });
```
 

## Author
* **Alberto Espinilla**
