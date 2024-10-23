package ey.com.personas.mspersonas.service.connectors;

import ey.com.personas.mspersonas.dto.api.renaper.RenaperResponse;
import ey.com.personas.mspersonas.dto.api.veraz.VerazResponse;
import ey.com.personas.mspersonas.dto.api.worldsys.WorldSysResponse;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class NodeAppHttpClient {
  private final RestTemplate restTemplate;

  @Autowired
  public NodeAppHttpClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public VerazResponse getVerazDetails(String dni) {
    try {

      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("dni", dni);
      String url = "http://node_app:3000/service/veraz?dni=".concat(dni);
      URI uri = new URI(url);

      log.info("URI consultada: {}", uri);

      var response = restTemplate.getForEntity(uri, VerazResponse.class);

      return response.getBody();
    } catch (ResourceAccessException e) {
      log.error("Error de acceso al recurso: {} - Causa: {}", e.getMessage(), e.getCause());
      throw new RuntimeException("No se pudo acceder al servicio Veraz", e);
    } catch (RestClientException e) {
      log.error("Error de cliente REST: {}", e.getMessage());
      throw new RuntimeException("Error al llamar al servicio Veraz", e);
    } catch (Exception e) {
      log.error("Ocurrió un error inesperado: {}", (Object) e.getStackTrace());
      throw new RuntimeException();
    }
  }

  public RenaperResponse getRenaperDetails(String dni) {
    try {

      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("dni", dni);
      String url = "http://node_app:3000/service/renaper?dni=".concat(dni);
      URI uri = new URI(url);

      log.info("URI consultada: {}", uri);

      var response = restTemplate.getForEntity(uri, RenaperResponse.class);

      return response.getBody();
    } catch (ResourceAccessException e) {
      log.error("Error de acceso al recurso: {}", e.getMessage());
      throw new RuntimeException("No se pudo acceder al servicio Renaper", e);
    } catch (RestClientException e) {
      log.error("Error de cliente REST: {}", e.getMessage());
      throw new RuntimeException("Error al llamar al servicio Renaper", e);
    } catch (Exception e) {
      log.error("Ocurrió un error inesperado: {}", (Object) e.getStackTrace());
      throw new RuntimeException();
    }
  }

  public WorldSysResponse getWorldSysDetails(String dni) {
    try {

      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("dni", dni);
      String url = "http://node_app:3000/service/worldsys?dni=".concat(dni);
      URI uri = new URI(url);

      log.info("URI consultada: {}", uri);

      var response = restTemplate.getForEntity(uri, WorldSysResponse.class);

      return response.getBody();
    } catch (ResourceAccessException e) {
      log.error("Error de acceso al recurso: {}", e.getMessage());
      throw new RuntimeException("No se pudo acceder al servicio WorldSys", e);
    } catch (RestClientException e) {
      log.error("Error de cliente REST: {}", e.getMessage());
      throw new RuntimeException("Error al llamar al servicio WorldSys", e);
    } catch (Exception e) {
      log.error("Ocurrió un error inesperado: {}", (Object) e.getStackTrace());
      throw new RuntimeException();
    }
  }
}
