import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Application {
    public static void main(String[] args) throws IOException {
        int serverPort = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        HttpContext context = server.createContext("/api/hello", exchange -> {
            if ("GET".equals(exchange.getRequestMethod())) {
                Map<String,String> mapped_queries =null;
                String respText;
                if(exchange.getRequestURI().getRawQuery()!=null){
                    Stream<String> queries = Arrays.stream(exchange.getRequestURI()
                            .getRawQuery().split("&"));
                    mapped_queries =
                            queries.collect(Collectors.toMap((String s)->  split(s,"=",0), (String s)-> split(s,"=",1)))
                            ;
                }

                if(mapped_queries!=null){
                    respText = "Hello" + (mapped_queries.containsKey("name") ? " " + mapped_queries.get("name") : "!");
                }else{
                    respText = "Hello!";
                }

                exchange.sendResponseHeaders(200, respText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(respText.getBytes());
                output.flush();
            }else{
                exchange.sendResponseHeaders(405,-1);
            }
            exchange.close();
        });

        context.setAuthenticator(new BasicAuthenticator("myrealm") {
            @Override
            public boolean checkCredentials(String user, String password) {
                return user.equals("admin") && password.equals("password");
            }
        });

        server.setExecutor(null); // creates a default executor
        server.start();
    }

    /**
     *
     * @param s : the string to split
     * @param part : 0 based part of the array we want to get
     * @return
     */
    public static String split(String s, String spliterator, int part){
        try{
            return s.split(spliterator)[part];
        }catch (IndexOutOfBoundsException e){
            return  "";
        }
    }

}
