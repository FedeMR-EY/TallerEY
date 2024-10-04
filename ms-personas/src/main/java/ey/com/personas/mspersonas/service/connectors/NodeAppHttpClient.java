package ey.com.personas.mspersonas.service.connectors;

import ey.com.personas.mspersonas.dto.api.renaper.RenaperResponse;
import ey.com.personas.mspersonas.dto.api.veraz.VerazResponse;
import ey.com.personas.mspersonas.dto.api.worldsys.WorldSysResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Service
@Slf4j
public class NodeAppHttpClient {
    private final RestTemplate restTemplate;
    @Autowired
    public NodeAppHttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public VerazResponse getVerazDetails(String dni){
        try {

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("dni",dni);
            String url = "http://node_app:3000/service/veraz?dni=".concat(dni);
            log.info("url: {} ",url);
            URI uri = UriComponentsBuilder.fromHttpUrl(Objects.requireNonNull(url))
                            .build()
                            .toUri();
            log.info("URI consultada: {}",uri);

            ResponseEntity<VerazResponse> response =
                    restTemplate.getForEntity(uri, VerazResponse.class);

            return response.getBody();
        }catch (ResourceAccessException e) {
            log.error("Error de acceso al recurso: {} - Causa: {}", e.getMessage(), e.getCause());
            throw new RuntimeException("No se pudo acceder al servicio Veraz", e);
        } catch (RestClientException e) {
            log.error("Error de cliente REST: {}", e.getMessage());
            throw new RuntimeException("Error al llamar al servicio Veraz", e);
        }catch (Exception e){
            log.error("Ocurrió un error inesperado: {}", (Object) e.getStackTrace());
            throw new RuntimeException();
        }
    }
    public RenaperResponse getRenaperDetails(String dni){
        try {

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("dni",dni);
            String url = "http://node_app:3000/service/renaper";
            URI uri =
                    UriComponentsBuilder.fromHttpUrl(Objects.requireNonNull(url))
                            .queryParams(params)
                            .build()
                            .toUri();
            log.info("URI consultada: {}",uri);

            ResponseEntity<RenaperResponse> response =
                    restTemplate.getForEntity(uri, RenaperResponse.class);

            return response.getBody();
        }catch (ResourceAccessException e) {
            log.error("Error de acceso al recurso: {}", e.getMessage());
            throw new RuntimeException("No se pudo acceder al servicio Renaper", e);
        } catch (RestClientException e) {
            log.error("Error de cliente REST: {}", e.getMessage());
            throw new RuntimeException("Error al llamar al servicio Renaper", e);
        }catch (Exception e){
            log.error("Ocurrió un error inesperado: {}", (Object) e.getStackTrace());
            throw new RuntimeException();
        }
    }
    public WorldSysResponse getWorldSysDetails(String dni){
        try {

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("dni",dni);
            String url = "http://node_app:3000/service/worldsys";
            URI uri =
                    UriComponentsBuilder.fromHttpUrl(Objects.requireNonNull(url))
                            .queryParams(params)
                            .build()
                            .toUri();
            log.info("URI consultada: {}",uri);

            ResponseEntity<WorldSysResponse> response =
                    restTemplate.getForEntity(uri, WorldSysResponse.class);

            return response.getBody();
        }catch (ResourceAccessException e) {
            log.error("Error de acceso al recurso: {}", e.getMessage());
            throw new RuntimeException("No se pudo acceder al servicio WorldSys", e);
        } catch (RestClientException e) {
            log.error("Error de cliente REST: {}", e.getMessage());
            throw new RuntimeException("Error al llamar al servicio WorldSys", e);
        }catch (Exception e){
            log.error("Ocurrió un error inesperado: {}", (Object) e.getStackTrace());
            throw new RuntimeException();
        }
    }
}
