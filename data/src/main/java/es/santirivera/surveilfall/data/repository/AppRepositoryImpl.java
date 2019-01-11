package es.santirivera.surveilfall.data.repository;

import android.content.Context;

import java.io.IOException;

import es.santirivera.surveilfall.data.exceptions.WSNetworkException;
import es.santirivera.surveilfall.data.model.SetList;
import es.santirivera.surveilfall.data.net.NetworkManager;
import es.santirivera.surveilfall.data.net.WServices;
import es.santirivera.surveilfall.data.repository.responses.NetErrorResponse;
import es.santirivera.surveilfall.data.repository.responses.NetRepositoryResponse;
import es.santirivera.surveilfall.data.repository.responses.RepositoryResponse;
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

    @Override
    public String getTag() {
        return getClass().getSimpleName();
    }

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
}
