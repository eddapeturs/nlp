package is;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

class GetHandler implements HttpHandler {

    @Override

    public void handle(HttpExchange he) throws IOException {
        // parse request
        Map<String, Object> parameters = new HashMap<String, Object>();
        InputStream io = he.getRequestBody();

        StringBuilder textBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (io, Charset.forName("ISO-8859-1")));
            int c = 0;
            while (c != -1 ){
                c = reader.read();
                textBuilder.append((char) c);
            }
        }

        catch (IOException ie){
            System.out.println("Could not read buffer");
        }
        textBuilder.deleteCharAt(textBuilder.length()-1);

        LocalServer.parseQuery(textBuilder.toString(), parameters);

        // send response
        System.out.println("String tagging");
        String string = parameters.get("string").toString();
        String taggedString = NLPHandler.tag(string);
        String parsedString = NLPHandler.parse(taggedString);
        parsedString =parsedString.replaceAll("\n", "");


        String response = BuildResponseString(string, taggedString, parsedString);

        response = response.replaceAll("\n", "");
        System.out.println(response);
        he.sendResponseHeaders(200, response.length());

        OutputStream os = he.getResponseBody();
        os.write( response.getBytes(Charset.forName("ISO-8859-1")));
        os.close();

    }

    private String BuildResponseString(String response, String taggedString, String parsedString){

        response = "\"string\":\"" +  response + "\"";
        taggedString = ", \"tagg\":\"" +  taggedString + "\"";
        parsedString = ", \"parse\":\"" +  parsedString + "\"";

        return "{ " + response + taggedString + parsedString + " }";
    }



}
