package es.santirivera.surveilfall.data.repository;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import es.santirivera.surveilfall.data.exceptions.WSNetworkException;
import es.santirivera.surveilfall.data.model.Card;
import es.santirivera.surveilfall.data.model.CardList;
import es.santirivera.surveilfall.data.model.Catalog;
import es.santirivera.surveilfall.data.model.Favorite;
import es.santirivera.surveilfall.data.model.SetList;
import es.santirivera.surveilfall.data.net.NetworkManager;
import es.santirivera.surveilfall.data.net.WServices;
import es.santirivera.surveilfall.data.repository.responses.NetErrorResponse;
import es.santirivera.surveilfall.data.repository.responses.NetRepositoryResponse;
import es.santirivera.surveilfall.data.repository.responses.RepositoryResponse;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Response;

public class AppRepositoryImpl implements AppRepository {

    private Context context;
    private WServices wServices;
    private NetworkManager networkManager;

    public AppRepositoryImpl(Context context, WServices wServices, NetworkManager networkManager) {
        this.context = context;
        this.wServices = wServices;
        this.networkManager = networkManager;
    }

    @NotNull
    @Override
    public String getTag() {
        return getClass().getSimpleName();
    }

    @NonNull
    @Override
    public RepositoryResponse<SetList> getSetList() {
        Call<SetList> call = wServices.getSetList();
        try {
            Response<SetList> response = call.execute();
            if (response.isSuccessful()) {
                return new NetRepositoryResponse<>(response.body());
            } else {
                return new NetErrorResponse<>();
            }
        } catch (IOException e) {
            throw new WSNetworkException(e);
        }
    }

    private void checkConnectivity() {
        networkManager.checkConnectivity();
    }

    @NotNull
    @Override
    public RepositoryResponse<List<String>> getArtistNames() {
        Call<Catalog> call = wServices.getArtistNames();
        try {
            Response<Catalog> response = call.execute();
            if (response.isSuccessful()) {
                return new NetRepositoryResponse<>(response.body().getData());
            } else {
                return new NetErrorResponse<>();
            }
        } catch (IOException e) {
            throw new WSNetworkException(e);
        }
    }

    @NotNull
    @Override
    public RepositoryResponse<List<String>> getWordBank() {
        Call<Catalog> call = wServices.getWordBank();
        try {
            Response<Catalog> response = call.execute();
            if (response.isSuccessful()) {
                return new NetRepositoryResponse<>(response.body().getData());
            } else {
                return new NetErrorResponse<>();
            }
        } catch (IOException e) {
            throw new WSNetworkException(e);
        }
    }


    @NotNull
    @Override
    public RepositoryResponse<Card> cardInSet(@NotNull String setCode, int cardInSet) {
        Call<Card> call = wServices.cardInSet(setCode, cardInSet);
        try {
            Response<Card> response = call.execute();
            if (response.isSuccessful()) {
                return new NetRepositoryResponse<>(response.body());
            } else {
                return new NetErrorResponse<>();
            }
        } catch (IOException e) {
            throw new WSNetworkException(e);
        }
    }

    @NotNull
    @Override
    public RepositoryResponse<Card> randomCard(@NotNull String query) {
        Call<Card> call = wServices.randomCard(query);
        try {
            Response<Card> response = call.execute();
            if (response.isSuccessful()) {
                return new NetRepositoryResponse<>(response.body());
            } else {
                return new NetErrorResponse<>();
            }
        } catch (IOException e) {
            throw new WSNetworkException(e);
        }
    }


    @NotNull
    @Override
    public RepositoryResponse<CardList> cardsForQuery(@NotNull String query, int page, @NotNull String prints, @NotNull String sortMethod, @NotNull String sortOrder) {
        Call<CardList> call = wServices.cardsForQuery(query, page, prints, sortMethod, sortOrder);
        try {
            Response<CardList> response = call.execute();
            if (response.isSuccessful()) {
                return new NetRepositoryResponse<>(response.body());
            } else {
                return new NetErrorResponse<>();
            }
        } catch (IOException e) {
            throw new WSNetworkException(e);
        }
    }
}
