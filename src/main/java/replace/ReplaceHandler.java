package replace;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.logging.Logging;

import static burp.api.montoya.http.handler.RequestToBeSentAction.continueWith;
import static burp.api.montoya.http.handler.ResponseReceivedAction.continueWith;

class ReplaceHandler implements HttpHandler {
    private final Logging logging;
    private final ReplacerTab replacerTab;

    public ReplaceHandler(MontoyaApi api, ReplacerTab replacerTab) {
        this.logging = api.logging();
        this.replacerTab = replacerTab;
    }

    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent requestToBeSent) {
        HttpRequest modifiedRequest = requestToBeSent;
        String needle = this.replacerTab.getNeedle();
        String replace = this.replacerTab.getReplace();
        if (!needle.isEmpty()) {
            modifiedRequest = HttpRequest.httpRequest(requestToBeSent.httpService(),
                    requestToBeSent.toString().replace(needle, replace));
            modifiedRequest = modifiedRequest.withBody(modifiedRequest.body().toString()); // update Content-Length

            logging.logToOutput("--------original----------\n" + requestToBeSent.toString());
            logging.logToOutput("--------replace-----------\n" + modifiedRequest.toString());
        }

        return continueWith(modifiedRequest);
    }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived httpResponseReceived) {
        return continueWith(httpResponseReceived);
    }
}