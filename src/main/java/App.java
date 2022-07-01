import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class App {

    private final static String URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build()) {

            HttpGet request = new HttpGet(URL);
            request.setHeader(HttpHeaders.ACCEPT, HttpHeaders.CONTENT_TYPE);

            CloseableHttpResponse response = httpClient.execute(request);

            System.out.println(response.getStatusLine().getStatusCode());
            Arrays.stream(response.getAllHeaders()).forEach(System.out::println);

            List<CatFacts> facts = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
            });
            facts.stream().filter(value -> value.getUpvotes() != 0 && value.getUpvotes() > 0)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
