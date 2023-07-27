package replace;

import javax.swing.*;

public class ReplacerTab extends JComponent {
    private JTextField needle;
    private JTextField replace;

    public ReplacerTab() {
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

    public String getNeedle() {
        return this.needle.getText();
    }

    public String getReplace() {
        return this.replace.getText();
    }
}
