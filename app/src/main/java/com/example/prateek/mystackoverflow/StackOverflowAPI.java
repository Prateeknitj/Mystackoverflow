package com.example.prateek.mystackoverflow;



import com.example.prateek.mystackoverflow.models.Answer;
import com.example.prateek.mystackoverflow.models.Badges;
import com.example.prateek.mystackoverflow.models.Comment;
import com.example.prateek.mystackoverflow.models.Items;
import com.example.prateek.mystackoverflow.models.Question;
import com.example.prateek.mystackoverflow.models.Tag;
import com.example.prateek.mystackoverflow.models.TagScore;
import com.example.prateek.mystackoverflow.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StackOverflowAPI {
    // Questions
    @GET("questions?order=desc&sort=votes&site=stackoverflow&filter=!9YdnSIN18")
    Call<Items<Question>> loadAllQuestions(@Query("sort") String sort);

    @GET("questions?order=desc&site=stackoverflow&filter=!-*f(6rc.lFba")
    Call<Items<Question>> loadQuestionsByTag(@Query("tagged") String tags, @Query("sort") String sort);

    @GET("questions/{id}/answers?order=desc&sort=votes&site=stackoverflow&filter=!b0OfN.wYmA0RcX")
    Call<Items<Answer>> loadAnswersFromQuestion(@Path("id") int id);

    @GET("questions/{id}/comments?order=desc&sort=votes&site=stackoverflow")
    Call<Items<Comment>> loadCommentsFromQuestion(@Path("id") int id);

    // Tags
    @GET("tags?order=desc&sort=popular&site=stackoverflow")
    Call<Items<Tag>> loadPopularTags();

    @GET("tags/{tag}/top-askers/all_time?site=stackoverflow")
    Call<Items<TagScore>> loadTopAskersByTag(@Path("tag") String tag);

    @GET("tags/{tag}/top-answerers/all_time?site=stackoverflow")
    Call<Items<TagScore>> loadTopAnswerersByTag(@Path("tag") String tag);

    //Users
    @GET("users?order=desc&sort=reputation&site=stackoverflow")
    Call<Items<User>> loadUsersByReputation();

    @GET("users/{id}/answers?order=desc&sort=votes&site=stackoverflow")
    Call<Items<Answer>> loadAnsersFromUserId(@Path("id") int id);

    @GET("users/{id}/badges?order=desc&sort=rank&site=stackoverflow")
    Call<Items<Badges>> loadBadgesFromUserId(@Path("id") int id);

    @GET("users/{id}/comments?order=desc&sort=creation&site=stackoverflow")
    Call<Items<Comment>> loadCommentsFromUserId(@Path("id") int id);

    @GET("users/{id}/tags?order=desc&sort=popular&site=stackoverflow")
    Call<Items<Tag>> loadTagsFormUserId(@Path("id") int id);

    // Misc
    @GET("badges?order=desc&sort=rank&site=stackoverflow")
    Call<Items<Badges>> loadBadges();

    @GET("search?order=desc&sort=votes&site=stackoverflow&filter=!-*f(6rc.lFba")
    Call<Items<Question>> loadSearchQuestions(@Query("intitle") String intitle);
}
