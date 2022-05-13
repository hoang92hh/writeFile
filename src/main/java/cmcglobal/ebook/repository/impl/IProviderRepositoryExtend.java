package cmcglobal.ebook.repository.impl;

import cmcglobal.ebook.entity.Provider;
import cmcglobal.ebook.model.response.ProviderResponse;
import cmcglobal.ebook.repository.IProviderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProviderRepositoryExtend {
    List<ProviderResponse> getAllResponseProvider(String[] xbCode);

}
