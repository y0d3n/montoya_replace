package replace;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.logging.Logging;

import static burp.api.montoya.http.handler.RequestToBeSentAction.continueWith;
import static burp.api.montoya.http.handler.ResponseReceivedAction.continueWith;

import javax.swing.JTextField;

class ReplaceHandler implements HttpHandler {
    private final Logging logging;
    private final JTextField needle;
    private final JTextField replace;

    public ReplaceHandler(MontoyaApi api, ReplacerTab replacerTab) {
        this.logging = api.logging();
        this.needle = replacerTab.needle;
        this.replace = replacerTab.replace;
    }

    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent requestToBeSent) {
        HttpRequest modifiedRequest = requestToBeSent;
        String needle = this.needle.getText();
        String replace = this.replace.getText();
        if (!needle.isEmpty()){
            modifiedRequest = HttpRequest.httpRequest(modifiedRequest.httpService(), modifiedRequest.toString().replace(needle, replace));
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