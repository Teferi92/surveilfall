package es.santirivera.surveilfall.data.repository;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import es.santirivera.surveilfall.data.exceptions.WSNetworkException;
import es.santirivera.surveilfall.data.model.Card;
import es.santirivera.surveilfall.data.model.CardList;
import es.santirivera.surveilfall.data.model.Catalog;
import es.santirivera.surveilfall.data.model.Format;
import es.santirivera.surveilfall.data.model.Identifier;
import es.santirivera.surveilfall.data.model.IdentifierList;
import es.santirivera.surveilfall.data.model.SetList;
import es.santirivera.surveilfall.data.model.Tournament;
import es.santirivera.surveilfall.data.model.TournamentURLs;
import es.santirivera.surveilfall.data.net.GithubWebServices;
import es.santirivera.surveilfall.data.net.NetworkManager;
import es.santirivera.surveilfall.data.net.ScryfallWebServices;
import es.santirivera.surveilfall.data.repository.responses.NetErrorResponse;
import es.santirivera.surveilfall.data.repository.responses.NetRepositoryResponse;
import es.santirivera.surveilfall.data.repository.responses.RepositoryResponse;
import retrofit2.Call;
import retrofit2.Response;

public class AppRepositoryImpl implements AppRepository {

    private final ScryfallWebServices scryfallWebServices;
    private final GithubWebServices githubWebServices;

    public AppRepositoryImpl(ScryfallWebServices scryfallWebServices, GithubWebServices githubWebServices) {
        this.scryfallWebServices = scryfallWebServices;
        this.githubWebServices = githubWebServices;
    }

    @NonNull
    @Override
    public RepositoryResponse<SetList> getSetList() {
        Call<SetList> call = scryfallWebServices.getSetList();
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

    @NotNull
    @Override
    public RepositoryResponse<List<String>> getArtistNames() {
        Call<Catalog> call = scryfallWebServices.getArtistNames();
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
        Call<Catalog> call = scryfallWebServices.getWordBank();
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
    public RepositoryResponse<Card> getCardInSet(@NotNull String setCode, int cardInSet) {
        Call<Card> call = scryfallWebServices.cardInSet(setCode, cardInSet);
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
    public RepositoryResponse<Card> getRandomCard(@NotNull String query) {
        Call<Card> call = scryfallWebServices.randomCard(query);
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
    public RepositoryResponse<CardList> getCardsForQuery(@NotNull String query, int page, @NotNull String prints, @NotNull String sortMethod, @NotNull String sortOrder) {
        Call<CardList> call = scryfallWebServices.cardsForQuery(query, page, prints, sortMethod, sortOrder);
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

    @NotNull
    @Override
    public RepositoryResponse<TournamentURLs> getTournamentURLs() {
        Call<TournamentURLs> call = githubWebServices.getTournamentList();
        try {
            Response<TournamentURLs> response = call.execute();
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
    public RepositoryResponse<Tournament> getTournament(@NotNull String format, @NotNull String date) {
        Call<Tournament> call = githubWebServices.tournament(format, date);
        try {
            Response<Tournament> response = call.execute();
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
    public RepositoryResponse<List<Format>> getFormats() {
        Call<List<Format>> call = githubWebServices.getFormats();
        try {
            Response<List<Format>> response = call.execute();
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
    public RepositoryResponse<List<Card>> getCardCollection(@NonNull List<Identifier> list) {
        Call<CardList> call = scryfallWebServices.cardCollection(new IdentifierList(list));
        try {
            Response<CardList> response = call.execute();
            if (response.isSuccessful()) {
                return new NetRepositoryResponse<>((List<Card>) response.body().getData());
            } else {
                return new NetErrorResponse<>();
            }
        } catch (IOException e) {
            throw new WSNetworkException(e);
        }
    }
}
