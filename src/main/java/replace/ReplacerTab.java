package replace;

import javax.swing.*;

import burp.api.montoya.MontoyaApi;

public class ReplacerTab extends JComponent {
    public JTextField needle;
    public JTextField replace;

    public ReplacerTab(MontoyaApi api) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        JPanel customTabContent = new JPanel();

        JLabel needleLabel = new JLabel("needle", SwingConstants.LEFT);
        customTabContent.add(needleLabel);

        this.needle = new JTextField(20);
        customTabContent.add(this.needle);

        JLabel replaceLabel = new JLabel("replace", SwingConstants.LEFT);
        customTabContent.add(replaceLabel);

        this.replace = new JTextField(20);
        customTabContent.add(this.replace);

        add(customTabContent);
    }
}
