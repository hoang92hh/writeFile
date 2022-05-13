package cmcglobal.exambook.repository.impl;

import cmcglobal.exambook.model.response.ProviderResponse;

import java.util.List;

public interface IProviderRepositoryExtend {
    List<ProviderResponse> getAllResponseProvider(String[] xbCode);

}
