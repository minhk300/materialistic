package io.github.hidroh.materialistic;

import android.accounts.AccountManager;
import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.hidroh.materialistic.accounts.UserServices;
import io.github.hidroh.materialistic.accounts.UserServicesClient;
import io.github.hidroh.materialistic.data.AlgoliaClient;
import io.github.hidroh.materialistic.data.AlgoliaPopularClient;
import io.github.hidroh.materialistic.data.FavoriteManager;
import io.github.hidroh.materialistic.data.FeedbackClient;
import io.github.hidroh.materialistic.data.HackerNewsClient;
import io.github.hidroh.materialistic.data.ItemManager;
import io.github.hidroh.materialistic.data.ReadabilityClient;
import io.github.hidroh.materialistic.data.RestServiceFactory;
import io.github.hidroh.materialistic.data.SessionManager;
import io.github.hidroh.materialistic.widget.MultiPageItemRecyclerViewAdapter;
import io.github.hidroh.materialistic.widget.PopupMenu;
import io.github.hidroh.materialistic.widget.SinglePageItemRecyclerViewAdapter;

@Module(
        injects = {
                SettingsActivity.class,
                AboutActivity.class,
                AskActivity.class,
                FavoriteActivity.class,
                ItemActivity.class,
                JobsActivity.class,
                ListActivity.class,
                NewActivity.class,
                SearchActivity.class,
                ShowActivity.class,
                PopularActivity.class,
                LoginActivity.class,
                ComposeActivity.class,
                FavoriteFragment.class,
                ItemFragment.class,
                ListFragment.class,
                WebFragment.class,
                DrawerFragment.class,
                ReadabilityFragment.class,
                SinglePageItemRecyclerViewAdapter.class,
                MultiPageItemRecyclerViewAdapter.class
        },
        library = true
)
public class ActivityModule {
    public static final String ALGOLIA = "algolia";
    public static final String POPULAR = "popular";
    public static final String HN = "hn";

    private final Context mContext;

    public ActivityModule(Context context) {
        mContext = context;
    }

    @Provides @Singleton
    public Context provideContext() {
        return mContext;
    }

    @Provides @Singleton @Named(HN)
    public ItemManager provideHackerNewsClient(HackerNewsClient client) {
        return client;
    }

    @Provides @Singleton @Named(ALGOLIA)
    public ItemManager provideAlgoliaClient(AlgoliaClient client) {
        return client;
    }

    @Provides @Singleton @Named(POPULAR)
    public ItemManager provideAlgoliaPopularClient(AlgoliaPopularClient client) {
        return client;
    }

    @Provides @Singleton
    public FeedbackClient provideFeedbackClient(FeedbackClient.Impl client) {
        return client;
    }

    @Provides @Singleton
    public ReadabilityClient provideReadabilityClient(ReadabilityClient.Impl client) {
        return client;
    }

    @Provides @Singleton
    public FavoriteManager provideFavoriteManager() {
        return new FavoriteManager();
    }

    @Provides @Singleton
    public SessionManager provideSessionManager() {
        return new SessionManager();
    }

    @Provides @Singleton
    public RestServiceFactory provideRestServiceFactory(Context context) {
        return new RestServiceFactory.Impl(context);
    }

    @Provides @Singleton
    public ActionViewResolver provideActionViewResolver() {
        return new ActionViewResolver();
    }

    @Provides
    public AlertDialogBuilder provideAlertDialogBuilder(Context context) {
        return new AlertDialogBuilder.Impl();
    }

    @Provides @Singleton
    public UserServices provideUserServices() {
        return new UserServicesClient(new OkHttpClient());
    }

    @Provides
    public AccountManager provideAccountManager(Context context) {
        return AccountManager.get(context);
    }

    @Provides
    public PopupMenu providePopupMenu() {
        return new PopupMenu.Impl();
    }
}
