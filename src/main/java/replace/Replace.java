package replace;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class Replace implements BurpExtension
{
    @Override
    public void initialize(MontoyaApi api)
    {
        api.extension().setName("My Replace extension");

        api.http().registerHttpHandler(new ReplaceHandler(api));
    }
}
