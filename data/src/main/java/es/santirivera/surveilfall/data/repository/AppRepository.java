package es.santirivera.surveilfall.data.repository;


import es.santirivera.surveilfall.data.model.SetList;
import es.santirivera.surveilfall.data.repository.responses.RepositoryResponse;

public interface AppRepository {

    String getTag();

    RepositoryResponse<SetList> getSetList();

}

