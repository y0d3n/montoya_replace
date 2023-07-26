package replace;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.logging.Logging;

import static burp.api.montoya.http.handler.RequestToBeSentAction.continueWith;
import static burp.api.montoya.http.handler.ResponseReceivedAction.continueWith;

class ReplaceHandler implements HttpHandler {
    private final Logging logging;

    public ReplaceHandler(MontoyaApi api) {
        this.logging = api.logging();
    }

    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent requestToBeSent) {
        HttpRequest modifiedRequest = requestToBeSent;

        modifiedRequest = HttpRequest.httpRequest(modifiedRequest.httpService(), modifiedRequest.toString().replace("hoge", "fua"));
        modifiedRequest = modifiedRequest.withBody(modifiedRequest.body().toString()); // update Content-Length

        logging.logToOutput("--------original----------\n" + requestToBeSent.toString());
        logging.logToOutput("--------replace-----------\n" + modifiedRequest.toString());

        return continueWith(modifiedRequest);
    }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived httpResponseReceived) {
        return continueWith(httpResponseReceived);
    }
}